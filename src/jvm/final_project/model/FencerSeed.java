package final_project.model;

import java.util.*;

public class FencerSeed extends PlayerSeed {
	private FencerRoundResults _fencerResults;

	public FencerSeed(PlayerRoundResults playerResults) {
		super(playerResults);
		_fencerResults = (FencerRoundResults)playerResults;
	}

	@Override
	public boolean isClearWinner(PlayerSeed playerOpponent) {
		if (this.isOfSameClass(playerOpponent)) {
			FencerRoundResults opponentResults = (FencerRoundResults)playerOpponent.getPlayerResults();
			if(this.hasBetterWinPercentage(opponentResults) ||
				hasBetterTouchSpread(opponentResults) ||
				hasMoreTouches(opponentResults))
				return true;
			return false;
		}
		else
			throw new ClassCastException();
	}

	private boolean isOfSameClass(PlayerSeed playerOpponent) {
		return _fencerResults.getClass() == playerOpponent.getPlayerResults().getClass();
	}

	private boolean hasBetterWinPercentage(FencerRoundResults opponentResults) {
		return _fencerResults.getWinningPercentage() > opponentResults.getWinningPercentage();
	}

	private boolean hasBetterTouchSpread(FencerRoundResults opponentResults) {
		return _fencerResults.getTouchSpread() > opponentResults.getTouchSpread();
	}

	private boolean hasMoreTouches(FencerRoundResults opponentResults) {
		return _fencerResults.getTouchesScored() > opponentResults.getTouchesScored();
	}

	@Override
	public boolean isTiebreakWinner(PlayerSeed opponent) {
		return chooseRandomWinner();
	}

	private boolean chooseRandomWinner() {
		Random r = new Random();
		int rand = r.nextInt(2);
		if (rand == 0)
			return true;
		return false;
	}
}
