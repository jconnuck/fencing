package final_project.model;

public class IncompleteResult extends Result{
	
	public IncompleteResult(int player1, int player2, int pointsToWin) {
		_player1 = player1;
		_player2 = player2;
		_pointsToWin = pointsToWin;
	}
	
	public void setPlayer1(int newP1) {
		_player1 = newP1;
	}
	
	public void setPlayer2(int newP2) {
		_player2 = newP2;
	}
}