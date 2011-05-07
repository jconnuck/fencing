package final_project.control;

import java.util.Scanner;

import final_project.model.IDataStore;
import final_project.model.IPerson;
import final_project.model.IReferee;
import final_project.model.IPlayer;
import final_project.model.IClub;

import mocks.*;

public class SMSParser {

	private IDataStore _store;
	private ISMSController _control;

	public SMSParser(IDataStore s, ISMSController ctrl) {
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

		//Message: "help string" 
		if (firstWord.equals("Help") || firstWord.equals("help")) {
			//TODO Alert GUI!
		}

		// Message: "Follow first last" or "follow clubName"
		else if(firstWord.equals("Follow") || firstWord.equals("follow")) {
			String firstName = "", lastName = "";
			if(s.hasNext())
				firstName = s.next();
			if(s.hasNext())
				lastName = s.next();

			this.subscribeUser(firstName, lastName, number);
		}

		// Message: "unsubscribe first last" or "Unsubscribe clubName"
		else if(firstWord.equals("Unsubscribe") || firstWord.equals("unsubscribe")) {
			String firstName = "", lastName = "";
			if(s.hasNext())
				firstName = s.next();
			if(s.hasNext())
				lastName = s.next();

			this.unsubscribeUser(firstName, lastName, number);
		}

		//Message: "refswap number"
		else if(firstWord.equals("Refswap") || firstWord.equals("refswap")) {
			String newRefNumber = "";
			if(s.hasNext())
				newRefNumber = s.next();

			this.swapRefs(number, newRefNumber);
		}

		//Message "result id beat id this-that" or "id beat id this to that"
		else if(firstWord.equals("Result") || firstWord.equals("result")) { //TODO: do i like this?
			int refID =0, winID = 0, loseID = 0, winScore = 0, loseScore = 0;
			
			/* Looping through to find the ref ID of this number */
			boolean found = false;
			for (IReferee i: _store.getReferees()) {
				if(i.getPhoneNumber().equals(number)) {
					refID = i.getID();
					found = true;
					break;
				}
			}
			if(!found) {
				_control.sendMessage("We're sorry, this number is not registered as a referee.", number);
				return;
			}
			
			/* Now, parsing out fencer IDs and score */
			if(s.hasNextInt())
				winID = s.nextInt();
			if(s.hasNext() && !s.next().equals("beat")) { //Eating "beat" token
				_control.sendMessage("We're sorry, this message could not be parsed.", number);
				return;
			}
			
			if(s.hasNextInt())
				loseID = s.nextInt();
			else {
				_control.sendMessage("We're sorry, this message could not be parsed.", number);
				return;
			}
			if(s.hasNextInt())
				winScore = s.nextInt();
			else {
				_control.sendMessage("We're sorry, this message could not be parsed.", number);
				return;
			}
			if(s.hasNext()) {
				String next = s.next();
				if (!next.equals("to") || !next.equals("-")){ //Eating "to"/"-" token
					_control.sendMessage("We're sorry, this message could not be parsed.", number);
					return;
				}
			}
			
			if(s.hasNextInt())
				loseScore = s.nextInt();
			else {
				_control.sendMessage("We're sorry, this message could not be parsed.", number);
				return;
			}
			
			/* NOW THAT WE'VE PARSED OUT ALL OF THE DATA */
			_control.returnResults(refID, winID, winScore, loseID, loseScore);
		}

		else {
			_control.sendMessage("We're sorry, this message could not be parsed.", number);
		}
	}

