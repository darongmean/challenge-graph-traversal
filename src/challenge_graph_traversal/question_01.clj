(ns challenge-graph-traversal.question-01
  (:import
    (clojure.lang PersistentQueue)))

;; 1. Extend the graph definition to include a weight between graph edges

(defn seq-graph [d g s]
  ((fn rec-seq [explored frontier]
     (lazy-seq
       (if (empty? frontier)
         nil
         (let [v (peek frontier)
               neighbors (keys (g v))]
           (cons v (rec-seq
                     (into explored neighbors)
                     (into (pop frontier) (remove explored neighbors))))))))
   #{s} (conj d s)))

(def seq-graph-dfs (partial seq-graph []))
(def seq-graph-bfs (partial seq-graph (PersistentQueue/EMPTY)))
