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

(def test-file "test_data/TestFRD.frd")

(defn parse-fencer [^IDataStore store clubs {:keys (attrs content) :as fencer}]
  (reduce #(.addClub %1 (:id (clubs %2)))
          (.createPlayer store
                         "" (:FirstName attrs) (:LastName attrs)
                         "" "Fencer" "" -1 nil)
          (for [[key val] attrs :when (re-find #"^:ClubID" (str key))]
            val)))

(defn parse-club [^IDataStore store {:keys [attrs] :as club}]
  [(:ClubID attrs) (.createClub store (:Name attrs))])

(defn parse-file [^IDataStore store file]
  (let [xml (zip/xml-zip (parse file))
        clubs (into {} (map (partial parse-club store)
                            (xml-> xml :ClubDatabase :Club zip/node)))
        fencers (xml-> xml :FencerDatabase :Fencer zip/node
                       (partial parse-fencer store clubs))]
    (doseq [datum (concat (vals clubs) fencers)]
      (.putData store datum))))

(defn init-store [file]
  (dosync
   (doto (DataStore.)
     (parse-file file))))