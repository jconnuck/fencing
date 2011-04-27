package final_project.control;

import java.awt.Point;
import final_project.model.*;

public class PoolRoundController {
	PoolRound pools = new PoolRound(); 

	public boolean createPools(int poolSize){
        Point p = calcPoolSize(pools.getNumPlayers(), poolSize);
        if (p.equals(Constants.INVALID_POOL_SIZE))
        	return false;
        pools.setPoolSize(poolSize);
        //What are these doing?
        int numPools = pools.getNumPlayers() / pools.getPoolSize();
        int leftOvers = pools.getNumPlayers() % pools.getPoolSize();
        return true;
	}
	
	
	//We have to discuss this function. Aside from the Point making me uneasy (but w/e)
	//What happens if the poolSize is such that the function never terminates?
	//I think we may need a new algorithm, possibly one that chooses the optimal poolSize, as opposed to letting
	//the user choose
	/**
	 * Calculates the number of pools of either size required to put all fencers into pools.
	 * @param numPlayers A non-negative integer representing the number of fencers to be placed into pools
	 * @param poolSize A non-negative integer representing the ideal number of fencers to be placed in each pool
	 * @return A Java.awt.Point representing the proper number of pools of poolSize fencers(X) and the number of pools of poolsize - 1 fencers(Y)
	 */
	public static java.awt.Point calcPoolSize(int numPlayers, int poolSize) throws IllegalArgumentException{
		int placedFencers = 0;
		int numBigPools = 0;
		int numSmallPools = 0;
		
		if (isInvalidPoolSizeForNumPlayers(numPlayers, poolSize)) {
			throw new IllegalArgumentException("Invalid pool size or number of players");
		}
		
		if (numPlayers == poolSize) {
			return new java.awt.Point(1, 0);
		}
        
		while (placedFencers <= numPlayers) {
			if (placedFencers == numPlayers) {
				return new java.awt.Point(numBigPools, numSmallPools);
			}

			numBigPools++;
			placedFencers = numBigPools * poolSize;
		}

		while (placedFencers > numPlayers) {
			if (placedFencers == numPlayers) {
				if (numBigPools == 0)                    
					return new java.awt.Point(-1, -1);
				return new java.awt.Point(numBigPools, numSmallPools);
			}

			numSmallPools++;
			numBigPools--;
			placedFencers = (numBigPools * poolSize) + (numSmallPools * (poolSize - 1));
		}
		//Does this ever return given the first if statement?
		return Constants.INVALID_POOL_SIZE;
	}

	private static boolean isInvalidPoolSizeForNumPlayers(int numPlayers,
			int poolSize) {
		return poolSize <= 0 || numPlayers <= 0 || numPlayers < poolSize;
	}
}
