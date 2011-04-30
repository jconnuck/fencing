package final_project.control;

import final_project.model.*;
import java.net.*;
import java.io.*;

/**
 * SMSController Class
 * This class handles sending out text messages to the instances of
 * the IMessagable interface stored in the IPersonStore class.
 * 
 * @author mksteele
 */

public class SMSController implements Constants{
	
	IDataStore _store;
	int _lastRetrievedID;
	
	public SMSController(IDataStore s) {
		_store = s;
		_lastRetrievedID = 0;
		
	}
	
	/**
	 * This is the main method that handles sending a certain message to 
	 * a certain phone number. It is called by all of the other, more 
	 * specialized send methods below.
	 * 
	 * @param message
	 * @param number
	 */
	public void sendMessage(String message, String number) {
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		try {
			//Constructing the data
			String data = "";
            data += "username=" + URLEncoder.encode("cs032fencing", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("F3ncing!", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode(message, "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=1" + number;

            URL url = new URL("http://usa.bulksms.com:5567/eapi/submission/send_sms/2/2.0");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            
            // Get the response
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
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
	 * The following methods are convenience methods that the 
	 * other classes can call so that they don't have to deal 
	 * with following our SMS protocol. 
	 */
	public void sendFencerStripMessage(int id,  int strip) throws Exception {
		String message = "Fencer id: " + id + " Strip assignment: " + strip;
		
		//Look up the fencer in the database to get their phone number
		String number = _store.getPerson(id).getPhoneNumber();  //TODO Figure out what happens when this ID does not point to a person

		if(number.equals(INVALID_PHONE_NUMBER)) {
			throw new Exception("Message could not be sent: No phone number for id " + id);
		}
				
		this.sendMessage(message, number);
	}
	
	//This method allows the admin to send a message to every member 
	public void sendAllMessage(String message) {
		for(IPerson i: _store.getPeople()) {
			this.sendMessage(message, _store.getPerson(id).getPhoneNumber()); //Something like this? TODO
		}
	}
	
	public void sendRefereesMessage(String message) {
		for(IPerson i: _store.getPeopleForGroup("Referee")) { //TODO is this ok???
			this.sendMessage(message, _store.getPerson(id).getPhoneNumber()); //Something like this? TODO
		}
	}  //Same idea as sendAll
	public void sendFencersMessage(String message) {} 
	public void sendCoachesMessage(String message) {} //etc. Any more we can think of?

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
            while ((line = rd.readLine()) != null) {
                // TODO Parse output to get out reply messages
                System.out.println(line);
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
	public void parseMessage(String received, String number) {
		//First, getting the first word
		//Then, choosing a possible path based on that word

		//This method will eventually call subscribeUser on a certain name
	}
	
	public void subscribeUser(String nameToSubscribeTo, String number) {
		
		//Checking to see that a name exists in the IPersonStore
		if(_store.contains(nameToSubscribeTo)) //How does the IPersonStore tell me if it contains a name?
			_store.lookup(nameToSubscribeTo).addSubscriber(number);

	}
}