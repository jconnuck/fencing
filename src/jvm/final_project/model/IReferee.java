package final_project.model;

public interface IReferee {
    boolean getReffing();
    IReferee setReffing(boolean isReffing);
    IReferee setPhoneNumber(String phoneNumber);
    IReferee setCarrier(String carrier);
    IReferee setGroup(String group);
    IReferee addWatched(int watched);
    IReferee removeWatched(int watched);
    IReferee clearWatched();
    IReferee addClub(int club);
    IReferee removeClub(int club);
    IReferee clearClubs();
    IReferee setFirstName(String name);
    IReferee setLastName(String name);
}