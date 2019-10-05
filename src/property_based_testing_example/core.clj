(ns property-based-testing-example.core
  (:gen-class))

(def goblin
  {:name   "Goblin"
   :ap     10
   :dp     1
   :hp     15
   :alive? true})

(def barbarian
  {:name   "Barbarian"
   :ap     20
   :dp     0
   :hp     50
   :alive? true})

(defn attack [attacker attacked]
  (let [damage (- (:ap attacker) (:dp attacked))
        new-hp (- (:hp attacked) damage)
        new-alive? (pos? new-hp)]
    (assoc attacked
      :hp new-hp
      :alive? new-alive?)))
