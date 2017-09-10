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


(defn siblings [state [x y]]
  (let [dx [  0 0 -1 1]
        dy [ -1 1  0 0]
        dxy (mapv (fn [x1 y1] [(+ x x1)
                               (+ y y1)] ) dx dy)
        valid-dxy (filterv (fn [[x2 y2]]
                             (and (>= x2 0)
                                  (>= y2 0)
                                  (< x2 (:rows state))
                                  (< y2 (:cols state)))) dxy)
        ]
    (filterv (fn [[x3 y3]]
               (get-in state [:board x3 y3]))
             valid-dxy)))

(defn init-field [state]
  (let [rows (:rows @state)
        cols (:cols @state)
        board (:board @state)
        xy (for [x (range 0 rows)
                 y (range 0 cols)]
             [x y])
        max2 (range 0 (* rows cols))
        max-field (reduce (fn [r [x y v]]
                            (assoc-in r [x y] v))
                          board
                          (map (fn [[x y] m]
                                 [x y m])  xy max2))]
    (reduce (fn [r [x y]]
              (assoc-in r [x y]
                        (if (get-in @state [:board x y])
                          (reduce
                           (fn [min1 [sx1 sy1]]
                             (if (< (get-in r [sx1 sy1])
                                    min1)
                               (get-in r [sx1 sy1])
                               min1))
                           (get-in r [x y])
                           (siblings @state [x y])) -1)))
            max-field xy)))

(def app-state (atom {:text "Hello Chestnut!"
                      :zero-col "#4E9A5D"
                      :one-col "#88F9D4"
                      :rows 3
                      :cols 4
                      :board [[true false false true]
                              [false true false true]
                              [false true false false]]}))

(map #(clojure.string/join " " %) (init-field app-state))
