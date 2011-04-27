package final_project.model;

import java.util.*;

//also serves as pool controller
public class PoolRound implements IRound{

	private Collection<Pool> _pools;
	private List<Integer> _seedList;
	private int _poolSize;
	private int _numPlayers;
	
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
		if (_seedList == null)
			seedFromResults();
		return _seedList;
	}

	public List<Integer> getTopNPlayers(int num) {
		if (_seedList == null) {
			seedFromResults();
		}
		return _seedList.subList(0, num);
	}

	public void seedFromResults() {
		_seedList = new LinkedList<Integer>();
		List<PlayerSeed> playerSeeds = new LinkedList<PlayerSeed>();
		for (Pool pool : _pools)
			playerSeeds.addAll(pool.getSeeds());
		Collections.sort(playerSeeds);
		for (PlayerSeed playerSeed : playerSeeds)
			_seedList.add(playerSeed.getPlayer());
	}
}