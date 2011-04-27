package final_project.model;

public class FencerRoundResults extends PlayerRoundResults{
	private int _touchesScored, _touchesReceived;
	
	public FencerRoundResults(int player) {
		super(player);
	}
	
	public int getTouchesScored() {
		return _touchesScored;
	}

	public int getTouchesReceived() {
		return _touchesReceived;
	}
	
	public void addTouchesScored(int touchesScored){
		_touchesScored += touchesScored;
	}
	
	public void addTouchesReceived(int touchesReceived){
		_touchesReceived += touchesReceived;
	}
	
	public double getTouchSpread() {
		return getTouchesScored() - getTouchesReceived();
	}
}
