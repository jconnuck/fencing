(ns final_project.test.xml-test
  (import [final_project.input XmlReader]
          [final_project.model DataStore])
  (:use final_project.input.XmlReader
        clojure.test))

(def correct-clubs
     (array-map 2180 "Bladeworks Fencing Club"
                136 "Knights of Trapani"
                1 "Unattached (or unknown)"
                945 "University of Virginia Fencing Club"
                698 "University of South Florida"
                793 "Grand Valley Sport Fencing"
                1527 "My Fencing Center"
                1723 "New Paltz Fencing Club"))

(def correct-fencers
     [{:firstName "Steven"
       :lastName "Uhlman"
       :clubs [136]
       :ratings {"Epee" "E2008"
                 "Foil" "U"
                 "Saber" "U"}}
      {:firstName "Frank"
       :lastName "La"
       :clubs [1]
       :ratings {"Epee" "U"
                 "Foil" "U"
                 "Saber" "U"}}
      {:firstName "Adrian"
       :lastName "Pacia"
       :clubs [1]
       :ratings {"Epee" "B2007"
                 "Foil" "E2007"
                 "Saber" "E2006"}}
      {:firstName "Ke"
       :lastName "Ma"
       :clubs [945]
       :ratings {"Epee" "U"
                 "Foil" "U"
                 "Saber" "D2007"}}
      {:firstName "Bradley"
       :lastName "L'Herrou"
       :clubs [698]
       :ratings {"Epee" "U"
                 "Foil" "U"
                 "Saber" "U"}}
      {:firstName "Donald"
       :lastName "Paccini"
       :clubs [793]
       :ratings {"Foil" "U"}}
      {:firstName "Blaine"
       :lastName "Pace"
       :clubs [1527]
       :ratings {"Epee" "E2009"
                 "Foil" "U"
                 "Saber" "U"}}
      {:firstName "Dexter"
       :lastName "Pabulayan"
       :clubs [1723]
       :ratings {"Epee" "E2009"
                 "Foil" "U"
                 "Saber" "U"}}])

(defn club-similar [{:keys [name]} name2]
  (= name name2))

(defn person-similar [store {first-name1 :firstName
                             last-name1 :lastName
                             clubs1 :clubs
                             ratings1 :ratings}
                      {first-name2 :firstName
                       last-name2 :lastName
                       clubs2 :clubs
                       ratings2 :ratings}]
  (and (= first-name1 first-name2)
       (= last-name1 last-name2)
       (= (count clubs2) (count clubs2))
       (every? identity
               (map #(club-similar (.getClub store %1)
                                   (correct-clubs %2))
                    clubs1 clubs2))
       (= ratings1 ratings2)))

(deftest XmlTest
  (let [reader (XmlReader.)
        store (.createDataStore reader test-file)
        fencers (.getPlayers store)
        clubs (.getClubs store)]
    (is (= (count fencers) (count correct-fencers)))
    (doseq [fencer fencers]
      (is (some (partial person-similar store fencer)
                correct-fencers)))
    (doseq [club clubs]
      (is (some (partial club-similar club)
                (vals correct-clubs))))))
