(ns challenge-graph-traversal.question-01-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [challenge-graph-traversal.question-01 :as question-01]))

(deftest traverse-graph-test
  (testing "Given a weighted directed graph,"
    (let [graph {:1 {:2 1 :3 2}
                 :2 {:4 4}
                 :3 {:4 2}
                 :4 {}}]

      (testing "BFS algorithm should be working"
        (is (= [:1 :2 :3 :4] (question-01/seq-graph-bfs graph :1))))

      (testing "DFS algorithm should be working"
        (is (= [:1 :3 :4 :2] (question-01/seq-graph-dfs graph :1)))))))
