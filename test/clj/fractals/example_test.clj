(ns fractals.example-test
  (:require [clojure.test :as test]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec.alpha :as s]))

(s/def ::even? (s/and integer? even?))
(s/def ::odd? (s/and integer? odd?))
(s/def ::a integer?)
(s/def ::b integer?)
(s/def ::c integer?)
(def s (s/cat :forty-two #{42}
              :odds (s/+ ::odd?)
              :m (s/keys :req-un [::a ::b ::c])
              :oes (s/* (s/cat :o ::odd? :e ::even?))
              :ex (s/alt :odd ::odd? :even ::even?)))
(s/def ::config (s/*
                 (s/cat :prop string?
                        :val  (s/alt :s string? :b boolean?))))

(test/deftest example-passing-test
  (test/is (= 1 1))
  (test/is (s/valid? s [42 11 13 15 {:a 1
                                     :b 2
                                     :c 3}
                        1 2 3 42 43 44 11]))
  (test/is (=  [11 13 15] (:odds (s/conform s [42 11 13 15 {:a 1
                                                 :b 2
                                                 :c 3}
                                               1 2 3 42 43 44 11]))))
  (test/is (= (get-in
               (s/conform ::config ["-server" "foo" "-verbose" true "-user" "joe"])
               [0 :val 1])
              "foo")))

(defspec first-element-is-min-after-sorting
  100
  (prop/for-all [v (gen/vector gen/int)]
                (= (sort v) (sort (sort v)))))

(defn ascending?
  "clojure.core/sorted? doesn't do what we might expect, so we write our own function"
  [coll]
  (every? (fn [[a b]]
            (<= a b))
          (partition 2 1 coll)))

(defspec property-ascending
  100
  (prop/for-all [v (gen/vector gen/int)]
                (let [s (sort v)]
                  (and (= (count v) (count s))
                       (ascending? s)))))
