package final_project.control;

import java.util.Date;
import java.util.Calendar;
import java.util.Timer;

import final_project.model.*;

public class SMSController implements Constants, ISMSController {

	private SMSSender _sender;
	private SMSParser _parser;
	private TournamentController _tournament;
	private Timer _timer;
	private Calendar _cal;

	/**
	 *  Constructor needs to take the username & password for the BulkSMS API 
	 * @param s
	 * @param t
	 * @param username
	 * @param password
	 */
	public SMSController(IDataStore s, TournamentController t, String username, String password) {
		_tournament = t;
		_cal = Calendar.getInstance();
		
		/* Making sender and parser */
		_sender = new SMSSender(s, this, username, password);
		_parser = new SMSParser(s, this);

		/* Starting the "receiver" thread to continuously check the inbox */
		SMSReceiver receiver = new SMSReceiver(this, username, password);
		_timer = new Timer();
		_timer.scheduleAtFixedRate(receiver, 0, RECEIVE_TIMER_STEP);

		receiver.getInbox(); //Getting the inbox for the first time to make sure that the inbox is empty
	}

	/* TODO: How to handle the booleans that the sender methods return? */
	public void sendMessage(String message, String number) {
		_sender.sendMessage(message, number);
	}

	public void sendAllMessage(String message) {
		_sender.sendAllMessage(message);
	}

	public void sendGroupMessage(String group, String message) {
		_sender.sendGroupMessage(group, message);
	}

	public void sendFencerStripMessage(int id,  int strip) {
		_sender.sendFencerStripMessage(id, strip);
	}

	public void parseOutput(String received, String number) {
		_parser.parseOutput(received, number);
	}

	public void returnResults(int refID, int winnerID, int winnerScore, int loserID, int loserScore) {
		/* Making the CompleteResult */
		CompleteResult cr = new CompleteResult(new PlayerResult(winnerID, winnerScore), new PlayerResult(loserID, loserScore));
		_tournament.addCompletedResult(cr, refID);
	}

	public void swapRefs(int oldRefID, int newRefID) {
		_tournament.swapRef(0, oldRefID, newRefID);
	}

	public void alertGUI(String message, Date time) {
		//should call some sort of alert method
	}
	
	public Date getTime() {
		return _cal.getTime();
	}
}
