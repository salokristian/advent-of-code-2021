(ns day-3.main
  (:require [clojure.edn :as edn]
            [common :as common]))

(def bits (range 12))

;; part 1
(let [diagnostics (common/parse-file (comp edn/read-string (partial str "2r")) "day-3/input.txt")
      bit-counts (map (fn [bit]
                        (->> diagnostics
                             (filter #(pos? (bit-and (int (Math/pow 2 bit)) %)))
                             count))
                      bits)
      gamma-bits (map #(if (< (/ (count diagnostics) 2) %) 1 0) bit-counts)]
      (apply str (reverse gamma-bits)))
