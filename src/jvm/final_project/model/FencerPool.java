package jvm.final_project.model;

import java.util.*;

public class FencerPool extends Pool{	
	HashMap<Integer, FencerRoundResults> _idToFencerResults = new HashMap<Integer, FencerRoundResults>();
	
	@Override
	public Collection<FencerSeed> getSeeds() {
		Collection<FencerSeed> fencerSeeds = new LinkedList<FencerSeed>();
		
		for(Integer i : _players)
			_idToFencerResults.put(i, new FencerRoundResults(i));
		for(CompletedBout b : _completedBouts){
			FencerRoundResults winner = _idToFencerResults.get(b.getWinner());
			FencerRoundResults loser = _idToFencerResults.get(b.getLoser());
			winner.addWin();
			loser.addLoss();

			winner.addTouchesScored(b.getWinnerScore());
			loser.addTouchesScored(b.getLoserScore());

			winner.addTouchesReceived(b.getLoserScore());
			loser.addTouchesReceived(b.getWinnerScore());
		}
		for(FencerRoundResults result : _idToFencerResults.values())
			fencerSeeds.add(new FencerSeed(result));
		
		return fencerSeeds;
	}
}
