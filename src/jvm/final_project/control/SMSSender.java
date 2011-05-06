package final_project.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import final_project.model.IPerson;
import final_project.model.IDataStore;

public class SMSSender implements Constants {

	private IDataStore _store;
	
	public SMSSender(IDataStore s) {
		_store = s;
	}

	/**
	 * This is the main method that handles sending a certain message to 
	 * a certain phone number. It is called by all of the other, more 
	 * specialized send methods below.
	 * 
	 * @param message
	 * @param number
	 */
	public boolean sendMessage(String message, String number) {
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		boolean toReturn = false;
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
            wr.write(data);	//Does this block? 
            wr.flush();
            
            // Get the response
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
                //Figure out if successful TODO and set toReturn
            }
            toReturn = true;

		}
		catch (Exception e) {
			toReturn = false;
			e.printStackTrace();
		}
		finally {
            try {
				if(wr!=null) wr.close();
				if(wr!=null) rd.close();
			} catch (IOException e) {
				e.printStackTrace(); //TODO make more useful... Do I really care if this is fucking up?
			}			
		}
		return toReturn;

	}
	
	/**
	 * The following methods are convenience methods that the 
	 * other classes can call so that they don't have to deal 
	 * with following our SMS protocol. 
	 */

	//This method allows the admin to send a message to every member 
	public boolean sendAllMessage(String message) {
		//concatenating phone numbers
		String number = ""; 
		for(IPerson i: _store.getPeople()) {
			if (i!= null)
				number += i.getPhoneNumber() + ",";
		}
		return this.sendMessage(message, number);
	}

	public boolean sendGroupMessage(String group, String message) { 
		String number = "";
		for(IPerson i: _store.getPeopleForGroup(group)) {
			if (i!=null) //TODO: ask will if this makes sense...
				number += i.getPhoneNumber() + ",";
		}
		return this.sendMessage(message, number);
	}

	//Should I organize this into a batch? TODO
	public boolean sendFencerStripMessage(int id,  int strip) throws Exception {
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

		return this.sendMessage(message, number);
	}
	
}
