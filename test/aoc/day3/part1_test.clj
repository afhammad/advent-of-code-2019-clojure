(ns aoc.day3.part1-test
  (:require [clojure.test :as t :refer [is are deftest testing]]
            [aoc.day3.part1 :as part1]))


(deftest instructions->coords
  (is (= (part1/instructions->coords ["R8" "U5" "L5" "D3"])
         [[[0 0] [8 0]] [[8 0] [8 5]] [[8 5] [3 5]] [[3 5] [3 2]]])))


(deftest overlap?
  (is (= (part1/overlap? [8 3] [2 5]) true))
  (is (= (part1/overlap? [5 10] [11 9]) true))
  (is (= (part1/overlap? [0 3] [5 8]) false))
  (is (= (part1/overlap? [10 15] [5 9]) false)))


(deftest intersecting-lines
  (is (= (part1/intersecting-lines [[[[0 0] [8 0]] [[8 0] [8 5]] [[8 5] [3 5]] [[3 5] [3 2]]]
                                    [[[0 0] [0 7]] [[0 7] [6 7]] [[6 7] [6 3]] [[6 3] [2 3]]]])
         '([[[8 5] [3 5]] [[6 7] [6 3]]]
           [[[3 5] [3 2]] [[6 3] [2 3]]]))))


(deftest intersection-point
  (is (= (part1/intersection-point [[[8 5] [3 5]] [[3 5] [3 2]]])
         [3 5])))


(deftest manhattan-distance
  (is (= (part1/manhattan-distance [[10 10] [20 20]])
         20)))
