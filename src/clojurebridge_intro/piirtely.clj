(ns clojurebridge-intro.piirtely
  ; Ota Quil kirjato käyttöön, käytä aliaksena 'q' symbolia.
  (:require [quil.core :as q]
            [quil.middleware :as m]))

; Värit esitetään kolmella numerolla jotka kuvaavat punaisen, vihreän, ja
; sinisen voimakkuutta. Minimi arvo on 0 ja maksimi 255.
;
;             Pun:   Vih:   Sin:
; Musta:        0,     0,     0
; Harmaa:     128,   128,   128
; Valkoinen:  255,   255,   255
; Punainen:   255,     0,     0
; Lila:       255,   255,     0

; Muutamia apu-muuttujia:

(def ikkunan-leveys 700)     ; Ikkunan leveys muuttuja
(def ikkunan-korkeus 600)    ; Ikkunan korkeus muuttuja

;
; Alustus funktio. Tätä kutsutaan vain piirtely-ikkunan
; luonti vaiheessa.
;

(defn alustus []
  (q/smooth)             ; Reunojen pehmennys päälle.
  (q/frame-rate 60)      ; Piirrä ikkuna 30 kertaa sekunnissa.
  {:x 0 
   :y 0})

;
; Tilan päivitys, saa vanhan tilan ja palauttaa uuden
; tilan. Tätä kutsutaan aina ennen piirtoa.
;

(defn paivita [state]
  ; Laitetaan :x ja :y avaimien arvoiksi hiiren paikka:
  (assoc state :x (q/mouse-x)
               :y (q/mouse-y)))

;
; Piirtely funktio. Tätä kutsutaan toistuvasti. Saa tilan.
;

(defn piirto [state]
  (let [x (:x state)
        y (:y state)
        ; Kun x kulkee 0 - ikkunan-leveys, punainen kulkee 0 - 255
        punainen (q/map-range x 0 ikkunan-leveys 0 255)
        ; Kun y kulkee 0 - ikkunan-korkeys, vihrea kulkee 0 - 255
        vihrea (q/map-range y 0 ikkunan-korkeus 0 255)
        ; Sininen on aina 128
        sininen 0]
    (q/stroke-weight 1)  ; Asettaa viivan paksuuden
    (q/stroke 0 0 0)     ; Asettaa viivan värin mustaksi
    (q/fill punainen vihrea sininen 92)  ; Täyttö väri, läpikuultavuus 92
    (q/ellipse x y 60 60)))            ; Ellipsi kohtaan x, y

;
; Luodaan Quil 'sketch', eli 'piirtely' -ikkuna.
;

(defn run []
  (q/defsketch piirtely-ikkuna
    :title "Piirtelyä"                        ; Otsikko.
    :middleware [m/fun-mode]                  ; Funktionaalinen tapa.
    :setup alustus                            ; Alustus funktio.
    :update paivita                           ; Päivitys funktio.
    :draw piirto                              ; Piirto funktio.
    :features [:keep-on-top]                  ; Taikuutta.
    :size [ikkunan-leveys ikkunan-korkeus]))  ; Piirto-ikkunan koko.
