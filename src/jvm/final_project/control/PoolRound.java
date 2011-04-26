package final_project.control;

import java.util.Random;
import java.util.*;

//also serves as pool controller
public class PoolRound implements IRound{

	private Collection<Pool> _pools;
	private List<Integer> _results;
	private int _poolSize;
	private int _numFencers;
	
	public int getNumFencers(){
		return _numFencers;
	}
	
	public void setNumFencers(int newNumFencers){
		_numFencers = newNumFencers;
	}
	
	public boolean createPools(int poolSize){
		int this._poolSize = poolSize;
		int numPools = _numFencers / _poolSize;
		int leftOvers = _numFencers % _poolSize;
	}

	public int getPoolSize(){
		return _poolSize;
	}
		
	public void setPoolSize(int newSize){
		_poolSize = newSize;
	}

	public List<Integer> getResults(){
		if(_results == null)
			seedFromResults();
		return _results;
	}

	public List<Integer> getTopFencer(int num) {
		if(_results == null) {
			seedFromResults();
		}
		return _results.subList(0, num);
	}

	public void seedFromResults() {
		_results = new LinkedList<Integer>();
		List<FencerResults> fencerResults = new LinkedList<FencerResults>();
		for(Pool pool : _pools)
			fencerResults.addAll(pool.getFencerResults());
		Collections.sort(fencerResults);
		for(FencerResults fr : fencerResults)
			_results.add(fr.getFencer());
	}
}