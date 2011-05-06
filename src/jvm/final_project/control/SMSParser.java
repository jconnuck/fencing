package final_project.control;

import java.util.Scanner;

import final_project.model.IClub;
import final_project.model.IDataStore;
import final_project.model.IObservable;
import final_project.model.IPerson;

public class SMSParser {

	private IDataStore _store;
	private SMSController _control;
	
	public SMSParser(IDataStore s, SMSController ctrl) {
		_store = s;
		_control = ctrl;

	}

	/** 
	 * This method handles parsing the message from the API & 
	 * then calling the appropriate other methods on the data that 
	 * it gets out of the line.
	 */ 
	public void parseOutput(String received, String number) {
		Scanner s = new Scanner(received);

		//First, getting the first word
		String firstWord = s.next();
		
		if (firstWord.equals("Help") || firstWord.equals("help")) {
			//TODO Appeal to GUI!
		}
		
		else if(firstWord.equals("Follow") || firstWord.equals("follow")) {
			String firstName = "", lastName = "";
			if(s.hasNext())
				firstName = s.next();
			if(s.hasNext())
				lastName = s.next();
			
			this.subscribeUser(firstName, lastName, number);
		}
		
		
		//Then, choosing a possible path based on that word
		//POSSIBLE FIRST WORDS: 'follow', 'help', 'refswap', 'cancel', fencer name

		//if 'follow':
		//This method will eventually call subscribeUser on a certain name
	}
	

	//Subscribes a user to an IObservable
	public boolean subscribeUser(String firstNameToSubscribeTo, String lastNameToSubscribeTo, String number) {
		//System.out.println("Subscribed user! " + firstNameToSubscribeTo + " " + lastNameToSubscribeTo + ", " + number);
		
		//Checking to see that the person is registered in the database -- linear search through all people.
		// TODO: Look up subscriber in IDataStore by phone number
		boolean found = false;
		for (IPerson i: _store.getPeople()) {
			if(i.getPhoneNumber().equals(number)) {
				found = true;
				break;
			}
		}
		// Sending an error message 
		if (!found) {
			_control.sendMessage("This phone number is not registered. Please send us a registration code.", number);
			return false;
		}

		/* Checking to see that the person/club to be followed exists */
		found = false;
		int counter = 0; //To make sure that we don't get duplicates
		IObservable o = null;
		if(lastNameToSubscribeTo.equals("")) { // Last name empty, this is a club.
			for (IClub i: _store.getClubs()) {
				if(i.getName().equals(firstNameToSubscribeTo)) {
						found = true;
				}
			}	
		}
		else {
			
		}

		// Sending an error message 
		if (!found) {
			_control.sendMessage("Subscription not successful: no fencer or group found with this name.", number);
			return false;
		}

		//Actually submitting the user
		_control.sendMessage("You were successfully subscribed to " + firstNameToSubscribeTo + lastNameToSubscribeTo + " !", number);
		return true;
	}

	//TODO
	public boolean cancelSubscription(){
		return false;
	}
	
	public static void main(String[] args) {
		SMSParser p = new SMSParser(null, null);
		p.parseOutput("Follow MiranjnbmamdaSteele", "8132987766");
	}

}
