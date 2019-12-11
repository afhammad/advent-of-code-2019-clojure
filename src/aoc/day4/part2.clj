;; Day 4:
;; https://adventofcode.com/2019/day/4
;;
(ns aoc.day4.part2
  (:require [clojure.spec.alpha :as s]
            [aoc.day4.part1 :as part1]))


(defn adjacent-dup?
  [v-seq]
  (->> v-seq
       (reduce
        (fn [acc n]
          (update acc n #(if (nil? %) 1 (inc %))))
        {})
       vals
       (some #(= 2 %))
       boolean))


(s/def ::password (s/and part1/valid-length?
                         part1/incremental?
                         part1/in-range?
                         adjacent-dup?))


(defn -main
  []
  (println ::main (part1/valid-password-count ::password)))
