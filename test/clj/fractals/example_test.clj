(ns fractals.example-test
  (:require [clojure.test :as test]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.spec.alpha :as s]))

(test/deftest example-passing-test
  (test/is (= 1 1)))

(defspec first-element-is-min-after-sorting
  100
  (prop/for-all [v (gen/vector gen/int)]
                (= (sort v) (sort (sort v)))))


(s/conform even? 1000)

(s/valid? even? 10)

(s/valid? nil? nil)

(s/valid? string? "abc")

(s/valid? #(> % 5) 10)

(s/valid? #(> % 5) 0)

(defn ascending?
  "clojure.core/sorted? doesn't do what we might expect, so we write our own function"
  [coll]
  (every? (fn [[a b]]
            (<= a b))
          (partition 2 1 coll)))
(def property
  (prop/for-all [v (gen/vector gen/int)]
                (let [s (sort v)]
                  (and (= (count v) (count s))
                       (ascending? s)))))

(def bad-property
  (prop/for-all [v (gen/vector gen/int)]
                (ascending? v)))

(tc/quick-check 100 bad-property)

(gen/sample gen/int)

(gen/sample gen/int 20)

(take 100 (gen/sample-seq gen/int))

(gen/sample (gen/vector gen/nat))

(gen/sample (gen/list gen/boolean))

(gen/sample (gen/map gen/keyword gen/boolean) 5)
