package final_project.model;

import java.util.*;

//also serves as pool controller
public abstract class PoolRound implements IRound{

	protected List<Pool> _pools;
	protected List<Integer> _resultSeedList;
	//TODO: delete protected List<Integer> _initialSeeding;
	protected int _poolSize;
	protected int _numPlayers;

	public int getNumPlayers(){
		return _numPlayers;
	}

	public void setNumPlayers(int numPlayers){
		_numPlayers = numPlayers;
	}

	public int getPoolSize(){
		return _poolSize;
	}

	public void setPoolSize(int newSize){
		_poolSize = newSize;
	}

	public List<Integer> getResults(){
		if (_resultSeedList == null)
			seedFromResults();
		return _resultSeedList;
	}

	public List<Integer> getTopNPlayers(int num) {
		if (_resultSeedList == null) {
			seedFromResults();
		}
		return _resultSeedList.subList(0, num);
	}

	public void seedFromResults() {
		_resultSeedList = new LinkedList<Integer>();
		List<PlayerSeed> playerSeeds = new LinkedList<PlayerSeed>();
		for (Pool pool : _pools)
			playerSeeds.addAll(pool.getSeeds());
		Collections.sort(playerSeeds);
		for (PlayerSeed playerSeed : playerSeeds)
			_resultSeedList.add(playerSeed.getPlayer());
	}

	public void populatePools() {
		for (int i = 0; i < _numPlayers; ++i)
			_pools.get(i % _pools.size()).addPlayer(_resultSeedList.get(i));
	}
}