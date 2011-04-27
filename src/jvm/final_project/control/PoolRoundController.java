package final_project.control;

import java.awt.Point;
import final_project.model.*;

public class PoolRoundController {
	PoolRound pools = new PoolRound(); 

	public boolean createPools(int poolSize) {
		int numBigPools, numSmallPools;
		PoolSizeCalculator poolSizeCalc;
		
		try {
			 poolSizeCalc = new PoolSizeCalculator(pools.getNumPlayers(), poolSize);
		} catch (IllegalArgumentException e) {
			return false;
		}
		
		numBigPools = poolSizeCalc.getNumBigPools();
		numSmallPools = poolSizeCalc.getNumSmallPools();
        pools.setPoolSize(poolSize);
        //What are these doing?
        int numPools = pools.getNumPlayers() / pools.getPoolSize();
        int leftOvers = pools.getNumPlayers() % pools.getPoolSize();
        return true;
	}
}
