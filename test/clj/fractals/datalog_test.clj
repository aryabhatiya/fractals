(ns fractals.datalog-test
  (:require
   [clojure.test :refer :all]
   [com.stuartsierra.component :as component]
   [system.components.datomic :refer [new-datomic-db]]
   [datomic.api :as d]
   [clojure.pprint :refer [pprint]]
   [clojure.test.check :as tc]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [clojure.spec.alpha :as spec]))

(defrecord Datomic-Test [uri conn schema]
  component/Lifecycle
  (start [component]
    (let [uri (str "datomic:mem://" (gensym))
          db (d/create-database uri)
          conn (d/connect uri)
          ]
      @(d/transact conn schema)
      (-> component
          (assoc  :conn conn)
          (assoc  :uri uri)
          (assoc :db (d/db conn)))))
  (stop [component]
    (when conn (d/release conn))
    (d/delete-database uri)
    (assoc component :conn nil)))

(def datomic-mock-db
  (map->Datomic-Test
   {:schema
    [{:db/ident             :todo/title
      :db/valueType         :db.type/string
      :db/cardinality       :db.cardinality/one
      :db/doc               "This is the title of the todo item"}
     {:db/ident             :todo/completed?
      :db/valueType         :db.type/boolean
      :db/cardinality       :db.cardinality/one
      :db/doc               "if true, this item is done"}]}))

(defn todo-new
  [title]
  [{:todo/title        title
    :todo/completed?   false}])

(defn todo-count
  [db]
  (count
   (d/q '[:find ?t
          :where [_ :todo/title ?t]]
        db)))


