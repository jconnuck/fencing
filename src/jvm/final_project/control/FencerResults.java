package jvm.final_project.control;

import java.util.Random;

public class FencerResults implements Comparable<FencerResults>{
	public int fencer;
	public int touchesScored, touchesReceived;
	public int wins, losses;

	public void addWin(){
		wins++;
	}
	
	public int getFencer(){
		return fencer;
	}
	
	public void addLoss(){
		losses++;
	}
	public void addTouchesScored(int toAdd){
		touchesScored += toAdd;
	}
	public void addTouchesReceived(int toAdd){
		touchesReceived += toAdd;
	}

	public FencerResults(int theFencer){
		this.fencer = theFencer;
	}

	//If a.compareTo(b) returns a negative number if a should be seeded higher
	//than b (a has better results)
	//If the the two FencerResults objects are equals, return 1 or -1 randomly
	public int compareTo(FencerResults other){
		if(wins / (wins + losses) > other.wins / (other.wins + other.losses)){
			return -1;
		}
		else if(wins / (wins + losses) < other.wins / (other.wins + other.losses)){
			return 1;
		}

		else if(touchesScored - touchesReceived > other.touchesScored - other.touchesReceived){
			return -1;
		}
		else if(touchesScored - touchesReceived < other.touchesScored - other.touchesReceived){
			return 1;
		}
		else if(touchesScored > other.touchesScored){
			return -1;
		}
		else if(touchesScored < other.touchesScored){
			return 1;
		}else{
			Random r = new Random();
			int rand = r.nextInt(2);
			if(rand == 0) return -1;
			else return 1;
		}
	}
}