package final_project.view;

import javax.swing.JPanel;
import final_project.control.TournamentController;
import final_project.model.store.IDataStore;
import final_project.model.*;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PoolRoundObserverPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PoolRoundObserverPanel(final TournamentController tournament) {
		setBackground(Color.BLACK);
		
		JButton btnDeRound = new JButton("DE Round");
		btnDeRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tournament.getMainWindow().loadRightPanel(new DEBracketPanel(tournament));
			}
		});
		add(btnDeRound);
		//TODO make a for loop, making anew PoolObserverPanel for each pool and add it
        int i=1;
        for (Pool p : tournament.getPools(0)) {
            PoolObserverPanel poolObserverPanel = new PoolObserverPanel(tournament, i++);
            add(poolObserverPanel);
        }
	}

}
