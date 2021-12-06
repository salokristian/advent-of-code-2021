(ns day-2.main
  (:require [clojure.edn :as edn]
            [common :as common]))

(defn parse-dir [dir-str]
  (let [[_ dir amount-str] (re-find #"(\w*) (\d*)" dir-str)
        amount (edn/read-string amount-str)]
    (case dir
      "down" {:aim amount}
      "up" {:aim (- amount)}
      "forward" {:forward amount})))

;; part 1
(let [directions (common/parse-file parse-dir "day-2/input.txt")
      forward (->> directions (map :forward) (filter some?) (apply +))
      aim (->> directions (map :aim ) (filter some?) (apply +))]
  (* forward aim))

;; part 2
(let [directions (common/parse-file parse-dir "day-2/input.txt")
      aims (->> directions
                (map :aim)
                (map #(if (nil? %) 0 %))
                (reduce (fn [[xs cur-aim] direction]
                          (let [new-aim (+ cur-aim direction)] 
                            [(conj xs new-aim) new-aim])) 
                        [[] 0])
                first)
      forward (->> directions 
                   (map :forward) 
                   (map #(if (nil? %) 0 %)))
      depth (map * aims forward)]
  (* (apply + depth) 
     (apply + forward)))