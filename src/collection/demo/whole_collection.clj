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
