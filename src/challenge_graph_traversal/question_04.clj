(ns challenge-graph-traversal.question-04
  (:require
    [challenge-graph-traversal.question-03 :as question-03]))

;; 4. Write a suite of functions to calculate distance properties for your graph.

(defn- distance [graph v u]
  (let [shortest-path (question-03/D graph v u)]
    (when shortest-path
      (-> shortest-path
          count
          dec))))

(defn eccentricity [graph vertex]
  (let [distances (->> (keys graph)
                       (map #(distance graph vertex %))
                       (remove nil?))]
    (when (seq distances)
      (apply max distances))))

(defn radius [graph]
  (let [eccentricities (->> (keys graph)
                            (map #(eccentricity graph %))
                            (remove nil?))]
    (when (seq eccentricities)
      (apply min eccentricities))))

(defn diameter [graph]
  (let [eccentricities (->> (keys graph)
                            (map #(eccentricity graph %))
                            (remove nil?))]
    (when (seq eccentricities)
      (apply max eccentricities))))
