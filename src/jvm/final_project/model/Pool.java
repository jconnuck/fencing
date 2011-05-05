//tested
package final_project.model;

import java.util.*;

public abstract class Pool {
	protected List<Integer> _players;
	protected Collection<Integer> _refs;
	protected Collection<CompleteResult> _results;
	protected List<IncompleteResult> _incompleteResults;

	public Pool(){
		_players = new ArrayList<Integer>();
		_refs = new HashSet<Integer>();
		_incompleteResults = new LinkedList<IncompleteResult>();
		_results = new HashSet<CompleteResult>();
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

	//currently for testing only
	public List<IncompleteResult> getIncompleteResults(){
		return _incompleteResults;
	}

	public abstract List<? extends PlayerSeed> getSeeds();

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