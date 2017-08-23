(ns fractals.system
  (:require [com.stuartsierra.component :as component]
            [fractals.components.ui :refer [new-ui-component new-ui-route]]))

(declare system)

(defn new-system []
  (component/system-map
   :app-route (new-ui-route)
   :app-root (new-ui-component)))

(defn init []
  (set! system (new-system)))

(defn start []
  (set! system (component/start system)))

(defn stop []
  (set! system (component/stop system)))

(defn ^:export go []
  (init)
  (start))

(defn reset []
  (stop)
  (go))
