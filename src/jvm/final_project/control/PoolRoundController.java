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
        System.out.println("smsController null in PoolRoundController constructor "+(smsController==null));
        _smsController = smsController;
	}

	public boolean addCompleteResult(CompleteResult result) throws IllegalArgumentException{
        return _poolRound.addCompleteResult(result);
	}
	
	public boolean rescoreLastMatch(int ref, CompleteResult result) {
		return _poolRound.rescoreLastMatch(ref, result);
	}

	public boolean createPools(int poolSize) {
		System.out.println("createPools method : " + poolSize);
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
        	System.out.println("ref getReffing()" + ref.getReffing());
        	if(!ref.getReffing())
        		availableRefs.add(ref.getID());
        }
        System.out.println("allRefs size: " + allRefs.size() + "\n availableRefs size: " + availableRefs.size());
        _poolRound.assignReferees(availableRefs);
        _poolRound.assignStrips();
        _poolRound.createAllIncompleteResult();
        System.out.println("made it to end of createPools. num pools: " + _poolRound.getPools().size());
        for (Pool p : getPools()) {
            System.out.println( " num incomplete results" + p.getIncompleteResults().size());
            System.out.println( " num strips inside pool" + p.getStrips().size());
        }

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