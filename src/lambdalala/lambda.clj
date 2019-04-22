(ns lambdalala.lambda
  (:require [clojure.java.io :as io]
            [cheshire.core :refer :all]
            [taoensso.timbre :as tlog])
  (:import [com.amazonaws.services.lambda.runtime RequestStreamHandler Context]
            ))

(defmacro deflambdafn
  "Create a named class that can be invoked as a AWS Lambda function."
  [name args & body]
  (assert (= (count args) 3) "lambda function must have exactly three args")
  (let [prefix (gensym)
        handleRequestMethod (symbol (str prefix "handleRequest"))]
    `(do
       (gen-class
        :name ~name
        :prefix ~prefix
        :implements [RequestStreamHandler])
       (defn ~handleRequestMethod
         ~(into ['this] args)
         ~@body))))

(defn log-ctx [^Context ctx]
  (try
    (when ctx
      (let [function-name (.getFunctionName ctx)
            req-id (.getAwsRequestId ctx)
            mem (.getMemoryLimitInMB ctx)]
        (tlog/infof "[%s]Handling request (%smb)%s" function-name mem req-id)))
    (catch Throwable t (tlog/warn t))))

(defn lambda-handle
  ([in out handler]
   (lambda-handle in out nil handler))
  ([in out ctx handler & {:keys [log-input?] :or {log-input? true}}]
   (let [input (parse-stream (io/reader in) true)
         keep-alive-call? (get input :keep-alive false)
         handler-func (if keep-alive-call?
                        (fn [] (tlog/info "Keeping alive boy!")
                                {:hello :world})
                        (partial handler input)
                        )]
     (when log-input?
       (tlog/info input))
     (log-ctx ctx)
     (try
       (let [response (handler-func)]
         (generate-stream response
                          (io/writer out)))
       (catch Throwable t
         (do
           (tlog/error t)
           ; error reporting systems can be intervean here
           ; (capture-ex! t input)
           (throw t)))))))
