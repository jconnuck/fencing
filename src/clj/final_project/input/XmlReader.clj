(ns final_project.input.XmlReader
  (:import [final_project.model IPerson IPlayer IDataStore
            IObservable DataStore IHasClub IReferee IData IClub]
           [final_project.input IDataInput ITournamentInfo XmlReader IEventInfo])
  (:use [clojure xml]
        clojure.contrib.zip-filter.xml)
  (:require [clojure.zip :as zip])
  (:gen-class :main false
              :implements [final_project.input.IDataInput]))

(defrecord TournamentInfo [dataStore events]
  ITournamentInfo
  (getEvents [this] events)
  (getDataStore [this] dataStore))

(defrecord EventInfo [weapon preregs]
  IEventInfo
  (getWeaponType [this] weapon)
  (getPreregs [this] preregs))

(def test-file "test_data/TestInput.frd")

(defn parse-fencer [^IDataStore store clubs fencer]
  (let [{:keys (attrs content)} (zip/node fencer)]
    [(:FencerID attrs)
     (reduce #(.addRating %1 (first %2) (second %2))
             (reduce #(.addClub %1 (:id (clubs %2)))
                     (.createPlayer store
                                    "" (:FirstName attrs) (:LastName attrs)
                                    "" "Fencer" -1)
                     (for [[key val] attrs :when (re-find #"^:ClubID" (str key))]
                       val))
             (map list
                  (xml-> fencer :Rating (attr :Weapon))
                  (xml-> fencer :Rating text)))]))

(defn parse-club [^IDataStore store {:keys [attrs] :as club}]
  [(:ClubID attrs) (.createClub store (:Name attrs))])

(defn parse-event [fencer-map event]
  (EventInfo. (xml1-> event (attr :Weapon))
              (xml-> event :PreReg (attr :CompetitorID) fencer-map #(:id %))))

(defn parse-file [^IDataStore store file]
  (let [xml (zip/xml-zip (parse file))
        clubs (into {} (map (partial parse-club store)
                            (xml-> xml :ClubDatabase :Club zip/node)))
        fencers (into {} (map (partial parse-fencer store clubs)
                              (xml-> xml :FencerDatabase :Fencer)))
        events (xml-> xml :Tournament :Event (partial parse-event fencers))
        preregs (set (mapcat #(.getPreregs %) events))]
    (doseq [datum (concat (vals clubs)
                          (filter (comp preregs :id) (vals fencers)))]
      (.putData store datum))
    (TournamentInfo. store events)))

(defn init-store [file]
  (dosync
   (parse-file (DataStore.) file)))

(defn -getTournamentInfo [this file]
  (init-store file))

(defn -updateTournamentInfo [this store file]
  (parse-file store file))
