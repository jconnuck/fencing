package final_project.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import final_project.control.TournamentController;
import final_project.model.Pool;
import final_project.model.store.IClub;
import final_project.model.store.IDataStore;

public class PoolRefList extends JPanel {
	private JTable table;
	private TournamentController tournament;

	/**
	 * Create the panel.
	 */
	public PoolRefList(TournamentController t, Pool pool, IDataStore store) {
		tournament = t;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{111, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		String refName = "Referee: ";
		for(Integer i: pool.getRefs())
			refName += store.getReferee(i).getFirstName() + " " + store.getReferee(i).getLastName();
		
		JLabel lblReferee = new JLabel(refName);
		GridBagConstraints gbc_lblReferee = new GridBagConstraints();
		gbc_lblReferee.insets = new Insets(0, 0, 5, 5);
		gbc_lblReferee.gridx = 0;
		gbc_lblReferee.gridy = 0;
		add(lblReferee, gbc_lblReferee);
		
		String club = "Club: ";
		for(Integer i: pool.getRefs()) {
			//for(Integer c: store.getReferee(i).getClubs()) TODO work here
				//refName += store.getClub(c);
		}
		
		
		JLabel lblClubMiamiHeat = new JLabel(club);
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

	public class PoolRefTable extends AbstractTableModel {
		//Where to get people from?
		
		private int id;
		private String[] columnNames = {"Pool Size", "Big Pools", "Small Pools", ""};
		private Object[][] data = tournament.getPoolRefListTable(id);

		public PoolRefTable(int id_param) {
			id = id_param;
		}
		
		public void setData(Object[][] newData) {
			data = newData;
		}
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		@Override
		public int getRowCount() {
			return data.length;
		}
		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}
		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
		@Override
		public boolean isCellEditable(int row, int col) {
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}
		@Override
		public void setValueAt(Object value, int row, int col) {
			if(data.length == 0)
				return;
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
	}
	
}
