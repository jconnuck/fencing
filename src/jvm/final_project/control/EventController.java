package final_project.control;

import java.util.Collection;
import final_project.model.*;


public class EventController {
	private Collection<Integer> _refs;
	private State _state;
	private DERoundController _deController;
	private PoolRoundController _poolController;
	private IDataStore _dataStore;

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
}