	//Subscribes a user to an IObservable 
	public boolean subscribeUser(String firstNameToSubscribeTo, String lastNameToSubscribeTo, String number) {
		//Checking to see that the person is registered in the database -- linear search through all people.
		boolean found = false;
		for (IPerson i: _store.getPeople()) {
			if(i.getPhoneNumber().equals(number)) {
				found = true;
				break;
			}
		}

		if (!found) { // Sending an error message if this number is not found
			_control.sendMessage("This phone number is not registered. Please send us a registration code.", number);
			return false;
		}

		/* Checking to see that the person/club to be followed exists */
		found = false;
		int counter = 0; //To make sure that we don't get duplicates
		int idToFollow = 0;
		if(lastNameToSubscribeTo.equals("")) { // Last name empty, this is a club.
			for (IClub i: _store.getClubs()) {
				if(i.getName().equals(firstNameToSubscribeTo)) {
					idToFollow = i.getID();
					found = true;
					counter++;
				}
			}
		}
		else {
			for (IPlayer i: _store.getPlayers()) {
				if(i.getFirstName().equals(firstNameToSubscribeTo)) {
					if (i.getLastName().equals(lastNameToSubscribeTo)) {
						idToFollow = i.getID();
						found = true;
						counter++;
					}
				}
			}
		}

		/* Handling error messages */
		if (!found) {
			_control.sendMessage("We're sorry, no fencer or group was found with this name.", number);
			return false;
		}
		else if (counter > 1) {
			_control.sendMessage("Multiple entries found for this name. Please respond with \"follow\" followed by an ID number.", number);
			return false;
		}

		/* ACTUALLY SUBSCRIBING USER */
		IPerson spect = _store.createSpectator(number, "", "", "", "Spectator");
		spect.addWatched(idToFollow);
		_store.putData(spect);

		_control.sendMessage("You were successfully subscribed to " + firstNameToSubscribeTo + lastNameToSubscribeTo + " !", number);
		return true;
	}

	public boolean unsubscribeUser(String firstToUnsubscribe, String lastToUnsubscribe, String number) {
		//Finding the spectator at this phone number
		int idToFind = 0;
		boolean found = false;
		for (IPerson i: _store.getPeople()) {
			if (i.getPhoneNumber().equals(number)) {
				idToFind = i.getID();
				found = true;
				break;
			}
		}
		if(!found) {
			_control.sendMessage("Error: this phone number is not registered.", number);
			return false;
		}

		/* Finding the person/club to be unsubscribed from*/
		found = false;
		int counter = 0; //To make sure that we don't get duplicates
		int idToUnfollow = 0;
		if(lastToUnsubscribe.equals("")) { // Last name empty, this is a club.
			for (IClub i: _store.getClubs()) {
				if(i.getName().equals(firstToUnsubscribe)) {
					idToUnfollow = i.getID();
					found = true;
					counter++;
				}
			}
		}
		else {
			for (IPlayer i: _store.getPlayers()) {
				if(i.getFirstName().equals(firstToUnsubscribe)) {
					if (i.getLastName().equals(lastToUnsubscribe)) {
						idToUnfollow = i.getID();
						found = true;
						counter++;
					}
				}
			}
		}

		/* Handling errors */
		if(!found) {
			_control.sendMessage("We're sorry, no fencer or group was found with this name.", number);
			return false;
		}
		if(counter > 1) {
			_control.sendMessage("Multiple entries found for this name. Please respond with \"unsubscribe\"" +
					" followed by an ID number.", number);
			return false;
		}

		/* UNSUBSCRIBING */
		IPerson spect = _store.getPerson(idToFind);
		if (spect == null || spect.removeWatched(idToUnfollow) == null) {
			_control.sendMessage("Error: unsubscription not successful", number);
			return false;
		}
		else {
			_control.sendMessage("You have been successfully unsubscribed from " + firstToUnsubscribe 
					+ " " + lastToUnsubscribe + " .", number);
			//Cleaning up Spectator
			if (spect.getWatched().isEmpty())
				_store.removeData(spect);
			return false;
		}
	}

	public boolean swapRefs(String oldRefNumber, String newRefNumber){
		/**
		 * Looking up ref ids by searching through list of referees. If either 
		 * phone number does not belong to a registered ref, the swap will not 
		 * go through.
		 */
		int oldRefID = 0, newRefID = 0;
		boolean found = false;
		for(IReferee i : _store.getReferees()){
			if (i.getPhoneNumber().equals(oldRefNumber)) {
				oldRefID = i.getID();
				found = true;
				break;
			}
		}
		if (!found) {
			_control.sendMessage("You (message sender) are not registered as a referee.", oldRefNumber);
			return false;
		}
		/* NEW REFEREE */
		found = false;
		for(IReferee i : _store.getReferees()){
			if (i.getPhoneNumber().equals(newRefNumber)) {
				newRefID = i.getID();
				found = true;
				break;
			}
		}
		
		if (!found) {
			_control.sendMessage("You (message sender) are not registered as a referee.", oldRefNumber);
			return false;
		}

		/* Now we know both numbers are valid referees */
		_control.swapRefs(oldRefID, newRefID);
		_control.sendMessage("Swap successful", oldRefNumber);
		return true;

	}

	public static void main(String[] args) {
		SMSParser p = new SMSParser(null, null);
		//p.parseOutput("Follow MiranjnbmamdaSteele", "8132987766");
	}

}
