package final_project.view;

import javax.swing.JPanel;
import final_project.control.TournamentController;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Color;

public class DEPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DEPanel(TournamentController tournament) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{439, 0};
		gridBagLayout.rowHeights = new int[]{280, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		DEBracketPanel bracketPanel = new DEBracketPanel(tournament);
		bracketPanel.setBackground(Color.BLACK);
		scrollPane.setViewportView(bracketPanel);
	}

}
