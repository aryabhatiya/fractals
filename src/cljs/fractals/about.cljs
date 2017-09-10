(ns fractals.about
  (:require [rum.core :as rum]
            [fractals.svg :as svg]))

(rum/defcs r-link2 < (rum/local "#333333" ::cl ) (rum/local "#F1F3F5" ::bg)
  [state link name]
  (let [local-backgroud (::bg state)
        local-color (::cl state)]
    [:a {:on-mouse-leave #(do
                            (reset! local-color "#333333")
                            (reset! local-backgroud "#F1F3F5"))
         :on-mouse-enter #(do
                            (reset! local-color "#F1F3F5")
                            (reset! local-backgroud "#333333"))
         :style {:display :flex
                 :color @local-color
                 :justify-content :center
                 :align-items :center
                 :text-decoration :none
                 :font-size "1.4rem"
                 :background-color @local-backgroud}
         :href link} name]))

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
    (r-link2 "#/"  "Show Demo" )
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

    ]])
