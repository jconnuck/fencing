package final_project.model;

import java.util.*;

//also serves as pool controller
public abstract class PoolRound implements IRound{

	private IDataStore _dataStore;
	protected List<Pool> _pools;
	protected List<Integer> _resultSeedList;
	//TODO: delete protected List<Integer> _initialSeeding;
	protected int _poolSize;
	protected int _numPlayers;

	public boolean addCompleteResult(CompleteResult result){
		for(Pool p : _pools){
			if(poolHasResult(p, result)){
				if(p.addCompletedResult(result)){
					//code to reassign ref from p to a pool with no ref
					Iterator<Integer> iter = p.getRefs().iterator();
					for(Pool toCheck : _pools){
						if(!iter.hasNext())
							break;
						if(toCheck.getRefs().isEmpty() && !toCheck.getIncompleteResults().isEmpty()){
							toCheck.addRef(iter.next());
							iter.remove();
						}
					}
					while(iter.hasNext()){
						final Integer temp = iter.next();
						_dataStore.runTransaction(new Runnable(){
							public void run(){
								_dataStore.putData(_dataStore.getReferee(temp).setReffing(false));
							}
						});
					}
					p.clearRefs();
				}
				return true;
			}
		}
		return false;
	}

	//return true if the given pool p has the given CompleteResult as one of its matches
	private boolean poolHasResult(Pool p, CompleteResult result){
		return  p.getPlayers().contains(result.getLoser()) && p.getPlayers().contains(result.getWinner());
	}

	//returns boolean of whether or not an optimal ref assignment was achieved
	public boolean assignReferees(List<Integer> refs){
		boolean toReturn = true;
		Map<Pool, Integer> poolToNumConflicts = new HashMap<Pool,Integer>();
		for(Pool p : _pools){
			poolToNumConflicts.put(p, 0);
		}
		for(Pool pool : _pools){
			for(Integer ref : refs){
				if(haveConflict(pool, ref))
					poolToNumConflicts.put(pool, poolToNumConflicts.get(pool) + 1);
			}
		}

		//Standard Insertion Sort
        for(int i = 1; i < _pools.size(); i++){
            Pool tmp = _pools.get(i);
            int k = i;
            //TODO: check if this comparison is correct (might be > instead??)
            while((k>0) && poolToNumConflicts.get(_pools.get(k - 1)) <  poolToNumConflicts.get(tmp)){
                _pools.set(k, _pools.get(k-1));
                k--;
            }
            _pools.set(k, tmp);
        }

        Iterator<Integer> iter;
        for(Pool p : _pools){
        	//TODO: notify gui and fencers that pool does not have ref
        	if(refs.isEmpty()){
        		p.clearRefs();
        	}else{

        	int temp = -1;
        	iter = refs.iterator();
        	while(iter.hasNext()){
        		temp = iter.next();
        		if(!haveConflict(p, temp)){
        			p.addRef(temp);
        			iter.remove();
        			break;
        		}
        	}
        	if(temp == -1){
        		//this should never be -1, (notice that if refs is empty we break, so temp must be initialized to something other than -1)
        		throw new IllegalStateException("Structural error.  Must terminate.");//TODO: make this error message better
        	}
        	p.addRef(temp);
        	iter.remove();
        	toReturn = false;
        	}
        }
        return toReturn;
	}

	private boolean haveConflict(Pool p, Integer ref){
		Collection<Integer> col1 = new HashSet<Integer>();
		Collection<Integer> col2 = _dataStore.getReferee(ref).getClubs();

		for(Integer player : p.getPlayers()){
			col1.addAll(_dataStore.getPlayer(player).getClubs());
		}

		Set<Integer> intersection = new HashSet<Integer>();
		for(Integer i : col1){
			if(col2.contains(i))
				intersection.add(i);
		}
		return (intersection.size() != 0);
	}

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