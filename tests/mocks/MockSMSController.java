package mocks;

import final_project.control.ISMSController;

public class MockSMSController implements ISMSController{

	@Override
	public void parseOutput(String received, String number) {
		System.out.println("Parse output called on: " + received + " " + number);
	}

	@Override
	public void returnResults(int refID, int winnerID, int winnerScore,
			int loserID, int loserScore) {
		System.out.println("Return results called on: ref " + refID + " winner " + winnerID +
				"loser " + loserID  + " score " + winnerScore + "-" + loserScore);
	}

	@Override
	public void sendAllMessage(String message) {
		System.out.println("Send all called on: " + message);	
	}

	@Override
	public void sendFencerStripMessage(int id, int strip) throws Exception {
		//This one is empty for now (testing SMSParsing and this is never called)
	}

	@Override
	public void sendGroupMessage(String group, String message) {
		System.out.println("Send group called on: " + message);	
	}

	@Override
	public void sendMessage(String message, String number) {
		System.out.println("Send message called on: " + message);
	}

	@Override
	public void swapRefs(int oldRefID, int newRefID) {
		System.out.println("Swap refs called on: old ref " + oldRefID + " new " + newRefID);
	}

	@Override
	public void alertGUI(String message) {
		System.out.println("Alert gui: " + message);
	}
}
