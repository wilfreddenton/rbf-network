(ns rbf-network.utils
  (:require [clojure.math.numeric-tower :as math]
            [clojure.zip :as zip])
  (:use [clojure.string :only [split split-lines trim]]))

(defn- square [n]
  "compute the square of n"
  (math/expt n 2))

(defn- tokens [s]
  "split a string on whitespace"
  (map read-string (split (trim s) #"\s+")))

(defn process-data [file-name]
  "given 2 column data created a 2D array"
  (map tokens (split-lines (slurp file-name))))

(defn h [x]
  "the function to be approximated"
  (+ 0.5 (* 0.4 (Math/sin (* 2 Math/PI x)))))

(defn sqr-euclid-dist [x u]
  "compute the squared euclidean distance for scalar inputs"
  (square (- x u)))

(defn magnitude [X]
  "compute the magnitude of a vector X"
  (math/sqrt (reduce + (map #(math/expt % 2) X))))

(defn rand-from-range [start end]
  "return a random value from the specified range"
  (+ start (rand (- end start))))

(defn variance [[X u]]
  "compute the variance of a set of scalar data"
  (if (= (count X) 1)
    0
    (* (/ 1.0 (magnitude X)) (reduce + (map (partial sqr-euclid-dist u) X)))))

(defn variances [clusters U]
  "compute the variances of the clusters"
  (let [vars (mapv variance (map vector clusters U))
        num-zero (count (filter #(== % 0) vars))
        mean-var (/ (reduce + vars) (- (count vars) num-zero))]
    (if (= num-zero 0)
      vars
      (map #(if (== % 0) mean-var %) vars))))

(defn get-x-values [X cluster]
  "retrieve the x values in the cluster"
  (map #(nth X %) cluster))

(defn average [nums]
  "compute the average of a set of scalar values"
  (/ (reduce + nums) (count nums)))

(defn gaussian [u variance x]
  "a guassian function that computes the output of an x value given
  a variance and a mean"
  (math/expt Math/E (/ (- (sqr-euclid-dist x u)) (* 2 variance))))

