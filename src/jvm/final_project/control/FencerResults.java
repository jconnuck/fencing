public class FencerResults implements Comparable{
	public int fencer;
	public int touchesScored, touchesReceived;
	public int wins, losses;

	//If a.compareTo(b) returns a negative number if a should be seeded higher
	//than b (a has better results)
	//If the the two FencerResults objects are equals, return 1 or -1 randomly
	public int compareTo(Object other){
		if(other instanceof FencerResults){
			FencerResults oth = (FencerResults)other;
			if(wins / (wins + losses) > oth.wins / (oth.wins + oth.losses)){
				return -1;
			}
			else if(wins / (wins + losses) < oth.wins / (oth.wins + oth.losses)){
				return 1;
			}

			else if(touchesScored - touchesReceived > oth.touchesScores - oth.touchesReceived){
				return -1;
			}
			else if(touchesScored - touchesReceived < oth.touchesScores - oth.touchesReceived){
				return 1;
			}
			else if(touchesScored > oth.touchesScored){
				return -1;
			}
			else if(touchesScored < oth.touchesScored){
				return 1;
			}else{
				Random r = new Random();
				int rand = r.nextInt(2);
				if(rand == 0) return -1;
				else return 1;
			}
		}
	}
}