^{:nextjournal.clerk/visibility {:code :hide :result :hide}}
(ns aoc.code.day-03
  (:gen-class)
  (:require [clojure.string :as str]))

(def direction-map
  {"^" "north"
   "v" "south"
   ">" "east"
   "<" "west"})

(defn is-opposite? [tupel]
  (let [[a b] tupel]
    (cond
      (and (= a "^") (= b "v")) -1
      (and (= a "v") (= b "^")) -1
      (and (= a ">") (= b "<")) -1
      (and (= a "<") (= b ">")) -1
      :else 2)))

(is-opposite? ["^" ">"])

(defn get-direction-tupels [data]
  (->>
   (slurp data)
   (str/trim)
   (#(str/split % #""))
   (partition 2)))

(reduce +
        (map #(is-opposite? %)
             (get-direction-tupels "./input/day_03/input.txt")))
