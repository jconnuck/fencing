package final_project.control;

import java.util.*;
import java.awt.Point;

import jvm.final_project.model.*;

//also serves as pool controller
public class PoolRound implements IRound{

	private Collection<Pool> _pools;
	private List<Integer> _seedList;
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
			if (curFencers == numFencers) {
				return new java.awt.Point(numBigPools, numSmallPools);
			}

			numBigPools ++;
			curFencers = numBigPools * poolSize;
		}

		while (curFencers >= numFencers) {
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
        Point p = calcPoolSize(_numFencers, poolSize);
        if (p.equals(Constants.INVALID_POOL_SIZE))
        	return false;
        _poolSize = poolSize;
        int numPools = _numFencers / _poolSize;
        int leftOvers = _numFencers % _poolSize;
        return true;
	}

	public int getPoolSize(){
		return _poolSize;
	}
		
	public void setPoolSize(int newSize){
		_poolSize = newSize;
	}

	public List<Integer> getResults(){
		if (_seedList == null)
			seedFromResults();
		return _seedList;
	}

	public List<Integer> getTopNPlayers(int num) {
		if (_seedList == null) {
			seedFromResults();
		}
		return _seedList.subList(0, num);
	}

	public void seedFromResults() {
		_seedList = new LinkedList<Integer>();
		List<PlayerSeed> playerSeeds = new LinkedList<PlayerSeed>();
		for (Pool pool : _pools)
			playerSeeds.addAll(pool.getSeeds());
		Collections.sort(playerSeeds);
		for (PlayerSeed playerSeed : playerSeeds)
			_seedList.add(playerSeed.getPlayer());
	}

	@Override
	public Collection<Integer> getTopNFencers(int num) {
		// TODO Auto-generated method stub
		return null;
	}
}