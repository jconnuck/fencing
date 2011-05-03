(ns final_project.input.XmlReader
  (:import [final_project.model IPerson IPlayer IDataStore
            IObservable DataStore IHasClub IReferee IData IClub]
           [final_project.input IDataInput ITournamentInfo XmlReader])
  (:use [clojure xml]
        clojure.contrib.zip-filter.xml)
  (:require [clojure.zip :as zip])
  (:gen-class :main false
              :implements [final_project.input.IDataInput]))

(defrecord TournamentInfo []
  ITournamentInfo)

(def test-file "test_data/TestInput.frd")

(defn parse-fencer [^IDataStore store clubs fencer]
  (let [{:keys (attrs content)} (zip/node fencer)]
    (reduce #(.addRating %1 (first %2) (second %2))
            (reduce #(.addClub %1 (:id (clubs %2)))
                    (.createPlayer store
                                   "" (:FirstName attrs) (:LastName attrs)
                                   "" "Fencer" -1 nil)
                    (for [[key val] attrs :when (re-find #"^:ClubID" (str key))]
                      val))
            (map list
                 (xml-> fencer :Rating (attr :Weapon))
                 (xml-> fencer :Rating text)))))

(defn parse-club [^IDataStore store {:keys [attrs] :as club}]
  [(:ClubID attrs) (.createClub store (:Name attrs))])

(defn parse-file [^IDataStore store file]
  (let [xml (zip/xml-zip (parse file))
        clubs (into {} (map (partial parse-club store)
                            (xml-> xml :ClubDatabase :Club zip/node)))
        fencers (xml-> xml :FencerDatabase :Fencer
                       (partial parse-fencer store clubs))]
    (doseq [datum (concat (vals clubs) fencers)]
      (.putData store datum))))

(defn init-store [file]
  (dosync
   (doto (DataStore.)
     (parse-file file))))

(defn -updateDataStore [this ^IDataStore store file]
  (parse-file store file))

(defn -createDataStore [this file]
  (init-store file))

(defn -getTournamentInfo [this file]
  (TournamentInfo.))
