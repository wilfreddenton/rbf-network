(ns rbf-network.core
  (:require [clojure.java.io :as io]
            [rbf-network.data-set-generator :as generator]
            [rbf-network.utils :as utils]))

(defn closest-centroid [x U]
  "finds the centroid u that is closest to x and returns u's index"
  (first (apply min-key second (map-indexed vector (map (partial utils/sqr-euclid-dist x) U)))))

(defn centroid-update [X cluster]
  "update the centroid to the average of the x values."
  (if (= (count cluster) 0)
    (rand-nth X)
    (utils/average (utils/get-x-values X cluster))))

(defn kmeans
  "performs k means clustering on a set of scalar values"
  ([X k start end]
   (kmeans X (for [i (range k)] (rand-nth X))))
  ([X U]
   (let [clusters
         (loop [i 0 clusters (vec (repeat (count U) []))]
           (if (= (count X) i)
             clusters
             (let [x (nth X i) u-index (closest-centroid x U)]
               (recur
                 (inc i)
                 (assoc clusters u-index (conj (nth clusters u-index) i))))))
         new-U (map (partial centroid-update X) clusters)]
     (if (= U new-U)
       [clusters new-U]
       (kmeans X new-U)))))

(defn F [U weights vars outputs? x]
  "computes the output of the rbf network. it needs the centroids, weights, variances
  of each cluster, a boolean to determine whether or not to return the individual
  outputs of each basis function along with the output of the network."
  (let [outputs (apply conj (vector (first weights)) (map #(* %1 (utils/gaussian %2 %3 x)) (rest weights) U vars))
        y (reduce + outputs)]
    (if outputs?
      [y outputs]
      y)))

(defn weight-update [d y eta w x]
  "update rule for single layer perceptron"
  (+ w (* eta (- d y) x)))

(defn rbf-weights [X Y clusters U vars eta]
  "train an rbf network and return the weights after 100 epochs"
  (loop [i 0 weights (vec (repeat (+ (count U) 1) 1.0))]
    (if (= 100 i)
      weights
      (recur
        (inc i)
        (loop [j 0 weights weights]
          (if (= (count X) j)
            weights
            (let [x (nth X j) d (nth Y j) [y outputs] (F U weights vars true x)]
              (recur (inc j) (map (partial weight-update d y eta) weights outputs)))))))))

(defn train [data k eta start end same-vars?]
  "collects all the data needed to train the rbf network and then initalizes
  the training. it returns the clusters, centroids, variances of the clusters, and
  the trained weights"
  (let [X (map first data)
        Y (map second data)
        [clusters U] (kmeans X k start end)
        vars (utils/variances (map (partial utils/get-x-values X) clusters) U same-vars?)]
    [clusters U vars (rbf-weights X Y clusters U vars eta)]))

(defn -main
  "generate data if it has not yet been created"
  [& args]
  (if-not (.exists (io/as-file "data.txt"))
    (generator/generate-data utils/h 75)))

