package final_project.view;

import javax.swing.JPanel;
import final_project.control.TournamentController;
import final_project.model.store.IDataStore;
import final_project.model.*;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;

public class PoolRoundObserverPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PoolRoundObserverPanel(final TournamentController tournament) {
		setBackground(Color.BLACK);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{179, 0};
		gridBagLayout.rowHeights = new int[]{29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnDeRound = new JButton("DE Round");
		btnDeRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tournament.getMainWindow().loadRightPanel(new DEPanel(tournament));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		scrollPane.setViewportView(panel);
		GridBagConstraints gbc_btnDeRound = new GridBagConstraints();
		gbc_btnDeRound.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnDeRound.gridx = 0;
		gbc_btnDeRound.gridy = 9;
		add(btnDeRound, gbc_btnDeRound);
		
		//TODO make a for loop, making anew PoolObserverPanel for each pool and add it
        int i=1;
        for (Pool p : tournament.getPools(0)) {
            PoolObserverPanel poolObserverPanel = new PoolObserverPanel(tournament, i++);
            panel.add(poolObserverPanel);
        }
	}

}
