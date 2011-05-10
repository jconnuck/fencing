(ns final_project.model.store.DataStore
  (:import [final_project.model.store IPerson IPlayer IDataStore
            IObservable DataStore IHasClub IReferee IData IClub])
  (:require [clojure.set :as set]
            [clojure.string :as str])
  (:gen-class :main false
              :state store
              :implements [final_project.model.store.IDataStore]
              :init init))

(defn capitalize-sym [id]
  (symbol (apply str
                 (Character/toUpperCase (first (str id)))
                 (rest (str id)))))

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

(defn make-associative-methods
  ([id]
     (make-associative-methods id
                               (capitalize-sym id)
                               (capitalize-sym id)))
  ([id method-name singular-method-name & _]
     (let [key (keyword id)
           add (symbol-concat 'add singular-method-name)
           remove (symbol-concat 'remove singular-method-name)
           clear (symbol-concat 'clear method-name)
           get (symbol-concat 'get singular-method-name)]
       `((~add [this# name# val#]
               (assoc this# ~key (assoc ~id name# val#)))
         (~remove [this# name#]
                  (assoc this# ~key (dissoc ~id name#)))
         (~clear [this#]
                 (assoc this# ~key {}))
         (~get [this# name#]
               (~id name#))))))

(defn make-interface [{:keys [name read-only has-one has-many has-map]}]
  `(~name ~@(map #(apply make-reader %) read-only)
          ~@(map #(apply make-reader %) has-one)
          ~@(map #(apply make-writer %) has-one)
          ~@(map #(apply make-reader %) has-many)
          ~@(apply concat
                   (map #(apply make-group-writers %) has-many))
          ~@(apply concat
                   (map #(apply make-associative-methods %) has-map))))

(defn make-record [name & interfaces]
  `(defrecord ~name
     ~(vec (apply concat
                  (for [{:keys [read-only
                                has-one
                                has-many
                                has-map]} interfaces]
                    (map first (concat read-only has-one has-many has-map)))))
     ~@(mapcat make-interface interfaces)))

(defmacro define-record [& args]
  (apply make-record args))

(define-record Spectator
  {:name IData
   :read-only [[id ID]]}
  {:name IPerson
   :has-one [[phoneNumber] [carrier] [group] [firstName] [lastName]]
   :has-many [[watched]]})

(define-record Player
  {:name IData
   :read-only [[id ID]]}
  {:name IHasClub
   :has-many [[clubs Clubs Club]]}
  {:name IPerson
   :has-one [[phoneNumber] [carrier] [group] [firstName] [lastName]]
   :has-many [[watched]]}
  {:name IPlayer
   :read-only [[observers]]
   :has-one [[rank] [checkedIn]]
   :has-map [[ratings Ratings Rating]]})

(define-record Referee
  {:name IData
   :read-only [[id ID]]}
  {:name IHasClub
   :has-many [[clubs Clubs Club]]}
  {:name IPerson
   :has-one [[phoneNumber] [carrier] [group] [firstName] [lastName]]
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

(defn make-spectator [store phone-number first-name last-name carrier group]
  (make-data #(Spectator. % phone-number carrier group first-name last-name #{}) store))

(defn make-player [store phone-number first-name last-name carrier group rank]
  (make-data #(Player. % #{} phone-number carrier group first-name last-name
                       #{} #{} rank false {})
             store))

(defn make-club [store name]
  (make-data #(Club. % #{} name) store))

(defn make-referee [store phone-number first-name last-name carrier group]
  (make-data #(Referee. % #{} phone-number carrier group first-name last-name
                        #{} false)
             store))

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
  (get-predicate #(and (isa? (class %) IPerson)
                       (= group (.getGroup %)))
                 (.store this)))

(defn -getPeopleWithoutGroup [this group]
  (get-predicate #(and (isa? (class %) IPerson)
                       (not= group (.getGroup %)))
                 (.store this)))

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

(defn -getNextReferee [this]
  (.runTransaction this
                   #(let [refs (filter (complement :isReffing)
                                       (.getReferees this))]
                      (if (not (empty refs))
                        (let [ref (.setReffing (first refs)
                                               true)]
                          (.putData this ref)
                          (:id ref))
                        -1))))

(comment
  (defn parse-text [str]
    (mapcat #(let [split (str/split % #"-")]
               (case (count split)
                     1 (list (Integer/parseInt (first split)))
                     2 (range (Integer/parseInt (first split))
                              (inc (Integer/parseInt (second split))))
                     (throw (IllegalArgumentException.
                             "wrong number of dash-separated numbers"))))
            (str/split str #",")))


  (def get-char
       (zipmap (range 62)
               (concat (range 10)
                       "abcdefghijklmnopqrstuvwxyz"
                       "ABCDEFGHIJKLMNOPQRSTUVWXYZ")))

  (defn rand-str [n]
    (apply str
           (repeatedly n #(get-char (int (rand 62))))))

  (for [ref all-refs :when (:reffing ref)]
    (:id ref))

  (map :id (filter :reffing all-refs)))