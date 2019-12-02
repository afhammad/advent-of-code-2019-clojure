;; Day 2:
;; https://adventofcode.com/2019/day/2
;;
(ns aoc.day2.part1)


(defonce input
  (->> (clojure.string/split (slurp "inputs/day2") #",")
       (map clojure.string/trim)
       (mapv #(Integer/parseInt %))))


(def opcodes
  {1 +
   2 *
   99 :halt})


(defn reset
  [ints]
  (-> ints (assoc 1 12) (assoc 2 2)))


(defn intcode
  [ints]
  (loop [codes ints
         idx 0]
    (let [val1    (nth codes (nth codes (+ idx 1)))
          val2    (nth codes (nth codes (+ idx 2)))
          new-pos (nth codes (+ idx 3))
          op      (get opcodes (nth codes idx))]
      (case op
        nil (throw (Exception. "Something went wrong, Uknown input"))
        :halt codes
        (recur (assoc codes new-pos (op val1 val2)) (+ idx 4))))))


(defn result
  [ints]
  (-> ints reset intcode first))


(println (result input))
