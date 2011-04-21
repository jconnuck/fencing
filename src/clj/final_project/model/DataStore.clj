(ns final_project.model.DataStore
  (:import [final_project.model IPerson IPlayer IDataStore])
  (:use [clojure.set])
  (:gen-class :main false
              :state state
              :implements [IDataStore]))

(defrecord Spectator [id phoneNumber watched]
  IPerson
  (getID [this] id)
  (getPhoneNumber [this] phoneNumber)
  (setPhoneNumber [this new] (assoc this :phoneNumber new))
  (getWatched [this] watched)
  (addWatched [this id]
              (assoc this :watched (conj watched id)))
  (removeWatched [this id]
                 (assoc this :watched (disj watched id)))
  (clearWatched [this]
                (assoc this :watched #{})))

(defrecord Player [id phoneNumber rating rank watched observers]
  IPlayer
  (getID [this] id)
  (getPhoneNumber [this] phoneNumber)
  (setPhoneNumber [this new] (assoc this :phoneNumber new))
  (getWatched [this] watched)
  (addWatched [this id]
              (assoc this :watched (conj watched id)))
  (removeWatched [this id]
                 (assoc this :watched (disj watched id)))
  (clearWatched [this]
                (assoc this :watched #{}))
  (getObservers [this] observers)
  (getRating [this] rating)
  (setRating [this new]
             (assoc this :rating new))
  (getRank [this] rank)
  (setRank [this new]
           (assoc this :rank new)))

(defrecord DSStore [data observers current-id])

(defn make-data [fn {:keys [current-id]} & args]
  (dosync (let [new-data (apply fn (ensure current-id) args)]
            (alter current-id inc)
            new-data)))

(defn make-spectator [store phone-number]
  (make-data #(Spectator. % phone-number #{}) store))

(defn make-player [store phone-number rating rank]
  (make-data #(Player. % phone-number rating rank #{} nil) store))

(defn make-store []
  (DSStore. (ref {}) (ref {}) (ref 0)))

(defn add-person [{:keys [data observers current-id]} {:keys [watched id] :as raw-person}]
  (assert (not (@data id)))
  (let [person (assoc raw-person :observers #{})]
    (alter data assoc id person)
    (alter observers
           #(reduce (fn [obs watched-id]
                      (assoc obs watched-id
                             (conj (obs watched-id #{}) id)))
                    %
                    watched)))
  (if (>= id (ensure current-id))
    (ref-set current-id (inc id))))

(defn remove-person [{:keys [data observers]} id]
  (alter observers
         #(reduce (fn [obs watched-id]
                    (assoc obs watched-id
                           (disj (obs watched-id) id)))
                  %
                  (:watched (@data id))))
  (alter data dissoc id))

(defn replace-person [store person]
  (remove-person store (:id person))
  (add-person store person))

(defn get-person [{data-ref :data observers-ref :observers} id]
  (let [[data observers] (dosync [(ensure data-ref)
                                  (ensure observers-ref)])
        person (data id)
        watchers (observers id)]
    (if watchers
      (assoc person :observers watchers)
      person)))

(defn get-data [{:keys [data] :as store}]
  (dosync (map #(get-person store %)
               (keys (ensure data)))))

(defn get-type [store interface]
  (filter #(isa? (class %) interface)))

(def get-people (partial get-type IPerson))
(def get-players (partial get-type IPlayer))

(defn run-tests []
  (let [s (make-store)]
    (dosync (replace-person s (make-spectator s "1111"))
            (replace-person s (make-spectator s "1111"))
            (replace-person s (make-player s "1111" "rating" 5))
            (replace-person s (make-player s "1111" "rating" 5)))
    (println (empty? (:observers (get-person s 2))))
    (println (empty? (:observers (get-person s 3))))
    (dosync (replace-person s (.addWatched (get-person s 0) 2)))
    (println (= (:observers (get-person s 2)) #{0}))
    (println (empty? (:observers (get-person s 3))))
    (dosync (replace-person s (.addWatched (.addWatched (get-person s 1) 2) 3)))
    (println (= (:observers (get-person s 2)) #{0 1}))
    (println (= (:observers (get-person s 3)) #{1}))
    (dosync (replace-person s (.clearWatched (get-person s 0))))
    (println (= (:observers (get-person s 2)) #{1}))
    (println (= (:observers (get-person s 3)) #{1}))
    (dosync (replace-person s (.addWatched (get-person s 0) 3)))
    (println (= (:observers (get-person s 2)) #{1}))
    (println (= (:observers (get-person s 3)) #{0 1}))
    (dosync (replace-person s (.removeWatched (get-person s 1) 3)))
    (println (= (:observers (get-person s 2)) #{1}))
    (println (= (:observers (get-person s 3)) #{0}))
    (dosync (remove-person s 0))
    (println (= (:observers (get-person s 2)) #{1}))
    (println (empty? (:observers (get-person s 3))))))