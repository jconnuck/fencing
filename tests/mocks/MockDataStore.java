package mocks;

import java.util.Collection;

import final_project.model.IClub;
import final_project.model.IData;
import final_project.model.IDataStore;
import final_project.model.IObservable;
import final_project.model.IPerson;
import final_project.model.IPlayer;
import final_project.model.IReferee;
import final_project.model.PlayerSeed;

public class MockDataStore implements IDataStore {

	//TODO: Make an array?
	
	@Override
	public IClub createClub(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPlayer createPlayer(String phoneNumber, String firstName,
			String lastName, String carrier, String group, int rank,
			PlayerSeed seed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IReferee createReferee(String phoneNumber, String firstName,
			String lastName, String carrier, String group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPerson createSpectator(String phoneNumber, String firstName,
			String lastName, String carrier, String group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IClub getClub(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IClub> getClubs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IData> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IData getData(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IObservable getObservable(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IObservable> getObservables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IPerson> getPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IPerson> getPeopleForGroup(String group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPerson getPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPlayer getPlayer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IPlayer> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IReferee getReferee(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IReferee> getReferees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putData(IData person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeData(IData person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeID(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runTransaction(Runnable transaction) {
		// TODO Auto-generated method stub
		
	}

}
