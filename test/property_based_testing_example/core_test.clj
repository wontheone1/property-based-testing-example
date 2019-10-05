(ns property-based-testing-example.core-test
  (:require [clojure.test :refer :all]
            [property-based-testing-example.core :refer [attack goblin barbarian]]))

(deftest attack-test
  (testing "Goblin attacks Barbarian and gives damage"
    (is (= (attack goblin barbarian)
           {:name   "Barbarian"
            :ap     20
            :dp     0
            :hp     40
            :alive? true})))

  (testing "Barbarian attacks Goblin and Goblin dies"
    (is (= (attack barbarian goblin)
           {:name   "Goblin"
            :ap     10
            :dp     1
            :hp     -4
            :alive? false}))))
