package final_project.model;

public class PlayerResult {
	private int _playerId;
	private int _playerScore;

	public PlayerResult(int playerId, int playerScore) {
		_playerId = playerId;
		_playerScore = playerScore;
	}

	public int getPlayerId() {
		return _playerId;
	}

	public int getPlayerScore() {
		return _playerScore;
	}
}
