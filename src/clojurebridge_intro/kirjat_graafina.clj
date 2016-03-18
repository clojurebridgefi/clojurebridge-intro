(ns clojurebridge-intro.kirjat-graafina
  (:require [clojurebridge-intro.tiedonkasittely :as t]
            [quil.core :as q]))

; Taulukko kirjan genrestä väriin:
;                Genre:        Pun  Vih  Sin
(def genre-vari {:fantasia    [32   178  62]
                 :mysteeri    [182  42   42]
                 :klassikko   [142  142  182]
                 :romantiikka [255  155  155]
                 :sci-fi      [230  20   192]})

; Funktio joka ottaa kirjan ja palauttaa värin:

(defn kirjan-vari [kirja]
  (let [genre (get kirja :genre)
        varit (get genre-vari genre)
        pun (nth varit 0)
        vih (nth varit 1)
        sin (nth varit 2)]
    (q/color pun vih sin)))

; Funktio joka ottaa kirjan ja palauttaa x-koordinaatin
; kirjan sivumäärän perusteella:

(defn kirjan-x [kirja]
  (let [sivuja (get kirja :sivuja)]
    (q/map-range sivuja
                 0 670    ; min ja max sivumäärä
                 0 600))) ; min ja max x-koordinaatti

; Funktio joka ottaa kirjan ja palauttaa y-koordinaatin
; kirjan hinnan perusteella:

(defn kirjan-y [kirja]
  (let [hinta (get kirja :hinta)]
    (q/map-range hinta
                 0 30     ; min ja max hinta
                 0 600))) ; min ja max y-koordinaatti

  ; Piirtää yhden kirjan:

(defn piirra-kirja [kirja]
  (let [vari (kirjan-vari kirja)
        x (kirjan-x kirja)
        y (kirjan-y kirja)
        r 50]
    (q/fill vari)
    (q/ellipse x y r r)
    (q/fill 0)
    (q/text (get kirja :nimi) x y)))

; Tyhjentää ruudun:

(defn tyhjennys []
  (q/background 190 190 190)      ;; Harmaa tausta
  (q/fill 0 0 0)                  ;; Täyttö väri
  (q/stroke 255 255 255)          ;; Viivan väri
  (q/stroke-weight 5)             ;; Viivan paksuus
  (q/text-align :center :center)) ;; Tekstin keskitys


; Piirtää ruudun:

(defn piirto []
  (tyhjennys)
  (doseq [kirja t/kirjat]
    (piirra-kirja kirja)))

; Alustus:

(defn alustus []
  ; Kirjaisimen asetus:
  (q/text-font (q/create-font "SansSerif" 36 true))
  (q/text-size 15)
  (q/smooth)           ;; Reunojen pehmennys
  (q/frame-rate 8))    ;; Päivitys tiheys

; Avaa uuden piirto-ikkunan:

(defn avaa-uusi-ikkuna []
  (q/defsketch kirjat-graafina
    :title "Kirjat palloina"     ;; Otsikko
    :setup alustus               ;; Alustus funktio
    :draw piirto                 ;; Piirto funktio
    :features [:keep-on-top]     ;; Taikuutta
    :size [685 600]))            ;; Koko

(comment
  ; Maalaa tämä ja paina (win) ctrl+enter (mac) cmd+enter
  (avaa-uusi-ikkuna)
  )
