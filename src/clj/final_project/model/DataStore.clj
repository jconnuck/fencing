(ns final_project.model.DataStore
  (:import [final_project.model IPerson IPlayer IDataStore IObservable DataStore])
  (:require [clojure.set :as set]
            [clojure.string :as str])
  (:use [clojure test])
  (:gen-class :main false
              :state 
              :implements [final_project.model.IDataStore]
              :init init))

(defn capitalize-sym [id]
  (symbol (apply str
                 (Character/toUpperCase (first (str id)))
                 (rest (str id)))))

(deftest test-capitalize
  (are [x y] (= (capitalize-sym x) y)
       'asdf 'Asdf
       'fast 'Fast
       'asdfAsdf 'AsdfAsdf))

(defn make-reader
  ([id]
     (make-reader id (capitalize-sym id)))
  ([id get-name]
     (let [name (symbol (str "get" get-name))]
       `(~name [this#] ~id))))

(defn make-writer
  ([id]
     (make-writer id (capitalize-sym id)))
  ([id write-name]
     (let [name (symbol (str "set" write-name))]
       `(~name [this# new#]
               (assoc this# ~(keyword id) new#)))))

(defn symbol-concat [& syms]
  (symbol (str/join (map str syms))))

(deftest test-symbol-concat
  (are [x y] (= (apply symbol-concat x) y)
       ['asdf 'dfsa] 'asdfdfsa
       ['foo 'BaR 'bAz 'baSH] 'fooBaRbAzbaSH))

(defn make-group-writers
  ([id]
     (make-group-writers id (capitalize-sym id)))
  ([id method-name]
     (let [key (keyword id)
           add (symbol-concat 'add method-name)
           remove (symbol-concat 'remove method-name)
           clear (symbol-concat 'clear method-name)]
       `((~add [this# id#]
               (assoc this# ~key (conj ~id id#)))
         (~remove [this# id#]
                  (assoc this# ~key (disj ~id id#)))
         (~clear [this#]
                 (assoc this# ~key #{}))))))

(defn make-interface [{:keys [name read-only has-one has-many]}]
  `(~name ~@(map #(apply make-reader %) read-only)
          ~@(map #(apply make-reader %) has-one)
          ~@(map #(apply make-writer %) has-one)
          ~@(map #(apply make-reader %) has-many)
          ~@(apply concat
                   (map #(apply make-group-writers %) has-many))))

(defn make-record [name & interfaces]
  `(defrecord ~name
     ~(vec (apply concat
                  (for [interface interfaces]
                    (apply concat
                           (list
                            (map first (:read-only interface))
                            (map first (:has-one interface))
                            (map first (:has-many interface)))))))
     ~@(apply concat (map make-interface interfaces))))

(defmacro define-record [& args]
  (apply make-record args))

(define-record Spectator
  {:name IPerson
   :read-only [[id ID]]
   :has-one [[phoneNumber]]
   :has-many [[watched]]})

(define-record Player
  {:name IPlayer
   :read-only [[id ID] [observers]]
   :has-one [[phoneNumber] [rating] [rank]]
   :has-many [[watched]]})

(def many-many {:watched :observers})

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

(defn add-store [store obj [forward-id reverse-id]]
  (assert (not (@(:data store) (:id obj))))
  (alter (reverse-id store)
         #(reduce (fn [rev-map other-id]
                    (assoc rev-map other-id
                           (conj (rev-map other-id #{}) (:id obj))))
                  %
                  (forward-id obj))))

(defn add-person [{:keys [data observers current-id] :as store}
                  {:keys [watched id] :as raw-person}]
  (assert (not (@data id)))
  (let [person (assoc raw-person :observers #{})]
    (doseq [id-pair many-many]
      (add-store store person id-pair))
    (alter data assoc id person)
    (if (>= id (ensure current-id))
      (ref-set current-id (inc id)))))

(defn remove-store [store id [forward-id reverse-id]]
  (alter (reverse-id store)
         #(reduce (fn [rev-map other-id]
                    (assoc rev-map other-id
                           (disj (rev-map other-id) id)))
                  %
                  (forward-id ((ensure (:data store)) id)))))

(defn remove-person [{:keys [data observers] :as store} id]
  (doseq [id-pair many-many]
    (remove-store store id id-pair))
  (alter data dissoc id))

(defn replace-person [store person]
  (remove-person store (:id person))
  (add-person store person))

(defn get-datum [store id]
  (let [data (dosync (into {}
                           (map #(vector % (ensure (get store %)))
                                (cons :data (vals many-many)))))
        original-obj ((:data data) id)]
    (reduce (fn [obj [forward-id reverse-id]]
              (let [reverse-data ((reverse-id data) (:id obj))]
                (if (seq reverse-data)
                  (assoc obj reverse-id reverse-data)
                  obj)))
            original-obj
            many-many)))

(defn get-data [{:keys [data] :as store}]
  (dosync (map #(get-datum store %)
               (keys (ensure data)))))

(defn get-type [store interface]
  (filter #(isa? (class %) interface)))

(def get-people (partial get-type IPerson))
(def get-players (partial get-type IPlayer))

(deftest observers-test
  (let [s (DataStore.)]
    (dosync
     (doto s
       (.putData (.createSpectator s "1111"))
       (.putData (.createSpectator s "11111"))
       (.putData (.createPlayer s "1111" "rating" 5))
       (.putData (.createPlayer s "1111" "rating" 5))))
    (is (empty? (.getObservers (.getData s 2))))
    (is (empty? (.getObservers (.getData s 3))))
    (dosync (.putData s (.addWatched (.getData s 0) 2)))
    (is (= (.getObservers (.getData s 2)) #{0}))
    (is (empty? (.getObservers (.getData s 3))))
    (dosync (.putData s (.addWatched (.addWatched (.getData s 1) 2) 3)))
    (is (= (.getObservers (.getData s 2)) #{0 1}))
    (is (= (.getObservers (.getData s 3)) #{1}))
    (dosync (.putData s (.clearWatched (.getData s 0))))
    (is (= (.getObservers (.getData s 2)) #{1}))
    (is (= (.getObservers (.getData s 3)) #{1}))
    (dosync (.putData s (.addWatched (.getData s 0) 3)))
    (is (= (.getObservers (.getData s 2)) #{1}))
    (is (= (.getObservers (.getData s 3)) #{0 1}))
    (dosync (.putData s (.removeWatched (.getData s 1) 3)))
    (is (= (.getObservers (.getData s 2)) #{1}))
    (is (= (.getObservers (.getData s 3)) #{0}))
    (dosync (.removeID s 0))
    (is (= (.getObservers (.getData s 2)) #{1}))
    (is (empty? (.getObservers (.getData s 3))))))

(defn -getData
  ([this]
     (get-data (.store this)))
  ([this id]
     (get-datum (.store this) id)))

(defn -getPeople [this]
  (get-type (.store this) IPerson))

(defn -getPlayers [this]
  (get-type (.store this) IPlayer))

(defn -getObservables [this]
  (get-type (.store this) IObservable))

(defn -putData [this person]
  (replace-person (.store this) person))

(defn -removeData [this person]
  (.removeID (.getID person)))

(defn -removeID [this id]
  (remove-person (.store this) id))

(defn -createSpectator [this & args]
  (apply make-spectator (.store this) args))

(defn -createPlayer [this & args]
  (apply make-player (.store this) args))

(defn -init []
  [[] (make-store)])