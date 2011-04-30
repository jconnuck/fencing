package final_project.model;

public class IncompleteResult {
	
	private int _player1;
	private int _player2;

	public IncompleteResult(int player1, int player2) {
		_player1 = player1;
		_player2 = player2;
	}

	public int[] getPlayers() {
		return new int[] {_player1, _player2};
	}

	public int getPlayer1(){
		return _player1;
	}
	public int getPlayer2(){
		return _player2;
	}
}