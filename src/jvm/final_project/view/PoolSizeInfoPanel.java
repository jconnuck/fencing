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

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class PoolSizeInfoPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PoolSizeInfoPanel() {
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"4", null, null, "Select"},
				{"5", null, null, "Select"},
				{"6", null, null, "Select"},
				{"7", null, null, "Select"},
				{"5", null, null, "Select"},
			},
			new String[] {
				"Pool Size", "Big Pools", "Small Pools", ""
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setCellSelectionEnabled(false);
		
		//Make last column buttons
		ButtonColumn buttonColumn = new ButtonColumn(table, new SelectAction(), 3);
	}
	
	class SelectAction extends AbstractAction {
	    public SelectAction() {
	        super("Select", null);
	    }
	    public void actionPerformed(ActionEvent e) {
	    }
	}

}
