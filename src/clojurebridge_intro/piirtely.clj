(ns clojurebridge-intro.piirtely
  ; Ota Quil kirjato käyttöön, käytä aliaksena 'q' symbolia.
  (:require [quil.core :as q]))

; Värit esitetään kolmella numerolla jotka kuvaavat punaisen, sinisen ja
; vihreän voimakkuutta. Minimi arvo on 0 ja maksimi 255.
;
; Musta        0,   0,   0
; Harmaa     128, 128, 128
; Valkoinen  255, 255, 255
; Punainen   255,   0,   0
; Lila       255, 255,   0

; Muutamia apu-muuttujia:

(def ikkunan-leveys 700)     ; Ikkunan leveys muuttuja
(def ikkunan-korkeus 600)    ; Ikkunan korkeus muuttuja

;
; Alustus funktio. Tätä kutsutaan vain piirtely-ikkunan
; luonti vaiheessa.
;

(defn alustus []
  (q/smooth)             ; Reunojen pehmennys päälle.
  (q/frame-rate 30))     ; Piirrä ikkuna 30 kertaa sekunnissa.

;
; Piirtely funktio. Tätä kutsutaan toistuvasti.
;

(defn piirto []
  (let [x (q/mouse-x)     ; Hiiren x-koordinaatti
        y (q/mouse-y)     ; Hiiren y-koordinaatti
        ; Kun x kulkee 0 - ikkunan-leveys, punainen kulkee 0 - 255
        punainen (q/map-range x 0 ikkunan-leveys 0 255)
        ; Kun y kulkee 0 - ikkunan-korkeys, vihrea kulkee 0 - 255
        vihrea (q/map-range y 0 ikkunan-korkeus 0 255)
        ; Sininen on aina 128
        sininen 128]
    ; Reunuksen vahvuus:
    (q/stroke-weight 1)
    ; Reunuksen väri:
    (q/stroke 0 0 0)
    ; Täyttöväri:
    (q/fill punainen vihrea sininen 92)
    ; Piirretään 60 x 60 ellipsi kohtaan x, y:
    (q/ellipse x y 60 60)))

;
; Luodaan Quil 'sketch', eli 'piirtely' -ikkuna.
;

(q/defsketch piirtely-ikkuna
  :title "Piirtelyä"                        ; Otsikko.
  :setup alustus                            ; Alustus funktio.
  :draw piirto                              ; Piirto funktio.
  :features [:keep-on-top]                  ; Taikuutta.
  :size [ikkunan-leveys ikkunan-korkeus])   ; Piirto-ikkunan koko.
