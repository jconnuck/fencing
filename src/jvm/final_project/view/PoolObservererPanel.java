package final_project.view;

import javax.swing.*;

import final_project.control.*;
import final_project.model.*;

import java.awt.*;
import java.awt.event.*;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.*;

public class PoolObservererPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	TournamentController tournament;
	JLabel statusLabel;
	JPanel upcommingBoutsPane;
	
	public PoolObservererPanel(TournamentController tournament, int poolNumber) {
		this.tournament = tournament;
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{136, 183, 134, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 24, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPool = new JLabel("pool " + poolNumber);
		lblPool.setFont(new Font("Score Board", Font.PLAIN, 24));
		lblPool.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPool = new GridBagConstraints();
		gbc_lblPool.gridwidth = 3;
		gbc_lblPool.insets = new Insets(0, 0, 5, 5);
		gbc_lblPool.gridx = 0;
		gbc_lblPool.gridy = 0;
		add(lblPool, gbc_lblPool);
		
		JLabel statusLabel = new JLabel("-status: fencing-");
		statusLabel.setForeground(Color.GREEN);
		statusLabel.setFont(new Font("Score Board", Font.PLAIN, 16));
		GridBagConstraints gbc_statusLabel = new GridBagConstraints();
		gbc_statusLabel.gridwidth = 3;
		gbc_statusLabel.insets = new Insets(0, 0, 5, 0);
		gbc_statusLabel.gridx = 0;
		gbc_statusLabel.gridy = 1;
		add(statusLabel, gbc_statusLabel);
		
		JLabel lblUpcommingBouts = new JLabel("upcoming bouts");
		lblUpcommingBouts.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpcommingBouts.setFont(new Font("Score Board", Font.PLAIN, 16));
		lblUpcommingBouts.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblUpcommingBouts = new GridBagConstraints();
		gbc_lblUpcommingBouts.gridwidth = 3;
		gbc_lblUpcommingBouts.insets = new Insets(0, 0, 5, 0);
		gbc_lblUpcommingBouts.gridx = 0;
		gbc_lblUpcommingBouts.gridy = 2;
		add(lblUpcommingBouts, gbc_lblUpcommingBouts);
		
		JScrollPane upcommingBoutsScrollPane = new JScrollPane();
		GridBagConstraints gbc_upcommingBoutsScrollPane = new GridBagConstraints();
		gbc_upcommingBoutsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_upcommingBoutsScrollPane.gridwidth = 3;
		gbc_upcommingBoutsScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_upcommingBoutsScrollPane.gridx = 0;
		gbc_upcommingBoutsScrollPane.gridy = 3;
		add(upcommingBoutsScrollPane, gbc_upcommingBoutsScrollPane);
		
		upcommingBoutsPane = new JPanel();
		upcommingBoutsPane.setBackground(Color.BLACK);
		upcommingBoutsScrollPane.setViewportView(upcommingBoutsPane);
		upcommingBoutsPane.setLayout(new BoxLayout(upcommingBoutsPane, BoxLayout.Y_AXIS));
		
		JLabel lblCurrentBout = new JLabel("current bout");
		lblCurrentBout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentBout.setForeground(Color.WHITE);
		lblCurrentBout.setFont(new Font("Score Board", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCurrentBout = new GridBagConstraints();
		gbc_lblCurrentBout.gridwidth = 3;
		gbc_lblCurrentBout.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentBout.gridx = 0;
		gbc_lblCurrentBout.gridy = 4;
		add(lblCurrentBout, gbc_lblCurrentBout);
		
		JLabel lblLebronJamesVs = new JLabel("Lebron James vs. Kobe Bryant");
		lblLebronJamesVs.setForeground(Color.CYAN);
		lblLebronJamesVs.setFont(new Font("Score Board", Font.PLAIN, 18));
		GridBagConstraints gbc_lblLebronJamesVs = new GridBagConstraints();
		gbc_lblLebronJamesVs.anchor = GridBagConstraints.SOUTH;
		gbc_lblLebronJamesVs.gridwidth = 3;
		gbc_lblLebronJamesVs.insets = new Insets(0, 0, 5, 0);
		gbc_lblLebronJamesVs.gridx = 0;
		gbc_lblLebronJamesVs.gridy = 5;
		add(lblLebronJamesVs, gbc_lblLebronJamesVs);
		
		//TODO remove these: Mock incomplete results:
		IncompleteResult testIncompleteResult1 = new IncompleteResult(0, 1, 5);
		IncompleteResult testIncompleteResult2 = new IncompleteResult(1, 2, 5);
		
		ScoreView scoreView = new ScoreView(tournament, testIncompleteResult1);
		upcommingBoutsPane.add(scoreView, 0);
		
		ScoreView scoreView_1 = new ScoreView(tournament, testIncompleteResult2);
		upcommingBoutsPane.add(scoreView_1, 0);
		
		ScoreView scoreView_2 = new ScoreView(tournament, testIncompleteResult1);
		upcommingBoutsPane.add(scoreView_2, 0);
		
		ScoreView scoreView_3 = new ScoreView(tournament, testIncompleteResult2);
		upcommingBoutsPane.add(scoreView_3, 0);
		
		ScoreView scoreView_4 = new ScoreView(tournament, testIncompleteResult1);
		upcommingBoutsPane.add(scoreView_4, 0);
		
		JLabel lblCompletedBouts = new JLabel("completed bouts");
		lblCompletedBouts.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompletedBouts.setForeground(Color.WHITE);
		lblCompletedBouts.setFont(new Font("Score Board", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCompletedBouts = new GridBagConstraints();
		gbc_lblCompletedBouts.gridwidth = 3;
		gbc_lblCompletedBouts.insets = new Insets(0, 0, 5, 0);
		gbc_lblCompletedBouts.gridx = 0;
		gbc_lblCompletedBouts.gridy = 6;
		add(lblCompletedBouts, gbc_lblCompletedBouts);
		
		JScrollPane completedBoutsScrollPane = new JScrollPane();
		GridBagConstraints gbc_completedBoutsScrollPane = new GridBagConstraints();
		gbc_completedBoutsScrollPane.gridwidth = 3;
		gbc_completedBoutsScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_completedBoutsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_completedBoutsScrollPane.gridx = 0;
		gbc_completedBoutsScrollPane.gridy = 7;
		add(completedBoutsScrollPane, gbc_completedBoutsScrollPane);
		
		JPanel completedBoutsPane = new JPanel();
		completedBoutsPane.setBackground(Color.BLACK);
		completedBoutsScrollPane.setViewportView(completedBoutsPane);
		completedBoutsPane.setLayout(new BoxLayout(completedBoutsPane, BoxLayout.Y_AXIS));
		
		//TODO remove these: Mock incomplete results:
		CompleteResult testCompleteResult1 = new CompleteResult(new PlayerResult(0, 5), new PlayerResult(1,3));
		CompleteResult testCompleteResult2 = new CompleteResult(new PlayerResult(1, 4), new PlayerResult(2,5));
		
		ScoreView scoreView_5 = new ScoreView(tournament, testCompleteResult1);
		completedBoutsPane.add(scoreView_5, 0);
		
		ScoreView scoreView_6 = new ScoreView(tournament, testCompleteResult2);
		completedBoutsPane.add(scoreView_6, 0);
		
		ScoreView scoreView_7 = new ScoreView(tournament, testCompleteResult1);
		completedBoutsPane.add(scoreView_7, 0);
		
		ScoreView scoreView_8 = new ScoreView(tournament, testCompleteResult2);
		completedBoutsPane.add(scoreView_8, 0);
		
		ScoreView scoreView_9 = new ScoreView(tournament, testCompleteResult1);
		completedBoutsPane.add(scoreView_9, 0);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 8;
		add(progressBar, gbc_progressBar);
		
		final JButton btnMessageReferee = new JButton("Message Referee");
		btnMessageReferee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageRefPanel messageRefPanel = new MessageRefPanel();
				BalloonTip messageRefTip = new BalloonTip(btnMessageReferee, messageRefPanel, new DefaultBalloonStyle(), false);
			}
		});
		GridBagConstraints gbc_btnMessageReferee = new GridBagConstraints();
		gbc_btnMessageReferee.gridx = 2;
		gbc_btnMessageReferee.gridy = 8;
		add(btnMessageReferee, gbc_btnMessageReferee);

	}
	
	public enum Status {
		FENCING, TECHNICAL, MEDICAL;
	}
	
	public void setStatus(Status status) {
		switch (status) {
			case FENCING:
				statusLabel.setText("-Status: Fencing-");
				statusLabel.setForeground(Color.GREEN);
				break;
			case TECHNICAL:
				statusLabel.setText("-Status: Technical-");
				statusLabel.setForeground(Color.YELLOW);
				break;
			case MEDICAL:
				statusLabel.setText("-Status: Medical-");
				statusLabel.setForeground(Color.RED);
				break;
			default:
				statusLabel.setText("-Status: Fencing-");
				statusLabel.setForeground(Color.GREEN);
				break;
		}
	}
	
	public void addIncompleteResult(IncompleteResult incompleteResult) {
		ScoreView newBout = new ScoreView(tournament, incompleteResult);
		upcommingBoutsPane.add(newBout, 0);
	}
	
	public void addCompleteResult(CompleteResult completeResult) {
		ScoreView newBout = new ScoreView(tournament, completeResult);
		upcommingBoutsPane.add(newBout, 0);
	}

}
