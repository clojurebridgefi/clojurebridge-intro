(ns clojurebridge-intro.cooking.robot
  (:require [clojure.string :as str]))

;;
;; Tehdään robotti joka suorittaa leivonta komentoja. Tämä ei ole ihan valmis robotti, tämä
;; robotti ainoastaan kertoo mitä pitäisi tehdä. Ehkä seuraava versio jo oikeasti leipoo :)
;;

(defn suorita [tehtävä]
  (println "kokkaus-robotti:" tehtävä))

(defn lisää [määrä yksikkö aine]
  (println "kokkaus-robotti: lisää" määrä yksikkö aine))

(defn odota [kauanko]
  (println "kokkaus-robotti: odotan" kauanko "minuuttia...")
  ; ei nyt oikeasti odoteta niin kauaa, odotetaan vain
  ; 0.1 sekunttia jokaista minuuttia kohden
  (Thread/sleep (* kauanko 100)))

