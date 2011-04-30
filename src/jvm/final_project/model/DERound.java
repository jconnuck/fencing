package final_project.control;

public class DERound implements IRound {
	private List<int> _seeding;  /*!< The seeding of fencers based on pool results. */
	private double _cut;         /*!< The percentage of the fencers to be cut before the round starts */
	private List<FutureBout> _bouts;  /*!< List of bouts to be fenced for this round. */
	private int _bracketSize;    /*!< The bracket size (How many slots there are in the first round of DEs) (must be a power of 2) */
	private int _currentBracket; /*!< The number of slots in the current stage of the DE round (must be a power of 2 and <= _bracketSize*/
	
	/**
	 * Sets up the DE round by cutting the bottom _cut percentage of the competitors and filling the bracket with the remaining competitors.
	 */
	public void setupRound() {
		makeCut();
		calcBracketSize();
		populateBracket();
	}
	
	/**
	 * Cuts the bottom _cut percentage of fencers from _seeding
	 */
	public void makeCut() {
		int newEnd = Math.ceil(_seeding.size() * (1 - cut));
		_seeding = _seeding.subList(0, newEnd);
	}
	
	/**
	 * Calculates the proper bracket size using the number of fencers in the round
	 */
	private void calcBracketSize() throws IllegalArgumentException{
		if(_seeding.size() < 2) 
			throw IllegalArgumentException("Attempted to build a bracket for less than 2 fencers");
		int curSize = 2;
		while(curSize < _seeding.size()) {
			curSize *= 2;
		}
		_bracketSize = curSize;
	}

	/**
	 * Populates the bracket
	 */
	public void populateBracket() {
		FutureBout tempBout;
		for(int i = 0; i < _bracketSize /2; i++) {
			tempBout = new tempBout();
			tempBout.setFencer1(_seeding.get(i));
		}
	}
	
	public void setCut(double newCut){
		_cut = newCut;
	}
	
	public void getCut() {
		return _cut;
	}
	
	public void getCurrentBracketSize() {
		return _currentBracket;
	}
	
	public FutureBout getNextBout() {
		
	}
	
	



}