package final_project.tests.mocks;

import java.util.Calendar;
import java.util.Date;

import final_project.control.ISMSController;

public class MockSMSController implements ISMSController {

	java.util.Calendar _cal;

	public MockSMSController() {
		_cal = Calendar.getInstance();
	}

	public void parseOutput(String received, String number) {
		System.out.println("Parse output called on: " + received + " " + number);
	}

	public void returnResults(int refID, int winnerID, int winnerScore,
			int loserID, int loserScore) {
		System.out.println("Return results called on: ref " + refID + " winner " + winnerID +
				"loser " + loserID  + " score " + winnerScore + "-" + loserScore);
	}

	public void sendAllMessage(String message) {
		System.out.println("Send all called on: " + message);
	}

	public void sendFencerStripMessage(int id, int strip) {
		//This one is empty for now (testing SMSParsing and this is never called)
	}

	public void sendGroupMessage(String group, String message) {
		System.out.println("Send group called on: " + message);
	}

	public void sendMessage(String message, String number) {
		System.out.println("Send message called on: " + message);
	}

	public void swapRefs(int oldRefID, int newRefID) {
		System.out.println("Swap refs called on: old ref " + oldRefID + " new " + newRefID);
	}

	public void alertGUI(String message, java.util.Date d) {
		System.out.println("Alert gui: " + message);
	}

	public Date getTime() {
		_cal.getTime();
		return null;
	}

	@Override
	public void updateSubscriberGUI() {
		// TODO Auto-generated method stub
		
	}
}
