package final_project.view;

import javax.swing.*;

import final_project.control.*;
import final_project.model.*;

import java.awt.*;
import java.awt.event.*;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.*;
import javax.swing.border.EmptyBorder;

public class PoolObserverPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	TournamentController tournament;
	JLabel statusLabel;
	JPanel upcommingBoutsPane;
	int numIncompleteBouts;
	
	public PoolObserverPanel(TournamentController tournament, int poolNumber) {
		this.tournament = tournament;
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{151, 80, 80, 150, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 24, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblRefereeJoeSmith = new JLabel("Ref: Joe Smith");
		lblRefereeJoeSmith.setForeground(Color.WHITE);
		lblRefereeJoeSmith.setFont(new Font("Score Board", Font.PLAIN, 17));
		GridBagConstraints gbc_lblRefereeJoeSmith = new GridBagConstraints();
		gbc_lblRefereeJoeSmith.insets = new Insets(0, 0, 5, 5);
		gbc_lblRefereeJoeSmith.gridx = 0;
		gbc_lblRefereeJoeSmith.gridy = 0;
		add(lblRefereeJoeSmith, gbc_lblRefereeJoeSmith);
		
		JLabel lblPool = new JLabel("pool " + poolNumber);
		lblPool.setFont(new Font("Score Board", Font.PLAIN, 24));
		lblPool.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPool = new GridBagConstraints();
		gbc_lblPool.gridwidth = 2;
		gbc_lblPool.insets = new Insets(0, 0, 5, 5);
		gbc_lblPool.gridx = 1;
		gbc_lblPool.gridy = 0;
		add(lblPool, gbc_lblPool);
		
		JLabel lblStrip = new JLabel("Strip: 5");
		lblStrip.setFont(new Font("Score Board", Font.PLAIN, 17));
		lblStrip.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStrip = new GridBagConstraints();
		gbc_lblStrip.insets = new Insets(0, 0, 5, 0);
		gbc_lblStrip.gridx = 3;
		gbc_lblStrip.gridy = 0;
		add(lblStrip, gbc_lblStrip);
		
		JLabel statusLabel = new JLabel("-status: fencing-");
		statusLabel.setForeground(Color.GREEN);
		statusLabel.setFont(new Font("Score Board", Font.PLAIN, 16));
		GridBagConstraints gbc_statusLabel = new GridBagConstraints();
		gbc_statusLabel.gridwidth = 4;
		gbc_statusLabel.insets = new Insets(0, 0, 5, 0);
		gbc_statusLabel.gridx = 0;
		gbc_statusLabel.gridy = 1;
		add(statusLabel, gbc_statusLabel);
		
		JScrollPane upcommingBoutsScrollPane = new JScrollPane();
		upcommingBoutsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagConstraints gbc_upcommingBoutsScrollPane = new GridBagConstraints();
		gbc_upcommingBoutsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_upcommingBoutsScrollPane.gridwidth = 4;
		gbc_upcommingBoutsScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_upcommingBoutsScrollPane.gridx = 0;
		gbc_upcommingBoutsScrollPane.gridy = 2;
		add(upcommingBoutsScrollPane, gbc_upcommingBoutsScrollPane);
		
		upcommingBoutsPane = new JPanel();
		upcommingBoutsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		upcommingBoutsPane.setBackground(Color.BLACK);
		upcommingBoutsScrollPane.setViewportView(upcommingBoutsPane);
		upcommingBoutsPane.setLayout(new BoxLayout(upcommingBoutsPane, BoxLayout.Y_AXIS));
		
		JLabel lblUpcommingBouts = new JLabel("upcoming bouts");
		lblUpcommingBouts.setAlignmentX(Component.CENTER_ALIGNMENT);
		upcommingBoutsPane.add(lblUpcommingBouts);
		lblUpcommingBouts.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpcommingBouts.setFont(new Font("Score Board", Font.PLAIN, 16));
		lblUpcommingBouts.setForeground(Color.WHITE);
		
		JLabel lblCurrentBout = new JLabel("current bout");
		lblCurrentBout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentBout.setForeground(Color.WHITE);
		lblCurrentBout.setFont(new Font("Score Board", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCurrentBout = new GridBagConstraints();
		gbc_lblCurrentBout.gridwidth = 4;
		gbc_lblCurrentBout.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentBout.gridx = 0;
		gbc_lblCurrentBout.gridy = 3;
		add(lblCurrentBout, gbc_lblCurrentBout);
		
		JLabel lblLebronJamesVs = new JLabel("Lebron James vs. Kobe Bryant");
		lblLebronJamesVs.setForeground(Color.CYAN);
		lblLebronJamesVs.setFont(new Font("Score Board", Font.PLAIN, 18));
		GridBagConstraints gbc_lblLebronJamesVs = new GridBagConstraints();
		gbc_lblLebronJamesVs.anchor = GridBagConstraints.SOUTH;
		gbc_lblLebronJamesVs.gridwidth = 4;
		gbc_lblLebronJamesVs.insets = new Insets(0, 0, 5, 0);
		gbc_lblLebronJamesVs.gridx = 0;
		gbc_lblLebronJamesVs.gridy = 4;
		add(lblLebronJamesVs, gbc_lblLebronJamesVs);
		
		//TODO remove these: Mock incomplete results:
		IncompleteResult testIncompleteResult1 = new IncompleteResult(0, 1, 5);
		IncompleteResult testIncompleteResult2 = new IncompleteResult(1, 2, 5);
		
		ScoreView scoreView = new ScoreView(tournament, testIncompleteResult1);
		upcommingBoutsPane.add(scoreView, 1);
		
		ScoreView scoreView_1 = new ScoreView(tournament, testIncompleteResult2);
		upcommingBoutsPane.add(scoreView_1, 1);
		
		ScoreView scoreView_2 = new ScoreView(tournament, testIncompleteResult1);
		upcommingBoutsPane.add(scoreView_2, 1);
		
		ScoreView scoreView_3 = new ScoreView(tournament, testIncompleteResult2);
		upcommingBoutsPane.add(scoreView_3, 1);
		
		ScoreView scoreView_4 = new ScoreView(tournament, testIncompleteResult1);
		upcommingBoutsPane.add(scoreView_4, 1);
		
		JScrollPane completedBoutsScrollPane = new JScrollPane();
		completedBoutsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagConstraints gbc_completedBoutsScrollPane = new GridBagConstraints();
		gbc_completedBoutsScrollPane.gridwidth = 4;
		gbc_completedBoutsScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_completedBoutsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_completedBoutsScrollPane.gridx = 0;
		gbc_completedBoutsScrollPane.gridy = 5;
		add(completedBoutsScrollPane, gbc_completedBoutsScrollPane);
		
		JPanel completedBoutsPane = new JPanel();
		completedBoutsPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		completedBoutsPane.setBackground(Color.BLACK);
		completedBoutsScrollPane.setViewportView(completedBoutsPane);
		completedBoutsPane.setLayout(new BoxLayout(completedBoutsPane, BoxLayout.Y_AXIS));
		
		JLabel lblCompletedBouts = new JLabel("completed bouts");
		lblCompletedBouts.setAlignmentX(Component.CENTER_ALIGNMENT);
		completedBoutsPane.add(lblCompletedBouts);
		lblCompletedBouts.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompletedBouts.setForeground(Color.WHITE);
		lblCompletedBouts.setFont(new Font("Score Board", Font.PLAIN, 16));
		
		//TODO remove these: Mock incomplete results:
		CompleteResult testCompleteResult1 = new CompleteResult(new PlayerResult(0, 5), new PlayerResult(1,3));
		CompleteResult testCompleteResult2 = new CompleteResult(new PlayerResult(1, 4), new PlayerResult(2,5));
		
		ScoreView scoreView_5 = new ScoreView(tournament, testCompleteResult1);
		completedBoutsPane.add(scoreView_5, 1);
		
		ScoreView scoreView_6 = new ScoreView(tournament, testCompleteResult2);
		completedBoutsPane.add(scoreView_6, 1);
		
		ScoreView scoreView_7 = new ScoreView(tournament, testCompleteResult1);
		completedBoutsPane.add(scoreView_7, 1);
		
		ScoreView scoreView_8 = new ScoreView(tournament, testCompleteResult2);
		completedBoutsPane.add(scoreView_8, 1);
		
		ScoreView scoreView_9 = new ScoreView(tournament, testCompleteResult1);
		completedBoutsPane.add(scoreView_9, 1);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 6;
		add(progressBar, gbc_progressBar);
		
		final JButton btnMessageReferee = new JButton("Message Referee");
		btnMessageReferee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageRefPanel messageRefPanel = new MessageRefPanel();
				BalloonTip messageRefTip = new BalloonTip(btnMessageReferee, messageRefPanel, new DefaultBalloonStyle(), false);
			}
		});
		GridBagConstraints gbc_btnMessageReferee = new GridBagConstraints();
		gbc_btnMessageReferee.gridx = 3;
		gbc_btnMessageReferee.gridy = 6;
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
		++numIncompleteBouts;
		ScoreView newBout = new ScoreView(tournament, incompleteResult);
		upcommingBoutsPane.add(newBout, 1);
	}
	
	public void addCompleteResult(CompleteResult completeResult) {
		ScoreView newBout = new ScoreView(tournament, completeResult);
		upcommingBoutsPane.add(newBout, 1);
	}
	
	public void setCurrentBout(IncompleteResult incompleteResult) {
	
	}

}
