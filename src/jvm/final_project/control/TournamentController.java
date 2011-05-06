package final_project.control;

import final_project.model.*;

public class TournamentController {
	public State _state;
	private Tournament _tournament;

	public TournamentController() {
		_state = State.REGISTRATION;
		_tournament = new Tournament();
	}

	public void addResult(CompleteResult result, int refID){

	}

	/**
	 * Takes an event and a fencer and calls registerFencer for that event and fencer.
	 * @param playerId The fencer to register
	 * @param event The event for which the fencer is registering
	 */
	public void registerPlayer(int playerId, IEvent event) {
		event.registerPlayer(playerId);
	}

	/**
	 * Takes a completed bout and adds it to appropriate event.
	 * @param bout A completed bout to be added to results
	 * @param stripID The number of the strip the bout was fenced on
	 */
	public void addResult(CompleteResult bout, int stripID) {
	}

	// Represents the phase of the tournament that the TournamentControl is ready to carry out
	public enum State {
		REGISTRATION, POOLS, DE, FINAL
	}
}