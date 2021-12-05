(ns day-1.main
  (:require [clojure.edn :as edn]
            [common :as common]))

;; part 1 
(let [input (common/parse-file edn/read-string "day-1/input.txt")]
  (->> (map < input (rest input))
       (filter true?)
       count))

;; part 2
(let [input (common/parse-file edn/read-string "day-1/input.txt")
      windows (partition 3 1 input)
      window-sums (map #(apply + %) windows)]
  (->> (map < window-sums (rest window-sums))
       (filter true?)
       count))