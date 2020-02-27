(ns collection.demo.song-shuffle)

(def playlist [1 2 3 4 5 6])

(defn shuffle-playlist [playlist shuffle-times]
  (mapcat shuffle (repeat shuffle-times playlist)))

(shuffle-playlist playlist 3)
;; => (2 3 5 1 4 6 6 2 4 3 5 1 3 6 1 5 4 2)
;; => (6 1 3 2 4 5 1 5 2 4 6 3 3 2 1 6 4 5)
;; => (1 2 5 4 6 3 4 6 3 2 1 5 3 6 2 1 5 4)

(shuffle [:a :b :c :d :e])
;; => [:c :b :d :a :e]
;; => [:a :e :b :c :d]
;; => [:c :e :d :a :b]
(shuffle #{1 2 3 4 5 6})
;; => [4 5 3 1 2 6]
;; => [3 2 5 1 6 4]
;; => [2 6 4 3 5 1]

(repeat 2 [:a :b :c :d :e])
;; => ([:a :b :c :d :e] [:a :b :c :d :e])
(repeat 3 [1 2 3])
;; => ([1 2 3] [1 2 3] [1 2 3])

(mapcat identity [[1 2 3] [4 5 6] [7 8 9]])
;; => (1 2 3 4 5 6 7 8 9)
(mapcat reverse [[1 2 3] [4 5 6] [7 8 9]])
;; => (3 2 1 6 5 4 9 8 7)