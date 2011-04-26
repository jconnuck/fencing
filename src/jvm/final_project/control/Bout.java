package final_project.control;

/*
 * The Bout class represents a completed bout
 *
 */

public class Bout {
	private int _pointsToWin;  // This number of points a fencer needs to score to win the bout
	private int _winner;
	private int _loser;
	private int _ref;
	private int _winnerScore;
	private int _loserScore;

	public Bout(IPlayer win, int winScore, IPlayer lose, int loseScore, IReferee ref) {
		_winner = win;
		_winnerScore = winScore;
		_loser = lose;
		_loserScore = loseScore;
		_ref = ref;
	}

	public void setPointsToWin(int toWin) {
		_pointsToWin = toWin;
	}

	public int getWinner() {
		return _winner;
	}

	public int getLoser() {
		return _loser;
	}

	public int getRef(){
		return _ref;
	}

	public int getWinnerScore(){
		return _winnerScore;
	}

	public int getLoserScore(){
		return _loserScore;
	}
}