(ns fractals.datomicdb
  (:require
   [datomic.api :as d]
   [com.stuartsierra.component :as component]))

(defrecord Datomic-Mem [uri conn schema]
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

(defn datomic-social-db []
  (map->Datomic-Mem
   {:schema
    [{:db/ident :resume/company
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :company/hash
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :company/things
      :db/valueType :db.type/ref
      :db/cardinality :db.cardinality/one}

     {:db/ident :resume/title
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/many
      :db/fulltext true
      :db/index true}

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
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/lastName
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/email
      :db/unique :db.unique/identity
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/dob
      :db/valueType :db.type/instant
      :db/cardinality :db.cardinality/one
      }

     {:db/ident :user/msisdn
      :db/unique :db.unique/identity
      :db/valueType :db.type/string
      :db/cardinality :db.cardinality/one}

     {:db/ident :user/upVotes
      :db/valueType :db.type/ref
      :db/cardinality :db.cardinality/many}

     ;; publish time
     {:db/ident :publish/at
      :db/valueType :db.type/instant
      :db/cardinality :db.cardinality/one}
     ]}))
