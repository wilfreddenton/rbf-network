(ns rbf-network.utils
  (:require [clojure.math.numeric-tower :as math]
            [clojure.zip :as zip])
  (:use [clojure.string :only [split split-lines trim]]))

(defn- square [n]
  (math/expt n 2))

(defn- tokens [s]
  (map read-string (split (trim s) #"\s+")))

(defn process-data [file-name]
  (map tokens (split-lines (slurp file-name))))

(defn h [x]
  (+ 0.5 (* 0.4 (Math/sin (* 2 Math/PI x)))))

(defn sqr-euclid-dist [x u]
  "compute the squared euclidean distance for scalar inputs"
  (square (- x u)))

(defn magnitude [X]
  (math/sqrt (reduce + (map #(math/expt % 2) X))))

(defn rand-from-range [start end]
  (+ start (rand (- end start))))

(defn variance [[X u]]
  (if (= (count X) 1)
    0
    (* (/ 1.0 (magnitude X)) (reduce + (map (partial sqr-euclid-dist u) X)))))

(defn variances [clusters U]
  (let [vars (mapv variance (map vector clusters U))
        num-zero (count (filter #(== % 0) vars))
        mean-var (/ (reduce + vars) (- (count vars) num-zero))]
    (if (= num-zero 0)
      vars
      (map #(if (== % 0) mean-var %) vars))))

(defn get-x-values [X cluster]
  (map #(nth X %) cluster))

(defn average [nums]
  (/ (reduce + nums) (count nums)))

(defn gaussian [u variance x]
  (math/expt Math/E (/ (- (sqr-euclid-dist x u)) (* 2 variance))))

