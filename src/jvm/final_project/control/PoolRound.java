
public class PoolRound implements IRound{

	private class FencerResults{
		public IPlayer fencer;
		public int indicator;
		public double winLossRatio;
		public int wins, losses;
 	}
	
	Collection<Pool> _pools;
	List<IPlayer> _results;
	
	public List<IPlayer> getTopFencer(int num) {
		if(_results == null) {
			seedFromResults();
		}
		return _results.subList(0, num);
	}
	
	public void seedFromResults() {
		
	}	
}
