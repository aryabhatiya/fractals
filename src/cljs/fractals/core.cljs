(ns fractals.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [rum.core :as rum]
            [fractals.svg :as svg]
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


(rum/defc greeting < rum/reactive [state]
  [:div {:style {:display :flex
                 :flex-direction :column}}
   [:h1 (:text (rum/react state))]
   [:a {:href "#/about"} "about page"]
   [:a {:href "#/svg"} "svg test page"]])

(rum/defc about < rum/reactive [state]
  [:div [:h1 "About Page"]
   [:a {:href "#/"} "home page"]])




(rum/defc current-page < rum/reactive [state]
  (cond
    (= (:page (rum/react state)) :home) (greeting state)
    (= (:page (rum/react state)) :svg) (svg/svg-test state)
    (= (:page (rum/react state)) :about) (about state)))


(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))

(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (swap! app-state assoc :page :home))

  (defroute "/about" []
    (swap! app-state assoc :page :about))

  (defroute "/svg" []
    (swap! app-state assoc :page :svg))

  ;; add routes here


  (hook-browser-navigation!))

(defn reset []
  (rum/mount (current-page app-state) (. js/document (getElementById "app"))))

(defn ^:export main []
  (dev-setup)
  (app-routes)
  (reset))
