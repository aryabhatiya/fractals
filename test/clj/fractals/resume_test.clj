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
    (str
     (ffirst
      (d/q '[:find ?dob
             :where
             [?id :user/lastName "Ashik"]
             [?id :user/firstName ?first]
             [?id :user/msisdn ?msisdn]
             [?id :user/dob ?dob]]
           (d/db
            (:conn resume-datom)))))
    (alter-var-root #'resume-datom component/stop)))
