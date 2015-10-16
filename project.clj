(defproject rbf-network "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [gnuplot "0.1.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/math.combinatorics "0.1.1"]]
  :plugins [[lein-gorilla "0.3.4"]]
  :main ^:skip-aot rbf-network.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
