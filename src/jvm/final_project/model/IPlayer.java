package final_project.model;

import java.util.*;

public interface IPlayer extends IHasClub, IPerson, IObservable {
    IPlayer setPhoneNumber(String phoneNumber);
    IPlayer setCarrier(String carrier);
    IPlayer setGroup(String group);
    IPlayer addWatched(int watched);
    IPlayer removeWatched(int watched);
    IPlayer clearWatched();
    IPlayer addClub(int club);
    IPlayer removeClub(int club);
    IPlayer clearClubs();
    String getRating(String weapon);
    IPlayer addRating(String weapon, String rating);
    IPlayer removeRating(String weapon);
    IPlayer clearRatings();
    //Is there a difference between rank and seed?
    int getRank();
    IPlayer setRank(int rank);
    public PlayerSeed getSeed();
    IPlayer setFirstName(String name);
    IPlayer setLastName(String name);
}