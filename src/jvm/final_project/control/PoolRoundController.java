package final_project.control;

import final_project.model.*;

public class PoolRoundController {
	PoolRound _pools;
	
	public PoolRoundController() {
		_pools = new PoolRound();
	}

	public boolean createPools(int poolSize) {
		
		int numBigPools, numSmallPools;
		PoolSizeCalculator poolSizeCalc;
		
		try {
			 poolSizeCalc = new PoolSizeCalculator(_pools.getNumPlayers(), poolSize);
		} catch (IllegalArgumentException e) {
			return false;
		}
		
		numBigPools = poolSizeCalc.getNumBigPools();
		numSmallPools = poolSizeCalc.getNumSmallPools();

        _pools.setPoolSize(poolSize);
        //What are these doing?
        int numPools  = _pools.getNumPlayers() / _pools.getPoolSize();
        int leftOvers = _pools.getNumPlayers() % _pools.getPoolSize();
        return true;
	}
}
