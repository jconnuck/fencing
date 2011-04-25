
public class TournamentControl {
	public State _state;
	private Tournament _tournament;
	private IPersonStore _people;
	
	public TournamentControl(IPersonStore store) {
		_people = store;
		_state = State.REGISTRATION;
		_tournament = new Tournament();
	}
	
	/**
	 * Takes an event and a fencer and calls registerFencer for that event and fencer.
	 * @param toRegister The fencer to register
	 * @param event The event for which the fencer is registering
	 */
	public void registerFencer(int toRegister, Tevent event) {
		event.registerFencer(toRegister);
	}
	
	/**
	 * Takes a bout and adds it to appropriate event.
	 * @param bout A finished bout to be added to results
	 * @param stripID The number of the strip the bout was fenced on
	 */
	public void addResult(Bout bout, int stripID) {
		Tevent temp;
				
	}
	
	// Represents the phase of the tournament that the TournamentControl is ready to carry out
	public enum State {
		REGISTRATION, POOLS, DE, FINAL
	}
}