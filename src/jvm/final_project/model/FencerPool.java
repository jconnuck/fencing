//tested
package final_project.model;

import java.util.*;

public class FencerPool extends Pool{
	HashMap<Integer, FencerRoundResults> _idToFencerResults;

	public FencerPool() {
		super();
		_idToFencerResults = new HashMap<Integer, FencerRoundResults>();
	}

	@Override
	//changes to return list to put the seedings in order
	public List<FencerSeed> getSeeds() {
		List<FencerSeed> fencerSeeds = new LinkedList<FencerSeed>();
		createIdToFencerResultsMap();
		tallyResults();
		populateSeedListFromResults(fencerSeeds);

		return fencerSeeds;
	}

	private void populateSeedListFromResults(List<FencerSeed> fencerSeeds) {
		for (FencerRoundResults result : _idToFencerResults.values())
			fencerSeeds.add(new FencerSeed(result));
	}

	private void createIdToFencerResultsMap() {
		for (Integer i : _players)
			_idToFencerResults.put(i, new FencerRoundResults(i));
	}

	private void tallyResults() {
		for (CompleteResult b : _results){
			FencerRoundResults winner = _idToFencerResults.get(b.getWinner());
			FencerRoundResults loser = _idToFencerResults.get(b.getLoser());
			winner.addWin();
			loser.addLoss();

			winner.addTouchesScored(b.getWinnerScore());
			loser.addTouchesScored(b.getLoserScore());

			winner.addTouchesReceived(b.getLoserScore());
			loser.addTouchesReceived(b.getWinnerScore());
		}
	}

	/**
	 * Based on the number of fencers in the pool, sets up the list of IncompleteResults to be fenced.
	 * @throws IllegalStateException
	 */
	public void createIncompleteResults() throws IllegalStateException{
		System.out.println("beginning hell method." + " players size " + _players.size());
		if(_players.size() > 8  || _players.size() < 4) {
			throw new IllegalStateException("Illegal pool size (>8 or <4");
		}
		else if(_players.size() == 4) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(3), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(2), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(3), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(5, temp);
		}
		else if(_players.size() == 5) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(0), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(3), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(2), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(4), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(0), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(4), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(1), 5);
			_incompleteResults.add(9, temp);
		}
		else if(_players.size() == 6) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(4), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(5), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(0), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(3), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(4), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(3), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(2), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(5), 5);
			_incompleteResults.add(9, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(1), 5);
			_incompleteResults.add(10, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(5), 5);
			_incompleteResults.add(11, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(0), 5);
			_incompleteResults.add(12, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(13, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(1), 5);
			_incompleteResults.add(14, temp);
		}

		else if(_players.size() == 7) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(0), _players.get(3), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(4), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(5), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(0), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(3), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(6), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(0), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(2), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(1), 5);
			_incompleteResults.add(9, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(6), 5);
			_incompleteResults.add(10, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(0), 5);
			_incompleteResults.add(11, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(5), 5);
			_incompleteResults.add(12, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(1), 5);
			_incompleteResults.add(13, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(4), 5);
			_incompleteResults.add(14, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(5), 5);
			_incompleteResults.add(15, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(3), 5);
			_incompleteResults.add(16, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(2), 5);
			_incompleteResults.add(17, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(4), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(6), 5);
			_incompleteResults.add(20, temp);
		}

		else if (_players.size() == 8) {
			IncompleteResult temp;
			temp = new IncompleteResult(_players.get(1), _players.get(2), 5);
			_incompleteResults.add(0, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(4), 5);
			_incompleteResults.add(1, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(3), 5);
			_incompleteResults.add(2, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(7), 5);
			_incompleteResults.add(3, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(1), 5);
			_incompleteResults.add(4, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(3), 5);
			_incompleteResults.add(5, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(5), 5);
			_incompleteResults.add(6, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(6), 5);
			_incompleteResults.add(7, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(0), 5);
			_incompleteResults.add(8, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(1), 5);
			_incompleteResults.add(9, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(2), 5);
			_incompleteResults.add(10, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(6), 5);
			_incompleteResults.add(11, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(1), 5);
			_incompleteResults.add(12, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(0), 5);
			_incompleteResults.add(13, temp);
			temp = new IncompleteResult(_players.get(6), _players.get(4), 5);
			_incompleteResults.add(14, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(5), 5);
			_incompleteResults.add(15, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(7), 5);
			_incompleteResults.add(16, temp);
			temp = new IncompleteResult(_players.get(4), _players.get(3), 5);
			_incompleteResults.add(17, temp);
			temp = new IncompleteResult(_players.get(5), _players.get(0), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(6), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(7), 5);
			_incompleteResults.add(20, temp);
			temp = new IncompleteResult(_players.get(1), _players.get(5), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(2), _players.get(4), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(6), 5);
			_incompleteResults.add(20, temp);
			temp = new IncompleteResult(_players.get(3), _players.get(5), 5);
			_incompleteResults.add(18, temp);
			temp = new IncompleteResult(_players.get(7), _players.get(1), 5);
			_incompleteResults.add(19, temp);
			temp = new IncompleteResult(_players.get(0), _players.get(2), 5);
			_incompleteResults.add(20, temp);
		}
	}
}
