package final_project.control;

import java.util.*;
import final_project.model.*;
import final_project.model.DERound.NoSuchMatchException;

//Created in the main method.
public class TournamentController {

	private Collection<EventController> _events;
	private IDataStore _dataStore;
	private int _currentEventID;
	private StripController _stripController;
	private SMSController _smsController;

	public TournamentController() {
		_currentEventID = 0;
		_events = new LinkedList<EventController>();
		//_dataStore = new DataStore();
		_dataStore = null; //Setting this temporarily to null because I need it for SMSController
		_stripController = new StripController();
		_smsController = new SMSController(_dataStore, this);
	}

	public void addEvent(String weapon){
		_events.add(new EventController(++_currentEventID, _dataStore, weapon));
	}

	public void addEvent(String weapon, Collection<Integer> preregs){
		_events.add(new EventController(++_currentEventID, _dataStore, weapon, preregs));
	}

	public void startPoolRound(int eventID) throws IllegalStateException{
		Iterator<EventController> iter = _events.iterator();
		if(iter.hasNext()){
			if(!iter.next().startPoolRound()){
				throw new IllegalStateException("Not correct time to create pool round.");
			}
		}
		throw new IllegalStateException("No even created.");
	}

	/**
	 *Adds the given player to the given event.
	 * @param eventID
	 * @param playerID
	 * @return a boolean, true if player was added, false otherwise.
	 */
	public boolean addPlayer(int eventID, int playerID){
		Iterator<EventController> iter = _events.iterator();
		if(iter.hasNext()){
			iter.next().addPlayer(playerID);
			return true;
		}
		return false;
	}

	//public

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
					//TODO Send ref confirmation message
					return;
				} catch (NoSuchMatchException e) {
					//TODO Send ref error message
					//_smsController.sendMessage(message, number)
					e.printStackTrace();
				}
			}
		}
		//TODO text ref again
		//
	}

	/**
	 * Method called by the SMSController when two refs need to be
	 * swapped.
	 * @param oldRefID
	 * @param newRefID
	 */
	public void swapRef(int eventID, int oldRefID, int newRefID) {
		//TODO: empty stub
	}
}