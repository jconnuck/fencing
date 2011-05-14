package final_project.model;

public class CompleteResult extends Result{
	private PlayerResult _winnerResult, _loserResult;

    public CompleteResult(PlayerResult winnerResult, PlayerResult loserResult) {
        this(winnerResult, loserResult, true);
    }

    /**
     * @param keepOrder should getPlayer1() return getWinner()?
     */
	public CompleteResult(PlayerResult winnerResult, PlayerResult loserResult, boolean keepOrder) {
		_winnerResult = winnerResult;
		_loserResult = loserResult;

        if (keepOrder) {
            _player1 = winnerResult.getPlayerId();
            _player2 = loserResult.getPlayerId();
        } else {
            _player2 = winnerResult.getPlayerId();
            _player1 = loserResult.getPlayerId();
        }
	}

	public int getPointsToWin() {
		return _pointsToWin;
	}

    public PlayerResult getWinnerResult() {
        return _winnerResult;
    }

    public PlayerResult getLoserResult() {
        return _loserResult;
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