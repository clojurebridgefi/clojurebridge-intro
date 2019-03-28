(ns clojurebridge-intro.books-graph
  (:require [clojurebridge-intro.books-archive :as t]
            [quil.core :as q]))

;;
;; Table of books from genre to colors:
;;               Genre          red green blue
(def genre-color {:fantasy     [ 32  178   62]
                  :mystery      [182   42   42]
                  :classic      [142  142  182]
                  :romantic     [255  155  155]
                  :sci-fi       [230   20  192]})



;;
;; Function that takes a book and returns its color:
;;

(defn book-color [book]
  (let [genre (get book :genre)
        colors (get genre-color genre)
        red (nth colors 0)
        green (nth colors 1)
        blue (nth colors 2)]
    (q/color red green blue)))



;;
;; Function that takes a book and returns its x-coordinate
;; based on the number of pages:
;;

(defn book-position-x [book]
  (let [pages (get book :pages)]
    (q/map-range pages
                 0 670    ; min and max number of pages
                 0 600))) ; min and max x-coordinate



;;
;; Function that takes a book and returns its y-coordinate
;; based on the book price:
;;

(defn book-position-y [book]
  (let [price (get book :price)]
    (q/map-range price
                 0 30     ; min and max price
                 0 600))) ; min and max y-coordinate



;;
;; Draw one book:
;;

(defn draw-book [book]
  (let [color (book-color book)
        x (book-position-x book)
        y (book-position-y book)
        r 50]
    (q/fill color)
    (q/ellipse x y r r)
    (q/fill 0)
    (q/text (get book :name) x y)))



;;
;; Clear the screen:
;;

(defn cleaning []
  (q/background 190 190 190)      ; Gray background
  (q/fill 0 0 0)                  ; Fill color
  (q/stroke 255 255 255)          ; Line color
  (q/stroke-weight 5)             ; Line thickness
  (q/text-align :center :center)) ; Text alignment



;;
;; Draw the screen:
;;

(defn drawing []
  (cleaning)
  (doseq [book t/books]
    (draw-book book)))



;;
;; Presets:
;;

(defn presets []
  ; Font settings:
  (q/text-font (q/create-font "SansSerif" 36 true))
  (q/text-size 15)
  (q/smooth)           ; Edges softening
  (q/frame-rate 8))    ; Updating frequency



;;
;; Open a new drawing window:
;;

(defn open-new-window []
  (q/defsketch books-graph
    :title "Books as cirles"     ; Title
    :setup presets               ; Presets function
    :draw drawing                ; Drawing function
    :features [:keep-on-top]     ; Magic
    :size [685 600]))            ; Dimensions

(comment
  ; Copy/paste this and press ctrl+enter (win) or cmd+enter (mac)
  (open-new-window))
  
