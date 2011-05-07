package final_project.control;

import java.util.*;

import final_project.model.*;

public class EventController {

	private Collection<Integer> _refs;
	private State _state;
	private DERoundController _deController;
	private PoolRoundController _poolController;
	private IDataStore _dataStore;
	private String _weapon;
	private Collection<Integer> _players;
	private int _eventID;

	public EventController(int id, IDataStore dataStore, String weapon){
		setup();
		_eventID = id;
		_dataStore = dataStore;
		_weapon = weapon;
	}

	public EventController(int id, IDataStore dataStore, String weapon, Collection<Integer> preregs){
		setup();
		_eventID = id;
		_weapon = weapon;
		_players.addAll(preregs);
	}

	//helper method for EventController constructor
	private void setup(){
		_state = State.REGISTRATION;
		_refs = new HashSet<Integer>();
		_players = new HashSet<Integer>();
	}

	public void addPlayer(int id){
		_players.add(id);
	}

	public void addRef(Integer refID){
		_refs.add(refID);
	}

	// Represents the phase of the tournament that the TournamentControl is ready to carry out
	public enum State {
		REGISTRATION, POOLS, DE, FINAL
	}

	public boolean hasRef(Integer ref){
		return _refs.contains(ref);
	}

	public void addCompletedResult(CompleteResult result) throws DERound.NoSuchMatchException{
		if(_state.equals(State.POOLS)){
			_poolController.addCompleteResult(result);
		}else if(_state.equals(State.DE)){
			_deController.addCompleteResult(result);
		}
	}

	/**
	 * Called in TournamentController;
	 * returns true if the pool round was started, false otherwise.
	 * @return
	 */
	public boolean startPoolRound(int poolSize) {
		if(_state != State.REGISTRATION)
			return false;
		_poolController = new PoolRoundController(_dataStore);
		boolean createPoolSuccess = _poolController.createPools(poolSize);
		if(!createPoolSuccess){
			_poolController = null;
		}
		return createPoolSuccess;
	}

	public Collection<PoolSizeInfo> getValidPoolSizes() {
		Collection<PoolSizeInfo> toReturn = new LinkedList<PoolSizeInfo>();
		PoolSizeCalculator poolSizeCalc;
		for(int i = 4; i < 9; i++){
			try{
				poolSizeCalc = new PoolSizeCalculator(_players.size(), i);
				toReturn.add(new PoolSizeInfo(i, poolSizeCalc.getNumBigPools(), poolSizeCalc.getNumSmallPools()));
			}catch(IllegalArgumentException e){

			}
		}
		return toReturn;
	}
}
