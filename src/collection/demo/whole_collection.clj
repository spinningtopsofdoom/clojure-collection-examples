(ns collection.demo.whole-collection)
;; Changing the whole collection

;; seq

(seq [:lollipop :chocolate-bar :mint])
;; => (:lollipop :chocolate-bar :mint)
(seq #{:peas :cucumber :zucchini})
;; => (:peas :zucchini :cucumber)
(seq {:phone 4 :camera 3 :gyroscope 2})
;; => ([:phone 4] [:camera 3] [:gyroscope 2])

;; Empty collection return nil
(seq [])
;; => nil
(seq #{})
;; => nil
(seq {})
;; => nil

;; reverse

(reverse [:lollipop :chocolate-bar :mint])
;; => (:mint :chocolate-bar :lollipop)
(reverse #{:peas :cucumber :zucchini})
;; => (:cucumber :zucchini :peas)
(reverse {:phone 4 :camera 3 :gyroscope 2})
;; => ([:gyroscope 2] [:camera 3] [:phone 4])

;; map-indexed

(map-indexed (fn index-fn [index value] [value index]) [:lollipop :chocolate-bar :mint])
;; => ([:lollipop 0] [:chocolate-bar 1] [:mint 2])
(map-indexed  (fn index-fn [index value] [value index]) #{:peas :cucumber :zucchini})
;; => ([:peas 0] [:zucchini 1] [:cucumber 2])
(map-indexed (fn index-fn [index [key value]] [index value key]) {:phone 4 :camera 3 :gyroscope 2})
;; => ([0 4 :phone] [1 3 :camera] [2 2 :gyroscope])

;; shuffle

(shuffle [:lollipop :chocolate-bar :mint])
;; => [:chocolate-bar :mint :lollipop]
;; => [:lollipop :chocolate-bar :mint]
(shuffle #{:peas :cucumber :zucchini})
;; => [:peas :cucumber :zucchini]
;; => [:cucumber :peas :zucchini]
(shuffle {:phone 4 :camera 3 :gyroscope 2})
;; => ClassCastException Can not cast to java.util.Collection

;; sort

(sort [:lollipop :chocolate-bar :mint])
;; => (:chocolate-bar :lollipop :mint)
(sort #{:peas :cucumber :zucchini})
;; => (:cucumber :peas :zucchini)
(sort {:phone 4 :camera 3 :gyroscope 2})
;; => ([:camera 3] [:gyroscope 2] [:phone 4])

;; Change comparison function for sorting
(sort (fn compare-fn [a b] (compare b a)) [:lollipop :chocolate-bar :mint])
;; => (:mint :lollipop :chocolate-bar)
(sort (fn compare-fn [a b] (compare b a)) #{:peas :cucumber :zucchini})
;; => (:zucchini :peas :cucumber)
(sort (fn compare-fn [a b] (compare b a)) {:phone 4 :camera 3 :gyroscope 2})
;; => ([:phone 4] [:gyroscope 2] [:camera 3])

;; sort-by

(sort-by (fn key-len [key] (count (str key))) [:lollipop :chocolate-bar :mint])
;; => (:mint :lollipop :chocolate-bar)
(sort-by (fn key-len [key] (count (str key))) #{:peas :cucumber :zucchini})
;; => (:peas :zucchini :cucumber)
(sort-by (fn key-len [key] (count (str key))) {:phone 4 :camera 3 :gyroscope 2})
;; => ([:phone 4] [:camera 3] [:gyroscope 2])

;; Change comparison function for sorting
(sort-by (fn key-len [key] (count (str key))) (fn compare-fn [a b] (compare b a)) [:lollipop :chocolate-bar :mint])
;; => (:chocolate-bar :lollipop :mint)
(sort-by (fn key-len [key] (count (str key))) (fn compare-fn [a b] (compare b a)) #{:peas :cucumber :zucchini})
;; => (:zucchini :cucumber :peas)
(sort-by (fn key-len [key] (count (str key))) (fn compare-fn [a b] (compare b a)) {:phone 4 :camera 3 :gyroscope 2})
;; => ([:gyroscope 2] [:camera 3] [:phone 4])