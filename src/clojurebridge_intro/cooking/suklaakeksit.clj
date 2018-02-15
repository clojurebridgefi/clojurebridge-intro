(ns clojurebridge-intro.cooking.suklaakeksit
  (:require [clojurebridge-intro.cooking.robot :as robotti]))

;; Kokeillaanpa miten robotti toimii:

(robotti/suorita "suurusta sekoittaen")
; tulostaa konsoliin: kokkaus robotti: suurusta sekoittaen

(robotti/lisää 2 "dl" "jauhoja")
; tulostaa konsoliin: kokkaus robotti: lisää 2 dl jauhoja

;;
;; Tehdään suklaakeksejä 8:lle:
;; lähde: https://www.kotikokki.net/reseptit/nayta/268454/SUUSSASULAVAT%20SUKLAAKEKSIT%20:D/
;;
;; 150 g 	 margariinia
;; 2 dl    sokeria
;; 1 tl    vaniliasokeria
;; 1       muna
;; 3 dl    vehnäjauhoja
;; 1 tl    leivinjauhetta
;; 3 ½ rkl kaakaojauhetta
;;
;; Laita uuni kuumenemaan 175´c.
;; Sekoita jauhot, leivinjauhot, kaakaojauhe ja vaniliasokeri.
;; Vaahdota sokeri ja rasva.
;; Lisää vaahtoon kananmunat ja jauhot.
;; Nostele leivinpaperille pieniksi keoiksi. Leivokset leviävät uunissa joten jätä väliä.
;; Anna suklaakeksien kypsyä uunissa noin 15min

(robotti/suorita "laita uuni kuumenemaan 175 C")
(robotti/lisää 2 "dl" "sokeri")
(robotti/lisää 150 "g" "margariini")
(robotti/suorita "vaahdota")
(robotti/lisää 1 "kpl" "muna")
(robotti/lisää 3 "dl" "vehnäjauho")
(robotti/lisää 1 "tl" "leivinjauho")
(robotti/lisää (+ 3 1/2) "rkl" "kaakaojauhe")
(robotti/lisää 1 "tl" "vaniliasokeri")
(robotti/suorita "nostele leivinpaperille pieniksi keoiksi")
(robotti/suorita "laita uuniin")
(robotti/odota 15)
(robotti/suorita "ota uunista, tarjoa")

; Yhdistetään nämä työvaiheet yhdeksi funktioksi:

(defn tee-suklaakeksit-kahdeksalle []
  (robotti/suorita "laita uuni kuumenemaan 175 C")
  (robotti/lisää 2 "dl" "sokeri")
  (robotti/lisää 150 "g" "margariini")
  (robotti/suorita "vaahdota")
  (robotti/lisää 1 "kpl" "muna")
  (robotti/lisää 3 "dl" "vehnäjauho")
  (robotti/lisää 1 "tl" "leivinjauho")
  (robotti/lisää (+ 3 1/2) "rkl" "kaakaojauhe")
  (robotti/lisää 1 "tl" "vaniliasokeri")
  (robotti/suorita "nostele leivinpaperille pieniksi keoiksi")
  (robotti/suorita "laita uuniin")
  (robotti/odota 15)
  (robotti/suorita "ota uunista, tarjoa"))

; Kokeillaan:

(tee-suklaakeksit-kahdeksalle)

; Resepti on 8:lle, mutta entäs jos meitä on 4? Tai 2? Tai 12?
; Muutetaan robotin ohjeita siten että kerrotaan sille montako
; syöjiä on, ja lasketaan aineiden määrä sen mukaan.

; Jos kerran 8:n annoksen versiossa laitetaan 2 dl sokeria, niin yhden
; annoksen sokerin määrä on
;    2 dl / 8 => eli 1/4 dl.

(/ 2 8)
;=> 1/4

; Jos annoksia halutaan 2, niin laitetaan sokeria
;    1/4 dl x 2 => eli 1/2 dl

(* 1/4 2)
;=> 1/2

; Tehdään apu-funktio jolle annetaan tuotteen määrä 8 annoksen versiossa ja haluttujen
; annosten mäuarä, funktio saa sitten laskea paljonko ainetta tarvitaan:

