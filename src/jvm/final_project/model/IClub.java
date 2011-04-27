package final_project.model;

import java.util.*;

public interface IClub extends IObservable {
    String getName();
    IClub setName(String name);
    Collection<Integer> getMembers();
}