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