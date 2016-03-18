(ns clojurebridge-intro.piirtely
  ; Ota Quil kirjato käyttöön, käytä aliaksena 'q' symbolia.
  (:require [quil.core :as q]))

;
; Funktio joka tyhjentää ruudun täyttämällä sen
; harmaalla värillä.
;

(defn tyhjennys []
  ; numerot ovat punaisen-, vihreän- ja sinisen värin
  ; coimakkuudet asteikolla 0 - 255. Musta on 0 0 0
  ; ja valkoinen 255 255 255.
  (q/background 190 190 190))

;
; Piirtely funktio. Tätä kutsutaan toistuvasti.
;

(defn piirto []
  (let [x (q/mouse-x)    ; hiiren x -koordinaatti.
        y (q/mouse-y)    ; hiiren y -koordinaatti.
        leveys 50        ; ellipsin leveys ja...
        korkeus 50]      ; ...korkeus
    (q/ellipse x y leveys korkeus)))

;
; Alustus funktio. Tätä kutsutaan vain piirtely-ikkunan
; luonti vaiheessa.
;

(defn alustus []
  (q/smooth)             ; Reunojen pehmennys päälle.
  (q/frame-rate 30))     ; Piirrä ikkuna 30 kertaa sekunnissa.

;
; Luodaan Quil 'sketch', eli 'piirtely' -ikkuna.
;

(q/defsketch piirtely-ikkuna
  :title "Piirtelyä"           ; Otsikko.
  :setup alustus               ; Alustus funktio.
  :draw piirto                 ; Piirto funktio.
  :features [:keep-on-top]     ; Taikuutta.
  :size [685 600])             ; Piirto-ikkunan koko.
