package final_project.control;

import java.awt.*;

public final class Constants {
	//Returned by PoolRoundController.calcPoolSize function if the given parameters cannot be satisfied
	//with a valid output. ie, the input to the function is not invalid, simply impossible to satisfy.
	public static final Point IMPOSSIBLE_POOL_CONSTRAINTS = new Point(-1,-1);
	
	private Constants() {
		throw new AssertionError();
	}
}
