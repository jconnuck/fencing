package final_project.view;

import javax.swing.JPanel;
import final_project.control.TournamentController;
import final_project.model.store.IDataStore;
import final_project.model.*;
import java.awt.Color;

public class PoolRoundObserverPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PoolRoundObserverPanel(TournamentController tournament) {
		setBackground(Color.BLACK);
		//TODO make a for loop, making anew PoolObserverPanel for each pool and add it
        int i=1;
        for (Pool p : tournament.getPools(0)) {
            PoolObserverPanel poolObserverPanel = new PoolObserverPanel(tournament, i++);
            add(poolObserverPanel);
        }
	}

}
