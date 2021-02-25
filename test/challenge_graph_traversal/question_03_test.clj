(ns challenge-graph-traversal.question-03-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [challenge-graph-traversal.question-02 :as question-02]
    [challenge-graph-traversal.question-03 :as question-03]))

(deftest dijkstra-test
  (testing "Given a weighted directed graph,"
    (let [graph {:1 {:2 1 :3 2}
                 :2 {:4 4}
                 :3 {:4 2}
                 :4 {}}]

      (testing "it should find the shortest path"
        (is (= [:1 :3 :4] (question-03/D graph :1 :4)))
        (is (= [:1 :3] (question-03/D graph :1 :3))))

      (testing "it should not find the path of unknown vertex"
        (is (= nil (question-03/D graph :1 :5)))
        (is (= nil (question-03/D graph :5 :4)))))))

(deftest dijkstra-02-test
  (testing "Given a weighted directed graph, it should find the shortest path"
    (is (= [:3 :6 :7 :1]
           (question-03/D {:0 {}
                           :1 {}
                           :2 {}
                           :3 {:0 1 :2 1 :4 1 :5 1 :6 1}
                           :4 {}
                           :5 {}
                           :6 {:7 1}
                           :7 {:1 1}}
                          :3
                          :1)))
    (is (= [:a :c :e]
           (question-03/D {:a {:b 1 :c 3}
                           :b {:e 6}
                           :c {:d 4 :e 2}
                           :d {:b 5 :e 1}
                           :e {}}
                          :a
                          :e)))))

(deftest dijkstra-03-test
  (testing "Given a generated graph,"
    (let [random-graph (question-02/G 10 10)]
      (testing "it should find the shortest path"
        (is (<= 0 (-> (question-03/D random-graph
                                     (first (keys random-graph))
                                     (last (keys random-graph)))
                      (count))))))))

