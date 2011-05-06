package final_project.input;

import java.util.*;
import clojure.lang.*;
import final_project.model.IDataStore;

public interface ITournamentInfo extends IPersistentMap {
    Collection<IEventInfo> getEvents();
    IDataStore getDataStore();
}