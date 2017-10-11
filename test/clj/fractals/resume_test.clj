(ns fractals.resume-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [com.stuartsierra.component :as component]
   [system.components.datomic :refer [new-datomic-db]]
   [datomic.api :as d]
   [clojure.pprint :refer [pprint]]
   [clojure.test.check :as tc]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [clojure.spec.alpha :as spec]
   [clojure.spec.gen.alpha :as sgen]
   [fractals.datomicdb :as datomicdb]))

(def resume-datom
  (datomicdb/map->Datomic-Mem
   {:schema
    [
     {:db/ident :user/firstName
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/lastName
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/email
      :db/unique :db.unique/identity
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/msisdn
      :db/unique :db.unique/identity
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/dob
      :db/valueType :db.type/instant
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/banner
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}]}))

(def domain (gen/elements ["gmail.com" "hotmail.com" "computer.org"]))
(def email-gen
  (gen/fmap (fn [[name domain-name]]
              (str name "@" domain-name))
            (gen/tuple (gen/not-empty gen/string-alphanumeric) domain)))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(spec/def ::email-type (spec/and string? #(re-matches email-regex %)))
(spec/def ::first-name string?)
(spec/def ::last-name string?)
(spec/def ::msisdn string?)
(spec/def ::email ::email-type)
(spec/def ::dob inst?)

(deftest resume-datom-transection-query
  (testing "Insert & Query data"
    (alter-var-root #'resume-datom component/start)
    (is (= (type (:conn resume-datom)) datomic.peer.LocalConnection))
    @(d/transact (:conn resume-datom)
                 [{:user/firstName "Md"
                   :user/lastName "Ashik"
                   :user/email "aryabhatiya.algebra@gmail.com"
                   :user/msisdn "+8801"
                   :user/dob #inst"1986-10-13"
                   :user/banner "FullStack"}])
    (is (every?
         true?
         (map
          (fn [data v]
            (spec/valid? v data))
          (first
           (d/q '[:find ?first ?last  ?msisdn ?dob
                  :where
                  [?id :user/lastName ?last]
                  [?id :user/firstName ?first]
                  [?id :user/msisdn ?msisdn]
                  [?id :user/dob ?dob]]
                (d/db
                 (:conn resume-datom))))
          [string? string? string? inst?])))
    (is (spec/valid?
         (spec/cat
          :last-name  ::last-name
          :first-name ::first-name
          :email      ::email
          :msisdn     ::msisdn
          :dob        ::dob)
         (first
          (d/q '[:find ?last ?first ?email ?msisdn ?dob
                 :where
                 [?id :user/lastName ?last]
                 [?id :user/firstName ?first]
                 [?id :user/msisdn ?msisdn]
                 [?id :user/email ?email]
                 [?id :user/dob ?dob]]
               (d/db
                (:conn resume-datom))))))
    (alter-var-root #'resume-datom component/stop)))
