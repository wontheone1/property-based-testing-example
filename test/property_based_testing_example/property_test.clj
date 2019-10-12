(ns property-based-testing-example.property-test
  (:require [midje.sweet :refer :all]
            [property-based-testing-example.core :refer [attack]]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def positive-int-gen
  (gen/one-of [(gen/such-that pos? gen/small-integer {:max-tries 50})
               (gen/such-that pos? gen/large-integer {:max-tries 50})]))

(def prop-HP-of-attacked-does-not-increase-after-attack
  (prop/for-all [[attacker-ap
                  attacker-dp
                  attacker-hp] (gen/tuple positive-int-gen positive-int-gen positive-int-gen)
                 [attacked-ap
                  attacked-dp
                  attacked-hp] (gen/tuple positive-int-gen positive-int-gen positive-int-gen)]
    (let [attacker {:name "attacker"
                    :ap   attacker-ap
                    :dp   attacker-dp
                    :hp   attacker-hp}
          attacked {:name "attacked"
                    :ap   attacked-ap
                    :dp   attacked-dp
                    :hp   attacked-hp}]
      (>= attacked-hp
          (:hp (attack attacker attacked))))))

(fact
  "HP of 'attacked' does not increase after attack"         ; This should fail
  (tc/quick-check 100 prop-HP-of-attacked-does-not-increase-after-attack)
  => (contains {:pass true}))

#_(prn (gen/sample gen/string 50))
