package final_project.model;

import java.util.*;

public class Tournament {
	Collection<IEvent> _events;
	IDataStore _dataStore;

	public IDataStore getDataStore() {
		return _dataStore;
	}
}
