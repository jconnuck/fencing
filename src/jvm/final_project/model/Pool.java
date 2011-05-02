package final_project.model;

import java.util.*;


public abstract class Pool {
	protected List<Integer> _players;
	protected Collection<Integer> _refs;
	protected Collection<CompleteResult> _results;
	protected List<IncompleteResult> _incompleteResults;
	
	public Pool(){
		_players = new ArrayList<Integer>();  ///// THIS used to be a hashset... why?
		_refs = new HashSet<Integer>();
		_incompleteResults = new LinkedList<IncompleteResult>();
	}
	
	/**
	 * Returns the Collection of all completed matches.
	 * @return Collection<CompletResult> All matches currently completed.
	 */
	public Collection<CompleteResult> getResults() {
		return _results;
	}

	/**
	 * Based on the number of fencers in the pool, sets up the list of IncompleteResults to be fenced.
	 * @throws IllegalStateException
	 */
	public void createIncompleteResults() throws IllegalStateException{
		if(_players.size() > 8  || _players.size() < 4) {
			throw new IllegalStateException("Illegal pool size (>8 or <4");
		}
		
		else if(_players.size() == 4) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(3), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(2), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(3), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(5, temp);			
		}

		else if(_players.size() == 5) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(0), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(3), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(2), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(4), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(0), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(4), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(1), 5);
			_incompleteResults.add(9, temp);
		}
		
		else if(_players.size() == 6) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(4), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(5), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(0), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(3), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(4), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(3), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(2), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(5), 5);
			_incompleteResults.add(9, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(1), 5);
			_incompleteResults.add(10, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(5), 5);
			_incompleteResults.add(11, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(0), 5);
			_incompleteResults.add(12, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(13, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(1), 5);
			_incompleteResults.add(14, temp);
		}
		
		else if(_players.size() == 7) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(3), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(4), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(5), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(0), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(3), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(6), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(0), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(2), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(1), 5);
			_incompleteResults.add(9, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(6), 5);
			_incompleteResults.add(10, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(0), 5);
			_incompleteResults.add(11, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(5), 5);
			_incompleteResults.add(12, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(1), 5);
			_incompleteResults.add(13, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(4), 5);
			_incompleteResults.add(14, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(5), 5);
			_incompleteResults.add(15, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(3), 5);
			_incompleteResults.add(16, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(2), 5);
			_incompleteResults.add(17, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(4), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(6), 5);
			_incompleteResults.add(20, temp);
		}

		else if (_players.size() == 8) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(4), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(3), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(7), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(5), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(6), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(0), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(1), 5);
			_incompleteResults.add(9, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(2), 5);
			_incompleteResults.add(10, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(6), 5);
			_incompleteResults.add(11, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(1), 5);
			_incompleteResults.add(12, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(0), 5);
			_incompleteResults.add(13, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(4), 5);
			_incompleteResults.add(14, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(5), 5);
			_incompleteResults.add(15, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(7), 5);
			_incompleteResults.add(16, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(3), 5);
			_incompleteResults.add(17, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(0), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(6), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(7), 5);
			_incompleteResults.add(20, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(5), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(4), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(6), 5);
			_incompleteResults.add(20, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(5), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(1), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(2), 5);
			_incompleteResults.add(20, temp);
		}
	}
	
	/**
	 * Returns the List of player IDs in the pool.
	 * @return List<Integer> The list of player IDs in the pool.
	 */
	public List<Integer> getPlayers() {
		return _players;
	}

	public Collection<Integer> getRefs() {
		return _refs;
	}

	public void addPlayer(int id){
		_players.add(id);
	}

	/**
	 * Gets the next match to be fenced.
	 * @return IncompleteResult The next match to be fenced.
	 */
	public IncompleteResult getNextResult() {
		return _incompleteResults.get(0);
	}
	
	public abstract Collection<? extends PlayerSeed> getSeeds();

	/**
	 * Adds its argument to the list of completed results if it matches the result the pool is expecting next.
	 * @param completeResult The result to be added to the pools collection of completed results.
	 * @throws IllegalArgumentException
	 */
	public void addCompletedResult(CompleteResult completeResult) throws IllegalArgumentException{
		if (isPrematureResult(completeResult)) {
			throw new IllegalArgumentException("Attempted to add result for bout that should not have been fenced now.");
		}
		else {
			_results.add(completeResult);
			_incompleteResults.remove(0);
		}
	}

	private boolean isPrematureResult(CompleteResult completeResult) {
		return !((completeResult.getWinner() == _incompleteResults.get(0).getPlayer1() && 
				completeResult.getLoser() == _incompleteResults.get(0).getPlayer2())) ||
				(completeResult.getWinner() == _incompleteResults.get(0).getPlayer2() &&
	    		completeResult.getLoser() == _incompleteResults.get(0).getPlayer1());
	}
}