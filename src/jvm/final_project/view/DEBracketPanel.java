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
		gridBagLayout.rowHeights = new int[]{50, 50, 0, 50, 50, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		DEBracketUnitPanel bracketUnitPanel = new DEBracketUnitPanel((TournamentController) null);
		GridBagConstraints gbc_bracketUnitPanel = new GridBagConstraints();
		gbc_bracketUnitPanel.gridheight = 2;
		gbc_bracketUnitPanel.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel.gridx = 0;
		gbc_bracketUnitPanel.gridy = 0;
		add(bracketUnitPanel, gbc_bracketUnitPanel);
		
		DEBracketUnitPanel bracketUnitPanel_1 = new DEBracketUnitPanel((TournamentController) null);
		GridBagConstraints gbc_bracketUnitPanel_1 = new GridBagConstraints();
		gbc_bracketUnitPanel_1.gridheight = 2;
		gbc_bracketUnitPanel_1.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel_1.gridx = 0;
		gbc_bracketUnitPanel_1.gridy = 3;
		add(bracketUnitPanel_1, gbc_bracketUnitPanel_1);
		
		DEBracketUnitPanel bracketUnitPanel_2 = new DEBracketUnitPanel((TournamentController) null);
		GridBagConstraints gbc_bracketUnitPanel_2 = new GridBagConstraints();
		gbc_bracketUnitPanel_2.insets = new Insets(0, 0, -1, 0);
		gbc_bracketUnitPanel_2.gridheight = 3;
		gbc_bracketUnitPanel_2.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel_2.gridx = 1;
		gbc_bracketUnitPanel_2.gridy = 1;
		add(bracketUnitPanel_2, gbc_bracketUnitPanel_2);
		
		DEBracketUnitPanel bracketUnitPanel_3 = new DEBracketUnitPanel((TournamentController) null);
		GridBagLayout gridBagLayout_1 = (GridBagLayout) bracketUnitPanel_3.getLayout();
		gridBagLayout_1.rowWeights = new double[]{1.0};
		gridBagLayout_1.rowHeights = new int[]{100};
		gridBagLayout_1.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0};
		GridBagConstraints gbc_bracketUnitPanel_3 = new GridBagConstraints();
		gbc_bracketUnitPanel_3.gridheight = 2;
		gbc_bracketUnitPanel_3.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel_3.gridx = 0;
		gbc_bracketUnitPanel_3.gridy = 6;
		add(bracketUnitPanel_3, gbc_bracketUnitPanel_3);
		
		DEBracketUnitPanel bracketUnitPanel_5 = new DEBracketUnitPanel((TournamentController) null);
		GridBagLayout gridBagLayout_3 = (GridBagLayout) bracketUnitPanel_5.getLayout();
		gridBagLayout_3.rowWeights = new double[]{1.0};
		gridBagLayout_3.rowHeights = new int[]{100};
		gridBagLayout_3.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout_3.columnWidths = new int[]{0, 0};
		GridBagConstraints gbc_bracketUnitPanel_5 = new GridBagConstraints();
		gbc_bracketUnitPanel_5.insets = new Insets(0, 0, 1, 0);
		gbc_bracketUnitPanel_5.gridheight = 3;
		gbc_bracketUnitPanel_5.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel_5.gridx = 1;
		gbc_bracketUnitPanel_5.gridy = 7;
		add(bracketUnitPanel_5, gbc_bracketUnitPanel_5);
		
		DEBracketUnitPanel bracketUnitPanel_4 = new DEBracketUnitPanel((TournamentController) null);
		GridBagLayout gridBagLayout_2 = (GridBagLayout) bracketUnitPanel_4.getLayout();
		gridBagLayout_2.rowWeights = new double[]{1.0};
		gridBagLayout_2.rowHeights = new int[]{100};
		gridBagLayout_2.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout_2.columnWidths = new int[]{0, 0};
		GridBagConstraints gbc_bracketUnitPanel_4 = new GridBagConstraints();
		gbc_bracketUnitPanel_4.insets = new Insets(0, 0, 4, 0);
		gbc_bracketUnitPanel_4.gridheight = 2;
		gbc_bracketUnitPanel_4.fill = GridBagConstraints.BOTH;
		gbc_bracketUnitPanel_4.gridx = 0;
		gbc_bracketUnitPanel_4.gridy = 9;
		add(bracketUnitPanel_4, gbc_bracketUnitPanel_4);

	}
}
