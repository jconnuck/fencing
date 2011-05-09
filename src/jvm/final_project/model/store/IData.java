package final_project.model.store;

import clojure.lang.IPersistentMap;

public interface IData extends IPersistentMap {
    int getID();
}