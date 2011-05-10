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

	private JLabel lblPlayer1Name, lblPlayer2Name;
	/**
	 * Create the panel.
	 */
	public DEBracketUnitPanel(TournamentController tournament, String player1Name, String player2Name) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{100, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics graphics) {
				Graphics2D g = (Graphics2D)graphics;
				g.setColor(Color.BLACK);
				//player 1 horizontal bar
				g.drawLine(0, 0, (this.getWidth() * 2) / 3, 0);
				//player 2 horizontal bar
				g.drawLine(0, this.getHeight() - 1, (this.getWidth() * 2) / 3, this.getHeight() - 1);
				//vertical connecting bar
				g.drawLine((this.getWidth() * 2) / 3, 0, (this.getWidth() * 2) / 3, this.getHeight());
				//horizontal connecting bar
				g.drawLine((this.getWidth() * 2) / 3, (this.getHeight()) / 2, this.getWidth(), (this.getHeight()) / 2);
			}
		};
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.repaint();
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblPlayer1Name = new JLabel(player1Name);
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 0;
		panel.add(lblPlayer1Name, gbc_lblPlayerName);
		
		lblPlayer2Name = new JLabel(player2Name);
		GridBagConstraints gbc_lblPlayerName_1 = new GridBagConstraints();
		gbc_lblPlayerName_1.gridx = 0;
		gbc_lblPlayerName_1.gridy = 2;
		panel.add(lblPlayer2Name, gbc_lblPlayerName_1);

	}
	
	public void setPlayerNames(String player1Name, String player2Name) {
		lblPlayer1Name.setText(player1Name);
		lblPlayer2Name.setText(player2Name);
	}
	

}
