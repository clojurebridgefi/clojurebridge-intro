(ns clojurebridge-intro.tervetuloa
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def viesti "Tervetuloa!")
(def linkki "http://www.clojurebridge.org")
(def kulman-nopeus 0.03)
(def varin-nopeus 0.06)

(defn alustus []
  (q/smooth)
  (q/frame-rate 30)
  (q/text-font (q/create-font "SansSerif" 36 true))
  (q/text-align :center :center)
  (q/stroke 0 0 0)
  (q/fill 0 0 0)
  (q/image-mode :center)
  {:logo (q/load-image "logo.png")
   :kulma 0.0
   :vari 0})

(defn liiku [tila]
  (-> tila
      (update :kulma + kulman-nopeus)
      (update :vari + varin-nopeus)))

(defn piirto [tila]
  (let [k (:kulma tila)
        x (* 180 (q/cos k))
        y (* 100 (q/sin k))
        r (-> tila :vari (q/sin) (+ 1.0) (* 127))]
    (q/background r 190 120)
    (q/with-translation [(/ (q/width) 2)
                         (+ (/ (q/height) 2) 10)]
      (q/text-size 42)
      (q/text viesti 0 -270)
      (q/image (:logo tila) x y)
      (q/text-size 20)
      (q/text linkki 0 265))))

(q/defsketch tervetuloa
    :title "Tervetuloa ClojureBridge koulutukseen"
    :setup alustus
    :draw piirto
    :update liiku
    :features [:keep-on-top]
    :size [800 600]
    :middleware [m/fun-mode])

(defn tervetuloa []
  (println "Tervetuloa, koneesi on valmis"))
