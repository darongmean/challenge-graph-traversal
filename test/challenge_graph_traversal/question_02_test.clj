(ns challenge-graph-traversal.question-02-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [challenge-graph-traversal.question-02 :as question-02]))

;; TODO: test generated graph is connected

(deftest random-graph-test
  (testing "Given the size and the number of generated graph, G should generate a connected graph"
    (is (= 10
           (-> (question-02/G 10 10)
               keys
               count)))))
