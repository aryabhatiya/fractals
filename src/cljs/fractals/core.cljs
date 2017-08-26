(ns fractals.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [rum.core :as rum]
            [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Routes

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (swap! app-state assoc :page :home))

  (defroute "/about" []
    (swap! app-state assoc :page :about))

  ;; add routes here


  (hook-browser-navigation!))

(rum/defc greeting < rum/reactive [state]
  [:div
   [:h1 (:text (rum/react state))]
   [:a {:href "#/about"} "about page4"]])

(rum/defc about < rum/reactive [state]
  [:div [:h1 "About Page2"]
   [:a {:href "#/"} "home page3"]])


(rum/defc current-page < rum/reactive [state]
  (if (= (:page (rum/react state)) :about)
    (about state)
    (greeting state)))

(defn render []
  (rum/mount (current-page app-state) (. js/document (getElementById "app"))))
