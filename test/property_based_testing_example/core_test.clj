(ns property-based-testing-example.core-test
  (:require [midje.sweet :refer :all]
            [property-based-testing-example.core :refer [attack goblin barbarian]]))

(facts
  "attack-test"
  (fact
    "Goblin attacks Barbarian and gives damage"
    (attack goblin barbarian)
    => {:name   "Barbarian"
        :ap     20
        :dp     0
        :hp     40})

  (fact
    "Barbarian attacks Goblin and Goblin dies"
    (attack barbarian goblin)
    => {:name   "Goblin"
        :ap     10
        :dp     1
        :hp     -4}))
