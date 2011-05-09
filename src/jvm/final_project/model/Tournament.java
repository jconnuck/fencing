package final_project.model;

import java.util.*;
import final_project.model.store.*;

public class Tournament {
	Collection<IEvent> _events;
	IDataStore _dataStore;

	public IDataStore getDataStore() {
		return _dataStore;
	}
}
