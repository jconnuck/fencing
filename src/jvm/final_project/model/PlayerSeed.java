package final_project.model;

public abstract class PlayerSeed implements Comparable<PlayerSeed>{
	protected PlayerRoundResults _playerResults;
	
	public PlayerSeed(PlayerRoundResults playerResults) {
		_playerResults = playerResults;
	}

	public int getPlayer() {
		return _playerResults.getPlayer();
	}
	
	public PlayerRoundResults getPlayerResults() {
		return _playerResults;
	}

	@Override
	public int compareTo(PlayerSeed opponent) {
		if (this.isHigherSeededThan(opponent))
			return -1;
		else
			return 1;
	}
	
	private boolean isHigherSeededThan(PlayerSeed opponent)  {
		if (isClearWinner(opponent) || isTiebreakWinner(opponent))
			return true;
		return false;
	}

	abstract boolean isClearWinner(PlayerSeed opponent);
	
	abstract boolean isTiebreakWinner(PlayerSeed opponent);
}
