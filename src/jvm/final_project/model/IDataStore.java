package final_project.model;

import java.util.*;

public interface IDataStore {
    Collection<IData> getData();
    Collection<IPerson> getPeople();
    Collection<IPlayer> getPlayers();
    Collection<IObservable> getObservables();
    Collection<IClub> getClubs();
    Collection<IPerson> getPeopleForGroup(String group);
    IData getData(int id);
    void putData(IData person);
    void removeData(IData person);
    void removeID(int id);
    IPerson createSpectator(String phoneNumber, String carrier, String group);
    IPlayer createPlayer(String phoneNumber, String carrier, String group,
                         String rating, int rank);
    IClub createClub(String name);
    IReferee createReferee(String phoneNumber, String carrier, String group);
}