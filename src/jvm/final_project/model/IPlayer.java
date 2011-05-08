package final_project.model;

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
    Rating getRating(String weapon);
    IPlayer addRating(String weapon, Rating rating);
    IPlayer removeRating(String weapon);
    IPlayer clearRatings();
    int getRank();
    IPlayer setRank(int rank);
    boolean getCheckedIn();
    IPlayer setCheckedIn(boolean checkedIn);
    IPlayer setFirstName(String name);
    IPlayer setLastName(String name);
}