package final_project.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PoolRefList extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PoolRefList() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{111, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblReferee = new JLabel("Referee: Joe Smith");
		GridBagConstraints gbc_lblReferee = new GridBagConstraints();
		gbc_lblReferee.insets = new Insets(0, 0, 5, 5);
		gbc_lblReferee.gridx = 0;
		gbc_lblReferee.gridy = 0;
		add(lblReferee, gbc_lblReferee);
		
		JLabel lblClubMiamiHeat = new JLabel("Club: Miami Heat");
		GridBagConstraints gbc_lblClubMiamiHeat = new GridBagConstraints();
		gbc_lblClubMiamiHeat.insets = new Insets(0, 0, 5, 5);
		gbc_lblClubMiamiHeat.gridx = 2;
		gbc_lblClubMiamiHeat.gridy = 0;
		add(lblClubMiamiHeat, gbc_lblClubMiamiHeat);
		
		JCheckBox chckbxConflict = new JCheckBox("Conflict");
		GridBagConstraints gbc_chckbxConflict = new GridBagConstraints();
		gbc_chckbxConflict.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxConflict.gridx = 4;
		gbc_chckbxConflict.gridy = 0;
		add(chckbxConflict, gbc_chckbxConflict);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"John Connuck", "NY Knicks", "1"},
				{"Landry Fields", "NY Knicks", "2"},
				{"Lebron James", "Miami Heat", "3"},
				{"Kobe Bryant", "LA Lakers", "4"},
			},
			new String[] {
				"Name", "Club", "Seed"
			}
		));
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.gridwidth = 5;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		add(table, gbc_table);

	}

}
