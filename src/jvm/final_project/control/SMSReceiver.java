package final_project.control;

import java.io.*;
import java.net.*;


/**
 * groups: technician & medical
 * @author Miranda
 *
 */
public class SMSReceiver implements Runnable {

	private SMSController _control;
	private String _username, _password; //Not the most secure but who cares.
	private int _lastRetrievedID;
	private boolean _listening;

	public SMSReceiver(SMSController ctrl, String username, String password) {
		_control = ctrl;
		_username = username;
		_password = password;
		_lastRetrievedID = 0;
		_listening = true;
	}
	
	public void run() {
		while(_listening) {
			this.getInbox();
		}
	}
	
	/* Methods to stop and start this thread by changing "_listening" boolean */
	public void stopListening() {
		_listening = false;
	}

	public void restartListening() {
		_listening = true;
		this.run();
	}

	/**
	 * GET INBOX METHOD
	 * Calls on the API to get all of the messages not previously 
	 * 
	 */
	public boolean getInbox() {
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		boolean toReturn = false;
		
		try {
			//Constructing data
			String data = "";
			data += "username=" + URLEncoder.encode(_username, "ISO-8859-1");
			data += "&password=" + URLEncoder.encode(_password, "ISO-8859-1");
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
			boolean firstLine = true;
			while ((line = rd.readLine()) != null) {
				//Parsing the very first line
				if(firstLine) {
					//First line from API looks something like:
					//0|records to follow|3


					
					//TODO: get substring & store lastReceivedID
					//TODO: Check to see that this does not return some error (no internet?)
					//Eating the second (empty) line
					line = rd.readLine();
					if(!line.equals(""))
						throw new Exception ("Weird output: " + line); //TODO: ?
					firstLine = false;
				}
				else { 
					//19|4412312345|Hi there|2004-01-20 16:06:40|44771234567|0
					//TODO: Get phone number and message out of the line
					//Then, call control's parsing methods
				}
			}
			toReturn = true; //Input successfully processed
		} catch (UnknownHostException e) { 
			//Letting the GUI know it ain't got no internet
			_control.alertGUI("You are not currently connected to the internet. SMS notification system disabled");
		} catch(SMSController.GUIAlertException e) {
			_control.alertGUI(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(); //What to do with these??
		} 		
		finally {
			try {
				if(wr!=null) wr.close();
				if(wr!=null) rd.close();
			} catch (IOException e) { } //TODO: Send to GUI? 
		}
		return toReturn;
	}

}
