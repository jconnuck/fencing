package final_project.control;

import java.util.*;
import final_project.model.*;
import final_project.model.DERound.NoSuchMatchException;

//Created in the main method.
public class TournamentController implements Constants{

	private Collection<EventController> _events;
	private Collection<int[]> _eventsStripSizes;
	private IDataStore _dataStore;
	private int _currentEventID;
	private StripController _stripController;
	private SMSController _smsController;
	private DataFormattingHelper _dataHelper;

	public TournamentController(String username, String password) {
		_currentEventID = 0;
		_events = new LinkedList<EventController>();
		_eventsStripSizes = new LinkedList<int[]>();
		_dataStore = new DataStore();
		_stripController = new StripController();

		_dataHelper = new DataFormattingHelper(_dataStore);
		_smsController = new SMSController(_dataStore, this, username, password);
	}

	public void addEvent(String weapon){
		_events.add(new EventController(++_currentEventID, _dataStore, weapon));
	}

	public void addEvent(String weapon, Collection<Integer> preregs){
		_events.add(new EventController(++_currentEventID, _dataStore, weapon, preregs));
	}

	public void setStripSizes(int stripRows, int stripCols) {
		Iterator<int[]> iter = _eventsStripSizes.iterator();
		if(iter.hasNext()) {
			
		}
		else { //creating the int[] for the first time
			_eventsStripSizes.add(new int[2]);
		}
	}
	public void startPoolRound(int eventID, int poolSize) throws IllegalStateException{
		Iterator<EventController> iter = _events.iterator();
		if(iter.hasNext()){
			if(!iter.next().startPoolRound(poolSize)){
				throw new IllegalStateException("Not correct time to create pool round.");
			}
		}
		throw new IllegalStateException("No event created.");
	}

	public Collection<PoolSizeInfo> getValidPoolSizes(int eventID){
		Iterator<EventController> iter = _events.iterator();
		if(iter.hasNext()){
			return iter.next().getValidPoolSizes();
		}
		throw new IllegalStateException("No event created.");
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
					_smsController.sendMessage("Result successfully submitted!", _dataStore.getReferee(ref).getPhoneNumber());
					return;
				} catch (NoSuchMatchException e) {
					_smsController.sendMessage("Error: result could not be processed", _dataStore.getReferee(ref).getPhoneNumber());
					e.printStackTrace();
				}
			}
		}
		//TODO text ref again --> why? -mk
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

	/* METHODS TO MAKE IT POSSIBLE FOR THE GUI TO GET INFORMATION FROM THE DATA STORE */
	public Object[][] checkInFencer(int playerID, boolean checkAs) {
		final IPlayer temp = _dataStore.getPlayer(playerID).setCheckedIn(checkAs);
		_dataStore.runTransaction(new Runnable(){
			public void run(){
				_dataStore.putData(temp);
			}
		});
		return _dataHelper.giveSignInPanelInfo();
	}

	//Checks in all players as the boolean parameter
	public Object[][] checkInAll(boolean checkAs) {
		for (IPlayer i: _dataStore.getPlayers()){
			final IPlayer temp = i.setCheckedIn(checkAs);
			_dataStore.runTransaction(new Runnable(){
				public void run(){
					_dataStore.putData(temp);
				}
			});
		}
		return _dataHelper.giveSignInPanelInfo();
	}

	public Object[][] giveSubscriberTableInfo() {
		return _dataHelper.giveSubscriberTableInfo();
	}

	public Object[][] giveSignInPanelInfo() {
		return _dataHelper.giveSignInPanelInfo();
	}

	public Object[][] registerSpectator(String number, String firstName, String lastName) {
		final IPerson temp =  _dataStore.createSpectator(number, firstName, lastName, "", "Spectator");
		_dataStore.runTransaction(new Runnable(){
			public void run(){
				_dataStore.putData(temp);
			}
		});
		//Should text new spectator that they were registered, so they'll have a phone number to respond to
		//_smsController.sendMessage("Thank you for registering with our system! Please respond " +
			 //"to this phone number with your follower requests.", number);
		return _dataHelper.giveSubscriberTableInfo();
	}

	public Object[][] registerAndCheckInFencer(String number, String firstName, String lastName, int rank) {
		final IPlayer p = _dataStore.createPlayer(number, firstName, lastName, "", "Fencer", rank).setCheckedIn(true);
		_dataStore.runTransaction(new Runnable(){
			public void run(){
				_dataStore.putData(p);
			}
		});
		return _dataHelper.giveSignInPanelInfo();
	}
	
	public void registerFencer(String number, String firstName, String lastName, int rank) {
		final IPlayer p = _dataStore.createPlayer(number, firstName, lastName, "", "Fencer", rank);
		_dataStore.runTransaction(new Runnable(){
			public void run(){
				_dataStore.putData(p);
			}
		});
	}

    public Collection<PoolSizeInfo> getValidPoolSizes() {
        if (!_events.empty())
            return _events.iterator().next().getValidPoolSizes();
        return new ArrayList<PoolSizeInfo>()
    }

	public Object[][] getPoolSizeInfoTable() {
		return _dataHelper.getPoolSizeInfoTable(_stripRows, _stripCols);
	}
}