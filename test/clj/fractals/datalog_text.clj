(ns fractals.datalog-test
  (:require
   [clojure.test :refer :all]
   [com.stuartsierra.component :as component]
   [system.components.datomic :refer [new-datomic-db]]
   [datomic.api :as d]
   [clojure.pprint :refer [pprint]]))

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
