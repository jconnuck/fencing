package final_project.model.store;

import java.util.*;

public interface IObservable extends IData {
    Collection<Integer> getObservers();
}