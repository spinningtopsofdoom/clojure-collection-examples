(ns collection.demo.game-of-life)

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