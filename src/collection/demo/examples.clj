(ns collection.demo.examples)

;; Vectors
[:this :is :a :vector]

;; Vectors are functions
([:duck :duck :goose :duck] 2)
;; => :goose
([:swan :parrot :falcon :flamingo] 1)
;; => :parrot

;; Using an out of bounds throws an exception
([:swan :parrot :falcon :flamingo] -1)
;; => IndexOutOfBoundsException
([:swan :parrot :falcon :flamingo] 5)
;; => IndexOutOfBoundsException

;; Maps
{:a 0 :clojure 1 :key 2 :value 3 :map 4}

;; Maps are also functions

({:peanuts 4 :walnuts 6 :jelly-beans 2} :peanuts)
;; => 4

({:north :grue :south :treasure :east :pit} :east)
;; => :pit

;; For keys not in the map nil is returned
({:north :grue :south :treasure :east :pit} :west)
;; => nil

;; Adding a second parameter (:food) set a non found value
({:north :grue :south :treasure :east :pit} :west :food)
;; => :food
({} :key :not-found)
;; => :not-found
({:north :grue :south :treasure :east :pit} :east :food)
;; => :pit
