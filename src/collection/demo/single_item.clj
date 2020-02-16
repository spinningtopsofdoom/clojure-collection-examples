(ns collection.demo.single-item)
 ;; first
(first [:red :orange :yellow :blue :indigo :violet])
;; => :red
(first #{:elm :birch :oak :willow})
;; => :oak
(first {:bedroom 4 :bathroom 2.5 :kitchen 1 :square_feet 2300})
;; => [:bedroom 4]

;; For empty collection nil is returned
(first [])
;; => nil
(first #{})
;; => nil
(first {})
;; => nil