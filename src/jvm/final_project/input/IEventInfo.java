package final_project.input;

import java.util.*;
import clojure.lang.*;

public interface IEventInfo extends IPersistentMap {
    String getWeaponType();
    Collection<Integer> getPreregs();
}