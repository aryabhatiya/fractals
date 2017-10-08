(ns fractals.email
  (:require
   [rum.core :as rum]))



(rum/defc first-site-email [state]
  (let [fw "1.1rem"
        align  :center]
    [:table {:style {:color "#fff"
                     :font-size fw
                     :padding-left "15px"
                     :padding-right "15px"
                     }}
     [:tbody
      [:tr
        [:td
         [:table
          [:tbody
           [:tr [:td [:img {:style {:height "100px"}
                            :src "https://i.pinimg.com/originals/f1/17/0f/f1170fa396df962b203fff09d002529e.png"}]] ]

           [:tr [:td {:style {:text-align align
                              :font-size "1rem"}} "+8801717018083"]]
           (comment
             [:tr [:td {:style {:text-align align
                                :font-size "12px"}}
                   [:div {:style {:background-color :white
                                  :height "1px"}
                          }]
                   [:div  "aryabhatiya.algebra@gmail.com"]]])
           [:tr [:td {:style {:text-align align
                              :font-size "10px"}}
                 (comment [:div {:style {:background-color :white
                                         :height "1px"}
                                 }])
                 [:div "H# 192, R# 5, Mirpur DOHS, Dhaka - 1216"]]]
           ]]]
        [:td [:div {:style {:width "50px"}}]]
        [:td
         [:table [:tbody
                  [:tr [:td "Résumé"]]
                  [:tr [:td {:style {:font-size fw
                                     :font-family "'Slabo 27px', serif"
                                     }}
                        ]]
                  [:tr [:td {:style {:font-size "1.4rem"
                                     :font-family "'Slabo 27px', serif"
                                     :padding "10px"}} "MD. ASHIK"]]
                  [:tr [:td {:style {:line-height "1rem"}} [:table
                             [:tbody
                              [:tr [:td
                                    [:span {:style {:align-text :left
                                                    :font-size "1.4rem"}} "“F"]
                                    "ullstack developing experience on mobile & IOT."]]
                              [:tr [:td  "Devops.... friendl"
                                    [:span {:style { :font-size "1.4rem"}} "y”"]]]
                              ]]]]]]]]]]))


