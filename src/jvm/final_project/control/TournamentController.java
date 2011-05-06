package final_project.control;

import java.util.*;

import final_project.model.*;
import final_project.model.DERound.NoSuchMatchException;

public class TournamentController {

	private Collection<EventController> _events;
	private IDataStore _dataStore;
	private int _currentEventID;
	private StripController _stripController;

	public TournamentController() {
		_currentEventID = 0;
		_events = new LinkedList<EventController>();
		_dataStore = new DataStore();
		_stripController = new StripController();
	}

	public void addEvent(String weapon){
		_events.add(new EventController(++_currentEventID, _dataStore, weapon));
	}

	public void addEvent(String weapon, Collection<Integer> preregs){
		_events.add(new EventController(++_currentEventID, _dataStore, weapon, preregs));
//		_dataStore = new DataStore();
	}

	/**
	 *Adds the given player to the given event.
	 * @param playerID
	 * @param eventID
	 * @return a boolean, true if player was added, false otherwise.
	 */
	public boolean addPlayer(int playerID, int eventID){
		Iterator<EventController> iter = _events.iterator();
		if(iter.hasNext()){
			iter.next().addPlayer(playerID);
			return true;
		}
		return false;
	}

	/**
	 * Takes a completed bout and adds it to appropriate event.
	 * @param bout A completed bout to be added to results
	 * @param stripID The number of the strip the bout was fenced on
	 */
	public void addCompletedResult(CompleteResult result, int ref) {
		for(EventController event : _events){
			if(event.hasRef(ref)){
				try {
					event.addCompletedResult(result);
					return;
				} catch (NoSuchMatchException e) {
					e.printStackTrace();
					//TODO text ref back saying problem with result
				}
			}
		}
		//TODO text ref again
		//
	}
}