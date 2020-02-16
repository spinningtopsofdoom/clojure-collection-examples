(ns collection.demo.single-item)
;; Retrieving a single item from a collection

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

;; second
(second [:red :orange :yellow :blue :indigo :violet])
;; => :orange
(second #{:elm :birch :oak :willow})
;; => :willow
(second {:bedroom 4 :bathroom 2.5 :kitchen 1 :square_feet 2300})
;; => [:bathroom 2.5]

;; For empty collection nil is returned
(second [])
;; => nil
(second #{})
;; => nil
(second {})
;; => nil

;; last

(last [:desk :chair :cabinet :bookcase])
;; => :bookcase
(last #{:lumen :decibel :psi :mass})
;; => :lumen
(last {:wheels 2 :pedal 2 :frame 1 :seat 1})
;; => [:seat 1]

;; For empty collection nil is returned
(last [])
;; => nil
(last #{})
;; => nil
(last {})
;; => nil

;; ffirst

(ffirst [[:table :chair] [:sofa :tv] [:bed :bookshelf]])
;; => :table
(ffirst #{#{:add :subtract :multiply} #{:shoes :shirt :pants}})
;; => :shoes
(ffirst {:rooms 56 :pool 1 :elevators 5})
;; => :rooms

;; For empty collection nil is returned
(ffirst [])
;; => nil
(ffirst #{})
;; => nil
(ffirst {})
;; => nil

;; get

(get [:desk :chair :cabinet :bookcase] 2)
;; => :cabinet
(get #{:lumen :decibel :psi :mass} :psi)
;; => :psi
(get {:wheels 2 :pedal 2 :frame 1 :seat 1} :pedal)
;; => 2

;; When "key" is not found nil is returned
(get [:desk :chair :cabinet :bookcase] 6)
;; => nil
(get #{:lumen :decibel :psi :mass} :speed)
;; => nil
(get {:wheels 2 :pedal 2 :frame 1 :seat 1} :horn)
;; => nil

;; Default value can set when "key" is not found
(get [:desk :chair :cabinet :bookcase] 6 :closet)
;; => :closet
(get #{:lumen :decibel :psi :mass} :speed :zero-point)
;; => :zero-point
(get {:wheels 2 :pedal 2 :frame 1 :seat 1} :horn 0)
;; => 0