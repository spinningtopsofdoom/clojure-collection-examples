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