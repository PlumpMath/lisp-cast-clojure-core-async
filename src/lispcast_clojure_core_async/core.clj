(ns lispcast-clojure-core-async.core
  (:require
   [lispcast-clojure-core-async.exercises :as ex]
   [lispcast-clojure-core-async.factory :refer :all]
   [clojure.core.async :as async
    :refer [>! <! alts! chan put! go]]))

(defn build-car [n]
  (let [body (loop []
               (let [part (take-part)]
                 (if (body? part)
                   part
                   (recur))))

        _ (println n "Got body")
        wheel1 (loop []
                 (let [part (take-part)]
                   (if (wheel? part)
                     part
                     (recur))))
        _ (println"Got first wheel")
        wheel2 (loop []
                 (let [part (take-part)]
                   (if (wheel? part)
                     part
                     (recur))))
        _ (println"Got second wheel")
        bw (attach-wheel body wheel1)
        _ (println n "attached first wheel")
        bww (attach-wheel bw wheel2)
        _ (println"attached second wheel")
        box (box-up bww)]
    (println n "Boxed up car")
    (put-in-truck box)
    (println n "Done!!")))

(defn start-ten "" []
  (dotimes [x 10]
    (go
      (time (build-car x)))))
