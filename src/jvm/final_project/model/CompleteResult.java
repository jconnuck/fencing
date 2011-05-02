package final_project.model;

public class CompleteResult extends Result{
	private PlayerResult _winnerResult, _loserResult;

	public CompleteResult(PlayerResult winnerResult, PlayerResult loserResult) {
		_winnerResult = winnerResult;
		_loserResult = loserResult;
	}

	public int getPointsToWin() {
		return _pointsToWin;
	}

	public int getWinner() {
		return _winnerResult.getPlayerId();
	}

	public int getLoser() {
		return _loserResult.getPlayerId();
	}

	public int getWinnerScore(){
		return _winnerResult.getPlayerScore();
	}

	public int getLoserScore(){
		return _loserResult.getPlayerScore();
	}
}