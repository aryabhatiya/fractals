(ns fractals.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [rum.core :as rum]
            [fractals.svg :as svg]
            [secretary.core :as secretary]
            [clojure.string :as str]
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


(rum/defc element [i area bcolor color]
  [:div {:key (str "lyout" i)
         :style {
                 :grid-area area
                 :background-color bcolor
                 :color color
                 :font-size "26px"
                 :display :flex
                 :align-items :center
                 :justify-content :center}}
   (str i)])

(rum/defc board [i area bcolor color]
  [:div {:key (str "lyout" i)
         :style {
                 :grid-area area
                 :background-color bcolor
                 :color color
                 :display :grid
                 :grid-template-columns (str/join " " (repeat 8 "1fr"))
                 :grid-gap "1px"
                 }}
   (map-indexed (fn [j e]
                  [:div { :style {:display :flex
                                 :justify-content :center
                                 :background-color "#4E9A5D"
                                 :align-items :center}} (str i " " j)])
        (range 0 64))])

(rum/defc element-link [i area bcolor color]
  [:div {:key (str "lyout" i)
         :style {
                 :grid-area area
                 :background-color bcolor
                 :color color
                 :font-size "1.2rem"
                 :display :flex
                 :flex-direction :column
                 }}
   [:a {:href "#/about"} "Résumé"]
   [:a {:href "#/svg"} "svg test page"]])

(def rgba #(str "rgba"  "(" (str %1) "," (str %2) "," (str  %3) "," (str %4) ")" ))
(def rgb #(str "rgb"  "(" (str %1) "," (str %2) "," (str  %3) ")" ))
(def color (fn ([c]
                (if (keyword? c) (str "#" (name c))))
             ([r g b]
              (rgb r g b))
             ([r g b a]
              (rgba r g b a))))

(defn font [size & fonts]
  (str size " " (str/join ", " fonts)))

(rum/defc layout []
  (let [colors
        ["#105B63"
         "#FFD34E"
         "#DB9E36"
         "#BD4932"
         "#FF6138"
         "#FFFF9D"
         "#BEEB9F"
         "#79BD8F"
         "#00A388"
         "#105B63"]
        font-colors
        ["#7F7F7F"
         "#404040"
         "#333333"
         "#BEA797"
         "#D9C9BA"
         "#F1F3F5"
         "#4B4D5A"
         "#686872"
         "#BFBFBF"
         "#333333"]]
   [:div
    {:style {:display :grid
             :background-color "#FFFAD5"
             :grid-template-columns (str/join " " (repeat 8 "1fr"))
             :grid-template-rows (str/join " " (repeat 5 "19.8vh"))
             :font (font "1em/1.4"
                         "Roboto" "Helvetica Neue"
                         "Helvetica", "Arial", "sans-serif")
             ;; grid-auto-rows
             :grid-gap ".2vh"}}
    (map-indexed
     (fn [i [bcolor color area component]]
       (rum/with-key
         (component i area bcolor color)
         (str "compoent-" i))
       )
     (take 5 (map (fn [u1 v1 size w h call-back]
                    [u1 v1 (clojure.string/join
                            " / "
                            [(inc (- h size))
                             (inc (- w size))
                             (inc h)
                             (inc w) ])
                     call-back])
                  (cycle colors)
                  (cycle font-colors)
                  (cycle [   5 3  2 1 1 ])
                  (cycle [   8 3  2 3 3 ])
                  (cycle [   5 5  2 1 0 ])
                  (cycle [board element element element-link element]))))]))


(rum/defc greeting < rum/reactive [state]
  [:div {:style {:display :grid
                 }}
   (layout)
   ])


;; font-family: 'Slabo 27px', serif;
;; font-family: 'Merriweather', serif;

(rum/defc about < rum/reactive [state]
  [:div {:style {:font "1em/1.4 Roboto, \"Helvetica Neue\", Helvetica, Arial, sans-serif"
                 :margin "0 auto"
                 :width "90%"
                 :backgroundColor "#fafafa"
                 :max-width "900px"
                 :display :grid
                 :grid-template-columns "1fr 3fr"
                 :grid-gap "20px"}}
   [:div {:style {:grid-column 1
                  :color "#fff"
                  :font-size "1.4rem"
                  :padding "15px"
                  :backgroundColor "#333"}}
    [:div {:style {:font-size "2.5rem"
                   :font-family "'Slabo 27px', serif"
                   :text-align :center
                   :padding "10px"}} "MD. ASHIK"]
    (svg/cover-photo)

    [:div {:style {:background-color :white
                   :height "1px"}
           } ]
    [:div {:style {:text-align :center}} "+8801717018083"]
    [:div {:style {:background-color :white
                   :height "1px"}
           } ]
    [:div {:style {:text-align :center
                   :font-size "12px"}} "aryabhatiya.algebra@gmail.com"]
    [:div {:style {:background-color :white
                   :height "1px"}
           } ]
    [:div {:style {:padding-top "50px"
                   :font-size "1.3rem"
                   :line-height "1.1rem"
                   :font-family "'Slabo 27px', serif"
                   }}
     [:div {:style {:display :grid
                    :grid-gap "20px"}}
      [:div
       [:span {:style {:font-size "3rem"}} "“F"]
       "ullstack developeing experience on mobile & IOT."]
      [:div "Rapid developement with great reliability"]

      [:div "Devops.. friendl" [:span {:style {:font-size "3rem"}} "y”"] ] ]]]
   [:div {:style {:font-family "'Merriweather', serif"}}
    [:div {:style {:font-size "1.4rem"
                   :color "#fff"
                   :padding "10px"
                   :background-color "rgb(41,73,130)"
                   :font-weight 500}}
     "Reference Projects & Responsibilities"]
    [:div {:style {:padding "10px"
                   :background-color "#fff"
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}

     [:div {:style { :font-weight 600
                    :font-size "1rem"
                    :padding "10px"
                    }}
      "Swapno Re-seller based prepaid Billing system"]
     [:div {:style {:display :flex
                    :justify-content :space-around
                    :fond-size "1.1rem"}}
      [:div
       [:div {:style {:font-size "1.1rem"
                      :padding-top "10px"}}
        "Technical Lead"]
       [:div {:style {:padding "10px"
                      :color "#666"
                      :font-size ".85rem"}}
        "Joined May 2012"]]
      [:div {:style {:background-color "#185E65"
                     :color "#BFBFBF"
                     :display :flex
                     :flex-direction :column
                     :justify-content :center
                     :align-items :center
                     :padding "10px"
                     :font-size "1rem"}}
       [:div  "Software Global Consultancy"]
       [:div  "SGC GLOBAL"]]

      ]
     ]
    [:div {:style {:padding "20px"
                   :font-size "1.2rem"
                   :color "#003399"
                   :background-color "#D9A648"
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}

     [:span {:style {:font-size "3rem"
                     :padding-right "7px"}} "I"]
     "started this project from the scratch."
     [:div
      "Key responsibilities and challenges were design"
      "and developement
 fault tolerant Prepaid Billing Platform. Developing graphical interface for Web and Mobile. Distributed database design
   and Query optimizations and System Automation & Testing.
" ]
     ]

    [:div {:style {:font-size "1.2rem"
                   :padding "10px"
                   :background-color "#7D889C"
                   :font-weight 500}}
     [:span {:style {:font-size "1.8rem"}} "T"]"echnology Stack"]
    [:div {:style {:padding "10px"
                   :display :grid
                   :grid-template-columns "1fr 1fr 1fr 1fr"
                   :grid-gap "5px"
                   :background-color "#fff"
                   }}
     (map-indexed
      (fn [i tech]
        [:div {:key (str "map-" i)
               :style {:text-align :center
                       :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
         tech])
      [[:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} "Web Developement"]
        [:div "Streaming"]
        [:div "Websocket"]
        [:div "GraphQL"]
        [:div "Rest"]
        ]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} "Web Programming"]
        [:div "Python"]
        [:div "Flask"]
        [:div "gEvent"]
        [:div "nodejs"]]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} "Realtime Analytics"]
        [:div "SQL Alcalamy"]
        [:div "Datalog"]
        [:div "Map Ruduce"]]
       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} "Python  Microservices"]
        [:div "ZeroMQ"]
        [:div "Erlang Actor"]
        [:div "Java, Clojure"]]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4A3356"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color :white
                       :background-color "#12808D"}} "Single Page APP (SPA)"]
        [:div "ReactJs"]
        [:div "Redux"]
        [:div "VueJS"]
        ]
       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4A3356"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color :white
                       :background-color "#12808D"}} "Android Hybrid APP"]
        [:div "Kotlin"]
        [:div "React Native"]
        [:div "Cordova"]]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4A3356"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color :white
                       :background-color "#12808D"}} "Iphone Hybrid APP"]
        [:div "Objective C"]
        [:div "React Native"]
        [:div "Cordova"]]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4A3356"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "12px"
                       :color :white
                       :background-color "#12808D"}} "Info Graphics"]
        [:div "HTML5/CSS3"]
        [:div "SVG/D3"]
        [:div "Spring Animation"]]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} "Relational Database"]
        [:div "Datalog"]
        [:div "SQL"]
        [:div "PostgreSQL"]
        [:div "Datomic"]
        ]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} "NO-SQL Database"]
        [:div "Map Reduce"]
        [:div "Raik"]
        [:div "Radis"]]

       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} " Linux Devops Automation"]
        [:div "Dockes"]
        [:div "Shell"]
        [:div "Ansiable"]
        [:div "ngnix"]]
       [:div {:style {:display :flex
                      :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      :align-items :flex-start
                      :flex-direction :column}}
        [:div {:style {:padding "7px"
                       :color "#4B4D5A"
                       :background-color "#7D889C"}} "Automated Testing"]
        [:div "Property based"]
        [:div "Generative"
         ]
        [:div "Testing"]
        [:div "CircleCi"]]
       ])]

    [:a {:href "#/"} "home page"]]])




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
