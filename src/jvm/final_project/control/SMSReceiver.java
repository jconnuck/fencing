package final_project.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class SMSReceiver implements Runnable {

	//private SMSController _control;
	private int _lastRetrievedID;
	private boolean _listening;

	public SMSReceiver(SMSController ctrl) {
		_lastRetrievedID = 0;
		_listening = true;
	}

	@Override
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
			boolean firstLine = true;
			while ((line = rd.readLine()) != null) {
				//Parsing the very first line
				if(firstLine) {
					//TODO: get substring & store lastReceivedID
					//TODO: Check to see that this does not return some error (no internet?)
					//Eating the second (empty) line
					line = rd.readLine();
					if(!line.equals(""))
						throw new Exception ("Weird output: " + line); //TODO: ?
					firstLine = false;
				}
				else { 
					//TODO: Get phone number and message out of the line
					//Then, call control's parsing methods
				}
			}
			toReturn = true; //Input successfully processed
		}
		catch (UnknownHostException e) { 
			//TODO: Let the GUI know it ain't got no internet
		}
		catch (Exception e) { }
		finally {
			try {
				if(wr!=null) wr.close();
				if(wr!=null) rd.close();
			} catch (IOException e) { } //TODO: Send to GUI? 
		}
		return toReturn;
	}
}
