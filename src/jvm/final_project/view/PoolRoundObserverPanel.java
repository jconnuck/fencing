package final_project.view;

import javax.swing.JPanel;
import final_project.control.TournamentController;
import final_project.model.store.IDataStore;

public class PoolRoundObserverPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PoolRoundObserverPanel(TournamentController tournament) {
		//TODO make a for loop, making anew PoolObserverPanel for each pool and add it
		PoolObserverPanel poolObserverPanel1 = new PoolObserverPanel(tournament, 1);
		PoolObserverPanel poolObserverPanel2 = new PoolObserverPanel(tournament, 2);
		PoolObserverPanel poolObserverPanel3 = new PoolObserverPanel(tournament, 3);
		add(poolObserverPanel1);
		add(poolObserverPanel2);
		add(poolObserverPanel3);

	}

}
