^{:nextjournal.clerk/visibility {:code :hide :result :hide}}
(ns aoc.code.day-02
  (:gen-class)
  (:require [clojure.string :as str]))

(defn parse-lines [input]
  (map #(str/split % #"x") (str/split-lines (slurp input))))

(defn calculate-wrapping-paper [line]
  (let [l (Integer/parseInt (first line))
        w (Integer/parseInt (second line))
        h (Integer/parseInt (last line))
        lw (* 2 l w)
        wh (* 2 w h)
        hl (* 2 h l)]
    (+ lw wh hl (/ (min lw wh hl) 2))))

(defn calculate-ribbon [line]
  (let [l (Integer/parseInt (first line))
        w (Integer/parseInt (second line))
        h (Integer/parseInt (last line))
        sorted (sort [l w h])
        smallest (first sorted)
        middle (second sorted)]
    (+ (* l w h) (+ smallest smallest middle middle))))

(defn part1 [input]
  (reduce +
          (map #(calculate-wrapping-paper %)
               (parse-lines input))))

(defn part2 [input]
  (reduce +
          (map #(calculate-ribbon %)
               (parse-lines input))))

(part1 "./input/day_02/input.txt")
(part2 "./input/day_02/input.txt")

