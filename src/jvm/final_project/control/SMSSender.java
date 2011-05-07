package final_project.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import final_project.model.IPerson;
import final_project.model.IDataStore;

public class SMSSender implements Constants {

	private String _username, _password;
	private IDataStore _store;
	private ISMSController _control; //Needed to alert when there are no more credits, etc.
	
	public SMSSender(IDataStore s, ISMSController ctrl, String username, String password) {
		_store = s;
		_control = ctrl;
		_username = username;
		_password = password;
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
            data += "username=" + URLEncoder.encode(_username, "ISO-8859-1");
            data += "&password=" + URLEncoder.encode(_password, "ISO-8859-1");
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
            String line, output = "";
            while ((line = rd.readLine()) != null) {
            	output += line;
            }
            //0|IN_PROGRESS|274166347
            Scanner s = new Scanner(output);
            if(!s.hasNext() || !s.hasNextInt()) {
            	toReturn = false;
            }
            else {
                int status_code = s.nextInt();
                if(status_code == 0) {
                	toReturn = true; //SMS in progress
                }
                if(status_code == 23) { //Authentication failure
                	throw new SMSController.GUIAlertException("Authentication failure!");
                }
            }
		}
		catch (Exception e) {
			toReturn = false;
			e.printStackTrace();
		}
		finally {
            try {
				if(wr!=null) wr.close();
				if(wr!=null) rd.close();
			} catch (IOException e) { } //Currently don't care if an exception is thrown
		}
		return toReturn;

	}
	
	/**
	 * The following methods are convenience methods that the 
	 * other classes can call so that they don't have to deal 
	 * with following our SMS protocol. 
	 */

	/**
	 * This method allows the admin to send a message to every person 
	 * in the tournament.
	 */
	public boolean sendAllMessage(String message) {
		//concatenating phone numbers
		String number = ""; 
		for(IPerson i: _store.getPeople()) {
			if (i!= null)
				number += i.getPhoneNumber() + ",";
		}
		return this.sendMessage(message, number);
	}

	/**
	 * Similar to sendAllMessage, this method also takes a group param,
	 * meaning that only members of this certain group will be alerted.
	 * 
	 * @param group
	 * @param message
	 * @return
	 */
	public boolean sendGroupMessage(String group, String message) { 
		String number = "";
		for(IPerson i: _store.getPeopleForGroup(group)) {
			if (i!=null && !i.getPhoneNumber().equals(""))
				number += i.getPhoneNumber() + ",";
		}
		return this.sendMessage(message, number);
	}

	//Should I have another method, where this is organized into a batch? TODO
	public boolean sendFencerStripMessage(int id,  int strip) {
		String message = "Fencer id: " + id + " Strip assignment: " + strip;
		
		//Look up the fencer in the database to get their phone number
		IPerson i = _store.getPerson(id);
		if(i==null) {
			return false;
		}

		String number = i.getPhoneNumber();
		if(number.equals("")) {
			return false;
		}

		return this.sendMessage(message, number);
	}
	
}
