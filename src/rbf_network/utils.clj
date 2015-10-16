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

(defn rand-from-range [start end]
  (+ start (rand (- end start))))

(defn average [nums]
  (/ (reduce + nums) (count nums)))

(defn variance [[X u]]
  (* (/ 1.0 (count X)) (reduce + (map (partial sqr-euclid-dist u) X))))

(defn variances [clusters U]
  (map variance (map vector clusters U)))

(defn get-x-values [X cluster]
  (map #(nth X %) cluster))

(defn gaussian [u variance x]
  (math/expt Math/E (/ (- (sqr-euclid-dist x u)) (* 2 variance))))

