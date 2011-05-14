package final_project.model;

import java.util.*;
import final_project.control.*;
import final_project.view.PoolObserverPanel.Status;
import final_project.model.store.*;

public abstract class Pool {
	protected List<Integer> _players;
	protected Collection<Integer> _refs;
	protected List<CompleteResult> _results;
	protected List<IncompleteResult> _incompleteResults;
	protected Collection<Integer> _strips;
	protected Collection<PoolObserver> _observers;
	protected Status _status;
    protected IDataStore _dataStore;

	public Pool(){
		_players = new ArrayList<Integer>();
		_refs = new HashSet<Integer>();
		_incompleteResults = new LinkedList<IncompleteResult>();
		_results = new ArrayList<CompleteResult>();
		_observers = new LinkedList<PoolObserver>();
		_strips = new LinkedList<Integer>();
		_status = Status.WAITING; //reset when a referee is added (in addRef)

	}

	public void addObserver(PoolObserver observer) {
		_observers.add(observer);
	}

	public void removeObserver(PoolObserver observer) {
		_observers.remove(observer);
	}
	
	public Collection<Integer> getPeople() {
		Collection<Integer> toReturn = new LinkedList<Integer>();
		toReturn.addAll(_refs);
		toReturn.addAll(_players);
		return toReturn;
	}

	//currently for testing only
	public int numPlayers(){
		return _players.size();
	}

	public void shufflePlayers() {
		Collections.shuffle(_players);
	}
	/**
	 * Returns the Collection of all completed matches.
	 * @return Collection<CompletResult> All matches currently completed.
	 */
	public Collection<CompleteResult> getResults() {
		return _results;
	}

	/**
	 * Returns the List of player IDs in the pool.
	 * @return List<Integer> The list of player IDs in the pool.
	 */
	public List<Integer> getPlayers() {
		return _players;
	}

	public Collection<Integer> getStrips() {
		return _strips;
	}

	public void addStrip(Integer toAdd) {
		_strips.add(toAdd);
	}

	public void clearStrips() {
		_strips.clear();
	}

	public Status getStatus() {
		return _status;
	}

	public void setStatus(Status newStatus) {
		_status = newStatus;
	}

	public Collection<Integer> getRefs() {
		return _refs;
	}

	public void addPlayer(int id){
		_players.add(id);
	}

	public void addRef(int i){
        final int id = i;
		_refs.add(id);
        _dataStore.runTransaction(new Runnable() {
                public void run() {
                    _dataStore.putData(_dataStore.getReferee(id).setReffing(true));
                }
            });
		if(_status == Status.WAITING)
			_status = Status.FENCING;
	}
	
	public void clearRefs(){
        for (int ref : _refs) {
            final int id = ref;
            _dataStore.runTransaction(new Runnable() {
                    public void run() {
                        _dataStore.putData(_dataStore.getReferee(id).setReffing(false));
                    }
                });
        }
		_refs.clear();
	}

	/**
	 * Gets the next match to be fenced.
	 * @return IncompleteResult The next match to be fenced.
	 */
	public IncompleteResult getNextResult() {
		if(_incompleteResults.isEmpty())
			return null;
		return _incompleteResults.get(0);
	}

	/**
	 * @return IncompleteResult The on deck match (the second element of _incompleteResult
	 * because the first one should already be in progress).
	 */
	public IncompleteResult getOnDeckResult() {
		if(_incompleteResults.size() <= 1)
			return null;
		return
		_incompleteResults.get(1);
	}

	/**
	 * @return IncompleteResult The match that is in the hole (the third element of _incompleteResults
	 * because the first one should already be in progress.
	 */
	public IncompleteResult getInHoleBout() {
		if(_incompleteResults.size() > 2)
			return null;
		return
		_incompleteResults.get(2);
	}

	//currently for testing only
	public List<IncompleteResult> getIncompleteResults(){
		return _incompleteResults;
	}

	public boolean isDone() {
		return _incompleteResults.isEmpty();
	}

	public abstract List<? extends PlayerSeed> getSeeds();

	/**
	 * Adds its argument to the list of completed results if it matches the result the pool is expecting next.
	 * @param completeResult The result to be added to the pools collection of completed results.
	 * @throws IllegalArgumentException
	 * @return a boolean true if all of this pool's matches have been completed.
	 */
	public boolean addCompletedResult(CompleteResult completeResult) throws IllegalArgumentException{
		if (isPrematureResult(completeResult)) {
			throw new IllegalArgumentException("Attempted to add result for bout that should not have been fenced now.");
		}else {
			_results.add(completeResult);
			_incompleteResults.remove(0);
			for (PoolObserver obs : _observers)
				obs.addCompleteResult(completeResult);
			if (_incompleteResults.isEmpty()) {
				for (PoolObserver obs : _observers)
					obs.setStatus(Status.DONE);
				return true;
			}
			return false;
		}
	}

	private boolean isPrematureResult(CompleteResult completeResult) {
		return !((completeResult.getWinner() == _incompleteResults.get(0).getPlayer1() &&
				completeResult.getLoser() == _incompleteResults.get(0).getPlayer2()) ||
				(completeResult.getWinner() == _incompleteResults.get(0).getPlayer2() &&
						completeResult.getLoser() == _incompleteResults.get(0).getPlayer1()));
	}

	public boolean rescoreLastMatch(CompleteResult newScore) {
		if(_results.size() ==0)
			return false;

		CompleteResult oldScore = _results.get(_results.size() - 1);
		if((oldScore.getPlayer1() == newScore.getPlayer1()  &&  oldScore.getPlayer2() == newScore.getPlayer2())  ||
				( oldScore.getPlayer1() == newScore.getPlayer2()  &&  oldScore.getPlayer2() == newScore.getPlayer1())) {
			_results.remove(_results.size() - 1);
			_results.add(newScore);
			for (PoolObserver obs : _observers)
				obs.changeMatchResult(newScore);

			return true;
		}
		else
			return false;
	}
}
