(ns fractals.board
  (:require [rum.core :as rum]
            [clojure.string :as str]
            [fractals.svg :as svg]))


(defn siblings [state [x y]]
  (let [dx [  0 0 -1 1]
        dy [ -1 1  0 0]
        dxy (mapv (fn [x1 y1] [(+ x x1)
                               (+ y y1)] ) dx dy)
        valid-dxy (filterv (fn [[x2 y2]]
                             (and (>= x2 0)
                                  (>= y2 0)
                                  (< x2 (:rows state))
                                  (< y2 (:cols state)))) dxy)
        ]
    (filterv (fn [[x3 y3]]
               (get-in state [:board x3 y3]))
             valid-dxy)))

(defn init-field [state]
  (let [rows (:rows @state)
        cols (:cols @state)
        board (:board @state)
        xy (for [x (range 0 rows)
                 y (range 0 cols)]
             [x y])
        max2 (range 0 (* rows cols))
        max-field (reduce (fn [r [x y v]]
                            (assoc-in r [x y] v))
                          board
                          (map (fn [[x y] m]
                                 [x y m])  xy max2))]
    (reduce (fn [r [x y]]
              (assoc-in r [x y]
                        (if (get-in @state [:board x y])
                          (reduce
                           (fn [min1 [sx1 sy1]]
                             (if (< (get-in r [sx1 sy1])
                                    min1)
                               (get-in r [sx1 sy1])
                               min1))
                           (get-in r [x y])
                           (siblings @state [x y])) -1)))
            max-field xy)))

(defn init-board [state]
  (let [rows (:rows @state)
        cols (:cols @state)]
    (vec (take rows (repeat (vec (take cols (repeat false))))))))

(rum/defc element [state i area bcolor color]
  [:div {:key (str "lyout" i)
         :style {
                 :grid-area area
                 :background-color bcolor
                 :color color
                 :font-size "2rem"
                 :display :flex
                 :align-items :center
                 :justify-content :center}}
   (str i)])




(rum/defc control < rum/reactive [state i area bcolor color]
  [:div {:key (str "lyout" i)
         :style {
                 :grid-area area
                 :background-color bcolor
                 :color color
                 :font-size "26px"
                 :display :flex
                 :align-items :center
                 :flex-direction :column
                 :justify-content :center}}
   [:div {:style {:display :flex}}
    [:div {:style {:background-color "#D9C9BA"}
           :on-click #(do
                        (swap! state update-in [:rows] dec)
                        (swap! state assoc :board (init-board state)))}
     (svg/logo-lt)]
    [:input  {:type "text"
              :style {:width "80px"
                      :height "40px"
                      :text-align :center
                      :font-size "2rem"}
              :value (:rows (rum/react state))}]
    [:div {:style {:background-color "#D9C9BA"}
           :on-click #(do (swap! state update-in [:rows] inc)
                          (swap! state assoc :board (init-board state)))}
     (svg/logo-gt)]]
   [:div {:style {:display :flex}}
    [:div {:style {:background-color "#D9C9BA"}
           :on-click #(do
                        (swap! state update-in [:cols] dec)
                        (swap! state assoc :board (init-board state)))}
     (svg/logo-lt)]
    [:input  {:type "text"
              :style {:width "80px"
                      :height "40px"
                      :text-align :center
                      :font-size "2rem"}
              :value (:cols (rum/react state))}]
    [:div {:style {:background-color "#D9C9BA"}
           :on-click #(do
                        (swap! state update-in [:cols] inc)
                        (swap! state assoc :board (init-board state)))}
     (svg/logo-gt)]]])



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

(rum/defcs r-link < (rum/local "#333333" ::bg ) (rum/local "#F1F3F5" ::cl)
  [state link name]
  (let [local-backgroud (::bg state)
        local-color (::cl state)]
    [:a {:on-mouse-enter #(do
                            (reset! local-color "#333333")
                            (reset! local-backgroud "#F1F3F5"))
         :on-mouse-leave #(do
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

(rum/defc element-link [state i area bcolor color]
  [:div {:key (str "lyout" i)
         :style {
                 :grid-area area
                 :background-color bcolor
                 :color color
                 :font-size "1.2rem"
                 :display :grid
                 :grid-gap "2px"
                 }}
   (r-link "#/about" "Résumé" )
   (r-link "#/svg" "Login" )
   ])

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
                  (cycle [board control element element-link element]))))]))
