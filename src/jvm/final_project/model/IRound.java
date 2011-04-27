package final_project.model;

import java.util.*;

public interface IRound {
	/**
	 * @param num The number of fencers to return
	 * @return Top "num" fencers as an ordered List of IPlayer by seeding
	 */
	public List<Integer> getTopNPlayers(int num);
}
