(ns challenge-graph-traversal.question-02-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [challenge-graph-traversal.question-01 :as question-01]
    [challenge-graph-traversal.question-02 :as question-02]))

(deftest random-graph-test
  (testing "Given the size and the number of generated graph, G should generate a connected graph"
    (let [n 10
          s 10]
      (is (= n (-> (question-02/G n s)
                   keys
                   count))
          (str "it should have " n " vertex"))
      (is (= s (->> (question-02/G n s)
                    vals
                    (mapcat keys)
                    count))
          (str "it should have " s " edge")))))

(deftest random-graph-generate-connected-graph-test
  (testing "Given a graph generated, it should be a connected graph"
    (let [random-graph (question-02/G 10 10)
          vertices (keys random-graph)]
      (doseq [u vertices
              v vertices]
        (is (some #{v} (question-01/seq-graph-bfs random-graph u))
            (str u " and " v " are not connected"))))))
