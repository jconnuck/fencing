package final_project.model;

import java.util.*;

public class DERound implements IRound {
	private List<Integer> _seeding;  /*!< The seeding of fencers based on pool results. */
	private double _cut;         /*!< The percentage of the fencers to be cut before the round starts */
	private List<Result> _bouts;  /*!< List of all bouts in this DE round. */
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
		int newEnd = (int) Math.ceil(_seeding.size() * (1 - _cut));
		_seeding = _seeding.subList(0, newEnd);
	}
	
	/**
	 * Calculates the proper bracket size using the number of fencers in the round
	 */
	private void calcBracketSize() throws IllegalArgumentException{
		if(_seeding.size() < 2) 
			throw new IllegalArgumentException("Attempted to build a bracket for less than 2 fencers");
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
		IncompleteResult tempBout;
		for(int i = 0; i < _bracketSize /2; i++) {
			tempBout = new IncompleteResult();
			tempBout.setFencer1(_seeding.get(i));
		}
	}
	
	public void setCut(double newCut){
		_cut = newCut;
	}
	
	public double getCut() {
		return _cut;
	}
	
	public int getCurrentBracketSize() {
		return _currentBracket;
	}
	
	public IncompleteResult getNextBout() {
		for(Result result : _bouts) {
			if(result instanceof ) {
				
			}
		}
		return _bouts.get(0);
		_bouts.remove(0);
	}

	@Override
	public List<Integer> getTopNPlayers(int num) {
		// TODO Auto-generated method stub
		return null;
	}
	
	



}