(deftest datomic-lifecycle-mock
  (testing "Datomic lifecycle operations."
    (alter-var-root #'datomic-mock-db component/start)
    (is (= (type (:conn datomic-mock-db))
           datomic.peer.LocalConnection))
    (is (:conn datomic-mock-db))
    (is (= 0 (todo-count (d/db (:conn datomic-mock-db)))))
    (is (= 1 (let [t @(d/transact (:conn datomic-mock-db) (todo-new "hello world"))]
               (todo-count (d/db (:conn datomic-mock-db))))))
    (alter-var-root #'datomic-mock-db component/stop)))


(def datomic-mbrainz-db
  (map->Datomic-Test
   {:schema
    [{:db/ident :country/name
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one
      :db/unique :db.unique/value
      :db/doc "The name of the country"
      }]}))


(deftest datomic-lifecycle-mbrainz-db
  (testing "Datomic lifecycle operations."
    (alter-var-root #'datomic-mbrainz-db component/start)
    (is (= (type (:conn datomic-mbrainz-db))
           datomic.peer.LocalConnection))
    (is (:conn datomic-mbrainz-db))
    (alter-var-root #'datomic-mbrainz-db component/stop)))


(def datomic-social-db
  (map->Datomic-Test
   {:schema
    [{:db/ident :story/title
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one
      :db/fulltext true
      :db/index true}
     {:db/ident :story/url
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one
      :db/unique :db.unique/identity}
     {:db/ident :story/slug
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}
     {:db/ident :comments
      :db/valueType :db.type/ref
      :db/cardinality :db.cardinality/many
      :db/isComponent true}
     {:db/ident :comment/body
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}
     {:db/ident :comment/author
      :db/valueType :db.type/ref
      :db/cardinality :db.cardinality/one}
     {:db/ident :user/firstName
      :db/index true
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}
     {:db/ident :user/lastName
      :db/index true
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}
     {:db/ident :user/email
      :db/index true
      :db/unique :db.unique/identity
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}
     {:db/ident :user/passwordHash
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}
     {:db/ident :user/upVotes
      :db/valueType :db.type/ref
      :db/cardinality :db.cardinality/many}
     ;; publish time
     {:db/ident :publish/at
      :db/valueType :db.type/instant
      :db/cardinality :db.cardinality/one
      :db/index true}]}))

(def domain (gen/elements ["gmail.com" "hotmail.com" "computer.org"]))
(def email-gen
  (gen/fmap (fn [[name domain-name]]
              (str name "@" domain-name))
            (gen/tuple (gen/not-empty gen/string-alphanumeric) domain)))


(defn gen-users-with-upvotes
  "Make transaction data for example users, possibly with upvotes"
  [n]
  (mapcat
   (fn [email stories]
     (conj (mapv (fn [story]
                   {:db/id story
                    :story/title story})
                 stories)
            {:user/email email
             :user/upVotes stories}))
   (take n (gen/sample email-gen))
   (take n (repeat  (take 10 (gen/sample (gen/not-empty gen/string-alpha-numeric)))))))


(deftest datomic-lifecycle-mbrainz-db
  (testing "Datomic lifecycle operations."
    (alter-var-root #'datomic-social-db component/start)
    (is (= (type (:conn datomic-social-db))
           datomic.peer.LocalConnection))
    (is (:conn datomic-social-db))
    (is (= "Teach Yourself Programming in Ten Years"
           (:story/title
            (d/entity
             (:db-after
              @(d/transact (:conn datomic-social-db)
                           [{:story/title "Teach Yourself Programming in Ten Years"
                             :story/url "http://norvig.com/21-days.html"}
                            { :story/title "Clojure Rationale"
                             :story/url "http://clojure.org/rationale"}
                            {:story/title "Beating the Averages"
                             :story/url "http://www.paulgraham.com/avg.html"}
                            { :user/firstName "Stu"
                             :user/lastName "Halloway"
                             :user/email "stuarthalloway@datomic.com"}
                            {:user/firstName "Ed"
                             :user/lastName "Itor"
                             :user/email "editor@example.com"}]))
             [:story/url "http://norvig.com/21-days.html"]))))

    (is (=  (count (d/q '[:find [?e ...]
                          :where [?e :story/url]]
                        (d/db (:conn datomic-social-db))))
            3))
    (is (= "john"
           (->> (conj
                 (mapv
                  (fn [story] [:db/add "john"  :user/upVotes story])
                  (d/q '[:find [?e ...]
                         :where [?e :story/url]]
                       (d/db (:conn datomic-social-db))))

                 {:db/id "john"
                  :user/email "john@example.com"
                  :user/firstName "john"
                  :user/lastName "Doe"})
                (d/transact (:conn datomic-social-db))
                deref
                :db-after
                ((fn [db]
                   (d/entity
                    db
                    [:user/email "john@example.com"])))
                :user/firstName)))

    (is (= (:user/firstName (d/entity
                             (:db-after
                              @(d/transact
                                (:conn datomic-social-db)
                                [{:user/email "john@example.com"
                                  ;; this finds the existing entity
                                  :user/firstName "Johnathan2"}]))
                             [:user/email "john@example.com"]))
           "Johnathan2"))
    (is (= 3 (count (:user/upVotes (d/entity (d/db (:conn datomic-social-db))
                                           [:user/email "john@example.com"])))))

  (is (= 2
         (count (:user/upVotes
                 (d/entity
                  (->>
                   (d/transact
                    (:conn datomic-social-db)
                    [[:db/retract [:user/email "john@example.com"] :user/upVotes
                      (d/q '[:find ?story .
                             :in $ ?e
                             :where [?e :user/upVotes ?story]
                             [?story :story/url "http://www.paulgraham.com/avg.html"]]
                           (d/db (:conn datomic-social-db))
                           [:user/email "john@example.com"])]])
                   deref
                   :db-after)
                  [:user/email "john@example.com"])))))

  (is  (= 2 (count (d/q '[:find ?u :where
                          [?e :user/email "john@example.com"]
                          [?e :user/upVotes ?v]
                          [?v :story/url ?u]]
                        (d/db (:conn datomic-social-db))))))
  (is (= nil (:user/upVotes
              (d/entity
               (:db-after
                @(d/transact (:conn datomic-social-db)
                             (->> (d/q '[:find ?op ?e ?a ?v
                                         :in $ ?op ?e ?a
                                         :where [?e ?a ?v]]
                                       (d/db (:conn datomic-social-db))
                                       :db/retract
                                       [:user/email "john@example.com"]
                                       :user/upVotes)
                                  (into []))
                             ))
               [:user/email "john@example.com"]))))
  @(d/transact (:conn datomic-social-db) (gen-users-with-upvotes 10))
  (is (= (count (d/q '[:find ?id :where
                       [?id :user/email]]
                     (d/db (:conn datomic-social-db))))
         13))
  (alter-var-root #'datomic-social-db component/stop)))
