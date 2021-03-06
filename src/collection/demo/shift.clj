(ns collection.demo.shift)
;; Shift collection left or right
(def alphabet [:a :b :c :d :e :f :g :h :i :j :k :l])

(defn partition-shift [n coll]
  (let [column-size (count coll)
        step (mod (+ column-size n) column-size)]
    (second (partition column-size step coll coll))))

(partition-shift 3 alphabet)
;; => (:d :e :f :g :h :i :j :k :l :a :b :c)
(partition-shift -3 alphabet)
;; => (:j :k :l :a :b :c :d :e :f :g :h :i)

;; Modular (Clock) arithmetic
(mod (+ (count alphabet) -3) (count alphabet))
;; => 9
(mod (+ (count alphabet) 9) (count alphabet))
;; => 9
(mod (+ (count alphabet) 3) (count alphabet))
;; => 3
(mod (+ (count alphabet) (+ (* 3 (count alphabet)) -2)) (count alphabet))
;; => 10
(mod (+ (count alphabet) (+ (* 8 (count alphabet)) -2)) (count alphabet))
;; => 10
(mod (+ (count alphabet) (+ (* 3 (count alphabet)) 10)) (count alphabet))
;; => 10

(partition (count alphabet) alphabet)
;; => ((:a :b :c :d :e :f :g :h :i :j :k :l))
(partition 5 alphabet)
;; => ((:a :b :c :d :e :f :g) (:d :e :f :g :h :i :j))
(partition 7 alphabet)
;; => ((:a :b :c :d :e :f :g))

(partition (count alphabet) 3 alphabet)
;; => ((:a :b :c :d :e :f :g :h :i :j :k :l))
(partition 4 3 alphabet)
;; => ((:a :b :c :d) (:d :e :f :g) (:g :h :i :j))
(partition 7 3 alphabet)
;; => ((:a :b :c :d :e :f :g) (:d :e :f :g :h :i :j))

(partition (count alphabet) 9 alphabet)
;; => ((:a :b :c :d :e :f :g :h :i :j :k :l))
(partition 2 9 alphabet)
;; => ((:a :b) (:j :k))
(partition 5 9 alphabet)
;; => ((:a :b :c :d :e))

(partition (count alphabet) 3 [:some :padding :for :the :partition] alphabet)
;; => ((:a :b :c :d :e :f :g :h :i :j :k :l) (:d :e :f :g :h :i :j :k :l :some :padding :for))
(partition (count alphabet) 9 [:some :padding :for :the :partition] alphabet)
;; => ((:a :b :c :d :e :f :g :h :i :j :k :l) (:j :k :l :some :padding :for :the :partition))

(partition (count alphabet) 3 alphabet alphabet)
;; => ((:a :b :c :d :e :f :g :h :i :j :k :l) (:d :e :f :g :h :i :j :k :l :a :b :c))
(partition (count alphabet) 9 alphabet alphabet)
;; => ((:a :b :c :d :e :f :g :h :i :j :k :l) (:j :k :l :a :b :c :d :e :f :g :h :i))

(defn cycle-shift [n coll]
  (let [column-size (count coll)
        drop-count (mod (+ column-size n) column-size)]
    (take column-size (drop drop-count (cycle coll)))))

(cycle-shift 3 alphabet)
;; => (:d :e :f :g :h :i :j :k :l :a :b :c)
(cycle-shift -3 alphabet)
;; => (:j :k :l :a :b :c :d :e :f :g :h :i)

(take 3 alphabet)
;; => (:a :b :c)
(take 5 alphabet)
;; => (:a :b :c :d :e)
(take (+ (count alphabet) 3) alphabet)
;; => (:a :b :c :d :e :f :g :h :i :j :k :l)

(drop 3 alphabet)
;; => (:d :e :f :g :h :i :j :k :l)
(drop 5 alphabet)
;; => (:f :g :h :i :j :k :l)
(drop (+ (count alphabet) 3) alphabet)
;; => ()

(take 20 (cycle [:round :and :round :we :go]))
;; => (:round :and :round :we :go :round :and :round :we :go :round :and :round :we :go :round :and :round :we :go)
(take 8 (cycle [:round :and :round :we :go]))

(take 20 (drop 2 (cycle [:round :and :round :we :go])))
;; => (:round :we :go :round :and :round :we :go :round :and :round :we :go :round :and :round :we :go :round :and)
(take 20 (drop 4 (cycle [:round :and :round :we :go])))
;; => (:go :round :and :round :we :go :round :and :round :we :go :round :and :round :we :go :round :and :round :we)

(take (count alphabet) (drop 3 (cycle alphabet)))
;; => (:d :e :f :g :h :i :j :k :l :a :b :c)
(take (count alphabet) (drop 9 (cycle alphabet)))
  ;; => (:j :k :l :a :b :c :d :e :f :g :h :i)