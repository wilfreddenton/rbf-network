(ns rbf-network.core
  (:require [clojure.java.io :as io]
            [rbf-network.data-set-generator :as generator]
            [rbf-network.utils :as utils]))

(defn closest-centroid [x U]
  "finds the centroid u that is closest to x and returns u's index"
  (first (apply min-key second (map-indexed vector (map (partial utils/sqr-euclid-dist x) U)))))

(defn kmeans
  ([X k start end]
   (kmeans X (for [i (range k)] (utils/rand-from-range start end))))
  ([X U]
   (let [clusters
         (loop [i 0 clusters (vec (replicate (count U) []))]
           (if (= (count X) i)
             clusters
             (let [x (nth X i) u-index (closest-centroid x U)]
               (recur
                 (inc i)
                 (assoc clusters u-index (conj (nth clusters u-index) i))))))
         new-U (map (fn [cluster] (utils/average (map #(nth X %) cluster))) clusters)]
     (if (= U new-U)
       [clusters new-U]
       (kmeans X new-U)))))

(defn F [U weights vars x]
  (+ (reduce + (map #(* %1 (utils/gaussian %2 %3 x)) (rest weights) U vars)) (first weights)))

(defn weight-update [d y eta w u]
  (+ w (* eta (- d y) u)))

(defn rbf-approx [X Y clusters U vars eta]
  "train an rbf network and return the weights after 100 epochs"
  (loop [i 0 weights (vec (replicate (+ (count U) 1) 0.0))]
    (if (= 100 i)
      weights
      (recur
        (inc i)
        (loop [j 0 weights weights]
          (if (= (count X) j)
            weights
            (let [x (nth X j) d (nth Y j) y (F U weights vars x)]
              (recur (inc j) (map (partial weight-update d y eta) weights (into [1] U))))))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (if-not (.exists (io/as-file "data.txt"))
    (generator/generate-data utils/h 75))
  (let [data (utils/process-data "data.txt")
        X (map first data)
        Y (map second data)
        [clusters U] (kmeans X 3 0 1)
        vars (utils/variances (map (partial utils/get-x-values X) clusters) U)
        weights (rbf-approx X Y clusters U vars 0.01)]
    (print (print-str clusters \newline U \newline vars \newline weights))))

