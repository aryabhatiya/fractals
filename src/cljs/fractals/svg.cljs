(ns fractals.svg
  (:require [rum.core :as rum]
            [clojure.string :as str]))

(rum/defc logo-serial-no []
  [:div {:style {:width "35px"
                 :height "35px"}}
   [:svg {:viewBox "0 0 24 24"}
    [:path {:style {:fill :white}
            :d "M10.605 0h-10.605v10.609l13.391 13.391 10.609-10.604-13.395-13.396zm-7.02 6.415c-.781-.783-.781-2.048 0-2.829.782-.782 2.048-.781 2.829-.001.782.783.781 2.047 0 2.829-.781.781-2.046.781-2.829.001zm3.101 4.514l4.243-4.243.707.707-4.243 4.243-.707-.707zm1.414 1.414l4.243-4.243.354.354-4.243 4.243-.354-.354zm1.061 1.061l4.243-4.243 1.061 1.061-4.243 4.243-1.061-1.061zm1.768 1.768l4.243-4.243.354.354-4.243 4.243-.354-.354zm1.767 1.767l-.707-.707 4.243-4.243.707.707-4.243 4.243zm.708.707l4.242-4.242.354.354-4.242 4.242-.354-.354z"}]]])

(rum/defc logo-circle [size]
  [:div {:style {:width "35px"
                 :height "35px"}}
   [:svg {:x "0"
          :y "0"
          :viewBox "0 0 512 512"}
    [:circle {:style {:fill :gray}
              :cx "256"
              :cy "256"
              :r "256"}
     ]
    ]])


(rum/defc svg-test < rum/reactive [state]
  [:div {:style {:backgroundColor "#ddd"}} [:div {:style {:color :white}} "Svg Page"]
   (logo-serial-no)
   [:a {:href "#/"
        :style {:color :white}} "home page"]])
