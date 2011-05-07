package final_project.control;

/**
 * INTERFACE FOR SMSController 
 * Allows for mock controller
 * 
 * @author mksteele
 * 
 */

public interface ISMSController {

	abstract void sendMessage(String message, String number);
	abstract void sendAllMessage(String message);
	abstract void sendGroupMessage(String group, String message);
	abstract void sendFencerStripMessage(int id,  int strip);
	abstract void parseOutput(String received, String number);
	abstract void returnResults(int refID, int winnerID, int winnerScore, int loserID, int loserScore);
	abstract void swapRefs(int oldRefID, int newRefID);
	abstract void alertGUI(String message, java.util.Date time);
	abstract java.util.Date getTime();
}
