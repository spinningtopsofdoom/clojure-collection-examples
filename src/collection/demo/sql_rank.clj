(ns collection.demo.sql-rank)

(def employees
  [{:name :jones    :salary 45000 :department :accounting}
   {:name :adams    :salary 50000 :department :sales}
   {:name :johnson  :salary 40000 :department :marketing}
   {:name :williams :salary 37000 :department :accounting}
   {:name :smith    :salary 55000 :department :sales}])

(defn department-salary-rank [company]
  (let [add-department-rank (fn [rank employee] (assoc employee :rank (inc rank)))]
    (mapcat
      (fn salary-rank [[_ department]]
        (map-indexed add-department-rank (sort-by :salary > department)))
      (group-by :department company))))

(department-salary-rank employees)
(comment
  ({:name :jones, :salary 45000, :department :accounting, :rank 1}
   {:name :williams, :salary 37000, :department :accounting, :rank 2}
   {:name :smith, :salary 55000, :department :sales, :rank 1}
   {:name :adams, :salary 50000, :department :sales, :rank 2}
   {:name :johnson, :salary 40000, :department :marketing, :rank 1}))

(group-by :department employees)
(comment
  {:accounting [{:name :jones, :salary 45000, :department :accounting}
                {:name :williams, :salary 37000, :department :accounting}],
   :sales [{:name :adams, :salary 50000, :department :sales} {:name :smith, :salary 55000, :department :sales}],
   :marketing [{:name :johnson, :salary 40000, :department :marketing}]})

(group-by count ["the" "quick" "brown" "fox" "jumped" "over" "lazy" "dog"])
;; => {3 ["the" "fox" "dog"], 5 ["quick" "brown"], 6 ["jumped"], 4 ["over" "lazy"]}

(sort-by :salary employees)
(comment
  ({:name :williams, :salary 37000, :department :accounting}
   {:name :johnson, :salary 40000, :department :marketing}
   {:name :jones, :salary 45000, :department :accounting}
   {:name :adams, :salary 50000, :department :sales}
   {:name :smith, :salary 55000, :department :sales}))

(sort-by :salary > employees)
(comment
  ({:name :smith, :salary 55000, :department :sales}
   {:name :adams, :salary 50000, :department :sales}
   {:name :jones, :salary 45000, :department :accounting}
   {:name :johnson, :salary 40000, :department :marketing}
   {:name :williams, :salary 37000, :department :accounting}))

(sort-by :department employees)
(comment
  ({:name :jones, :salary 45000, :department :accounting}
   {:name :williams, :salary 37000, :department :accounting}
   {:name :johnson, :salary 40000, :department :marketing}
   {:name :adams, :salary 50000, :department :sales}
   {:name :smith, :salary 55000, :department :sales}))

(map-indexed (fn salary-rank [i employee] (assoc employee :rank i)) (sort-by :salary > employees))
(comment
  ({:name :smith, :salary 55000, :department :sales, :rank 0}
   {:name :adams, :salary 50000, :department :sales, :rank 1}
   {:name :jones, :salary 45000, :department :accounting, :rank 2}
   {:name :johnson, :salary 40000, :department :marketing, :rank 3}
   {:name :williams, :salary 37000, :department :accounting, :rank 4}))

(map-indexed
  (fn [i value] (assoc {:value value} :class (if (even? i) :even :odd)))
  [:a :b :c :d :e :f :g])
(comment
  ({:value :a, :class :even}
   {:value :b, :class :odd}
   {:value :c, :class :even}
   {:value :d, :class :odd}
   {:value :e, :class :even}
   {:value :f, :class :odd}
   {:value :g, :class :even}))

(map-indexed (fn add-i [i val] [i val]) (range 5 10))
;; => ([0 5] [1 6] [2 7] [3 8] [4 9])

(mapcat second (group-by :department employees))
(comment
  ({:name :jones, :salary 45000, :department :accounting}
   {:name :williams, :salary 37000, :department :accounting}
   {:name :adams, :salary 50000, :department :sales}
   {:name :smith, :salary 55000, :department :sales}
   {:name :johnson, :salary 40000, :department :marketing}))

(mapcat (fn dropper [coll] (drop 1 coll)) [[1 2 3] [4 5 6] [7 8 9]])
;; => (2 3 5 6 8 9)
(mapcat (fn taker [coll] (take 2 coll)) [[1 2 3] [4 5 6] [7 8 9]])
;; => (1 2 4 5 7 8)