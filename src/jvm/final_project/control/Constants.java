package final_project.control;

import java.awt.*;

public interface Constants {
	//Returned by PoolRoundController.calcPoolSize function if the given parameters cannot be satisfied
	//with a valid output. i.e., the input to the function is not invalid, simply impossible to satisfy.
	public static final Point IMPOSSIBLE_POOL_CONSTRAINTS = new Point(-1,-1);
	
	public static final String INVALID_PHONE_NUMBER = "No number found";
}
