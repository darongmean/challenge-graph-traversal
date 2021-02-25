(ns challenge-graph-traversal.question-03
  (:require
    [challenge-graph-traversal.question-01 :as question-01]
    [clojure.walk :as walk])
  (:import
    (java.util Comparator PriorityQueue)))

;; 3. Write an implementation of Dijkstra's algorithm that traverses your graph and
;;    outputs the shortest path between any 2 randomly selected vertices.

(defn- make-cost-table [graph start-vertex]
  (let [vertices (into [] (keys graph))]
    (-> (reduce #(assoc %1 %2 {:cost Integer/MAX_VALUE :prev-vertex nil}) {} vertices)
        (assoc start-vertex {:cost 0 :prev-vertex nil}))))

(defn- calculate-cost [cost-table prev-vertex vertices]
  (for [[vertex cost] vertices]
    {:vertex vertex
     :cost (+ cost (get-in cost-table [prev-vertex :cost]))
     :prev-vertex prev-vertex}))

(defn- update-shortest-path-cost [cost-table neighbors-cost]
  (reduce (fn [acc {:keys [vertex cost prev-vertex]}]
            (if (< cost (get-in acc [vertex :cost]))
              (assoc acc vertex {:cost cost :prev-vertex prev-vertex})
              acc))
          cost-table
          neighbors-cost))

(defn- shortest-path-cost-table
  [graph start end]
  (let [priority-queue-frontier (PriorityQueue. ^Comparator (comparator #(< (:cost %1) (:cost %2))))
        cost-table-seq (fn loop-seq [explored cost-table]
                         (lazy-seq
                           (when-let [{:keys [vertex cost]} (.poll priority-queue-frontier)]
                             (cond
                               (= end vertex) nil

                               (some #{vertex} explored)
                               (loop-seq explored cost-table)

                               (< (get-in cost-table [vertex :cost]) cost)
                               (loop-seq explored cost-table)

                               :else
                               (let [neighbors-cost (->> vertex
                                                         (get graph)
                                                         (calculate-cost cost-table vertex))
                                     updated-cost-table (update-shortest-path-cost cost-table neighbors-cost)]
                                 (.addAll priority-queue-frontier neighbors-cost)
                                 (cons updated-cost-table
                                       (loop-seq (conj explored vertex) updated-cost-table)))))))]
    (.add priority-queue-frontier {:vertex start :cost 0 :prev-vertex nil})
    (->> (make-cost-table graph start)
         (cost-table-seq #{})
         last)))

(defn D [graph start end]
  (let [cost-table (shortest-path-cost-table graph start end)
        as-shortest-path-graph (fn [cost-table] (walk/walk (fn [[k {:keys [cost prev-vertex]}]] [k {prev-vertex cost}])
                                                           identity
                                                           cost-table))
        traverse-graph (fn [g] (drop-last (question-01/seq-graph-bfs g end)))]
    (when (get-in cost-table [end :prev-vertex])
      (->> cost-table
           as-shortest-path-graph
           traverse-graph
           reverse))))
