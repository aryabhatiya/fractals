(ns fractals.about
  (:require
   [rum.core :as rum]
   [fractals.svg :as svg]))

(rum/defcs r-link2 < (rum/local "#333333" ::cl ) (rum/local "#F1F3F5" ::bg)
  [state link name fw align]
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
                 :justify-content align
                 :align-items :center
                 :text-decoration :none
                 :font-size fw
                 :background-color @local-backgroud}
         :href link} name]))


(rum/defc first-site < rum/reactive [state wsize]
  (let [fw (if (= wsize :swall) "1rem" "1.4rem")
        align (if (= wsize :swall) :left :center)]
    [:div {:style {:grid-column 1
                   :color "#fff"
                   :font-size fw
                   :padding "15px"
                   :backgroundColor "#333"}}
     (r-link2 "#/about"  "Show Demo" fw align)
     [:div {:style {:font-size (if (= wsize :swall) "1.4rem" "2.5rem")
                    :font-family "'Slabo 27px', serif"
                    :text-align align
                    :padding "10px"}} "MD. ASHIK"]
     (svg/cover-photo)

     [:div {:style {:background-color :white
                    :height "1px"}
            } ]
     [:div {:style {:text-align align}} "+8801717018083"]
     [:div {:style {:background-color :white
                    :height "1px"}
            }]
     [:div {:style {:text-align align
                    :font-size "12px"}} "aryabhatiya.algebra@gmail.com"]
     [:div {:style {:background-color :white
                    :height "1px"}
            } ]
     [:div {:style {:padding-top "50px"
                    :font-size fw
                    :line-height "1.1rem"
                    :font-family "'Slabo 27px', serif"
                    }}
      [:div {:style {:display :grid
                     :grid-gap "20px"}}
       [:div
        [:span {:style {:font-size "3rem"}} "“F"]
        "ullstack developeing experience on mobile & IOT."]
       [:div "Rapid developement with great reliability"]

       [:div "Devops.. friendl" [:span {:style {:font-size "3rem"}} "y”"] ] ]]]))

(rum/defc right-site [state]
  [:div {:style {:color "#fff"
                  :font-size "1.4rem"
                  :padding "15px"
                  :backgroundColor "#333"}}
    [:div
     [:input {:style {:background-color "#C6CEBE"
                      :height "25px"
                      :width "175px"}
              :type "text"
              :placeholder "New task"}]
     [:input {:type "text"
              :style {:background-color "#C6CEBE"
                      :height "25px"
                      :width "175px"}
              :placeholder "Project"}]
     [:input {:type "text"
              :style {:background-color "#C6CEBE"
                      :height "25px"
                      :width "175px"}
              :placeholder "Tags"}]
     [:input {:type "text"
              :style {:background-color "#C6CEBE"
                      :height "25px"
                      :width "175px"}
              :placeholder "Due data"}]
     [:input {:style {:background-color "#C6CEBE"
                      :height "25px"
                      :width "175px"}
              :type "text"
              :placeholder "Add task"}]
     [:input {:style {:color "#fff"
                      :background-color "#4A3356"
                      :margin-top "5px"
                      :font-size "1.2rem"
                      :height "35px"
                      :width "178px"}
              :type "submit" :value "Add Task"}]]])

(rum/defc middle-site < rum/reactive [state]
  (let [wsize (get-in (rum/react state) [:window :layout])
        fw (if (= wsize :swall) "1rem" "1.4rem")
        fw-small (if (= wsize :swall) "0.7rem" "1rem")]
    [:div {:style {:font-family "'Merriweather', serif"}}
     [:div  (cond
              (= wsize :swall) [:div
                                (first-site state wsize)
                                (right-site state)]
              (= wsize :medium) (right-site state))]
    [:div {:style {:font-size fw
                   :color "#fff"
                   :padding "10px"
                   :background-color "rgb(41,73,130)"
                   :font-weight 500}}
     "Reference Projects & Responsibilities"]
    [:div {:style {:padding "10px"
                   :background-color "#fff"
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}

     [:div {:style {:font-weight 600
                    :font-size fw-small
                    :padding "10px"
                    }}
      "Swapno Re-seller based prepaid Billing system"]
     [:div {:style {:display :flex
                    :flex-direction (if (= wsize :swall)
                                      "column"
                                      "row")
                    :justify-content :space-around
                    :fond-size fw-small}}
      [:div
       [:div {:style {:font-size fw-small
                      :padding-top "10px"}}
        "Senior Software Engineer"]
       [:div {:style {:padding "10px"
                      :color "#666"
                      :font-size fw-small}}
        "Joined May 2012"]]
      [:div {:style {:background-color "#185E65"
                     :color "#BFBFBF"
                     :display :flex
                     :flex-direction :column
                     :justify-content :center
                     :align-items :center
                     :padding "10px"
                     :font-size fw-small}}
       [:a {:style {:color "#BFBFBF"
                    :text-decoration :none}
            :href "http://sgcsoft.net/"}  "Software Global Consultancy"]
       [:div  "SGC GLOBAL"]]

      ]
     ]
    [:div {:style {:padding "20px"
                   :font-size fw-small
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
                   :grid-template-columns (if (= wsize :swall) "1fr 1fr" "1fr 1fr 1fr 1fr")
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
    [:div {:style {:font-size "1.4rem"
                   :color "#fff"
                   :padding "10px"
                   :background-color "rgb(41,73,130)"
                   :font-weight 500}}
     "Education & Recommendations"]
    [:div
     [:div {:style {:padding "10px"
                    :font-size ".86rem"}} "I have my BSC  in Computer Engineering from American International University - Bangladesh."]
     [:div {:style {:padding "10px"
                    :font-size ".86rem"}} "I was programming contest in national lavel and
   My final project was - Design 32-bit processor using MISPs
  instruction on Xlink FPGA"]
     ]
    [:div {:style {:padding "10px"
                   :display :flex
                   :justify-content :space-around}}
     [:div {:style {:padding "10px"
                    :color "#4B4D5A"
                    :background-color "#7D889C"}}
      [:div "M. Mamunuzzaman"]
      [:div "CEO SGC Global"]
      [:div "mamunuzzaman@sgcglobal.com"]]
     [:div {:style {:padding "10px"
                    :background-color "#4A3356"
                    :color "#D9C9BA"}}
      [:div "Shama Ar Rashid"]
      [:div "Director Engineering, SGC Global"]
      [:div "shama.rashid@gmail.com"]]]
    [:div {:style {:padding "10px"
                   :font-size ".86rem"}} "I was born in Dhaka in 20th July 1986. I spend my childhood in many corners of Bangladesh. My father MD Gulam Kibria and My mother is Asia Akter, we are from district Netrokuna. I am married to Rezwanna Sharmin. We have two daughters"]]))

(rum/defc about < rum/reactive [state]
  (let [wsize (get-in (rum/react state) [:window :layout])]
    [:div {:style {:font "1em/1.4 Roboto, \"Helvetica Neue\", Helvetica, Arial, sans-serif"
                   :margin "0 auto"
                   :width "100%"
                   :backgroundColor "#fafafa"
                   :display :grid
                   :grid-template-columns (cond
                                            (= wsize :wide) "1fr 3fr 1fr"
                                            (= wsize :medium) "1fr 3fr"
                                            (= wsize :small) "1fr")
                   :grid-gap "20px"}}
     (if (or (= wsize :wide) (= wsize :medium))
       (first-site state))
     (middle-site state)
     (if (=  wsize :wide )
       (right-site state))
     ]))
