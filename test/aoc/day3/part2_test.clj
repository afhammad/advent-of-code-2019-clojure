(ns aoc.day3.part2-test
  (:require [clojure.test :as t :refer [is are deftest testing]]
            [aoc.day3.part2 :as part2]))


(deftest steps-to-point
  (let [path [[[0 0] [8 0]] [[8 0] [8 5]] [[8 5] [3 5]] [[3 5] [3 2]]]
        point [3 3]]
    (is (= (part2/steps-to-point path point)
           20))
    (is (= (part2/steps-to-point path [8 3])
           10))))
