package final_project.control;

import java.util.*;

import final_project.model.*;
import final_project.model.DERound.NoSuchMatchException;

public class TournamentController {

	private Collection<EventController> _events;
	private IDataStore _dataStore;
	private StripController _stripController;

	public TournamentController() {
		_events = new LinkedList<EventController>();
		_stripController = new StripController();
//		_dataStore = new DataStore();
	}

	/**
	 * Takes an event and a fencer and calls registerFencer for that event and fencer.
	 * @param playerId The fencer to register
	 * @param event The event for which the fencer is registering
	 */
	public void registerPlayer(int playerId, IEvent event) {
		event.registerPlayer(playerId);
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