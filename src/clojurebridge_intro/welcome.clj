(ns clojurebridge-intro.welcome
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def message "Welcome!")
(def link "http://www.clojurebridge.org")
(def turning-speed 0.03)
(def color-speed 0.06)

(defn presets []
  (q/smooth)
  (q/frame-rate 30)
  (q/text-font (q/create-font "SansSerif" 36 true))
  (q/text-align :center :center)
  (q/stroke 0 0 0)
  (q/fill 0 0 0)
  (q/image-mode :center)
  {:logo (q/load-image "logo.png")
   :turn 0.0
   :color 0})

(defn move [state]
  (-> state
      (update :turn + turning-speed)
      (update :color + color-speed)))

(defn drawing [state]
  (let [k (:turn state)
        x (* 180 (q/cos k))
        y (* 100 (q/sin k))
        r (-> state :color (q/sin) (+ 1.0) (* 127))]
    (q/background r 190 120)
    (q/with-translation [(/ (q/width) 2)
                         (+ (/ (q/height) 2) 10)]
      (q/text-size 42)
      (q/text message 0 -270)
      (q/image (:logo state) x y)
      (q/text-size 20)
      (q/text link 0 265))))

(q/defsketch welcome
    :title "Welcome to Futurice x ClojureBridge"
    :setup presets
    :draw drawing
    :update move
    :features [:keep-on-top]
    :size [800 600]
    :middleware [m/fun-mode])

(defn welcome []
  (println "You're all set up! Welcome!"))
