(ns final_project.test.datastore-tests
  (:use [clojure test]
        [final_project.model DataStore])
  (:import [final_project.model DataStore]))

(deftest observers-test
  (let [s (DataStore.)]
    (is (empty? (.getData s)))
    (.runTransaction
     s
     (reify Runnable
            (run [this]
                 (doto s
                   (.putData (.createSpectator s "1111" "John" "Smith" "Verizon" "Spectators"))
                   (.putData (.createReferee s "1111" "John" "Smith" "Verizon" "Coaches"))
                   (.putData (.createClub s "My Club"))
                   (.putData (.createPlayer s "1111" "John" "Smith" "Verizon"
                                            "Players" 5))
                   (.putData (.createPlayer s "1111" "John" "Smith" "Verizon"
                                            "Players" 5))))))

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
    (is (= (.getRating (.getData s 3) "gravity gun") nil))
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
    (is (= (.getRating (.getData s 3) "gravity gun") nil))
    (is (= (.getRank (.getData s 3)) 5))

    (is (= (.getName (.getData s 2)) "My Club"))

    (dosync
     (.putData s
               (-> (.getData s 3)
                   (.setPhoneNumber "1234")
                   (.setCarrier "Sprint")
                   (.setGroup "SuperPlayers")
                   (.addRating "gravity gun" "amazing")
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
    (is (= (.getRating (.getData s 3) "gravity gun") "amazing"))
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
    (is (= (.getRating (.getData s 3) "gravity gun") "amazing"))
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
    (is (= (.getRating (.getData s 3) "gravity gun") "amazing"))
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

(deftest test-symbol-concat
  (are [x y] (= (apply symbol-concat x) y)
       ['asdf 'dfsa] 'asdfdfsa
       ['foo 'BaR 'bAz 'baSH] 'fooBaRbAzbaSH))

(deftest test-capitalize
  (are [x y] (= (capitalize-sym x) y)
       'asdf 'Asdf
       'fast 'Fast
       'asdfAsdf 'AsdfAsdf))

(deftest test-club
  (let [store (DataStore.)
        club (.createClub store "name")]
    (dosync (.putData store club))
    (is (= (:name (first (.getClubs store))) "name"))))