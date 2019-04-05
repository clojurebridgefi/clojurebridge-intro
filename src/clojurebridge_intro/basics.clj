(ns clojurebridge-intro.basics)

;;
;; Familiarizing with the Clojure Syntax:
;;

; Comments

; Numbers

; Booleans

; Strings

; Keywords

; Calling Functions

; Using Constants


;;
;; Let's make our own function
;;

; Calculate the average height of you and two neighbours


;;
;; Data Structures:
;;

; Vector
[1 2 3 4]
(vector 1 2 3 4)
(def prime-numbers [2 3 5 7 11 13 17 19 23])

; - count
(count [1 2 3 4])
(count prime-numbers)

; - conj
(conj [1 2 3 4] 5)
(conj prime-numbers 29)

; - first
(first [1 2 3 4])
(first prime-numbers)

; - what happens if you use first with a string?
(first "Hello!")

; - nth
(nth [1 2 3 4] 1)
(nth prime-numbers 1)

; Exercise
;
; Make a vector of the high temperatures for the next 7 days in the town where you live.
; Then use the nth function to get the high temperature for next Tuesday.

; Map
{:name "Fictional Person" :age 45}
(defn person {:name "Fictional Person" :age 45})

; - get
(get {:name "Fictional Person" :age 45} :name)

; - assoc
(assoc {:name "Fictional Person" :age 45} :occupation "Programmer")

; - dissoc
(dissoc {:name "Fictional Person" :age 45} :name)

; - merge
(merge {:name "Fictional Person" :age 25} {:age 50})

; - update
(update {:name "Fictional Person" :age 45} :age + 15)

; Exercise
;
; Make a map called me with your first and last name.
; Ten add your hometown to it using assoc

(def me {:first "Rudolf" :last "Poels"})
(assoc me :hometown "Amsterdam")

; Vector of Maps
[{:name "Fictional Person", :age 100} {:name "Your Name" :age 200}]

; Optional Exercise
;
; Create a vector with a map of your name and home town
; Then use assoc to add a map with your neighbour's name and home town

; Map of Maps

{:name {:first "My" :last "Name"}}

; - get-in
(get-in {:name {:first "My" :last "Name"}} [:name :first])

;;
;; Flow control
;;

; if
(if (= 1 1)
  "true"
  "lies!")

(defn trip-type [kilometers]
  (if (< kilometers 5)
    :walk
    :car))

; truthiness 
(if ""
  "yes"
  "no")

; cond
(cond
  (= 1 2) "no"
  (= 3 4) "nooo"
  :else "yep")

(defn trip-type [kilometers]
  (cond
    (< kilometers 5) :walk
    (< kilometers 15) :bicycle
    :else :car))

; boolean logic

;;
;; let
;; 
(let [x 2]
  println x)

(println x)