(rum/defc position-email [state]
  (let [fw "1rem"
        fw-small "1rem"]
    [:table {:style
             {:font-family "'Merriweather', serif"}}
     [:tbody
      [:tr [:td  [:div {:style {:font-size fw
                                :color "#fff"
                                :padding "10px"
                                :background-color "#21374B"
                                :font-weight 500}}
                  "Positions"]]
       [:td [:div {:style {:font-size fw
                           :color "#fff"
                           :padding "10px"
                           :background-color "#21374B"
                           :font-weight 500}}
             "Reference Projects"]]]
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
       [:td {:style {:fond-size fw-small
                     :width "250px"
                     :padding-left "10px"
                     :padding-right "10px"
                     :background-color "#fff"
                     :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
        [:div {:style {:padding "5px"
                       :color "#666"
                       :font-size fw-small}}
         "Joined Nov 2013"]
        [:div {:style {
                       :background-color "#185E65"
                       :color "#BFBFBF"
                       :padding "5px"
                       :font-size fw-small}}
         [:a {:style {:color "#BFBFBF"
                      :text-decoration :none}
              :href "http://sgcsoft.net/"}  "Software Global Consultancy"]
         [:div {:style {:text-align :center}} [:span {:style {:font-weight 600
                                                              :font-size "1.1rem"}} "// " ]
          " SGC GLOBAL"]]
        [:div {:style {:padding "2px"
                       :color "#666"
                       :font-size fw-small}}
         "3.5+ years"]]
       [:td

        [:table {:style {:padding "10px"
                         :font-size fw-small
                         :color "#003399"
                         :background-color "#fff"
                         :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
         [:tbody
          [:tr [:td {:style {:font-size "0.8rem"}}"I started this project from the scratch."]]
          [:tr [:td {:style {:font-size "1rem"
                             :color :black
                             :padding "4px"}} "Key responsibilities and challenges"]]
          [:tr [:td {:style {:text-align :left
                             :background-color "#181818"
                             :padding-top "7px"
                             :borderRadius "3px"
                             :font-size "14px"
                             :color "#BFBFBF"}}
                [:table
                 [:tbody
                  [:tr [:td "➟ Fault tolerant Prepaid Billing"]]
                  [:tr [:td "➟ GUI for Web and Mobile"]]
                  [:tr [:td "➟ Database and Query optimization"]]
                  [:tr [:td "➟ System Automation & Testing"]]]]]]]]]]]]))

(rum/defc position-email-gp [state]
  (let [fw "1rem"
        fw-small "1rem"]
    [:table {:style
             {:font-family "'Merriweather', serif"}}
     [:tbody
      [:tr
       [:td [:div {:style {:font-weight 600
                           :font-size fw-small
                           :text-align :center
                           :padding "10px"}}
             "System Engineer"] ]
       [:td [:div {:style {:font-weight 600
                           :font-size fw-small
                           :padding "10px"
                           }}
             "GP trouble ticketing System ‒ GPTTS"]]]
      [:tr
       [:td {:style {:fond-size fw-small
                     :width "250px"
                     :padding-left "10px"
                     :padding-right "10px"
                     :background-color "#fff"
                     :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
        [:div {:style {:padding "5px"
                       :color "#666"
                       :font-size fw-small}}
         "Joined Feb 2012"]
        [:div {:style {
                       :background-color "#87ceeb"
                       :color "#582828"
                       :padding "10px"
                       :font-size fw-small}}
         [:div {:style {:text-align :center}}
          "Grameen Phone"]]
        [:div {:style {:padding "2px"
                       :color "#666"
                       :font-size fw-small}}
         "1.5+ years"]]
       [:td

        [:table {:style {:padding "10px"
                         :font-size fw-small
                         :color "#003399"
                         :background-color "#fff"
                         :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
         [:tbody
          [:tr [:td {:style {:text-align :left
                             :background-color "#181818"
                             :padding-top "7px"
                             :borderRadius "3px"
                             :font-size "14px"
                             :color "#BFBFBF"}}
                [:table
                 [:tbody
                  [:tr [:td "Real Time Notification System from Network Alarms"]]
                  [:tr [:td "Multimedia Messaging Systems for Web & Mobile"]]
                  ]]]]]]]]]]))

(rum/defc email-iphone [state]
  [:table {:style
           {:height "250px"
            :background-color "#DDDDDD"
            :background-position "20% 80%"
            :background-size "400px 300px"
            :background-image (str "url" "("  "\"https://i.pinimg.com/originals/8c/b5/0d/8cb50d60ce244eb04907fc7924763493.gif\""  ")")
            ;; :background-image (str "url" "("  "\"https://cdn.dribbble.com/users/273461/screenshots/2513600/shopping2.gif\""  ")")
            :background-repeat :no-repeat}}
   [:tbody
    [:tr
       [:td
        [:table
         [:tbody
          [:tr
           [:td
            [:div {:style {:box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                           :opacity ".5"
                           :color "#D9C9BA"
                           :height "100%"
                           :width "150px"
                           :font-size "0.9rem"
                           :background-color "#383838"
                           :padding-left "5px"
                           :text-align :left
                           }}
             [:div {:style {:padding "7px"
                            :color :white
                            :background-color "#12808D"}} "iPhone Hybrid APP"]
             [:div "React Native"]
             [:div "RN Bridge"]
             [:div "RN Animated"]
             [:div "RN Navigation"]
             [:div "RN Touch & Gesture"]
             [:div "Objective C"]
             ;; [:div "Apple Swift"]
             ;; [:div "Offline first"]
             [:div "Yoga Layout"]
             [:div "Cocoa Touch"]
             [:div "iOS SDK"]
             [:div "Core Services"]
             [:div "Xcode"]
             [:div "Managing Memory"]
             [:div "C/C++"]
             ]]
           [:td {:style  {:box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                      :text-align :left
                      :color "#181818"
                      :padding-left "170px"}}
            [:table
             [:tbody
              [:tr [:td {:style {:padding "3px"
                             :background-color "#fff"}} "We have implemented rich user interface in iPhone for corporate users which are real time websocket based platforms connected to real time database, offline caching for airplane mode. We have adopted React Native for programming flexibility, interfacing with technologies like GraphQL and for real time programming experience."]

           (comment
             [",  navigation controllers, tab controller, split view controller which is a the center of iOS development."]
             [:tr [:td "Now a days we use Objective C for heavy lifting like Cocoa Touch and UI transition. We use swift for developing new modules. As Swift doesn’t just embrace Cocoa APIs, it actively improves them."]]
             [:tr [:td "I have mastered with Model-View-Controller pattern and View controllers pattern, view hierarchy, Containers and Storyboards,  navigation controllers, tab controller, split view controller which is a the center of iOS development. Worked closely with auto Layout, table views and the delegate pattern which display a scrollable list of items on the screen, containers and storyboards which make the flow of the interface from one screen to the next happen, Xcode and Interface Buider.
" ]]
             [:tr [:td "Cocoa is an impressively deep API—dig a little below the surface of any common tool, so far I have worked with following modules"]]
             [:tr [:td "Simple data persistence with NSCoding and NSKeyedArchiver"]]
             [:tr [:td "Object-oriented concurrent execution with NSOperation"]]
             [:tr [:td "Detection of all sorts of data with NSDataDetector"]]
             [:tr [:td "Native custom sharing and editing controls with UIActivityViewController and UIMenuController"]])]
              [:tr [:td {:style {:background-color "#fff"}} "We have mastered Objective-C's MVC, view controllers pattern, view hierarchy, containers and storyboards implementations. This leverage us to do hybrid apps where performance incentive codes are implemented in Object-C and React Native business logic."]]

              (comment [:tr [:td {:style {:background-color "#fff"}}
                             "In order to run at 60 FPS, we are implementing declarative api which will work with Native diver as The async nature of the React Native bridge incurs an inherent performance penalty, preventing JavaScript code from running at high framerates."]])
              ]]]]]]]]]])

(rum/defc technology-stack [state]
    [:table
     [:tbody
      [:tr
       [:td
        [:table {:style {:width "150px"}}
         [:tbody
          [:tr
           [:td {:style {:text-align :left
                         :color "#D9C9BA"
                         :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                         :font-size "0.85rem"
                         :background-color "#4B4D5A"
                         :padding-left "5px"
                         }}
            [:div {:style {:padding "7px"
                           :color "#4B4D5A"
                           :background-color "#7D889C"}} " Linux Devops Automation"]
            [:div "Docker"]
            [:div "Xen"]
            [:div "Vmware"]
            [:div "OpenSips"]
            [:div "STUN-TURN"]
            [:div "RDP"]
            [:div "Asterisk"]
            [:div "Virtual Box"]
            [:div "Awk"]
            [:div "Shell"]
            [:div "Ansiable"]
            [:div "ngnix"]]]
          [:tr [:td {:style { :color "#D9C9BA"
                             :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                             :text-align :left
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
                [:div "CircleCi"]]]
          ]]
        ]
       [:td

        [:table
         [:tbody
          [:tr [:td {:style {:font-weight 600
                             }} "Generative Testing The Case for Property-based Testing"]
           [:td {:style {:font-weight 600
                         :text-align :left
                         }}
            "System Automation Key to maintain software and obtain faster quality production cycle"]]
          [:tr [:td {:style {:text-align :left
                             :width "160px"
                             :padding "2px"}}
                "Unit testing is very necessary to protect
  overall integrity of the product from new change requests. Unit tests takes significant
  amount time to write test cases not to mention some time we got reluctant and cover
  every edge cases. I have introduced property base testing along with software specification
  language so that test case can be auto generated."]
           [:td {:style {:text-align :left}}
            "Finally system automation is necessary, because it is nature of human to make mistake
  and human learns from mistake. On the other hand machine breaks on error. In order
  to feel this gap and keep whole system up and running from development to production
  and maintenance we need to automate whole system with best practices and proven tactics
  and keep it out of human intervention. This is why I use to work with linux over windows
  to implement policy based devops systems and UI using Python, Javascirpt, AWK,
  Shell scripting over the cloud architecture and build cloud architecture using Xen,
  Docker and virtual desktop"]
           ]
          ]]
        (comment
          "
  Generative Testing
  The Case for Property-based Testing

  Introduction

  Generative testing is a widespread approach in the functional world, mainly due to the popularity of Haskell’s QuickCheck and Clojure’s QuickCheck implementation test.check. Traditional unit tests enable us to verify assumptions for a given function. This approach, also known as example based testing, is based on manually defined inputs and expected outputs. In other words a developer has to figure out a set of inputs/outputs to assert that the function under test is working as intended. This also means that one would have to write a large number of tests just so to be able to verify a wide range of cases.

  So while unit tests might verify a reasonable set of assumptions, you can’t manually catch all the possible cases that need to be tested. Thinking beyond a subset of expectations becomes very inefficient at some point. There’s a limitation here. This is where generative testing comes into play.

.")]]]])

(rum/defc android-email [state]
  [:table {:style {:background-color "#fff"
                   :width "100%"}}
   [:tbody
    [:tr
     [:td {:style {:box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,0.12)"
                   :text-align :left
                   :width "150px"
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#383838"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color :white
                     :background-color "#a4c639"}} "Android Hybrid APP"]
      [:div "Java"]
      [:div "Kotlin"]
      [:div "WebRTC"]
      [:div "RecyclerView"]
      [:div "Intents"]
      [:div "Preferences"]
      [:div "Lifecycle"]
      [:div "Content Providers"]
      [:div "Backgroud Tasks"]
      [:div "Android Studio3"]
      [:div "Gradle"]
      [:div "JNI NDK"]]
     [:td {:style {:text-align :left
                   :padding "2px"
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
      [:div {:style {:padding "5px"
                     :font-size "14px"
                     :font-weight "600"}} "UI Rending Optimizations"]

      "As UIs become increasingly sophisticated, it gets harder to complete all the rendering work that needs to get done in this time frame of 60 frames per second. We addresses this with RecyclerView, a dynamic UI container that is able to display elements from large data sets by creating only enough views to fill the screen and then recycling and reusing them as the UI scrolls."]
     [:td {:style {:box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :text-align :left
                   :padding "2px"}}
      [:div {:style {
                     :padding "2px"
                     :font-size "14px"
                     :font-weight "600"}}  "WebRTC based Social Network"]
      "We have implemented WebRTC based corporate instant messaging, video conferencing and voice over IP. We have worked The WebRTC specification specifies the use of STUN, TURN and ICE to support NAT traversal requirements which ensure guaranteed connectivity, highest possible P2P rates."
      ]
     (comment
       [:td {:style {:text-align :left
                     :padding "2px"}}  "RecyclerView also keeps views in different pools depending on their type. So for UIs with many different view types, the same view is less likely to be reused frequently. This means that more views are held in memory while new views are constantly being created, which affects both memory and performance. We build UI that is designed to be faster and more efficient when working with scrolling surfaces. It aims to improve performance by moving the heavy work to a background thread and spreading the rendering work across multiple frames. Decouple the layout operations from Android views. This allows us to move the CPU-intensive measure and layout operations to the background thread, saving milliseconds.

Finally, We efficiently breaks down complex views into smaller pieces such as text, images, and videos, and renders them incrementally, spreading the work that needs to be done across multiple frames. It also recycles those smaller pieces and can recombine them in a virtually infinite number of ways, reducing the total number of views that need to be created and stored in memory. These improves in scroll performance of up to 35 percent, better memory behavior with complex lists, and easier-to-test UI code."])
     ]]])

(rum/defc fullstack-email [state]
  [:table {:style {:box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :width "150px"}}
   [:tbody
    [:tr
     [:td {:style {:text-align :left
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#7D889C"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color :white
                     :background-color "#12808D"}} "Single Page APP (SPA)"]
      [:div "ReactJs"]
      [:div "Redux"]
      [:div "ClojureScript"]
      ]]
    [:tr
     [:td {:style {:text-align :left
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#7D889c"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "12px"
                     :color :white
                     :background-color "#12808D"}} "Info Graphics"]
      [:div "HTML5/CSS3"]
      [:div "SVG"]
      [:div "Inkscape"]
      [:div "Spring Animation"]]]
    [:tr
     [:td {:style {:text-align :left
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#4B4D5A"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color "#4B4D5A"
                     :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                     :background-color "#7D889C"}} "Protocols"]
      [:div "Streaming"]
      [:div "Websocket"]
      [:div "GraphQL"]
      [:div "Rest ‒ JSON"]
      ]]
    [:tr

     [:td {:style {:text-align :left
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#4B4D5A"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color "#4B4D5A"
                     :background-color "#7D889C"}} "Programming"]
      [:div "Java"]
      [:div "Nodejs"]
      [:div "Clojure"]
      [:div "Python"]]]
    [:tr
     [:td {:style {:text-align :left
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#4B4D5A"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color "#4B4D5A"
                     :background-color "#7D889C"}} "Real Time Analytics"]
      [:div "Datalog"]
      [:div "SQL"]
      [:div "GraphQL"]]]


    [:tr
     [:td {:style {:text-align :left
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :background-color "#4B4D5A"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color "#4B4D5A"
                     :background-color "#7D889C"}} "Relational Database"]
      [:div "Oracle 10i"]
      [:div "PostgreSQL"]
      [:div "Datomic"]
      ]]
    [:tr
     [:td {:style {:text-align :left
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#4B4D5A"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color "#4B4D5A"
                     :background-color "#7D889C"}} "NO-SQL Database"]
      [:div "Raik"]
      [:div "Mongo"]
      [:div "Radis"]]]
    [:tr
     [:td {:style {:text-align :left
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"
                   :color "#D9C9BA"
                   :height "100%"
                   :font-size "0.85rem"
                   :background-color "#4B4D5A"
                   :padding-left "5px"
                   }}
      [:div {:style {:padding "7px"
                     :color "#4B4D5A"
                     :background-color "#7D889C"}} "Artificial intelligence "]
      [:div "Prolog"]
      [:div "Mercury"]
      [:div "MiniKanren"]]]]])

(rum/defc devops-email [state]
  [:div "hello"])

(rum/defc iot-email [state]
  [:div "hello"])

(rum/defc web-ui [state]
  [:div "hello"])

(rum/defc fullstack-email-body [state]
  [:table {:style {:text-align :left
                   :box-shadow "0 2px 2px 0 rgba(0,0,0,.14),0 3px 1px -2px rgba(0,0,0,.2),0 1px 5px 0 rgba(0,0,0,.12)"}}
                  [:tbody
                   [:tr [:td {:style {:font-size "1.1rem"
                                      :padding "5px"}} "Interactive Web and Mobile User Interface development"]]
                   [:tr [:td {:style {:padding "2px"}} "Rapid user interfaces Development and look&feel for Web, Android, iPhone are main
  challenges which needs to be overcome. We overcome these challenges by choosing programming languages and frameworks that equally works for web, mobile development and
Backend." ]]

                   [:tr [:td {:style {:padding "2px"}}
                         "CSS, HTML5, SVG, Canvas are essential part of UI look&feel, in order to UI responsive but one need to take special care when using these
  technologies. Spatially when using CSS frameworks and JavaScript libraries often makes website bulky slower loading time and slower response time.
  We used dead code elimination techniques using google Clojure compiler so that web site never gets bulky."]]
                   [:tr [:td {:style {:padding "2px"}}
                         "Data Visualization infographics is very important part of user interface design
  its empower user so that user can understand whole business case at a glance. We develop and designing the infographics that can work equally in mobile and web."]]
                   [:tr [:td {:style {:padding "5px"
                                      :font-size "1.1rem"}}
                         "Concurrent and fault tolerant Application Development for Web and Internet of things (IOT)"]]
                   [:tr [:td {:style {:padding "2px"}}
                         "Rise of cloud infrastructure and IOT devices make us thinking of current monolithic
  application architecture twice. Estimated number of IOT devices will connect to web by 2020
  will be 5 times total population of the earth; on the other hand in order to leverage cloud
  infrastructure application needs to be concurrent, fault tolerant and distributed.
  We use to work with immutable data structure because data mutation is a serious problem
  for multi thread environment which causes deadlock and data loss and frequent crashing
  We use communication sequential processing (CPS) to effective message passing between the threads
  which can leverage thousands of threads over the cloud."]]
                   (comment [:tr [:td {:style {:padding "2px"}}
                                  "Micro services are necessary part of cloud infrastructure which leverage heterogeneous
  programming languages work together. I have implemented Micro services with java, nodes,
  python, clojure, c/c++ using by exposing Rest service and JSON data structure to
  subscriber and Message Queues for real time notification. We had tough time ensuring
  height availability and redundancy of micro services and update code one production server.
  I am working on GraphQL, datalog, logic programming,  Erlang error handling mechanism to solve
  the problems."]])
                   [:tr [:td {:style {:padding "5px"
                                      :font-size "1.1rem"}}
                         "Distributed database design and Query optimization"]]
                   [:tr [:td {:style {:padding "2px"}}
                         "I worked with Relational Databases - oracle and postgre and implemented sql quries and stored
  procedure with large set of telecommunication data witch had over ten million subscribers.
  I have found it even takes days to complete a query when data volume gets this size.
  Implementing Redis Cluster solve this problem because data stays memory use disk for persistence
  but it loose the ability to SQL over database. Finally we came out optimal data base which only only
  leverage SQL query but also can query graph database and document query at the same time efficiently
  use Redis Clusters."]]]])

(rum/defc middle-site-email [state]
  (let [fw "1.4rem"
        fw-small "1rem"]
    [:table
     [:tbody
      [:tr
       [:td
        (position-email state)]]
      [:tr
       [:td
        (position-email-gp state)]]
      [:tr [:td (email-iphone state)]]
      [:tr  [:td  (android-email)]
       ]
      [:tr
       [:td {:style {:padding "7px"
                     :font-size "1.2rem"
                     :background-color "#7D889C"
                     :font-weight 500
                     :color :white}}
        [:span {:style {:font-size "1.8rem"}} "T"]"echnology Stack"]
       ]
      [:tr
       [:td
        [:table
         [:tbody
          [:tr
           [:td (fullstack-email state)]
           [:td (fullstack-email-body state)]]
          ]]
        ]]

      [:tr
       [:td {:style {:font-size "1.2rem"
                     :background-color "#7D889C"
                     :font-weight 500
                     :color :white}}
        [:span {:style {:font-size "1.8rem"}} "T"]"echnology Stack"]
       ]
      [:tr
       [:td
        [:div {:style {:background-color "#fff"}}
         (technology-stack state)] ] ]
      [:tr
       [:td {:style {:font-size "1.4rem"
                     :color "#fff"
                     :padding "10px"
                     :background-color "rgb(41,73,130)"
                     :font-weight 500}}
        "Education & Recommendations"]]
      [:tr
       [:td
        [:div {:style {:padding "10px"
                       :font-size ".86rem"}}
         "I have my BSC  in Computer Engineering from American International University - Bangladesh. I had participated programming contests and my final project was - Design 32-bit processor using MISPs instruction on Xlink FPGA"]

        ]]
      [:tr
       [:td {:style {:padding-left "50px"}}
        [:table
         [:tbody
          [:tr {:style {:padding "10px"
                         }}
           [:td {:style {:padding "10px"
                          :color "#4B4D5A"
                          :background-color "#7D889C"}}
            [:div "M. Mamunuzzaman"]
            [:div "CEO SGC Global"]
            [:div "mamunuzzaman@sgcglobal.com"]]
           [:td {:style {:padding "10px"
                          :background-color "#4A3356"
                          :color "#D9C9BA"}}
            [:div "Shama Ar Rashid"]
            [:div "Director Engineering, SGC Global"]
            [:div "shama.rashid@gmail.com"]]]]]]]
      [:tr
       [:td
        [:div {:style {:padding "10px"
                       :font-size ".86rem"}} "I was born in Dhaka in 20th July 1986. I spend my childhood on countryside of Bangladesh. My father MD Gulam Kibria and My mother is Asia Akter, we are from district Netrokuna. I am married to Rezwanna Sharmin. We are blessed by two daughters."]]]]]))


(rum/defc email-body [state]
  [:table {:style {:border-collapse "collapse"
                   :margin "0"
                   :padding "0"
                   :height "100%"
                   :width "100%"
                   }}
   [:tbody
    [:tr [:td (first-site-email state)]]
    [:tr [:td (middle-site-email state)]]]])


(rum/defc email [state]
  [:div {:style {:height "100%"
                 :margin "0"
                 :padding "0"
                 :width "100%"}}
   [:center
    [:table {
             :cell-padding "0"
             :cell-spacing "0"
             :height "100%"
             :width "100%"
             :style {
                     :text-align "center"
                     :border-collapse "collapse"
                     :margin "0"
                     :padding "0"
                     :height "100%"
                     :width "100%"
                     :background-color "#ddd"}}
     [:tbody
      [:tr
       [:td {:style {
                     :border-top-color "#bbbbbb"
                     :border-top-style :solid
                     :border-top-width "4px"
                     :color "#878787"
                     :font-family "'Lucida Grande','Lucida Sans Unicode',Verdana,sans-serif"
                     :font-size "13px"
                     :padding "20px"
                     :width "100%"

                     }}
        [:table {:cell-padding "0"
                 :cell-spacing "0"
                 :style {
                         :border "0"
                         :border-collapse :collapse
                         :width "600px"}}
         [:tbody
          [:tr [:td {:style {:padding "10px"
                             :text-align :left
                             :background-color "rgb(41, 73, 130)"}}
                [:div {:style {:font-size "1.1rem"
                               :padding "4px"}}
                 "Hello " [:img {:style {:height "21px"}
                                 :src "http://www.logicaltriangle.com/images/company_logo.png"}]]
                [:div {:style {:fond-size "1rem"
                               :color "#f1f1f1"
                               :padding-left "5px"
                               :text-align :left}} "Please accept my Résumé for Fullstack developer – iPhone and Android"]]]

          [:tr
           [:td {:style {
                         :color "#878787"
                         :font-size "13px"
                         :font-family "'Lucida Grande','Lucida Sans Unicode',Verdana,sans-serif"}}
            [:table {:cell-padding "0"
                     :cell-spacing "0"
                     :style {:border-collapse :collapse
                             :background-color "#181818"
                             :font-size "13px"}}
             [:tbody
              [:tr [:td {:style {:text-align "center"}}
                    (email-body state)]]]]]]
          [:tr
           [:td {:style { :background-color "#F3F3F3"}}
                [:table
                 [:tbody
                  [:tr [:td [:img {:style {:height "23px"
                                           }
                                   :src "https://pbs.twimg.com/profile_images/616309728688238592/pBeeJQDQ.png"}]]
                   [:td [:a {:href "https://github.com/aryabhatiya"} "https://github.com/aryabhatiya"]]
                   (comment
                     [:td [:img {:style {:height "23px"
                                         :padding-right "3px"}
                                 :src "https://cdn1.iconfinder.com/data/icons/simple-icons/2048/heroku-2048-black.png"}]]
                     [:td [:a {:href "https://pacific-earth-90914.herokuapp.com/#/"}
                           "https://pacific-earth-90914.herokuapp.com/#/"]])]]]]]]]]]]]]])
