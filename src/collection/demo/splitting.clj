(ns collection.demo.splitting)
;; Splitting up collections

;; partition

(partition 3 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c) (:d :e :f) (:g :h :i) (:j :k :l))
(partition 4 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d) (:e :f :g :h) (:i :j :k :l))

;; When there are not enough elements to fill partition the remaining elements are discarded
(partition 5 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d :e) (:f :g :h :i :j))
(partition 7 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d :e :f :g))

;; second parameter is a step parameter
(partition 2 3 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b) (:d :e) (:g :h) (:j :k))
(partition 2 4 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b) (:e :f) (:i :j))
(partition 3 4 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c) (:e :f :g) (:i :j :k))

;; smaller step takes partition number of elements after ech step
(partition 3 2 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c) (:c :d :e) (:e :f :g) (:g :h :i) (:i :j :k))
(partition 4 2 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d) (:c :d :e :f) (:e :f :g :h) (:g :h :i :j) (:i :j :k :l))
(partition 4 3 [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d) (:d :e :f :g) (:g :h :i :j))

;; Adding a third parameter is a collection that adds remaining elements to last partition
(partition 7 7 [:fill :in :the :blanks] [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d :e :f :g) (:h :i :j :k :l :fill :in))
(partition 5 5 [:fill :in :the :blanks] [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d :e) (:f :g :h :i :j) (:k :l :fill :in :the))
(partition 5 5 [] [:a :b :c :d :e :f :g :h :i :j :k :l])
;; => ((:a :b :c :d :e) (:f :g :h :i :j) (:k :l))