package final_project.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

import final_project.control.TournamentController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CutPanel extends JPanel {
	private JTextField textField;
	private TournamentController tournament;

	/**
	 * Create the panel.
	 */
	public CutPanel(final TournamentController tournament) {
		this.tournament = tournament;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{229, 134, 0};
		gridBagLayout.rowHeights = new int[]{28, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPercentageToMove = new JLabel("Percentage to move on to DE Round:");
		GridBagConstraints gbc_lblPercentageToMove = new GridBagConstraints();
		gbc_lblPercentageToMove.anchor = GridBagConstraints.EAST;
		gbc_lblPercentageToMove.insets = new Insets(0, 0, 5, 5);
		gbc_lblPercentageToMove.gridx = 0;
		gbc_lblPercentageToMove.gridy = 0;
		add(lblPercentageToMove, gbc_lblPercentageToMove);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tournament.getMainWindow().loadRightPanel(new DEPanel(tournament));
			}
		});
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.anchor = GridBagConstraints.EAST;
		gbc_btnDone.gridx = 1;
		gbc_btnDone.gridy = 1;
		add(btnDone, gbc_btnDone);

	}

}
