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



(defonce app-state (atom {:text "Hello Chestnut!"
                          :zero-col "#4E9A5D"
                          :one-col "#88F9D4"
                          :rows 3
                          :cols 4
                          :res 0
                          :board [[true false false true]
                                  [false true false true]
                                  [false true false false]]}))

(def schema {:todo/tags {:db/cardinality :db.cardinality/many}
             :todo/project {:db/valueType :db.type/ref}})

(def conn (d/create-conn schema))

(d/listen! conn :test #(do
                         (js/console.log "state" (clj->js (:tx-data %)))
                         (swap! app-state assoc :report %)))


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
       (js/console.log (.-token event))
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
    (println "dev mode2")
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

(defonce layout (atom 1000))

(defn set-layout [w]
  (swap! app-state assoc-in
         [:window :layout]
         (cond
           (and  (< w 1000) (> w 600)) :medium
           (< w 600 ) :swall
           :else :wide)))

(add-watch layout ::layout
           (fn [_ _ _ w]
             (set-layout w)))

(js/window.addEventListener
 "resize"
 (fn [_]
   (reset! layout js/window.innerWidth)))




(defn reset []
  (rum/mount (current-page app-state) (. js/document (getElementById "app"))))

(defn ^:export main []
  (dev-setup)
  (app-routes)
  (set-layout app-state)
  (reset))
