package final_project.control;

import final_project.model.*;

public class PoolRoundController {
	PoolRound _poolRound;
	
	public PoolRoundController() {
	}

	public boolean createPools(int poolSize) {
		
		int numPools, numBigPools, numSmallPools;
		PoolSizeCalculator poolSizeCalc;
		
		try {
			 poolSizeCalc = new PoolSizeCalculator(_poolRound.getNumPlayers(), poolSize);
		} catch (IllegalArgumentException e) {
			return false;
		}
		
		numBigPools = poolSizeCalc.getNumBigPools();
		numSmallPools = poolSizeCalc.getNumSmallPools();
		numPools = numBigPools + numSmallPools;

		_poolRound = new FencerPoolRound(numPools, poolSize);
        _poolRound.populatePools();		
        
        return true;
	}

}
