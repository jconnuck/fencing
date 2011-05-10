package final_project.view;

import javax.swing.JPanel;
import final_project.control.TournamentController;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public class DEBracketPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DEBracketPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{50, 50, 0, 50, 50, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		DEBracketUnitPanel bracketUnitPanel = new DEBracketUnitPanel((TournamentController) null);
		GridBagConstraints gbc_bracketUnitPanel = new GridBagConstraints();
		gbc_bracketUnitPanel.gridheight = 2;
		gbc_bracketUnitPanel.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel.gridx = 0;
		gbc_bracketUnitPanel.gridy = 0;
		add(bracketUnitPanel, gbc_bracketUnitPanel);
		
		DEBracketUnitPanel bracketUnitPanel_2 = new DEBracketUnitPanel((TournamentController) null);
		GridBagConstraints gbc_bracketUnitPanel_2 = new GridBagConstraints();
		gbc_bracketUnitPanel_2.insets = new Insets(0, 0, -1, 0);
		gbc_bracketUnitPanel_2.gridheight = 3;
		gbc_bracketUnitPanel_2.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel_2.gridx = 1;
		gbc_bracketUnitPanel_2.gridy = 1;
		add(bracketUnitPanel_2, gbc_bracketUnitPanel_2);
		
		DEBracketUnitPanel bracketUnitPanel_1 = new DEBracketUnitPanel((TournamentController) null);
		GridBagConstraints gbc_bracketUnitPanel_1 = new GridBagConstraints();
		gbc_bracketUnitPanel_1.gridheight = 2;
		gbc_bracketUnitPanel_1.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel_1.gridx = 0;
		gbc_bracketUnitPanel_1.gridy = 3;
		add(bracketUnitPanel_1, gbc_bracketUnitPanel_1);

	}
}
