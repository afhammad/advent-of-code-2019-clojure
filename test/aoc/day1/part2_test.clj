(ns aoc.day1.part2-test
  (:require [clojure.test :as t :refer [is are deftest testing]]
            [aoc.day1.part2 :as part2]))


(deftest mass->fuel
  (is (= 2.0 (part2/mass->fuel 12)))
  (is (= 966.0 (part2/mass->fuel 1969)))
  (is (= 50346.0 (part2/mass->fuel 100756))))


(deftest total-fuel-req
  (is (= 51314.0 (part2/total-fuel-req [12 1969 100756]))))
