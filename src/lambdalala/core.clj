(ns lambdalala.core
  (:require
    [lambdalala.lambda :refer [deflambdafn lambda-handle]]
    [taoensso.timbre :as tlog])
  (:gen-class))

(defn do-something-important
  [{:keys [] :as input}]
  (tlog/infof "This is the input received %s" input)
  (assoc input :result :done))

(deflambdafn lambdalala.core.SuperNiceLambdaFunction
              [in out ctx]
              (lambda-handle in out do-something-important))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
