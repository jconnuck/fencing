package jvm.final_project.model;

import java.util.*;

public interface IRound {
	/**
	 * @param num The number of fencers to return
	 * @return Top "num" fencers as a collection of IPlayer
	 */
	public Collection<Integer> getTopNFencers(int num);
}
