(ns fractals.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [datascript.core :as d]
   [rum.core :as rum]
   [fractals.svg :as svg]
   [fractals.about :as about]
   [fractals.board :as board]
   [secretary.core :as secretary]
   [clojure.string :as str]
   [goog.events :as events]
   [cljs.reader :as reader]
   [goog.history.EventType :as EventType]))

(enable-console-print!)

(def schema {:todo/tags {:db/cardinality :db.cardinality/many}
             :todo/project {:db/valueType :db.type/ref}})
(defonce conn (d/create-conn schema))


(defonce app-state (atom {:text "Hello Chestnut!"
                          :zero-col "#4E9A5D"
                          :one-col "#88F9D4"
                          :rows 3
                          :cols 4
                          :res 0
                          :board [[true false false true]
                                  [false true false true]
                                  [false true false false]]}))

(def ws (js/WebSocket. (str "ws://" js/location.hostname ":" js/location.port "/ws" )))

(aset ws "onopen" (fn [event]
                    (.send ws "hello world")))
(aset ws "onmessage" (fn [event]
                       (swap! app-state assoc :ws-text (.-data event))))


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
;; (board/layout state)

;; font-family: 'Slabo 27px', serif;
;; font-family: 'Merriweather', serif;

(rum/defc current-page < rum/reactive [state]
  (cond
    (= (:page (rum/react state)) :home) (about/about state)
    (= (:page (rum/react state)) :svg) (svg/svg-test state)
    (= (:page (rum/react state)) :about) (greeting state)))


(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))


(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (d/transact! conn [{:page/current :root}]))

  (defroute "/about" []
    (d/transact! conn [{:page/current :about}]))

  (defroute "/svg" []
    (d/transact! conn [{:page/current :svg}]))

  ;; add routes here


  (hook-browser-navigation!))

(defn reset []
  (rum/mount (current-page app-state) (. js/document (getElementById "app"))))

;; (d/listen! conn :render
;;            (fn [tx-report]
;;              (reset)))

(defn ^:export main []
  (dev-setup)
  (app-routes)
  (reset))
