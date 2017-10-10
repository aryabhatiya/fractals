(ns fractals.css-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]])
  (:import [java.io StringReader]
           [com.steadystate.css.parser CSSOMParser SACParserCSS3]
           [com.steadystate.css.dom CSSStyleSheetImpl]
           [com.steadystate.css.format CSSFormat]
           [org.w3c.css.sac InputSource]
           ))

(deftest cssparse
  (let [input-source (InputSource. (StringReader. "h1 { background: #ffcc44; }"))
        parser (CSSOMParser. (SACParserCSS3. ))]
    (is (= "h1 { background: #ffcc44 }"
           (.getCssText (.parseStyleSheet parser input-source nil nil)
                        (.setRgbAsHex (CSSFormat. ) true))))
    (= (first (for [meth  (.getMethods CSSOMParser)
                    :let [name2 (.getName meth)]
                    :when (re-find #"parse" name2)]
                name2)) "parseStyleSheet")))
