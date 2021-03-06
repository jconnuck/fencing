package final_project.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.Action;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import final_project.control.Constants;
import final_project.control.TournamentController;

import java.awt.Dimension;

public class PoolSizeInfoPanel extends JPanel implements Constants {
	private JTable table;
	private TournamentController tournament;
	private PoolSizeInfoTable poolSizeInfoTable;
	/**
	 * Create the panel.
	 */
	public PoolSizeInfoPanel(TournamentController t) {
		tournament = t;
		
		setPreferredSize(new Dimension(300, 177));
		setMaximumSize(new Dimension(300, 200));
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{299, 0};
		gridBagLayout.rowHeights = new int[]{0, 146, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPleaseSelectYour = new JLabel("Please select your preferred pool size:");
		GridBagConstraints gbc_lblPleaseSelectYour = new GridBagConstraints();
		gbc_lblPleaseSelectYour.insets = new Insets(0, 0, 5, 0);
		gbc_lblPleaseSelectYour.gridx = 0;
		gbc_lblPleaseSelectYour.gridy = 0;
		add(lblPleaseSelectYour, gbc_lblPleaseSelectYour);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(24);
		scrollPane.setViewportView(table);
		
		poolSizeInfoTable = new PoolSizeInfoTable();
		table.setModel(poolSizeInfoTable);
		table.setCellSelectionEnabled(false);
		
		//Make last column buttons
		ButtonColumn buttonColumn = new ButtonColumn(table, new SelectAction(), 3);
	}
	
	public void setData(Object[][] newData) {
		poolSizeInfoTable.setData(newData);
	}
	
	public class PoolSizeInfoTable extends AbstractTableModel {
		private String[] columnNames = {"Pool Size", "Big Pools", "Small Pools", ""};
		private Object[][] data = tournament.getPoolSizeInfoTable();

		public void setData(Object[][] newData) {
			data = newData;
            fireTableDataChanged();
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
		public Class getColumnClass(int col) {
            if (col <= 2)
                return new Integer(1).getClass();
            else
                return "Select".getClass();
		}
		@Override
		public boolean isCellEditable(int row, int col) {
			if (col <= 2) {
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
        public int getPoolSize(int row) {
            return ((Integer)data[row][0]).intValue();
        }
	}

	class SelectAction extends AbstractAction {
	    public SelectAction() {
	        super("Select", null);
	    }
	    public void actionPerformed(ActionEvent e) {
            int row = Integer.parseInt(e.getActionCommand());
            int poolSize = poolSizeInfoTable.getPoolSize(row);
            tournament.startPoolRound(0,poolSize);
            //TODO: Make gui move to pool round
            tournament.getMainWindow().loadRightPanel(new PoolSetupPanel(tournament));
            tournament.getMainWindow().hideAllBalloons();
	    }
	}

}
