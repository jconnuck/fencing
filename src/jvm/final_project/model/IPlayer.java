package final_project.model;

import java.util.*;

public interface IPlayer extends IPerson, IObservable {
    IPlayer setPhoneNumber(String phoneNumber);
    IPlayer setCarrier();
    IPlayer setGroup(String group);
    IPlayer addWatched(int watched);
    IPlayer removeWatched(int watched);
    IPlayer clearWatched();
    IPlayer addClub(int club);
    IPlayer removeClub(int club);
    IPlayer clearClubs();
    String getRating();
    IPlayer setRating(String rating);
    //Is there a difference between rank and seed?
    int getRank();
    IPlayer setRank(int rank);
    public PlayerSeed getSeed();
}