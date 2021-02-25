(ns challenge-graph-traversal.question-04-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [challenge-graph-traversal.question-04 :as question-04]))

(def graph-01
  {:1 {:2 1 :3 2}
   :2 {:4 4}
   :3 {:4 2}
   :4 {}})

(def graph-02
  {:0 {}
   :1 {}
   :2 {}
   :3 {:0 1 :2 1 :4 1 :5 1 :6 1}
   :4 {}
   :5 {}
   :6 {:7 1}
   :7 {:1 1}})

(def graph-03
  {:a {:b 1 :c 3}
   :b {:e 6}
   :c {:d 4 :e 2}
   :d {:b 5 :e 1}
   :e {}})

(deftest eccentricity-test
  (testing "Given a weighted directed graph, it should compute the eccentricity"
    (is (= 2 (question-04/eccentricity graph-01 :1)))
    (is (= 3 (question-04/eccentricity graph-02 :3)))
    (is (= 2 (question-04/eccentricity graph-03 :a)))))

(deftest radius-test
  (testing "Given a weighted directed graph, it should compute the radius"
    (is (= 1 (question-04/radius graph-01)))
    (is (= 1 (question-04/radius graph-02)))
    (is (= 1 (question-04/radius graph-03)))))

(deftest diameter-test
  (testing "Given a weighted directed graph, it should compute the diameter"
    (is (= 2 (question-04/diameter graph-01)))
    (is (= 3 (question-04/diameter graph-02)))
    (is (= 2 (question-04/diameter graph-03)))))
