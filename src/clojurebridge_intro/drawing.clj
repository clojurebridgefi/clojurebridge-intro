(ns clojurebridge-intro.drawing
  ; Take in use Quil library, use 'q' as alias.
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;
;; Colors are represented by three numbers that describe the intensity of
;; red, green and blue. The minimum value is 0 and the maximum is 255.
;;
;;             Red: Green: Blue:
;; Black:        0,     0,     0
;; Gray:       128,   128,   128
;; White:      255,   255,   255
;; Red:        255,     0,     0
;; Lilac:      255,   255,     0
;;


;;
;; Some helper variables:
;;

(def window-width 700)     ; Window width variable
(def window-height 600)    ; Window height variable



;;
;; Presets function. It is called only during the drawing-window creation step.
;;

(defn presets []
  (q/smooth)             ; Edges softening.
  (q/frame-rate 60)      ; Draw the window 30 times per second.
  {:x 0 
   :y 0})



;;
;; State update, gets the old state and updates with the new state.
;; Always called before the drawing.
;;

(defn update [state]
  ; Updates :x and :y keys with the current mouse location:
  (assoc state :x (q/mouse-x)
               :y (q/mouse-y)))



;;
;; Drawing function. This is called repeatedly. Takes state as parameter.
;;

(defn drawing [state]
  (let [x (:x state)
        y (:y state)
        ; When x reaches 0 - window-width, red moves 0 - 255
        red (q/map-range x 0 window-width 0 255)
        ; When y reaches 0 - window-height, green moves 0 - 255
        green (q/map-range y 0 window-height 0 255)
        ; Blue is always 128
        blue 0]
    (q/stroke-weight 1)         ; Sets the line thickness
    (q/stroke 0 0 0)            ; Sets the colors of the line to black
    (q/fill red green blue 92)  ; Fill color, transparency 92
    (q/ellipse x y 60 60)))     ; Ellipse points x, y



;;
;; Create a Quil 'sketch', basically a 'drawing' window.
;;

(defn run []
  (q/defsketch drawing-window
    :title "Drawing"                      ; Title
    :middleware [m/fun-mode]              ; Functional mode
    :setup presets                        ; Presets function
    :update update                        ; Update function
    :draw drawing                         ; Drawing function
    :features [:keep-on-top]              ; Magic
    :size [window-width window-height]))  ; Drawing-window size
