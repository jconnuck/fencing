package final_project.model;

public class CompletedBout {
	private int _ref;
	private int _pointsToWin;
	private PlayerBoutResult _winnerResult, _loserResult;

	public CompletedBout(PlayerBoutResult winnerResult, PlayerBoutResult loserResult, int ref) {
		_winnerResult = winnerResult;
		_loserResult = loserResult;
		_ref = ref;
	}

	//Where is this used?
	public void setPointsToWin(int toWin) {
		_pointsToWin = toWin;
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

	public int getRef(){
		return _ref;
	}

	public int getWinnerScore(){
		return _winnerResult.getPlayerScore();
	}

	public int getLoserScore(){
		return _loserResult.getPlayerScore();
	}
}