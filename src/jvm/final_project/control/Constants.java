package final_project.control;

import java.awt.*;

public interface Constants {
	//Returned by PoolRoundController.calcPoolSize function if the given parameters cannot be satisfied
	//with a valid output. i.e., the input to the function is not invalid, simply impossible to satisfy.
	public static final Point IMPOSSIBLE_POOL_CONSTRAINTS = new Point(-1,-1);
	public static final String INVALID_PHONE_NUMBER = "No number found";
	
	public static final String[] groups = {"Fencer", "Referee", "Technician", "Medical", "Subscriber"};
	
	public static final String API_RECEIVE_URL = "http://usa.bulksms.com:5567/eapi/reception/get_inbox/1/1.0";
	public static final String API_SEND_URL = "http://usa.bulksms.com:5567/eapi/reception/get_inbox/1/1.0";
	
	public static final int EVENT_ID = 0; //Currently only supporting one event, which has id 0
	
	public static final long RECEIVE_TIMER_STEP = 500; //500 milliseconds = updates every 0.5s

	/* GUI CONSTANTS */
	public static final int NUM_COLS_SIGN_IN = 4;
	public static final int NUM_COLS_SUBSCRIBER_PANEL = 3;
}
