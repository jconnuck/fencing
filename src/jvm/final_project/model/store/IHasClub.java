package final_project.model.store;

import java.util.*;

public interface IHasClub extends IData {
    Collection<Integer> getClubs();
    IHasClub addClub(int club);
    IHasClub removeClub(int club);
    IHasClub clearClubs();
}