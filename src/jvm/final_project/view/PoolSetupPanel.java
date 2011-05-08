package final_project.view;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class PoolSetupPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PoolSetupPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		PoolRefList poolRefList = new PoolRefList();
		add(poolRefList);
		
		PoolRefList poolRefList_1 = new PoolRefList();
		add(poolRefList_1);
		
		PoolRefList poolRefList_2 = new PoolRefList();
		add(poolRefList_2);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnReassignReferees = new JButton("Re-assign Referees");
		panel.add(btnReassignReferees);
		
		JButton btnAcceptSeeding = new JButton("Accept Assignments");
		panel.add(btnAcceptSeeding);

	}
}