(defn muuta-määrä [määrä-8lle annosten-määrä]
  (let [yhden-annoksen-määrä (/ määrä-8lle 8)
        tarvittava-määrä (* yhden-annoksen-määrä annosten-määrä)]
    tarvittava-määrä))

; Kokeillaas: Sokeria tulee 8 annokseen 2dl, mutta jos halutaan tehdä kolmelle:

(muuta-määrä 2 3)
;=> 3/4

; Eli kolmen hengen kekseihin tulee 3/4 dl sokeria

; Entäs leivinjauhetta, 8 annokseen tulee 1tl, entäs jos tehdään kolmelle:

(muuta-määrä 1 3)
;=> 3/8

; Muutetaan reseptiä niin että sille voidaan antaa annosten määrä argumenttina:

(defn tee-suklaakeksit [annoksia]
  (robotti/suorita "laita uuni kuumenemaan 175 C")
  (robotti/lisää (muuta-määrä 2 annoksia) "dl" "sokeri")
  (robotti/lisää (muuta-määrä 150 annoksia) "g" "margariini")
  (robotti/suorita "vaahdota")
  (robotti/lisää (muuta-määrä 1 annoksia) "kpl" "muna")
  (robotti/lisää (muuta-määrä 3 annoksia) "dl" "vehnäjauho")
  (robotti/lisää (muuta-määrä 1 annoksia) "tl" "leivinjauho")
  (robotti/lisää (muuta-määrä (+ 3 1/2) annoksia) "rkl" "kaakaojauhe")
  (robotti/lisää (muuta-määrä 1 annoksia) "tl" "vaniliasokeri")
  (robotti/suorita "nostele leivinpaperille pieniksi keoiksi")
  (robotti/suorita "laita uuniin")
  (robotti/odota 15)
  (robotti/suorita "ota uunista, tarjoa"))

; Kokeillaan:

(tee-suklaakeksit 1)
(tee-suklaakeksit 3)

; Entäs vegaanit?
;
; Yksi ruokalusikallinen soijajauhoja sekoitettuna 2 ruoka-
; lusikalliseen vettä korvaa yhden kananmunan.

(defn vegaaninen-muna [munien-maara]
  (robotti/lisää (* 1 munien-maara) "rkl" "soijajauho")
  (robotti/lisää (* 2 munien-maara) "rkl" "vettä")
  (robotti/suorita "lisää keitokseen"))

(vegaaninen-muna 8)

; Muutetaan reseptiä niin että sille voi kertoa halutaanko vegaaninen keksi vai tavallinen:

(defn tee-nykyaikaiset-suklaakeksit [annoksia vegaaninen?]
  (robotti/suorita "laita uuni kuumenemaan 175 C")
  (robotti/lisää (muuta-määrä 2 annoksia) "dl" "sokeri")
  (robotti/lisää (muuta-määrä 150 annoksia) "g" "margariini")
  (robotti/suorita "vaahdota")
  (if vegaaninen?
    (vegaaninen-muna (muuta-määrä 1 annoksia))
    (robotti/lisää (muuta-määrä 1 annoksia) "kpl" "muna"))
  (robotti/lisää (muuta-määrä 3 annoksia) "dl" "vehnäjauho")
  (robotti/lisää (muuta-määrä 1 annoksia) "tl" "leivinjauho")
  (robotti/lisää (muuta-määrä (+ 3 1/2) annoksia) "rkl" "kaakaojauhe")
  (robotti/lisää (muuta-määrä 1 annoksia) "tl" "vaniliasokeri")
  (robotti/suorita "nostele leivinpaperille pieniksi keoiksi")
  (robotti/suorita "laita uuniin")
  (robotti/odota 15)
  (robotti/suorita "ota uunista, tarjoa"))

(tee-nykyaikaiset-suklaakeksit 2 true)

; lähteet:
; https://www.kotikokki.net/reseptit/nayta/268454/SUUSSASULAVAT%20SUKLAAKEKSIT%20:D/
; https://www.meillakotona.fi/artikkelit/mitat-ja-muunnokset
; https://www.mtv.fi/lifestyle/makuja/artikkeli/kananmunan-korvaaminen-onnistuu-ruoanlaitossa-helposti-lue-keittiomestarin-vinkit/3419058#gs.3ntp6AE
