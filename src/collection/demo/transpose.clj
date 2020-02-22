(ns collection.demo.transpose)

(def grocery-lists
  [[:meat :salmon :flank-steak :brat]
   [:fruit :apple :pear :peach :grapes]
   [:vegetables :carrot :onion :celery]
   [:spices :cumin :paprika :cloves :pepper :red-pepper]])


(defn interleave-transpose [list-of-lists]
  (partition (count list-of-lists) (apply interleave list-of-lists)))

(interleave-transpose grocery-lists)
(comment
  ((:meat :fruit :vegetables :spices)
   (:salmon :apple :carrot :cumin)
   (:flank-steak :pear :onion :paprika)
   (:brat :peach :celery :cloves)))

(interleave [:one :list :of :things] [:a :list :of :stuff] [:maybe :another :list :perhaps])
;; => (:one :a :maybe :list :list :another :of :of :list :things :stuff :perhaps)
(interleave [:one :list :of :things] [:a :list :of :stuff] [:maybe :another :list])
;; => (:one :a :maybe :list :list :another :of :of :list)

(apply interleave [[:one :list :of :things] [:a :list :of :stuff] [:maybe :another :list :perhaps]])
;; => (:one :a :maybe :list :list :another :of :of :list :things :stuff :perhaps)

(defn iterate-with-holes-transpose
  [list-of-lists]
 (ffirst
   (take 1
     (drop-while (fn empty-remainder [[_ remainder]] (not (every? empty? remainder)))
      (iterate
        (fn transpose [[new-list remainder]]
          [(conj new-list (mapv first remainder)) (map rest remainder)])
        [[] list-of-lists])))))

(iterate-with-holes-transpose grocery-lists)
(comment
  [[:meat :fruit :vegetables :spices]
   [:salmon :apple :carrot :cumin]
   [:flank-steak :pear :onion :paprika]
   [:brat :peach :celery :cloves]
   [nil :grapes nil :pepper]
   [nil nil nil :red-pepper]])

(mapv first grocery-lists)
;; => [:meat :fruit :vegetables :spices]

(mapv first [[:empty] [:not :empty] [:maybe :empty]])
;; => [:empty :not :maybe]
(mapv first [[] [:empty] [:not :empty] [:maybe :empty]])
;; => [nil :empty :not :maybe]
(mapv first [nil [:empty] [:not :empty] [:maybe :empty]])
;; => [nil :empty :not :maybe]

(map rest grocery-lists)
(comment
  ((:salmon :flank-steak :brat)
   (:apple :pear :peach :grapes)
   (:carrot :onion :celery)
   (:cumin :paprika :cloves :pepper :red-pepper)))

(map rest [[:empty] [:not :empty] [:maybe :empty]])
;; => (() (:empty) (:empty))
(map rest [[] [:empty] [:not :empty] [:maybe :empty]])
;; => (() () (:empty) (:empty))
(map rest [nil [:empty] [:not :empty] [:maybe :empty]])
;; => (() () (:empty) (:empty))

((fn transpose [[new-list remainder]]
   [(conj new-list (mapv first remainder)) (map rest remainder)])
 [[] grocery-lists])
(comment
  [[[:meat :fruit :vegetables :spices]]
   ((:salmon :flank-steak :brat)
    (:apple :pear :peach :grapes)
    (:carrot :onion :celery)
    (:cumin :paprika :cloves :pepper :red-pepper))])

((fn transpose [[new-list remainder]]
   [(conj new-list (mapv first remainder)) (map rest remainder)])
 [[] [[:empty] [:not :empty] [:maybe :empty]]])
;; => [[[:empty :not :maybe]] (() (:empty) (:empty))]
((fn transpose [[new-list remainder]]
   [(conj new-list (mapv first remainder)) (map rest remainder)])
 [[] [[] [:empty] [:not :empty] [:maybe :empty]]])
;; => [[[nil :empty :not :maybe]] (() () (:empty) (:empty))]

(take
  3
  (iterate
    (fn transpose [[new-list remainder]]
      [(conj new-list (mapv first remainder)) (map rest remainder)])
    [[] grocery-lists]))
(comment
  ([[]
    [[:meat :salmon :flank-steak :brat]
     [:fruit :apple :pear :peach :grapes]
     [:vegetables :carrot :onion :celery]
     [:spices :cumin :paprika :cloves :pepper :red-pepper]]]

   [[[:meat :fruit :vegetables :spices]]
    ((:salmon :flank-steak :brat)
     (:apple :pear :peach :grapes)
     (:carrot :onion :celery)
     (:cumin :paprika :cloves :pepper :red-pepper))]

   [[[:meat :fruit :vegetables :spices] [:salmon :apple :carrot :cumin]]
    ((:flank-steak :brat) (:pear :peach :grapes) (:onion :celery) (:paprika :cloves :pepper :red-pepper))]))

(take
  3
  (iterate
    (fn transpose [[new-list remainder]]
      [(conj new-list (mapv first remainder)) (map rest remainder)])
    [[] [[] [:empty] [:not :empty] [:maybe :empty]]]))

(comment
  ([[] [[] [:empty] [:not :empty] [:maybe :empty]]]

   [[[nil :empty :not :maybe]] (() () (:empty) (:empty))]

   [[[nil :empty :not :maybe] [nil nil :empty :empty]] (() () () ())]))

(every? empty? grocery-lists)
;; => false
(every? empty? [[] [:empty] [:not :empty] [:full]])
;; => false
(every? empty? [[] [] [] []])
;; => true

(drop-while (fn no-perhaps [word] (not= word :perhaps)) [:maybe :possibly :perhaps :sometime :probably])
;; => (:perhaps :sometime :probably)
(drop-while #{:init :add} [:init :add :add :add :subtract :add :add :subtract :subtract])
;; => (:subtract :add :add :subtract :subtract)
(drop-while #{:init :add :subtract} [:init :add :add :add :subtract :add :add :subtract :subtract])
;; => ()
(drop-while #{:foo} [:init :add :add :add :subtract :add :add :subtract :subtract])
;; => (:init :add :add :add :subtract :add :add :subtract :subtract)

(ffirst [[:this :is :a :first :for :me]])
;; => this
(ffirst [])
;; => nil

(take
  3
  (drop-while
    (fn empty-remainder [[_ remainder]] (not (every? empty? remainder)))
    (iterate
      (fn transpose [[new-list remainder]]
        [(conj new-list (mapv first remainder)) (map rest remainder)])
      [[] [[] [:empty] [:not :empty] [:maybe :empty]]])))
(comment
  ([[[nil :empty :not :maybe] [nil nil :empty :empty]]
    (() () () ())]

   [[[nil :empty :not :maybe] [nil nil :empty :empty] [nil nil nil nil]]
    (() () () ())]

   [[[nil :empty :not :maybe] [nil nil :empty :empty] [nil nil nil nil] [nil nil nil nil]]
    (() () () ())]))

(comment
  (->>
    (iterate
      (fn transpose [[new-list remainder]]
        [(conj new-list (mapv first remainder)) (map rest remainder)])
      [[] grocery-lists])
    (take 1000) ;; Makes the infinite list finite
    (drop-while (fn empty-remainder [[_ remainder]] (not (every? empty? remainder)))) ;; If wrong test may cause infinite loop
    (take 1)))