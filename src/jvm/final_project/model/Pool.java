package final_project.model;

import java.util.*;


public abstract class Pool {
	protected Collection<Integer> _players;
	protected Collection<Integer> _refs;
	protected Collection<Result> _results;
	protected List<IncompleteResult> _incompleteResults;
	
	public Pool(){
		_players = new HashSet<Integer>();
		_refs = new HashSet<Integer>();
		_incompleteResults = new LinkedList<IncompleteResult>();
	}
	
	public Collection<Result> getResults() {
		return _results;
	}

	public Collection<Integer> getPlayers() {
		return _players;
	}

	public Collection<Integer> getRefs() {
		return _refs;
	}

	public void addPlayer(int id){
		_players.add(id);
	}

	public IncompleteResult getNextBout() {
		return _incompleteResults.get(0);
	}
	
	public abstract Collection<? extends PlayerSeed> getSeeds();

	public void addCompletedBout(Result bout) throws IllegalArgumentException{
		if (isPrematureBout(bout)) {
			throw new IllegalArgumentException("Attempted to add result for bout that should not have been fenced now.");
		}
		else {
			_results.add(bout);
			_incompleteResults.remove(0);
		}
	}

	private boolean isPrematureBout(Result bout) {
		return !((bout.getWinner() == _incompleteResults.get(0).getPlayer1() && 
				bout.getLoser() == _incompleteResults.get(0).getPlayer2())) ||
				(bout.getWinner() == _incompleteResults.get(0).getPlayer2() &&
	    		bout.getLoser() == _incompleteResults.get(0).getPlayer1());
	}
}