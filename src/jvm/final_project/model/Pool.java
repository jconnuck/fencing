package final_project.model;

import java.util.*;


public abstract class Pool {
	protected Collection<Integer> _players;
	protected Collection<Integer> _refs;
	protected Collection<Result> _completedBouts;
	protected List<FutureBout> _futureBouts;
	
	public Pool(){
		_players = new HashSet<Integer>();
		_refs = new HashSet<Integer>();
		_futureBouts = new LinkedList<FutureBout>();
	}
	
	public Collection<Result> getResults() {
		return _completedBouts;
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

	public FutureBout getNextBout() {
		return _futureBouts.get(0);
	}
	
	public abstract Collection<? extends PlayerSeed> getSeeds();

	public void addCompletedBout(Result bout) throws IllegalArgumentException{
		if (isPrematureBout(bout)) {
			throw new IllegalArgumentException("Attempted to add result for bout that should not have been fenced now.");
		}
		else {
			_completedBouts.add(bout);
			_futureBouts.remove(0);
		}
	}

	private boolean isPrematureBout(Result bout) {
		return !((bout.getWinner() == _futureBouts.get(0).getFencer1() && 
				bout.getLoser() == _futureBouts.get(0).getFencer2())) ||
				(bout.getWinner() == _futureBouts.get(0).getFencer2() &&
	    		bout.getLoser() == _futureBouts.get(0).getFencer1());
	}
}