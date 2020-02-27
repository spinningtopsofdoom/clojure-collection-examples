(ns collection.demo.queue-diffs
  (:require [clojure.data :as cdata]))

(def base-operations [[:init 4] [:add 3] [:add 5] [:add 4] [:drop] [:drop] [:add 9] [:add 8] [:add 1] [:clear]])
(def diff-operations (assoc base-operations 0 [:init 2]))

(defn dropping-queue-reducer [queue action]
  (case (first action)
    :init (let [[_ size] action]
            {:queue [] :size size})
    :add (if (< (count (queue :queue [])) (queue :size 0))
           (let [[_ value] action]
             (update queue :queue (fn push [current-queue] (conj current-queue value))))
           queue)
    :drop (update queue :queue pop)
    :clear (assoc queue :queue [])
    queue))

(reductions
  dropping-queue-reducer
  {}
  base-operations)
(comment
  ({}
   {:queue [], :size 4}
   {:queue [3], :size 4}
   {:queue [3 5], :size 4}
   {:queue [3 5 4], :size 4}
   {:queue [3 5], :size 4}
   {:queue [3], :size 4}
   {:queue [3 9], :size 4}
   {:queue [3 9 8], :size 4}
   {:queue [3 9 8 1], :size 4}
   {:queue [], :size 4}))

(reductions
  dropping-queue-reducer
  {}
  diff-operations)
(comment
  ({}
   {:queue [], :size 2}
   {:queue [3], :size 2}
   {:queue [3 5], :size 2}
   {:queue [3 5], :size 2}
   {:queue [3], :size 2}
   {:queue [], :size 2}
   {:queue [9], :size 2}
   {:queue [9 8], :size 2}
   {:queue [9 8], :size 2}
   {:queue [], :size 2}))

(reductions + 0 [1 2 3 4])
;; =>  (0 1 3 6 10)
(reductions
  (fn reducing-assoc [m [key value]] (assoc m key value))
  {}
  [[:name :fred] [:location :st-paul] [:occupation :yooper] [:name :paula] [:location :sf]])
(comment
  ({}
   {:name :fred}
   {:name :fred, :location :st-paul}
   {:name :fred, :location :st-paul, :occupation :yooper}
   {:name :paula, :location :st-paul, :occupation :yooper}
   {:name :paula, :location :sf, :occupation :yooper}))

(let [base-queue (map :queue (reductions dropping-queue-reducer {} base-operations))
      diff-queue (map :queue (reductions dropping-queue-reducer {} diff-operations))]
 (map
   (fn difference [[base diff]]
     (let [[in-base in-diff in-both] (cdata/diff base diff)]
       {:base in-base :diff in-diff :both in-both}))
   (partition 2 (interleave base-queue diff-queue))))
(comment
  ({:base nil, :diff nil, :both nil}
   {:base nil, :diff nil, :both []}
   {:base nil, :diff nil, :both [3]}
   {:base nil, :diff nil, :both [3 5]}
   {:base [nil nil 4], :diff nil, :both [3 5]}
   {:base [nil 5], :diff nil, :both [3]}
   {:base [3], :diff nil, :both nil}
   {:base [3 9], :diff [9], :both nil}
   {:base [3 9 8], :diff [9 8], :both nil}
   {:base [3 9 8 1], :diff [9 8], :both nil}
   {:base nil, :diff nil, :both []}))

(cdata/diff [3 5 4] [3 5])
;; => [[nil nil 4] nil [3 5]]
(cdata/diff [3 5 4] [1 5 9 8])
;; => [[3 nil 4] [1 nil 9 8] [nil 5]]

(cdata/diff [nil 5 4] [1 5 9 8])
;; => [[nil nil 4] [1 nil 9 8] [nil 5]]

(cdata/diff [nil 5 4] [nil 5 9 8])
;; => [[nil nil 4] [nil nil 9 8] [nil 5]]