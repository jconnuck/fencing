(ns final_project.model.DataStore
  (:import [final_project.model IPerson IPlayer IDataStore
            IObservable DataStore IHasClub IReferee IData IClub])
  (:require [clojure.set :as set]
            [clojure.string :as str])
  (:use [clojure test])
  (:gen-class :main false
              :state store
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
  ([id get-name & _]
     (let [name (symbol (str "get" get-name))]
       `(~name [this#] ~id))))

(defn make-writer
  ([id]
     (make-writer id (capitalize-sym id)))
  ([id write-name & _]
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
     (make-group-writers id
                         (capitalize-sym id)
                         (capitalize-sym id)))
  ([id method-name singular-method-name & _]
     (let [key (keyword id)
           add (symbol-concat 'add singular-method-name)
           remove (symbol-concat 'remove singular-method-name)
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
  {:name IData
   :read-only [[id ID]]}
  {:name IPerson
   :has-one [[phoneNumber] [carrier] [group]]
   :has-many [[watched]]})

(define-record Player
  {:name IData
   :read-only [[id ID]]}
  {:name IHasClub
   :has-many [[clubs Clubs Club]]}
  {:name IPerson
   :has-one [[phoneNumber] [carrier] [group]]
   :has-many [[watched]]}
  {:name IPlayer
   :read-only [[observers] [seed]]
   :has-one [[rating] [rank]]})

(define-record Referee
  {:name IData
   :read-only [[id ID]]}
  {:name IHasClub
   :has-many [[clubs Clubs Club]]}
  {:name IPerson
   :has-one [[phoneNumber] [carrier] [group]]
   :has-many [[watched]]}
  {:name IReferee
   :has-one [[reffing]]})

(define-record Club
  {:name IClub
   :read-only [[id ID] [members]]
   :has-one [[name]]})

(def many-many {:watched :observers
                :clubs :members})

(defrecord DSStore [data observers members current-id])

(defn make-data [fn {:keys [current-id]} & args]
  (dosync (let [new-data (apply fn (ensure current-id) args)]
            (alter current-id inc)
            new-data)))

(defn make-spectator [store phone-number carrier group]
  (make-data #(Spectator. % phone-number carrier group #{}) store))

(defn make-player [store phone-number carrier group rating rank seed]
  (make-data #(Player. % #{} phone-number carrier group #{} #{} seed rating rank) store))

(defn make-club [store name]
  (make-data #(Club. % #{} name) store))

(defn make-referee [store phone-number carrier group]
  (make-data #(Referee. % #{} phone-number carrier group #{} false) store))

(defn make-store []
  (DSStore. (ref {}) (ref {}) (ref {}) (ref 0)))

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

(defn get-datum-pred [pred store id]
  (let [data (dosync (into {}
                           (map #(vector % (ensure (get store %)))
                                (cons :data (vals many-many)))))
        original-obj ((:data data) id)]
    (when (and original-obj
               (pred original-obj))
      (reduce (fn [obj [forward-id reverse-id]]
                (let [reverse-data ((reverse-id data) (:id obj))]
                  (if (seq reverse-data)
                    (assoc obj reverse-id reverse-data)
                    obj)))
              original-obj
              many-many))))

(def get-datum (partial get-datum-pred (constantly true)))

(defn get-datum-type [store id type]
  (get-datum-pred #(isa? (class %) type) store id))

(defn get-predicate [pred {:keys [data] :as store}]
  (dosync (for [[id person] @data :when (pred person)]
            (get-datum store id))))

(def get-data
     (partial get-predicate (constantly true)))

(defn get-type [store type]
  (get-predicate #(isa? (class %) type) store))

(deftest observers-test
  (let [s (DataStore.)]
    (is (empty? (.getData s)))
    (.runTransaction
     s
     (reify Runnable
            (run [this]
                 (doto s
                   (.putData (.createSpectator s "1111" "Verizon" "Spectators"))
                   (.putData (.createReferee s "1111" "Verizon" "Coaches"))
                   (.putData (.createClub s "My Club"))
                   (.putData (.createPlayer s "1111" "Verizon" "Players" "rating" 5 nil))
                   (.putData (.createPlayer s "1111" "Verizon" "Players" "rating" 5 nil))))))

    (is (= (.getPerson s 1) (.getData s 1)))
    (is (= (.getPerson s 2) nil))
    (is (= (.getClub s 2) (.getData s 2)))
    (is (= (.getClub s 3) nil))
    (is (= (.getPlayer s 3) (.getData s 3)))
    (is (= (.getPlayer s 1) nil))
    (is (= (.getReferee s 1) (.getData s 1)))
    (is (= (.getReferee s 0) nil))
    (is (= (.getObservable s 4) (.getData s 4)))
    (is (= (.getObservable s 1) nil))

    (is (= (.getPhoneNumber (.getData s 0)) "1111"))
    (is (= (.getCarrier (.getData s 0)) "Verizon"))
    (is (= (.getGroup (.getData s 0)) "Spectators"))

    (is (= (.getPhoneNumber (.getData s 1)) "1111"))
    (is (= (.getCarrier (.getData s 1)) "Verizon"))
    (is (= (.getGroup (.getData s 1)) "Coaches"))
    (is (= (.getReffing (.getData s 1)) false))

    (is (= (.getPhoneNumber (.getData s 3)) "1111"))
    (is (= (.getCarrier (.getData s 3)) "Verizon"))
    (is (= (.getGroup (.getData s 3)) "Players"))
    (is (= (.getRating (.getData s 3)) "rating"))
    (is (= (.getRank (.getData s 3)) 5))

    (is (= (.getName (.getData s 2)) "My Club"))

    (dosync
     (.putData s
               (-> (.getData s 0)
                   (.setPhoneNumber "1234")
                   (.setCarrier "Sprint")
                   (.setGroup "Coaches"))))

    (is (= (.getPhoneNumber (.getData s 0))) "1234")
    (is (= (.getCarrier (.getData s 0))) "Sprint")
    (is (= (.getGroup (.getData s 0)) "Coaches"))

    (is (= (.getPhoneNumber (.getData s 1)) "1111"))
    (is (= (.getCarrier (.getData s 1)) "Verizon"))
    (is (= (.getGroup (.getData s 1)) "Coaches"))
    (is (= (.getReffing (.getData s 1)) false))

    (is (= (.getPhoneNumber (.getData s 3)) "1111"))
    (is (= (.getCarrier (.getData s 3)) "Verizon"))
    (is (= (.getGroup (.getData s 3)) "Players"))
    (is (= (.getRating (.getData s 3)) "rating"))
    (is (= (.getRank (.getData s 3)) 5))

    (is (= (.getName (.getData s 2)) "My Club"))

    (dosync
     (.putData s
               (-> (.getData s 3)
                   (.setPhoneNumber "1234")
                   (.setCarrier "Sprint")
                   (.setGroup "SuperPlayers")
                   (.setRating "better")
                   (.setRank 6))))

    (is (= (.getPhoneNumber (.getData s 0))) "1234")
    (is (= (.getCarrier (.getData s 0))) "Sprint")
    (is (= (.getGroup (.getData s 0)) "Coaches"))

    (is (= (.getPhoneNumber (.getData s 1)) "1111"))
    (is (= (.getCarrier (.getData s 1)) "Verizon"))
    (is (= (.getGroup (.getData s 1)) "Coaches"))
    (is (= (.getReffing (.getData s 1)) false))

    (is (= (.getPhoneNumber (.getData s 3))) "1234")
    (is (= (.getCarrier (.getData s 3))) "Sprint")
    (is (= (.getGroup (.getData s 3)) "SuperPlayers"))
    (is (= (.getRating (.getData s 3)) "better"))
    (is (= (.getRank (.getData s 3)) 6))
    
    (is (= (.getName (.getData s 2)) "My Club"))

    (dosync
     (.putData s (.setName (.getData s 2) "Your Club")))

    (is (= (.getPhoneNumber (.getData s 0))) "1234")
    (is (= (.getCarrier (.getData s 0))) "Sprint")
    (is (= (.getGroup (.getData s 0)) "Coaches"))

    (is (= (.getPhoneNumber (.getData s 1)) "1111"))
    (is (= (.getCarrier (.getData s 1)) "Verizon"))
    (is (= (.getGroup (.getData s 1)) "Coaches"))
    (is (= (.getReffing (.getData s 1)) false))

    (is (= (.getPhoneNumber (.getData s 3))) "1234")
    (is (= (.getCarrier (.getData s 3))) "Sprint")
    (is (= (.getGroup (.getData s 3)) "SuperPlayers"))
    (is (= (.getRating (.getData s 3)) "better"))
    (is (= (.getRank (.getData s 3)) 6))
    
    (is (= (.getName (.getData s 2)) "Your Club"))

    (dosync
     (.putData s
               (-> (.getData s 1)
                   (.setPhoneNumber "1234")
                   (.setCarrier "Sprint")
                   (.setGroup "Referees")
                   (.setReffing true))))

    (is (= (.getPhoneNumber (.getData s 0))) "1234")
    (is (= (.getCarrier (.getData s 0))) "Sprint")
    (is (= (.getGroup (.getData s 0)) "Coaches"))

    (is (= (.getPhoneNumber (.getData s 1)) "1234"))
    (is (= (.getCarrier (.getData s 1)) "Sprint"))
    (is (= (.getGroup (.getData s 1)) "Referees"))
    (is (= (.getReffing (.getData s 1)) true))

    (is (= (.getPhoneNumber (.getData s 3))) "1234")
    (is (= (.getCarrier (.getData s 3))) "Sprint")
    (is (= (.getGroup (.getData s 3)) "SuperPlayers"))
    (is (= (.getRating (.getData s 3)) "better"))
    (is (= (.getRank (.getData s 3)) 6))
    
    (is (= (.getName (.getData s 2)) "Your Club"))

    (is (empty? (.getObservers (.getData s 3))))
    (is (empty? (.getObservers (.getData s 4))))
    (dosync (.putData s (.addWatched (.getData s 0) 3)))
    (is (= (.getObservers (.getData s 3)) #{0}))
    (is (empty? (.getObservers (.getData s 4))))
    (dosync (.putData s (.addWatched (.addWatched (.getData s 1) 3) 4)))
    (is (= (.getObservers (.getData s 3)) #{0 1}))
    (is (= (.getObservers (.getData s 4)) #{1}))
    (dosync (.putData s (.clearWatched (.getData s 0))))
    (is (= (.getObservers (.getData s 3)) #{1}))
    (is (= (.getObservers (.getData s 4)) #{1}))
    (dosync (.putData s (.addWatched (.getData s 0) 4)))
    (is (= (.getObservers (.getData s 3)) #{1}))
    (is (= (.getObservers (.getData s 4)) #{0 1}))
    (dosync (.putData s (.removeWatched (.getData s 1) 4)))
    (is (= (.getObservers (.getData s 3)) #{1}))
    (is (= (.getObservers (.getData s 4)) #{0}))
    (dosync (.removeID s 0))
    (is (= (.getObservers (.getData s 3)) #{1}))
    (is (empty? (.getObservers (.getData s 4))))

    (is (empty? (.getMembers (.getData s 2))))
    (dosync (.putData s (.addClub (.getData s 3) 2))
            (.putData s (.addClub (.getData s 4) 2))
            (.putData s (.addClub (.getData s 1) 2)))
    (is (= (.getMembers (.getData s 2)) #{1 3 4}))
    (dosync (.putData s (.removeClub (.getData s 3) 2)))
    (is (= (.getMembers (.getData s 2)) #{1 4}))
    (dosync (.putData s (.clearClubs (.getData s 4)))
            (.putData s (.clearClubs (.getData s 1))))
    (is (empty? (.getMembers (.getData s 2))))))

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

(defn -getClubs [this]
  (get-type (.store this) IClub))

(defn -getReferees [this]
  (get-type (.store this) IReferee))

(defn -getPeopleForGroup [this group]
  (get-predicate #(= group (.getGroup %)) (.store this)))

(defn -getPerson [this id]
  (get-datum-type (.store this) id IPerson))

(defn -getPlayer [this id]
  (get-datum-type (.store this) id IPlayer))

(defn -getObservable [this id]
  (get-datum-type (.store this) id IObservable))

(defn -getClub [this id]
  (get-datum-type (.store this) id IClub))

(defn -getReferee [this id]
  (get-datum-type (.store this) id IReferee))

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

(defn -createClub [this & args]
  (apply make-club (.store this) args))

(defn -createReferee [this & args]
  (apply make-referee (.store this) args))

(defn -runTransaction [this transaction]
  (dosync (.run transaction)))

(defn -init []
  [[] (make-store)])