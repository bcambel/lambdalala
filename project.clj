(defproject lambdalala "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
                      :url "https://www.eclipse.org/legal/epl-2.0/"}
            :dependencies
            [[org.clojure/clojure "1.10.0"]
             [cheshire "5.8.0"]
             [amazonica "0.3.133"
              :exclusions [com.amazonaws/aws-java-sdk]]
             [com.amazonaws/aws-lambda-java-core "1.2.0"]
             [com.taoensso/timbre "4.10.0"]
             ]
            :main ^:skip-aot lambdalala.core
            :target-path "target/%s"
            :profiles {:uberjar {:aot :all}})
