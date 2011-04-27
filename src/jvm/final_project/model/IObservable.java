package final_project.model;

import java.util.*;

public interface IObservable extends IData {
    Collection<Integer> getObservers();
}