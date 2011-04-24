import java.util.Collection;
import java.util.List;


public class Pool {
	private Collection<IPlayer> _fencers;
	private Collection<IReferee> _refs;
	private Collection<Bout> _results;
	private List<FutureBout> _futureBouts;
	
	
	public FutureBout getNextBout() {
		return _futureBouts.get(0);
	}
	
	public void addResult(Bout toAdd) throws IllegalArgumentException{
		
		if(!((toAdd.getWinner().equals(_futureBouts.get(0).getFencer1()) && 
				toAdd.getLoser().equals(_futureBouts.get(0).getFencer2()))    ||
	        (toAdd.getWinner().equals(_futureBouts.get(0).getFencer2()) && 
	    		toAdd.getLoser().equals(_futureBouts.get(0).getFencer1())))) {
				throw new IllegalArgumentException("Attempted to add result for bout that should not have been fenced now.");
		}
		_results.add(toAdd);
		_futureBouts.remove(0);
	}
	
	public Collection<Bout> getResults() {
		return _results;
	}
	
	public class FutureBout {
		private IPlayer _fencer1;
		private IPlayer _fencer2;
	
		public FutureBout(IPlayer f1, IPlayer f2) {
			_fencer1 = f1;
			_fencer2 = f2
		}
		
		public IPlayer[] getFencers(){
			return new IPlayer[] {_fencer1, _fencer2}; 
		}
		
		public IPlayer getFencer1(){
			return _fencer1;
		}
		public IPlayer getFencer2(){
			return _fencer2;
		}
	}
	
}
