(ns fractals.application
  (:gen-class)
  (:require
   [datomic.api :as d]
   [com.stuartsierra.component :as component]
            [system.components.endpoint :refer [new-endpoint]]
            [system.components.handler :refer [new-handler]]
            [system.components.middleware :refer [new-middleware]]
            [system.components.http-kit :refer [new-web-server]]
            [fractals.config :refer [config]]
            [fractals.routes :refer [home-routes]]
            [fractals.datomicdb :refer [datomic-social-db]]))

(defn app-system [config]
  (component/system-map
   :db (datomic-social-db)
   :routes    (->  (new-endpoint home-routes)
                   (component/using [:db]))
   :middleware (new-middleware {:middleware (:middleware config)})
   :handler    (-> (new-handler)
                   (component/using [:routes :middleware]))
   :http       (-> (new-web-server (:http-port config))
                   (component/using [:handler]))))

(defn -main [& _]
  (let [config (config)]
    (-> config
        app-system
        component/start)
    (println "Started fractals on" (str "http://localhost:" (:http-port config)))))
