(ns final_project.input.XmlReader
  (:import [final_project.model IPerson IPlayer IDataStore
            IObservable DataStore IHasClub IReferee IData IClub]
           [final_project.input IDataInput ITournamentInfo XmlReader])
  (:use clojure.xml)
  (:gen-class :main false
              :implements [final_project.input.IDataInput]))

(defrecord TournamentInfo []
  ITournamentInfo)