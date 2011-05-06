package final_project.control;

import final_project.model.*;

import java.net.*;
import java.util.Collection;
import java.io.*;

/**
 * SMSController Class
 * This class handles sending out text messages to the instances of
 * the IMessagable interface stored in the IPersonStore class.
 * 
 * TA Questions:
 * - how to write tests for this class
 * - sychronization & thread questions (getting Collections from the DataStore)
 * - is it necessary to have a thread pool?
 * 
 * @author mksteele
 */


public class SMSController implements Constants {

	// Also need a reference to the GUI
	private SMSSender _sender;
	private SMSParser _parser;
	private TournamentController _tournament;

	public SMSController(IDataStore s, TournamentController t) {
		_tournament = t;
		
		/* Making sender and parser */
		_sender = new SMSSender(s);
		_parser = new SMSParser(s, this);
		
		/* Starting the "receiver" thread to continuously check the inbox */
		SMSReceiver receiver = new SMSReceiver(this);
		Thread thread = new Thread(receiver);
		thread.run();
	}
	
	/* TODO: How to handle the booleans that the sender methods return? */
	public void sendMessage(String message, String number) {
		_sender.sendMessage(message, number);
	}
	
	public void sendAllMessage(String message) {
		_sender.sendAllMessage(message);
	}
	
	public void sendGroupMessage(String group, String message) { 
		String number = "";
		for(IPerson i: _store.getPeopleForGroup(group)) {
			//also TODO problem with collection & synchronization?
			if(i!=null)
				number += i.getPhoneNumber() + ",";
		}
	}

	public void sendFencerStripMessage(int id,  int strip) throws Exception {
		String message = "Fencer id: " + id + " Strip assignment: " + strip;
		
		//Look up the fencer in the database to get their phone number
		IPerson i = _store.getPerson(id);
		if(i==null) {
			throw new Exception("No fencer found for id " + id);
		}

		String number = i.getPhoneNumber();
		if(number.equals(INVALID_PHONE_NUMBER)) {
			throw new Exception("Message could not be sent: No phone number for id " + id);
		}

		this.sendMessage(message, number);
	}
	
	/* RECEIVING MESSAGES */
	
	public void getInbox() {
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		try {
			//Constructing data
			String data = "";
            data += "username=" + URLEncoder.encode("cs032fencing", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("F3ncing!", "ISO-8859-1");
            data += "&last_retrieved_id=" + _lastRetrievedID;
			
            URL url = new URL("http://usa.bulksms.com:5567/eapi/reception/get_inbox/1/1.0");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            
            // Get the response
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
	    int count = 0;
            while ((line = rd.readLine()) != null) {
                //Parsing the very first line
		if(count==0) {
			//TODO: get substring & store lastReceivedID
			count ++;	
		}
		else if (count == 1) {
			count ++; //Just eating empty line
		}
                else { 
			//TODO: Get phone number and message out of the line
			//Then, call parseMessage()					
		}
            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
            try {
				if(wr!=null) wr.close();
				if(wr!=null) rd.close();
			} catch (IOException e) {
				e.printStackTrace(); //TODO make more useful
			}
		}
	}
	
	/**
	 * SMS PROTOCOL: 
	 * What kind of messages are people going to be able to sign up for?
	 * 
	 * SUBSCRIBING TO A CERTAIN FENCER:
	 * they should type: "Follow Miranda Steele" --> Full name?
	 * or for a team: "Follow team Brown"
	 * 
	 * STOPPING SUBSCRIPTIONS:
	 * to stop all subscriptions: "Stop"
	 * or to just stop a certain fencer: "Stop following Miranda Steele"
	 * or to stop a certain team: "Stop following team Brown"
	 *
	 * OTHER THINGS: ??
	 * 
	 * Things we need to be able to handle: 
	 * - case sensitivity
	 * - common typos (fencre, follw) --> is this really necessary?
	 * 
	 */

	/** 
	 * This method handles parsing the message from the API & 
	 * then calling the appropriate other methods on the data that 
	 * it gets out of the line.
	 */ 
	private void parseOutput(String received, String number) {
		//First, getting the first word
		//Then, choosing a possible path based on that word
		//POSSIBLE FIRST WORDS: 'follow', 'help', 'refswap', 'cancel', fencer name

		//if 'follow':
		//This method will eventually call subscribeUser on a certain name
	}
	
	public void returnResults(int refID, int winnerID, int winnerScore, int loserID, int loserScore) {
		/* Making the CompleteResult */
		CompleteResult cr = new CompleteResult(new PlayerResult(winnerID, winnerScore), new PlayerResult(loserID, loserScore));
		_tournament.addCompletedResult(cr, refID);
	}
	
}
