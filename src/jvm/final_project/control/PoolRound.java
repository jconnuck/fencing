package src.jvm.final_project.control;

import java.util.*;

//also serves as pool controller
public class PoolRound implements IRound{

	private Collection<Pool> _pools;
	private List<Integer> _results;
	private int _poolSize;
	private int _numFencers;
	
	public int getNumFencers(){
		return _numFencers;
	}
	
	public void setNumFencers(int newNumFencers){
		_numFencers = newNumFencers;
	}
	
	/**
	 * Calculates the number of pools of either size required to put all fencers into pools.
	 * @param numFencers A non-negative integer representing the number of fencers to be placed into pools
	 * @param poolSize A non-negative integer representing the ideal number of fencers to be placed in each pool
	 * @return A Java.awt.Point representing the proper number of pools of poolSize fencers(X) and the number of pools of poolsize - 1 fencers(Y)
	 */
	public static java.awt.Point calcPoolSize(int numFencers, int poolSize) throws IllegalArgumentException{
		if (poolSize <= 0 || numFencers <= 0 || numFencers < poolSize) {
			throw new IllegalArgumentException("Invalid pool size or number of fencers");
		}
		if (numFencers == poolSize) {
			return new java.awt.Point(1, 0);
		}
		
		int curFencers = 0;
		int numBigPools = 0;
		int numSmallPools = 0;

		while (curFencers <= numFencers) {
//			System.out.println(curFencers + ", " + numFencers);
			if (curFencers == numFencers) {
				return new java.awt.Point(numBigPools, numSmallPools);
			}

			numBigPools ++;
			curFencers = numBigPools * poolSize;
		}
//		System.out.println("-----------------");
		while (curFencers >= numFencers) {
//			System.out.println(curFencers + ", " + numFencers);
			if (curFencers == numFencers) {
				if (numBigPools == 0)
					return new java.awt.Point(-1, -1);
				return new java.awt.Point(numBigPools, numSmallPools);
			}

			numSmallPools++;
			numBigPools--;
			curFencers = (numBigPools * poolSize) + (numSmallPools * (poolSize - 1));
		}
		// invalidPoolSize is a global constant
		//return invalidPoolSize;
		return new java.awt.Point(-1, -1);
	}
	
	public boolean createPools(int poolSize){
		this._poolSize = poolSize;
		int numPools = _numFencers / _poolSize;
		int leftOvers = _numFencers % _poolSize;
	}

	public int getPoolSize(){
		return _poolSize;
	}
		
	public void setPoolSize(int newSize){
		_poolSize = newSize;
	}

	public List<Integer> getResults(){
		if (_results == null)
			seedFromResults();
		return _results;
	}

	public List<Integer> getTopFencer(int num) {
		if (_results == null) {
			seedFromResults();
		}
		return _results.subList(0, num);
	}

	public void seedFromResults() {
		_results = new LinkedList<Integer>();
		List<FencerResults> fencerResults = new LinkedList<FencerResults>();
		for (Pool pool : _pools)
			fencerResults.addAll(pool.getFencerResults());
		Collections.sort(fencerResults);
		for (FencerResults fr : fencerResults)
			_results.add(fr.getFencer());
	}
}