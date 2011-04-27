package final_project.model;

public class PlayerRoundResults {
	private int _player;
	private int _wins, _losses;
	
	public PlayerRoundResults(int player){
		_player = player;
	}

	public int getPlayer(){
		return _player;
	}
	
	public int getWins() {
		return _wins;
	}
	
	public int getLosses() {
		return _losses;
	}

	public void addWin(){
		_wins++;
	}
	
	public void addLoss(){
		_losses++;
	}
	
	public double getWinningPercentage() {
		return getWins() / (getWins() + getLosses());
	}
}