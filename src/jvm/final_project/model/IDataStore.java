package jvm.final_project.model;

import java.util.*;

public interface IDataStore {
    Collection<IData> getData();
    Collection<IPerson> getPeople();
    Collection<IPlayer> getPlayers();
    Collection<IObservable> getObservables();
    IData getData(int id);
    void putData(int id);
    void removeData(IData person);
    void removeID(int id);
    IPerson createSpectator(String phoneNumber);
    IPlayer createPlayer(String phoneNumber, String rating, int rank);
}