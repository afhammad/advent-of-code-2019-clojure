;; Day 3:
;; https://adventofcode.com/2019/day/3
;;
(ns aoc.day3.part1
  (:require [clojure.string :as str]))


(defonce input
  (->> (slurp "inputs/day3")
       str/split-lines
       (map #(str/split % #","))))


(defn apply-op
  [[x y] dir dist]
  (case dir
    "R" [(+ x dist) y]
    "L" [(- x dist) y]
    "U" [x (+ y dist)]
    "D" [x (- y dist)]))


(defn instructions->coords
  "Takes a instructions as a collection [R10 U20]
   and returns a vector of coordinates representing lines
         line1           line2
   [ [[0 0] [10 0]] [[10 0] [10 20]] ]"
  [instructions]
  (reduce
   (fn [coords inst]
     (let [dir (subs inst 0 1) ; "R" from "R10"
           dist (-> inst (subs 1) Integer/parseInt) ; 10 from "R10"
           p1 (if (empty? coords) [0 0] (-> coords last last))
           p2 (apply-op p1 dir dist)]
      (conj coords [p1 p2])))
   []
   instructions))


(defn overlap?
  " p1          p2
    |-----------|
            |---------|
           q1         q2

   We take the min/max of each pair to account for reverse coords
   going Left or Down. We can then apply the formula:
   max(p1,q1) <= min(p2,q2)"
  [[p1 p2] [q1 q2]]
  (when-not (zero? (+ p1 q1)) ;; starting point doesn't count
    (<= (max (min p1 p2) (min q1 q2))
        (min (max p1 p2) (max q1 q2)))))


(defn intersecting-lines
  [[path1 path2]]
  (->>
   (for [[[l1x1 l1y1] [l1x2 l1y2] :as line1] path1
         [[l2x1 l2y1] [l2x2 l2y2] :as line2] path2]
     (when (and (overlap? [l1x1 l1x2] [l2x1 l2x2])
                (overlap? [l1y1 l1y2] [l2y1 l2y2]))
       [line1 line2]))
   (remove nil?)))


(defn intersection-point
  "Takes a vector of adjacent 2 lines made up of start and end coordinates.
   Returns the intersection point coordinates"
  [[[[l1x1 l1y1] [l1x2 l1y2] :as line1]
    [[l2x1 l2y1] [l2x2 l2y2] :as line2]]]
  [(if (= l1x1 l1x2) l1x1 l2x1)   ; get x from vertical line
   (if (= l1y1 l1y2) l1y1 l2y1)]) ; get y from horizontal line


(defn manhattan-distance
  [[[p1 p2] [q1 q2]]]
  (+ (Math/abs (- p1 q1))
     (Math/abs (- p2 q2))))


(defn closest-intersection
  [instructions]
  (->> instructions
       (map instructions->coords)
       intersecting-lines
       (map intersection-point)
       (map #(manhattan-distance [[0 0] %]))
       (apply min)))


(defn -main
  []
  (println ::main (closest-intersection input)))
