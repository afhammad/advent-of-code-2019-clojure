;; Day 3:
;; https://adventofcode.com/2019/day/3
;;
(ns aoc.day3.part2
  (:require [aoc.day3.part1 :as part1]))


(defn line-crosses-point?
  [[[lx1 ly1] [lx2 ly2]] [px py]]
  (and (<= lx1 px lx2)
       (<= ly1 py ly2)))


(defn steps-to-point
  [path [px py :as point]]
  (loop [[[lx1 ly1] [lx2 ly2] :as line] (first path)
         rest-path (rest path)
         steps -1]
    (println line point steps)
    (if-not (nil? line)
      (if (line-crosses-point? line point)
        (do
          (println :line line)
          (+ steps
             (Math/abs (- px lx1))
             (Math/abs (- py ly1))))
        (recur (first rest-path)
               (rest rest-path)
               (+ steps
                  (Math/abs (- lx2 lx1))
                  (Math/abs (- ly2 ly1)))))
      steps)))


(defn closest-intersection
  [instructions]
  (let [[path1 path2 :as paths] (map part1/instructions->coords instructions)
        lines (part1/intersecting-lines paths)
        points (map part1/intersection-point lines)
        steps (for [point points]
                (do
                  (+ (steps-to-point path1 point)
                     (steps-to-point path2 point))))]
    (apply min steps)))


(defn -main
  []
  (println ::main (closest-intersection part1/input)))
