(ns property-based-testing-example.core
  (:gen-class))

(def goblin
  {:name "Goblin"
   :ap   10
   :dp   1
   :hp   15})

(def barbarian
  {:name "Barbarian"
   :ap   20
   :dp   0
   :hp   50})

(defn attack [attacker attacked]
  (let [damage (- (:ap attacker) (:dp attacked))
        new-hp (- (:hp attacked) damage)]
    (assoc attacked
      :hp new-hp)))
