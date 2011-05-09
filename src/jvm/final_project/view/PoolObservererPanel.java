package final_project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.*;

public class PoolObservererPanel extends JPanel {
	JLabel statusLabel;
	/**
	 * Create the panel.
	 */
	public PoolObservererPanel() {
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{27, 27, 291, 134, 0, 0};
		gridBagLayout.rowHeights = new int[]{1, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		statusLabel = new JLabel("Status: Fencing");
		statusLabel.setFont(new Font("Score Board", Font.PLAIN, 16));
		statusLabel.setForeground(new Color(50, 205, 50));
		GridBagConstraints gbc_lblStatusFencing = new GridBagConstraints();
		gbc_lblStatusFencing.gridwidth = 5;
		gbc_lblStatusFencing.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatusFencing.gridx = 0;
		gbc_lblStatusFencing.gridy = 0;
		add(statusLabel, gbc_lblStatusFencing);
		
		JLabel lblCurrentBout = new JLabel("Current Bout:");
		lblCurrentBout.setFont(new Font("Score Board", Font.PLAIN, 14));
		lblCurrentBout.setForeground(new Color(0, 191, 255));
		GridBagConstraints gbc_lblCurrentBout = new GridBagConstraints();
		gbc_lblCurrentBout.gridwidth = 2;
		gbc_lblCurrentBout.anchor = GridBagConstraints.WEST;
		gbc_lblCurrentBout.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentBout.gridx = 0;
		gbc_lblCurrentBout.gridy = 1;
		add(lblCurrentBout, gbc_lblCurrentBout);
		
		JLabel lblLebronJamesVs = new JLabel("Lebron James vs. Kobe Bryant");
		lblLebronJamesVs.setForeground(new Color(0, 191, 255));
		lblLebronJamesVs.setFont(new Font("Score Board", Font.PLAIN, 18));
		GridBagConstraints gbc_lblLebronJamesVs = new GridBagConstraints();
		gbc_lblLebronJamesVs.anchor = GridBagConstraints.SOUTH;
		gbc_lblLebronJamesVs.gridwidth = 3;
		gbc_lblLebronJamesVs.insets = new Insets(0, 0, 5, 0);
		gbc_lblLebronJamesVs.gridx = 2;
		gbc_lblLebronJamesVs.gridy = 1;
		add(lblLebronJamesVs, gbc_lblLebronJamesVs);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		ScoreView scoreView = new ScoreView();
		panel.add(scoreView);
		scoreView.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 3;
		add(progressBar, gbc_progressBar);
		
		final JButton btnMessageReferee = new JButton("Message Referee");
		btnMessageReferee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageRefPanel messageRefPanel = new MessageRefPanel();
				BalloonTip messageRefTip = new BalloonTip(btnMessageReferee, messageRefPanel, new DefaultBalloonStyle(), false);
			}
		});
		GridBagConstraints gbc_btnMessageReferee = new GridBagConstraints();
		gbc_btnMessageReferee.insets = new Insets(0, 0, 0, 5);
		gbc_btnMessageReferee.gridx = 3;
		gbc_btnMessageReferee.gridy = 3;
		add(btnMessageReferee, gbc_btnMessageReferee);
		
		//create a ScoreView for each score from the pool
		for (int i = 0; i < 4; ++i) {
			panel.add(new ScoreView());
		}

	}
	
	public enum Status {
		FENCING, TECHNICAL, MEDICAL;
	}
	
	public void setStatus(Status status) {
		switch (status) {
			case FENCING:
				statusLabel.setText("Status: Fencing");
				statusLabel.setForeground(Color.GREEN);
				break;
			case TECHNICAL:
				statusLabel.setText("Status: Technical");
				statusLabel.setForeground(Color.YELLOW);
				break;
			case MEDICAL:
				statusLabel.setText("Status: Medical");
				statusLabel.setForeground(Color.RED);
				break;
			default:
				statusLabel.setText("Status: Fencing");
				statusLabel.setForeground(Color.GREEN);
				break;
		}
	}

}
