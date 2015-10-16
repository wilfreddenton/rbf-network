(ns rbf-network.data-set-generator
  "Generates the data for training the rbf-network"
  (:require [clojure.java.io :as io])
  (:use [rbf-network.utils :only (rand-from-range)]))

(defn generate-data [h n]
  "generate n data points graph them and write them to a file"
  (with-open [wrtr (io/writer "data.txt")]
    (dotimes [i n]
      (let [x (rand-from-range 0.0 1.0)
            noise (rand-from-range -0.1 0.1)
            y (+ noise (h x))]
        (.write wrtr (str x "\t" y \newline))))))

