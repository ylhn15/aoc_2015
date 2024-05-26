^{:nextjournal.clerk/visibility {:code :hide :result :hide}}
(ns aoc.code.day-01
  (:gen-class)
  (:require [clojure.string :as str]))
; --- Day 1: Not Quite Lisp ---
;
; Santa was hoping for a white Christmas, but his weather machine's "snow" function is powered by stars, and he's fresh out! To save Christmas, he needs you to collect fifty stars by December 25th.
;
; Collect stars by helping Santa solve puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
;
; Here's an easy puzzle to warm you up.
;
; Santa is trying to deliver presents in a large apartment building, but he can't find the right floor - the directions he got are a little confusing. He starts on the ground floor (floor 0) and then follows the instructions one character at a time.
;
; An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go down one floor.
;
; The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.
;
; For example:
;
;     (()) and ()() both result in floor 0.
;     ((( and (()(()( both result in floor 3.))))))
;     ((((( also results in floor 3.)))))
;     () and ( both result in floor -1 (the first basement level).)
;      and ()() both result in floor -3.
;
; To what floor do the instructions take Santa?

(defn count-paranthese [para]
  (cond (= para "(") 1
        (= para ")") -1
        :else 0))
(def input (str/split (slurp "./input/day_01/input.txt") #""))

(defn a [input] (map #(count-paranthese %))
  input)

(defn count-parentheses [s]
  (reduce (fn [acc ch]
            (case ch
              \( (inc acc)
              \) (dec acc)
              acc))
          0
          s))

(defn reduce-count-parentheses [input]
  (loop [acc 0
         idx 0
         coll input]
    (if (or (empty? coll) (= acc -1))
      (if (= acc -1) idx -1) ; return index if -1, otherwise -1 indicating not found
      (recur (let [count (count-parentheses (first coll))]
               (+ acc count))
             (inc idx)
             (rest coll)))))

(reduce + (map #(count-paranthese %)
               input))
(.indexOf  (map #(count-paranthese %)
                input) -1)

