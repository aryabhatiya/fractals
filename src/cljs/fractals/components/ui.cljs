(ns fractals.components.ui
  (:require [com.stuartsierra.component :as component]
            [fractals.core :refer [render app-routes]]))

(defrecord UIComponent []
  component/Lifecycle
  (start [component]
    (render)
    component)
  (stop [component]
    component))

(defrecord UIRoute []
  component/Lifecycle
  (start [component]
    (app-routes)
    component)
  (stop [component]
    component))


(defn new-ui-component []
  (map->UIComponent {}))

(defn new-ui-route []
  (map->UIRoute {}))
