package final_project.control;

import final_project.model.*;
import java.util.*;

public class PoolRoundController {
	PoolRound _poolRound;
	
	public PoolRoundController() {
		_poolRound = new PoolRound();
	}

	public boolean createPools(int poolSize) {
		
		int numBigPools, numSmallPools;
		PoolSizeCalculator poolSizeCalc;
		
		try {
			 poolSizeCalc = new PoolSizeCalculator(_poolRound.getNumPlayers(), poolSize);
		} catch (IllegalArgumentException e) {
			return false;
		}
		
		numBigPools = poolSizeCalc.getNumBigPools();
		numSmallPools = poolSizeCalc.getNumSmallPools();

        _poolRound.setPoolSize(poolSize);
        populatePools(numBigPools + numSmallPools);		
        return true;
	}

	
	/**
	 * Assigns fencers to the proper pools given the number of pools to be filled
	 * @param numPools  The number of pools to be filled
	 * @param fencers  The ordered list of the fencers to be placed in pools
	 */
	private void populatePools(int numPools) {
		FencerPool temp;
		for(int i=0; i <= numPools; i++) {
			temp = new FencerPool();
			_poolRound._pools.add(temp);
		}
		int i = 0;
		for(int fencer : _poolRound._seedList) {
			if(i >= _poolRound._pools.size())
				i = 0;
				
			_poolRound._pools.get(i)._players.add(fencer);
			i++;
		}
	}
}
