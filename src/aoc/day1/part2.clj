;; Day 1: The Tyranny of the Rocket Equation
;; https://adventofcode.com/2019/day/1
;;
(ns aoc.day1.part2)


(defonce input
  (->> (slurp "inputs/day1")
       clojure.string/split-lines
       (map #(Integer/parseInt %))))


(defn mass->fuel
  [mm]
  (loop [acc 0
         res mm]
    (let [fuel (-> res (/ 3) Math/floor (- 2))]
      (if (> fuel 0)
        (recur (+ acc fuel) fuel)
        acc))))


(defn total-fuel-req
  [masses]
  (reduce + (map mass->fuel masses)))


(println ::total-fuel-req (total-fuel-req input))
