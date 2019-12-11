(ns aoc.day4.part1-test
  (:require [clojure.test :as t :refer [is are deftest testing]]
            [aoc.day4.part1 :as part1]))

(deftest valid?
  (is (true? (part1/valid? ::part1/password 455678)) "valid")
  (is (false? (part1/valid? ::part1/password 111111)) "too small")
  (is (false? (part1/valid? ::part1/password 999999)) "too big")
  (is (false? (part1/valid? ::part1/password 456789)) "no adjacent duplicate")
  (is (false? (part1/valid? ::part1/password 455489)) "not incremental"))


(deftest valid-length?
  (is (true? (part1/valid-length? [4 5 5 6 7 8])))
  (is (false? (part1/valid-length? [4 5 5 6 7 8 6])) "too long")
  (is (false? (part1/valid-length? [4 5 5 6])) "too short"))


(deftest incremental?
  (is (true? (part1/incremental? [4 5 5 6 7 8])))
  (is (false? (part1/incremental? [4 6 7 5 8 9])) "5 is less than 7"))


(deftest adjacent-dup?
  (is (true? (part1/adjacent-dup? [4 5 5 6 7 8])))
  (is (true? (part1/adjacent-dup? [4 5 5 6 6 7])) "several")
  (is (true? (part1/adjacent-dup? [4 5 5 5 6 7])) "triple")
  (is (false? (part1/adjacent-dup? [4 5 6 5 6 7])) "no adjacent dups"))


(deftest in-range?
  (is (true? (part1/in-range? 455678)))
  (is (false? (part1/in-range? 111111)) "too low")
  (is (false? (part1/in-range? 999999)) "too high"))
