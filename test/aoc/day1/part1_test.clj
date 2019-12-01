(ns aoc.day1.part1-test
  (:require [clojure.test :as t :refer [is are deftest testing]]
            [aoc.day1.part1 :as part1]))


(deftest mass->fuel
  (is (= 2.0 (part1/mass->fuel 12)))
  (is (= 2.0 (part1/mass->fuel 14)))
  (is (= 654.0 (part1/mass->fuel 1969)))
  (is (= 33583.0 (part1/mass->fuel 100756))))


(deftest total-fuel-req
  (is (= 34241.0 (part1/total-fuel-req [12 14 1969 100756]))))
