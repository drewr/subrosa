(ns subrosa.main
  (:gen-class)
  (:use [subrosa.config :only [config]]
        [subrosa.netty :only [create-server]])
  (:require [clojure.tools.logging :as log]))

(defn -main []
  (let [{:keys [start-fn stop-fn]} (create-server (config :port))]
    (start-fn)
    (.addShutdownHook (Runtime/getRuntime) (Thread. stop-fn))))
