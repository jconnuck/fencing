(ns final_project.input.data
  (:import [final_project.input ITournamentInfo IEventInfo]))

(defrecord TournamentInfo [dataStore events]
  ITournamentInfo
  (getEvents [this] events)
  (getDataStore [this] dataStore))

(defrecord EventInfo [weapon preregs]
  IEventInfo
  (getWeaponType [this] weapon)
  (getPreregs [this] preregs))

