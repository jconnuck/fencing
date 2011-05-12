package final_project.control;

import java.util.*;
import final_project.model.*;
import final_project.model.store.*;

/**
 * Class to help the TournamentController format the
 * data from the data store in a specific way, so that
 * the GUI can display it nicely.
 *
 * @author mksteele
 */
public class DataFormattingHelper implements Constants {

	private IDataStore _dataStore;
	private TournamentController _tournament;

	public DataFormattingHelper(IDataStore s, TournamentController t) {
		_dataStore = s;
		_tournament = t;
	}

	/**
	 * Formats data for the SignInPanel. Has the form
	 * {"First Last", "Club", "Group", signed in y/n, "ID" }
	 * @return
	 */
	public Object[][] giveSignInPanelInfo() {
        Collection<IPerson> people = _dataStore.getPeopleWithoutGroup("Spectator");
		int numPeople = people.size();
		Object[][] toReturn = new Object[numPeople][NUM_COLS_SIGN_IN];

		int index = 0;
		for (IPerson i: people) {
            /* NAME */
            toReturn[index][0] = i.getFirstName() + " " + i.getLastName();

            /* CLUB */
            if(i instanceof IHasClub) {
                Iterator<Integer> iter = ((IHasClub) i).getClubs().iterator();
                if(iter.hasNext()) //Such a mess, just to get out the club name...
                    toReturn[index][1] =  _dataStore.getClub(iter.next()).getName();
                else
                    toReturn[index][1] = "";
            }
            else
                toReturn[index][1] = "";

            /* GROUP */
            toReturn[index][2] = i.getGroup();

            /* SIGNED IN (if player) */
            if(i instanceof IPlayer) {
                toReturn[index][3] = ((IPlayer)i).getCheckedIn();
            }
            else
                toReturn[index][3] = true;

            /* ID */
            toReturn[index][4] = i.getID();

            index++;
        }
		return toReturn;
	}

	/**
	 *
	 *
	 * {"First Last", "Number", "Following"},
	 */
	public Object[][] giveSubscriberTableInfo() {
		//Making the object array with as many rows as spectators in the data store
		Object[][] toReturn = null;
		System.out.println("Data store spectator group size: " + _dataStore.getPeopleForGroup("Spectator").size());
        toReturn = new Object[_dataStore.getPeopleForGroup("Spectator").size()][NUM_COLS_SUBSCRIBER_PANEL];

		int index = 0;
		for(IPerson i: _dataStore.getPeopleForGroup("Spectator")) {
			toReturn[index][0] = i.getFirstName() + " " + i.getLastName();
			toReturn[index][1] = i.getPhoneNumber();

			toReturn[index][2] = "";
			System.out.println("--Before iterator in data formatting helper give Subscriber info " + i.getID());
			System.out.println("--Watched size " + i.getWatched().size());
			Iterator<Integer> iter = i.getWatched().iterator();
			while(iter.hasNext()) {
				int id = ((Integer)(iter.next())).intValue();
				IObservable followed = _dataStore.getObservable(id);
				if(followed instanceof IClub)
					toReturn[index][2] =  ((IClub) followed).getName() + "";
				else
					toReturn[index][2] = ((IPlayer) followed).getFirstName() + " " + ((IPlayer) followed).getLastName();
			}

			index++;
		}

		return toReturn;
	}

	public Object[][] getPoolSizeInfoTable(int stripRows, int stripCols) {
		Collection<PoolSizeInfo> sizes = _tournament.getValidPoolSizes();
		Object[][] toReturn = new Object[sizes.size()][NUM_COLS_POOL_SETUP];
		int i = 0;
		for (PoolSizeInfo info : sizes) {
			toReturn[i][0] = info._poolSize;
			toReturn[i][1] = info._numBigPools;
			toReturn[i][2] = info._numSmallPools;
			toReturn[i][3] = "Select";
			i++;
		}
		return toReturn;
	}

	public Object[][] getPoolRefListTable(Pool pool) {
		Object[][] toReturn = new Object[pool.getPlayers().size()][NUM_COLS_POOL_REF_LIST];

		int i = 0;
		for(int p: pool.getPlayers()) {
			//Not checking for data store null ptrs but whatever.
			toReturn[i][0] = _dataStore.getPerson(p).getFirstName() + " " + _dataStore.getPerson(p).getLastName();

			toReturn[i][1] = "";
			Iterator<Integer> clubs = _dataStore.getPlayer(p).getClubs().iterator();
			if(clubs.hasNext()) {
				toReturn[i][1] = _dataStore.getClub(clubs.next()).getName();
			}

			toReturn[i][2] = i+1;

			i++;
		}

		return toReturn;
	}

}

