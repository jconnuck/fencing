package final_project.model;

public class CompleteResult extends Result{
	private PlayerResult _winnerResult, _loserResult;

	public CompleteResult(PlayerResult winnerResult, PlayerResult loserResult) {
		_winnerResult = winnerResult;
		_loserResult = loserResult;
	}

	@Override
	public int getPlayer1() {
		return _winnerResult.getPlayerId();
	}
	
	@Override
	public int getPlayer2() {
		return _loserResult.getPlayerId();
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

    @Override
    public String toString() {
        return "{"+getWinner()+"("+getWinnerScore()+"), "+getLoser()+"("+getLoserScore()+")}";
    }
}