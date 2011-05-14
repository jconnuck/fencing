package final_project.model;

import java.util.*;

import final_project.control.SMSController;
import final_project.control.StripController;
import final_project.model.store.IDataStore;

public class FencerPoolRound extends PoolRound{
	public FencerPoolRound(IDataStore store, List<Integer> initialSeeding, int numPools, int poolSize, StripController stripController, SMSController smsController) {
		_dataStore = store;
		_stripControl = stripController;
        _initialSeeding = initialSeeding;
		_poolSize = poolSize;
        _pools = new LinkedList<Pool>();
		Pool newPool;
		for (int i = 0; i < numPools; i++) {
			newPool = new FencerPool(_dataStore);
			_pools.add(newPool);
		}
		_smsController = smsController;
	}

}
