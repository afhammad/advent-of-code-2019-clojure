;; Day 2:
;; https://adventofcode.com/2019/day/2
;;
(ns aoc.day2.part2
  (:require [aoc.day2.part1 :as part1]))


(defn brute
  [ints]
  (for [noun (range 100)
        verb (range 100)
        :let [res (-> ints
                      (assoc 1 noun)
                      (assoc 2 verb)
                      part1/intcode
                      first)]
        :when (= res 19690720)]
    (-> 100 (* noun) (+ verb))))


(println (-> part1/input brute first))
