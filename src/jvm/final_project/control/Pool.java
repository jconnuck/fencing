package final_project.control;

import java.util.*;

public class Pool {
	private Collection<Integer> _fencers;
	private Collection<Integer> _refs;
	private Collection<Bout> _results;
	private List<FutureBout> _futureBouts;

	public void addFencer(int id){
		_fencers.add(id);
	}

	public Pool(){
		setFencers(new HashSet<Integer>());
		setRefs(new HashSet<Integer>());
		_futureBouts = new LinkedList<FutureBout>();
	}

	public FutureBout getNextBout() {
		return _futureBouts.get(0);
	}
	
	public Collection<FencerResults> getFencerResults(){
		HashMap<Integer, FencerResults> idToFencerResults = new HashMap<Integer, FencerResults>();
		for(Integer i : _fencers)
			idToFencerResults.put(i, new FencerResults(i));
		for(Bout b : _results){
			FencerResults winner = idToFencerResults.get(b.getWinner());
			FencerResults loser = idToFencerResults.get(b.getLoser());
			winner.addWin();
			loser.addLoss();

			winner.addTouchesScored(b.getWinnerScore());
			loser.addTouchesScored(b.getLoserScore());

			winner.addTouchesReceived(b.getLoserScore());
			loser.addTouchesReceived(b.getWinnerScore());
		}
		return idToFencerResults.values();
	}

	public void addResult(Bout toAdd) throws IllegalArgumentException{
		if(!((toAdd.getWinner() == _futureBouts.get(0).getFencer1() &&
				toAdd.getLoser() == _futureBouts.get(0).getFencer2()))    ||
	        (toAdd.getWinner() == _futureBouts.get(0).getFencer2() &&
	    		toAdd.getLoser() == _futureBouts.get(0).getFencer1())) {
				throw new IllegalArgumentException("Attempted to add result for bout that should not have been fenced now.");
		}
		_results.add(toAdd);
		_futureBouts.remove(0);
	}

	public Collection<Bout> getResults() {
		return _results;
	}



	public void setFencers(Collection<Integer> _fencers) {
		this._fencers = _fencers;
	}

	public Collection<Integer> getFencers() {
		return _fencers;
	}



	public void setRefs(Collection<Integer> _refs) {
		this._refs = _refs;
	}

	public Collection<Integer> getRef() {
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