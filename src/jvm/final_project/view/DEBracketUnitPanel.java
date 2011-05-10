package final_project.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import final_project.control.TournamentController;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public class DEBracketUnitPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DEBracketUnitPanel(TournamentController tournament) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPlayerName = new JLabel("Player 1 Name");
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 0;
		add(lblPlayerName, gbc_lblPlayerName);
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics graphics) {
				Graphics2D g = (Graphics2D)graphics;
				g.setColor(Color.BLACK);
				//player 1 horizontal bar
				g.drawLine(0, 8, (this.getWidth() * 2) / 3, 8);
				//player 2 horizontal bar
				g.drawLine(0, this.getHeight() - 1, (this.getWidth() * 2) / 3, this.getHeight() - 1);
				//vertical connecting bar
				g.drawLine((this.getWidth() * 2) / 3, 8, (this.getWidth() * 2) / 3, this.getHeight() - 1);
				//horizontal connecting bar
				g.drawLine((this.getWidth() * 2) / 3, (this.getHeight() - 9) / 2, this.getWidth(), (this.getHeight() - 9) / 2);
			}
		};
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.repaint();
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JLabel lblPlayerName_1 = new JLabel("Player 2 Name");
		GridBagConstraints gbc_lblPlayerName_1 = new GridBagConstraints();
		gbc_lblPlayerName_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlayerName_1.gridx = 0;
		gbc_lblPlayerName_1.gridy = 2;
		add(lblPlayerName_1, gbc_lblPlayerName_1);

	}
	
	

}
