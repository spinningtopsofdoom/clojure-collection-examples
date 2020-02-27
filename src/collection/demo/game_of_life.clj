(ns collection.demo.game-of-life
  (:require [clojure.set :as cset]))

(def board #{[0 0] [0 1] [1 0] [1 1] [2 1] [1 2]})

(defn group-by-next-step [current-board]
  (->>
    current-board
    (mapcat (fn neighbors [[cell-x cell-y]]
              (for
                [x (range -1 2) y (range -1 2) :when (case [x y] [0 0] false true)]
                [(+ cell-x x) (+ cell-y y)])))
    (group-by identity)
    (mapv
      (fn parse [[cell neighbor-cells]]
        [cell (if (current-board cell) :alive :dead) (count neighbor-cells)]))
    (filter
      (fn lives-on [[_ liveness neighbor-count]]
        (case liveness
              :alive (#{2 3} neighbor-count)
              :dead (= 3 neighbor-count)
              false)))
    (map first)
    (into #{})))

(group-by-next-step board)
;; => #{[2 2] [0 0] [0 2] [2 0] [2 1] [1 2]}

(for
  [x (range -1 2) y (range -1 2)]
  [x y])
;; => ([-1 -1] [-1 0] [-1 1] [0 -1] [0 0] [0 1] [1 -1] [1 0] [1 1])
(for
  [x (range -1 2) y (range -1 2)]
  [(+ 2 x) (+ 0 y)])
;; => ([1 -1] [1 0] [1 1] [2 -1] [2 0] [2 1] [3 -1] [3 0] [3 1])
(for
  [x (range -1 2) y (range -1 2) :when (case [x y] [0 0] false true)]
  [(+ 2 x) (+ 0 y)])
;; => ([1 -1] [1 0] [1 1] [2 -1] [2 1] [3 -1] [3 0] [3 1])

(mapcat
  (fn items [x-pos] (for [x (range 2) y (range 2)] [(+ x x-pos) y]))
  [0 1 2])
;; => ([0 0] [0 1] [1 0] [1 1] [1 0] [1 1] [2 0] [2 1] [2 0] [2 1] [3 0] [3 1])
(map
  (fn items [x-pos] (for [x (range 2) y (range 2)] [(+ x x-pos) y]))
  [0 1 2])
;; => (([0 0] [0 1] [1 0] [1 1]) ([1 0] [1 1] [2 0] [2 1]) ([2 0] [2 1] [3 0] [3 1]))


(group-by identity [:a :a :b :b :a :c :c])
;; => {:a [:a :a :a], :b [:b :b], :c [:c :c]}
(group-by
  (fn color-type [item] [(:color item) (:type item)])
  [{:type :vegetable :name :carrot :color :orange}
   {:type :vegetable :name :pepper :color :orange}
   {:type :vegetable :name :green-bean :color :green}
   {:type :fruit :name :orange :color :orange}
   {:type :fruit :name :honeycrisp :color :red}
   {:type :fruit :name :strawberry :color :red}
   {:type :fruit :name :raspberries :color :red}])
(comment
  {[:orange :vegetable] [{:type :vegetable, :name :carrot, :color :orange}
                         {:type :vegetable, :name :pepper, :color :orange}],
   [:green :vegetable] [{:type :vegetable, :name :green-bean, :color :green}],
   [:orange :fruit] [{:type :fruit, :name :orange, :color :orange}],
   [:red :fruit] [{:type :fruit, :name :honeycrisp, :color :red}
                  {:type :fruit, :name :strawberry, :color :red}
                  {:type :fruit, :name :raspberries, :color :red}]})

(into [] [:a :set :is :a :set :you :bet])
;; => [:a :set :is :a :set :you :bet]
(into #{} [:a :set :is :a :set :you :bet])
;; => #{:you :is :set :bet :a}
(into #{})
;; #{}

(defn frequencies-next-step [current-board]
  (->>
    current-board
    (mapcat (fn neighbors [[cell-x cell-y]]
              (for
                [x (range -1 2) y (range -1 2) :when (case [x y] [0 0] false true)]
                [(+ cell-x x) (+ cell-y y)])))
    (group-by identity)
    (mapv
      (fn parse [[cell neighbor-cells]]
        [cell (if (current-board cell) :alive :dead) (count neighbor-cells)]))
    (filter
      (fn lives-on [[_ liveness neighbor-count]]
        (case liveness
          :alive (#{2 3} neighbor-count)
          :dead (= 3 neighbor-count)
          false)))
    (map first)
    (into #{})))

(frequencies-next-step board)
;; => #{[2 2] [0 0] [0 2] [2 0] [2 1] [1 2]}
(frequencies [:a :a :b :a :a :b :b :c])
;; => {:a 4, :b 3, :c 1}

(defn cell-changes [board steps]
  (->>
    (take steps (iterate frequencies-next-step board))
    (partition 2 1)
    (map
      (fn [[current-cells next-cells]]
        (let [common-cells (cset/intersection current-cells next-cells)
              dying-cells (cset/difference current-cells common-cells)
              baby-cells (cset/difference next-cells common-cells)]
          {:dying dying-cells :baby baby-cells})))))

(cell-changes board 6)
(comment
  ({:dying #{[1 0] [1 1] [0 1]}, :baby #{[2 2] [0 2] [2 0]}}
   {:dying #{[0 0] [0 2] [2 0]}, :baby #{[1 0] [1 3] [3 1] [0 1]}}
   {:dying #{[2 2] [2 1] [1 2]}, :baby #{[2 3] [0 2] [2 0] [3 2]}}
   {:dying #{}, :baby #{}}
   {:dying #{}, :baby #{}}))

(take 6 (iterate frequencies-next-step board))
(comment
  (#{[0 0] [1 0] [1 1] [2 1] [1 2] [0 1]}
   #{[2 2] [0 0] [0 2] [2 0] [2 1] [1 2]}
   #{[2 2] [1 0] [1 3] [3 1] [2 1] [1 2] [0 1]}
   #{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]}
   #{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]}
   #{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]}))

(take 6 (iterate (fn plus-2 [x] (+ x 2)) 1))
;; => (1 3 5 7 9 11)

(partition 2 1 (take 6 (iterate frequencies-next-step board)))
(comment
  ((#{[0 0] [1 0] [1 1] [2 1] [1 2] [0 1]} #{[2 2] [0 0] [0 2] [2 0] [2 1] [1 2]})
   (#{[2 2] [0 0] [0 2] [2 0] [2 1] [1 2]} #{[2 2] [1 0] [1 3] [3 1] [2 1] [1 2] [0 1]})
   (#{[2 2] [1 0] [1 3] [3 1] [2 1] [1 2] [0 1]} #{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]})
   (#{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]} #{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]})
   (#{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]} #{[1 0] [2 3] [1 3] [0 2] [2 0] [3 1] [3 2] [0 1]})))
(partition 2 1 [:a :b :c :d :e :f])
;; => ((:a :b) (:b :c) (:c :d) (:d :e) (:e :f))

(let [current-cells #{[0 0] [1 0] [1 1] [2 1] [1 2] [0 1]}
      next-cells #{[2 2] [0 0] [0 2] [2 0] [2 1] [1 2]}
      common-cells (cset/intersection current-cells next-cells)
      dying-cells (cset/difference current-cells common-cells)
      baby-cells (cset/difference next-cells common-cells)]
  {:common common-cells :dying dying-cells :baby baby-cells})
;; => {:common #{[0 0] [2 1] [1 2]}, :dying #{[1 0] [1 1] [0 1]}, :baby #{[2 2] [0 2] [2 0]}}

(cset/intersection #{:a :b :c} #{:c :d :e})
;; => #{:c}
(cset/intersection #{:a :b :c} #{:c :d :e} #{:e :f :g})
;; => #{}
(cset/intersection #{:a :b :c} #{:c :d :e :b} #{:e :f :b :g})
;; => #{:b}

(cset/difference #{:a :b :c} #{:b :c})
;; => #{:a}
(cset/difference #{:a :b :c} #{:d :e} #{:b :c})
;; => #{:a}
(cset/difference #{:a :b :c} #{:b :c} #{:d :e})
;; => #{:a}

(cset/difference #{:a :b :c} #{:a :b :c :d} #{:d :e})
;; => #{}
(cset/difference #{:a :b :c :d} #{:a :b :c} #{:d :e})
;; => #{}