(ns fractals.board
  (:require [rum.core :as rum]
            [clojure.string :as str]))

(rum/defc element [state i area bcolor color]
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

(rum/defc board < rum/reactive [state i area bcolor color]
  [:div {:key (str "lyout" i)
         :style {
                 :grid-area area
                 :background-color bcolor
                 :color color
                 :display :grid
                 :grid-template-columns (str/join " "
                                                  (repeat (:cols (rum/react state)) "1fr"))
                 :grid-gap "4px"
                 }}
   (map
    (fn [[e area]]
      (let [cell (get-in (rum/react state)
                         e)
            col (if cell (:zero-col (rum/react state))
                    (:one-col (rum/react state)))]
        [:div {:key (str  "board-" (str e))
               :on-click #(swap! state assoc-in e (not cell) )
               :style {:grid-area (str/join " / " area)
                       :display :flex
                       :justify-content :center
                       :background-color col
                       :align-items :center}}
         ]))
    (for [x (range 0 (:rows  (rum/react state)))
          y (range 0 (:cols  (rum/react state)))]
      [[:board x y] [(inc x) (inc y) (+ x 2) (+ y 2) ]]))])

(rum/defc element-link [state i area bcolor color]
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

(rum/defc layout [state]
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
         (component state i area bcolor color)
         (str "compoent-" i)))
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
