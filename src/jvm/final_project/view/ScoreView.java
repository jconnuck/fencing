package final_project.view;

import java.awt.*;

import javax.swing.*;

import final_project.control.*;
import final_project.model.*;

public class ScoreView extends JPanel {
	TournamentController tournament;
	/**
	 * Create the panel.
	 */
	private ScoreView(TournamentController tournament, PlayerResult player1, PlayerResult player2) {
		this.tournament = tournament;
		String player1Name = tournament.getNameFromId(player1.getPlayerId());
		String player2Name = tournament.getNameFromId(player2.getPlayerId());
		int player1Score = player1.getPlayerScore();
		int player2Score = player2.getPlayerScore();
		
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{362, 0};
		gridBagLayout.rowHeights = new int[]{16, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.gridy = 0;
		gbc_panel1.anchor = GridBagConstraints.NORTH;
		add(panel, gbc_panel1);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel player1NameLabel = new JLabel(player1Name);
		panel.add(player1NameLabel);
		player1NameLabel.setFont(new Font("Score Board", Font.PLAIN, 18));
		player1NameLabel.setForeground(Color.WHITE);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JLabel player1ScoreLabel = new JLabel("");
		if (player1Score != -1)
			player1ScoreLabel.setText("" + player1Score);
		panel.add(player1ScoreLabel);
		player1ScoreLabel.setFont(new Font("Score Board", Font.PLAIN, 18));
		player1ScoreLabel.setForeground(Color.CYAN);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		JLabel vs = new JLabel("vs");
		vs.setFont(new Font("Score Board", Font.PLAIN, 16));
		vs.setForeground(Color.WHITE);
		panel.add(vs);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_3);
		
		JLabel player2ScoreLabel = new JLabel("");
		if (player2Score != -1)
			player2ScoreLabel.setText("" + player2Score);
		panel.add(player2ScoreLabel);
		player2ScoreLabel.setForeground(Color.ORANGE);
		player2ScoreLabel.setFont(new Font("Score Board", Font.PLAIN, 18));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_2);
		
		JLabel player2NameLabel = new JLabel(player2Name);
		panel.add(player2NameLabel);
		player2NameLabel.setForeground(Color.WHITE);
		player2NameLabel.setFont(new Font("Score Board", Font.PLAIN, 18));
	}

	public ScoreView(TournamentController tournament, IncompleteResult result) {
		this(tournament, new PlayerResult(result.getPlayer1(), -1), new PlayerResult(result.getPlayer2(), -1));
	}
	
	public ScoreView(TournamentController tournament, CompleteResult result) {
		this(tournament, new PlayerResult(result.getWinner(), result.getWinnerScore()), new PlayerResult(result.getLoser(), result.getLoserScore()));
	}
}
