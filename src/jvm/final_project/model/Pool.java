package final_project.model;

import java.util.*;


public abstract class Pool {
	protected Collection<Integer> _players;
	protected Collection<Integer> _refs;
	protected Collection<CompletedBout> _completedBouts;
	protected List<FutureBout> _futureBouts;

	public void addPlayer(int id){
		_players.add(id);
	}

	public Pool(){
		_players = new HashSet<Integer>();
		_refs = new HashSet<Integer>();
		_futureBouts = new LinkedList<FutureBout>();
	}

	public FutureBout getNextBout() {
		return _futureBouts.get(0);
	}
	
	public abstract Collection<? extends PlayerSeed> getSeeds();

	public void addResult(CompletedBout toAdd) throws IllegalArgumentException{
		if(!((toAdd.getWinner() == _futureBouts.get(0).getFencer1() &&
				toAdd.getLoser() == _futureBouts.get(0).getFencer2()))    ||
	        (toAdd.getWinner() == _futureBouts.get(0).getFencer2() &&
	    		toAdd.getLoser() == _futureBouts.get(0).getFencer1())) {
				throw new IllegalArgumentException("Attempted to add result for bout that should not have been fenced now.");
		}
		_completedBouts.add(toAdd);
		_futureBouts.remove(0);
	}

	public Collection<CompletedBout> getResults() {
		return _completedBouts;
	}

	public Collection<Integer> getPlayers() {
		return _players;
	}

	public Collection<Integer> getRefs() {
		return _refs;
	}

	public class FutureBout {
		private int _fencer1;
		private int _fencer2;

		public FutureBout(int f1, int f2) {
			_fencer1 = f1;
			_fencer2 = f2;
		}

		public int[] getFencers(){
			return new int[] {_fencer1, _fencer2};
		}

		public int getFencer1(){
			return _fencer1;
		}
		public int getFencer2(){
			return _fencer2;
		}
	}
}