(ns challenge-graph-traversal.question-02)

;; 2. Write an algorithm to randomly generate a simple directed graph using your answer from #1
;; Input:
;;     N - size of generated graph
;;     S - sparseness (number of directed edges actually; from N-1 to N(N-1)/2)
;; Output:
;;    simple connected graph G(n,s) with N vertices and S edges

(defn G [n s]
  {:pre [(<= (- n 1) s) (< s (/ (* n (- n 1)) 2))]}
  (let [vertices (->> (range 1 (inc n))
                      (map str)
                      (map keyword)
                      (shuffle))
        base-graph (reduce #(assoc %1 %2 {}) (array-map) vertices)
        connected-graph-min-edges (map vector vertices (drop 1 vertices))
        other-combinator-edges (for [head (shuffle vertices)
                                     tail (shuffle vertices)
                                     :when (not= head tail)
                                     :when (not-any? #{[head tail]} connected-graph-min-edges)]
                                 [head tail])
        graph-edges (concat connected-graph-min-edges
                            (take (- s (- n 1)) other-combinator-edges))]
    (reduce (fn [graph [head tail]]
              (assoc-in graph [head tail] (inc (rand-int n))))
            base-graph
            graph-edges)))
