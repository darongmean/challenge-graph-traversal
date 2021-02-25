(ns challenge-graph-traversal.core
  (:gen-class)
  (:require
    [clojure.pprint :as pprint]
    [challenge-graph-traversal.question-02 :as question-02]
    [challenge-graph-traversal.question-03 :as question-03]
    [challenge-graph-traversal.question-04 :as question-04]))

(defn- parse-int? [x]
  (try
    (Integer/parseInt x)
    (catch Exception _)))

(defn -main [& args]
  (let [[arg-1 n arg-2 s] args]
    (cond
      (not= "-N" arg-1)
      (do (println "Unsupported argument" arg-1)
          (println "Try something like: graph -N 5 -S 10"))

      (not= "-S" arg-2)
      (do (println "Unsupported argument" arg-2)
          (println "Try something like: graph -N 5 -S 10"))

      (not (parse-int? n))
      (do (println "-N expects an integer but got" n)
          (println "Try something like: graph -N 5 -S 10"))

      (not (parse-int? s))
      (do (println "-S expects an integer but got" s)
          (println "Try something like: graph -N 5 -S 10"))

      :else
      (try
        (let [graph (question-02/G (Integer/parseInt n)
                                   (Integer/parseInt s))
              vertices (keys graph)
              random-1 (rand-nth vertices)
              random-2 (rand-nth vertices)]
          (println "The following graph is generated:")
          (pprint/pprint graph)
          (println "The radius is" (question-04/radius graph) ".")
          (println "The diameter is" (question-04/diameter graph) ".")
          (println "The shortest path between random nodes" random-1 "and" random-2 "is"
                   (question-03/D graph random-1 random-2)
                   ".")
          (println "The eccentricity of" random-1 "is" (question-04/eccentricity graph random-1) "."))
        (catch Throwable ex
          (println "There's something wrong!" (ex-message ex)))))))
