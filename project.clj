(defproject org.clojars.cavecod/easyio "0.1.5-SNAPSHOT"
  :description "make easy to use io on clojure, wrap java.io.File and other"
  :url "https://github.com/cavecod/easyio"
  :license {:name "GPL3"
            :url "https://www.gnu.org/licenses/gpl-3.0.txt"}
  :plugins [[quickie "0.4.2"]
            [lein-codox "0.10.8"]]
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/tools.logging "1.1.0"]]
  :target-path "target/%s"
  :deploy-repositories [["releases" :clojars :creds :gpg]
                        ["snapshots" :clojars :creds :gpg]]
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
