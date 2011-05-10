package final_project.model;

import java.util.*;

import final_project.control.StripController;

public class FencerPoolRound extends PoolRound{
	public FencerPoolRound(List<Integer> initialSeeding, int numPools, int poolSize, StripController stripController) {
		_stripControl = stripController;
        _initialSeeding = initialSeeding;
		_poolSize = poolSize;
        _pools = new LinkedList<Pool>();
		Pool newPool;
		for (int i = 0; i < numPools; i++) {
			newPool = new FencerPool();
			_pools.add(newPool);
		}
	}

}
