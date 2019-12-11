(ns aoc.day4.part2-test
  (:require [clojure.test :as t :refer [is are deftest testing]]
            [aoc.day4.part2 :as part2]))

(deftest adjacent-dup?
  (is (true? (part2/adjacent-dup? [4 5 5 6 7 8])))
  (is (true? (part2/adjacent-dup? [4 5 5 6 6 7])) "several")
  (is (false? (part2/adjacent-dup? [4 5 5 5 6 7])) "triple"))
