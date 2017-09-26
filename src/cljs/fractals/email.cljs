(ns fractals.email
  (:require
   [rum.core :as rum]))



(rum/defc first-site < rum/reactive [state wsize]
  (let [fw "1.4rem"
        align  :center]
    [:div {:style { :color "#fff"
                   :font-size fw
                   :padding "15px"
                   :backgroundColor "#586473"}}
     [:table
      [:tbody
       [:tr
        [:td [:div {:style {:width "50px"}}]]
        [:td
         ;; (svg/cover-photo)
         [:img {:style {:height "200px"}
                :src "https://i.pinimg.com/originals/f1/17/0f/f1170fa396df962b203fff09d002529e.png"}]
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
                } ]]
        [:td [:div {:style {:width "50px"}}]]
        [:td
         [:div "Résumé"]
         [:div {:style {:padding-top "25px"
                        :font-size fw
                        :line-height "1.1rem"
                        :font-family "'Slabo 27px', serif"
                        }}
          [:div {:style {:font-size "2.5rem"
                         :font-family "'Slabo 27px', serif"
                         :padding "10px"}} "MD. ASHIK"]
          [:br]
          [:br]
          [:div
           [:div
            [:span {:style {:font-size "3rem"}} "“F"]
            "ullstack developeing experience on mobile & IOT."]
           [:div "Rapid developement with great reliability Devops.. friendl"
            [:span {:style {:font-size "3rem"}} "y”"]]

           ]]
         [:div {:style  {:padding-top "50px"
                         :font-size fw
                         :line-height "1.1rem"
                         :text-align :right
                         :font-family "'Slabo 27px', serif"
                         }} "H# 192, R# 5, Mirpur DOHS, Dhaka - 1216"]]
        ]]]]))


(rum/defc middle-site [state]
  (let [fw "1.4rem"
        fw-small "1rem"]
    [:div {:style {:font-family "'Merriweather', serif"}}

    [:div {:style {:font-size fw
                   :color "#fff"
                   :padding "10px"
                   :text-align :center
                   :background-color "#21374B"
                   :font-weight 500}}
     "Reference Projects & Responsibilities"]

     [:table
      [:tbody
       [:tr
        [:td [:div {:style {:font-weight 600
                            :font-size fw-small
                            :text-align :center
                            :padding "10px"}}
              "Senior Software Engineer"] ]
        [:td [:div {:style {:font-weight 600
                            :font-size fw-small
                            :padding "10px"
                            }}
              "Swapno Re-seller based prepaid Billing system"]]]
       [:tr
        [:td
         [:div {:style {:fond-size fw-small
                        :width "250px"
                        :padding "10px"
                        :background-color "#fff"
                        :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
          [:div {:style {:padding "10px"
                         :color "#666"
                         :font-size fw-small}}
           "Joined May 2012"]
          [:div {:style {
                         :background-color "#185E65"
                         :color "#BFBFBF"
                         :padding "10px"
                         :font-size fw-small}}
           [:a {:style {:color "#BFBFBF"
                        :text-decoration :none}
                :href "http://sgcsoft.net/"}  "Software Global Consultancy"]
           [:div {:style {:text-align :center}} [:span {:style {:font-weight 600
                                                                :font-size "1.5rem"}} "// " ]
            " SGC GLOBAL"]]]]
        [:td

         [:div {:style {:padding "15px"
                        :font-size fw-small
                        :color "#003399"
                        :background-color "#D9A648"
                        :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}

          [:span {:style {:font-size "2rem"
                          :padding-right "7px"}} "I"]
          "started this project from the scratch."
          [:div
           "Key responsibilities and challenges were design"
           "and developement fault tolerant Prepaid Billing Platform.
Developing graphical interface for Web and Mobile. Distributed database design
and Query optimizations and System Automation & Testing." ]]]]
       [:tr [:td "Hello" ]
        [:td [:img {:style {:height "300px"}
                    :src "https://dragdis.blob.core.windows.net/assets/2014/03/26/f88c5ea28a1f4342879d6dd50c40403d.gif"}]]]]]




    [:div {:style {:font-size "1.2rem"
                   :padding "10px"
                   :background-color "#7D889C"
                   :font-weight 500}}
     [:span {:style {:font-size "1.8rem"}} "T"]"echnology Stack"]
    [:div {:style {:padding "10px"
                   :background-color "#fff"
                   }}
     (map-indexed
      (fn [i tech]
        [:div {:key (str "map-" i)
               :style {:text-align :center
                       :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
         tech])
      [[:div {:style {:color "#D9C9BA"
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

       [:div {:style { :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"
                      }}
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
       [:div {:style { :color "#D9C9BA"
                      :height "100%"
                      :font-size "0.85rem"
                      :background-color "#4B4D5A"
                      :padding-left "5px"

                      }}
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
                   }}
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
                   }}
     (first-site state)
     (middle-site state)
     ]))


(rum/defc email [state]
  (about state))
