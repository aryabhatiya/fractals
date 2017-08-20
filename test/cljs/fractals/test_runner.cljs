(ns fractals.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [fractals.core-test]
   [fractals.common-test]))

(enable-console-print!)

(doo-tests 'fractals.core-test
           'fractals.common-test)
