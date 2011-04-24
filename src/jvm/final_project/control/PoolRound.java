import java.util.Random;


public class PoolRound implements IRound{

	private Collection<Pool> _pools;
	private List<Integer> _results;

	public List<Integer> getTopFencer(int num) {
		if(_results == null) {
			seedFromResults();
		}
		return _results.subList(0, num);
	}

	public void seedFromResults() {

	}


}
