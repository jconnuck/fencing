package final_project.model.store;

import java.util.*;

public interface IClub extends IObservable {
    String getName();
    IClub setName(String name);
    Collection<Integer> getMembers();
}