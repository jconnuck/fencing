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
     {3315 {:firstName "Steven"
            :lastName "Uhlman"
            :clubs [136]
            :ratings {"Epee" (Rating. "E2008")
                      "Foil" (Rating. "U")
                      "Saber" (Rating. "U")}}
      15074 {:firstName "Frank"
             :lastName "La"
             :clubs [1]
             :ratings {"Epee" (Rating. "U")
                       "Foil" (Rating. "U")
                       "Saber" (Rating. n"U")}}
      16181 {:firstName "Adrian"
             :lastName "Pacia"
             :clubs [1]
             :ratings {"Epee" (Rating. "B2007")
                       "Foil" (Rating. "E2007")
                       "Saber" (Rating. "E2006")}}
      18641 {:firstName "Ke"
             :lastName "Ma"
             :clubs [945]
             :ratings {"Epee" (Rating. "U")
                       "Foil" (Rating. "U")
                       "Saber" (Rating. "D2007")}}
      29029 {:firstName "Bradley"
             :lastName "L'Herrou"
             :clubs [698]
             :ratings {"Epee" (Rating. "U")
                       "Foil" (Rating. "U")
                       "Saber" (Rating. "U")}}
      34420 {:firstName "Donald"
             :lastName "Paccini"
             :clubs [793]
             :ratings {"Foil" (Rating. "U")}}
      42052 {:firstName "Blaine"
             :lastName "Pace"
             :clubs [1527]
             :ratings {"Epee" (Rating. "E2009")
                       "Foil" (Rating. "U")
                       "Saber" (Rating. "U")}}
      45980 {:firstName "Dexter"
             :lastName "Pabulayan"
             :clubs [1723]
             :ratings {"Epee" (Rating. "E2009")
                       "Foil" (Rating. "U")
                       "Saber" (Rating. "U")}}})

(def correct-events
     #{{:weapon "Saber"
        :preregs [18641 15074 16181 3315 29029 45980 34420 42052]}})
     
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

(defn event-similar [store {:keys [preregs weapon]} {preregs2 :preregs weapon2 :weapon}]
  (and (every? identity
               (map #(person-similar store (.getData store %1) (correct-fencers %2))
                    preregs preregs2))
       (= weapon weapon2)))

(deftest XmlTest
  (let [reader (XmlReader.)
        ti (.getTournamentInfo reader test-file)
        events (.getEvents ti)
        store (.getDataStore ti)
        fencers (.getPlayers store)
        clubs (.getClubs store)]
    (is (= (count fencers) (count correct-fencers)))
    (doseq [fencer fencers]
      (is (some (partial person-similar store fencer)
                (vals correct-fencers))))
    (doseq [club clubs]
      (is (some (partial club-similar club)
                (vals correct-clubs))))
    (doseq [event events]
      (is (some (partial event-similar store event)
                correct-events)))))
