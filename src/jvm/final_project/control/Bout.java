/*
 * The Bout class represents a completed bout
 * 
 */

public class Bout {
	private int _pointsToWin;  // This number of points a fencer needs to score to win the bout
	private IPlayer _winner;
	private IPlayer _loser;
	private IReferee _ref;
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
	
	public IPlayer getWinner() {
		return _winner;
	}
	
	public IPlayer getLoser() {
		return _loser;
	}
	
	public IReferee getRef(){
		return _ref;		
	}
	
	public int getWinnerScore(){
		return _winnerScore;
	}
	
	public int getLoserScore(){
		return _loserScore;
	}
}
