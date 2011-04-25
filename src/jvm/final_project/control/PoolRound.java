package final_project.control;

import java.util.Random;
import java.util.*;

public class PoolRound implements IRound{

	private Collection<Pool> _pools;
	private List<Integer> _results;

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
