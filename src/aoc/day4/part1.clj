;; Day 4:
;; https://adventofcode.com/2019/day/4
;;
(ns aoc.day4.part1
  (:require [clojure.spec.alpha :as s]))

(defonce rng [382345 843167])
(defonce length 6)


(defn split-number
  "Takes an integer and returns a list of all the digits
   1234 -> (1 2 3 4)"
  [n]
  (map #(Character/digit % 10) (str n)))


(defn valid-length?
  [v-seq]
  (= length (count v-seq)))


(defn incremental?
  [v-seq]
  (apply <= v-seq))


(defn adjacent-dup?
  "Returns true if there is atleast 1 pair of adjacent duplicate digits"
  [v-seq]
  (loop [rem v-seq]
    (let [[n1 n2] (take 2 rem)]
      (if (= n1 n2)
        (not= nil n1 n2)
        (recur (rest rem))))))


(defmulti in-range? seq?)
(defmethod in-range? true [v-seq]
  (in-range? (-> v-seq clojure.string/join Integer/parseInt)))
(defmethod in-range? false [v]
  (<= (first rng) v (last rng)))


(s/def ::password (s/and valid-length?
                         incremental?
                         in-range?
                         adjacent-dup?))


(defn valid?
  [spec v]
  (s/valid? spec (split-number v)))


(defn valid-password-count
  [spec]
  (let [ps (for [v (apply range rng)
                 :when (valid? spec v)]
             v)]
    (count ps)))


(defn -main
  []
  (println ::main (valid-password-count ::password)))
