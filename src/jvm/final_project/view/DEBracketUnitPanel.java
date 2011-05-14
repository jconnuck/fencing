package final_project.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import final_project.control.TournamentController;
import final_project.model.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class DEBracketUnitPanel extends JPanel {

	private JLabel lblPlayer1Name, lblPlayer2Name;
	/**
	 * Create the panel.
	 */
	public DEBracketUnitPanel(TournamentController tournament, Result result) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{100, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		lblPlayer1Name = new JLabel();
		lblPlayer1Name.setHorizontalTextPosition(SwingConstants.LEFT);
		lblPlayer2Name = new JLabel();
		lblPlayer2Name.setHorizontalTextPosition(SwingConstants.LEFT);
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics graphics) {
				Graphics2D g = (Graphics2D)graphics;
				g.setColor(Color.WHITE);
				//player 1 horizontal bar
				g.drawLine(0, 0, (this.getWidth() * 4) / 5, 0);
				//player 2 horizontal bar
				g.drawLine(0, this.getHeight() - 1, (this.getWidth() * 4) / 5, this.getHeight() - 1);
				//vertical connecting bar
				g.drawLine((this.getWidth() * 4) / 5, 0, (this.getWidth() * 4) / 5, this.getHeight());
				//horizontal connecting bar
				g.drawLine((this.getWidth() * 4) / 5, (this.getHeight()) / 2, this.getWidth(), (this.getHeight()) / 2);
			}
		};
		
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.repaint();
		
		lblPlayer1Name.setFont(new Font("Score Board", Font.PLAIN, 15));
		lblPlayer1Name.setForeground(Color.WHITE);
		lblPlayer2Name.setFont(new Font("Score Board", Font.PLAIN, 15));
		lblPlayer2Name.setForeground(Color.WHITE);
		
		
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

        if (result==null)
            lblPlayer1Name.setText("");
        else if (result instanceof CompleteResult) {
			CompleteResult cr = (CompleteResult) result;
			//if this was the winner get its score
			if (result.getPlayer1() == cr.getWinner()) {
				lblPlayer1Name.setText(tournament.getNameFromId(cr.getWinner()) + " (" + cr.getWinnerScore() + ")");
				lblPlayer1Name.setForeground(Color.CYAN);
			}
			else {
				lblPlayer1Name.setText(tournament.getNameFromId(cr.getLoser()) + " (" + cr.getLoserScore() + ")");
				lblPlayer1Name.setForeground(Color.ORANGE);
			}
		} 	
		else {
			lblPlayer1Name.setText(tournament.getNameFromId(result.getPlayer1()));
		}
        
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 0;
		panel.add(lblPlayer1Name, gbc_lblPlayerName);

        if (result==null)
            lblPlayer2Name.setText("");
        else if (result instanceof CompleteResult) {
			CompleteResult cr = (CompleteResult) result;
			//if this was the winner get its score
			if (result.getPlayer2() == cr.getWinner()) {
				lblPlayer2Name.setText(tournament.getNameFromId(cr.getWinner()) + " (" + cr.getWinnerScore() + ")");
				lblPlayer1Name.setForeground(Color.CYAN);
			}
			else {
				lblPlayer2Name.setText(tournament.getNameFromId(cr.getLoser()) + " (" + cr.getLoserScore() + ")");
				lblPlayer2Name.setForeground(Color.ORANGE);
			}
		} 	
		else {
			lblPlayer2Name.setText(tournament.getNameFromId(result.getPlayer2()));
		}
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
