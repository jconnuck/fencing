
public interface IRound {
	/**
	 * @param num The number of fencers to return
	 * @return Top "num" fencers as a collection of IPlayer
	 */
	public Collection<IPlayer> getTopFencer(int num);
}
