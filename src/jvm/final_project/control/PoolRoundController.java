package final_project.control;

import java.util.*;

import final_project.model.*;
import final_project.model.store.*;

public class PoolRoundController {
	private PoolRound _poolRound;
	private int _numPools, _numBigPools, _numSmallPools;
	private IDataStore _dataStore;
    private List<Integer> _initialSeeding;
    private StripController _stripController;
    private SMSController _smsController;
    private int _poolSize;

	public PoolRoundController(IDataStore ds, List<Integer> initialSeeding, StripController stripController, SMSController smsController, int poolSize) {
		this._poolSize = poolSize;
		_stripController = stripController;
		//_poolRound = new FencerPoolRound();
		_dataStore = ds;
        _initialSeeding = initialSeeding;
        _smsController = smsController;
	}

	public boolean addCompleteResult(CompleteResult result) throws IllegalArgumentException{
		System.out.println("Calling complete result on pool round");
        return _poolRound.addCompleteResult(result);
	}
	
	public boolean rescoreLastMatch(int ref, CompleteResult result) {
		return _poolRound.rescoreLastMatch(ref, result);
	}

	public boolean createPools(int poolSize) {

		//Try to calculate the pool size. If unable to do so, return false
		try {
			calcPoolSize(poolSize);
		} catch (IllegalArgumentException e) {
			return false;
		}

		_poolRound = new FencerPoolRound(_dataStore, _initialSeeding, _numPools, poolSize, _stripController, _smsController);
        _poolRound.populatePools();
        Collection<IReferee> allRefs = _dataStore.getReferees();
        List<Integer> availableRefs = new  LinkedList<Integer>();

        for(IReferee ref: allRefs){
        	if(!ref.getReffing())
        		availableRefs.add(ref.getID());
        }
        _poolRound.assignReferees(availableRefs);
        _poolRound.assignStrips();
        _poolRound.createAllIncompleteResult();

        return true;
	}

	public void notifyNewPools() {
		_poolRound.notifyPools();
	}

	private void calcPoolSize(int poolSize) throws IllegalArgumentException{
		PoolSizeCalculator poolSizeCalc = new PoolSizeCalculator(_initialSeeding.size(), poolSize);
		_numBigPools = poolSizeCalc.getNumBigPools();
		_numSmallPools = poolSizeCalc.getNumSmallPools();
		_numPools = _numBigPools + _numSmallPools;
	}

	public List<Pool> getPools() {
		return _poolRound.getPools();
	}

    public List<Integer> getResults() {
        return _poolRound.getResults();
    }
}