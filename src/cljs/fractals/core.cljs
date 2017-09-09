(ns fractals.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [rum.core :as rum]
            [fractals.svg :as svg]
            [fractals.about :as about]
            [fractals.board :as board]
            [secretary.core :as secretary]
            [clojure.string :as str]
            [goog.events :as events]
            [goog.history.EventType :as EventType]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"
                          :zero-col "#4E9A5D"
                          :one-col "#88F9D4"
                          :rows 3
                          :cols 4
                          :board [[true false false true]
                                  [false true false true]
                                  [false true false false]]}))


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
  (board/layout state))


;; font-family: 'Slabo 27px', serif;
;; font-family: 'Merriweather', serif;

(rum/defc current-page < rum/reactive [state]
  (cond
    (= (:page (rum/react state)) :home) (greeting state)
    (= (:page (rum/react state)) :svg) (svg/svg-test state)
    (= (:page (rum/react state)) :about) (about/about state)))


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
