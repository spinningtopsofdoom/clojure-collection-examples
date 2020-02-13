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
