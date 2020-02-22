(ns collection.demo.file-operations)

(def transitions
  {:start #{:open}
   :open #{:read :write :close}
   :read #{:read :write :close}
   :write #{:read :write :close}
   :close #{:open}})

(def operations
  [:open :write :read :read :close :close :write :open :write :open :open :read :close])

(defn partition-validate-operations [file-operations]
  (partition-by
    second
    (map
      (fn validate [[prev-operation current-operation]]
        [current-operation (not (nil? ((transitions prev-operation) current-operation)))])
      (cons [:start (first file-operations) ] (partition 2 1 file-operations)))))

(partition-validate-operations operations)
(comment
  (([:open true] [:write true] [:read true] [:read true] [:close true])
   ([:close false] [:write false] [:open false])
   ([:write true])
   ([:open false] [:open false])
   ([:read true] [:close true])))

(partition 2 1 operations)
(comment
  ((:open :write)
   (:write :read)
   (:read :read)
   (:read :close)
   (:close :close)
   (:close :write)
   (:write :open)
   (:open :write)
   (:write :open)
   (:open :open)
   (:open :read)
   (:read :close)))

(partition 2 1 [:lemon :peach :olive :potato :lime])
;; => ((:lemon :peach) (:peach :olive) (:olive :potato) (:potato :lime))

(cons [:start (first [:lemon :peach :olive :potato :lime])]
  (partition 2 1 [:lemon :peach :olive :potato :lime]))
;; => ([:start :lemon] (:lemon :peach) (:peach :olive) (:olive :potato) (:potato :lime))

((transitions :start) :open)
;; => :open
((transitions :write) :close)
;; => :close
((transitions :write) :open)
;; => nil
((transitions :close) :write)
;; => nil

(partition-by second [[:open true] [:open true] [:close false] [:read false] [:open true]])
;; => (([:open true] [:open true]) ([:close false] [:read false]) ([:open true]))
(partition-by first [[:open true] [:open true] [:close false] [:read false] [:open true]])
;; => (([:open true] [:open true]) ([:close false]) ([:read false]) ([:open true]))