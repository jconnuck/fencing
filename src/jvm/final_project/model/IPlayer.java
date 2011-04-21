package final_project.model;

import java.util.*;

public interface IPlayer extends IPerson, IObservable {
    IPlayer setPhoneNumber(String phoneNumber);
    IPlayer addWatched(int watched);
    IPlayer removeWatched(int watched);
    IPlayer clearWatched();
    String getRating();
    IPlayer setRating(String rating);
    int getRank();
    IPlayer setRank(int rank);
}