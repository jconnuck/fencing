package final_project.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;

public class ConfirmationPanel extends JPanel {
	private JButton cancelButton;
	private JButton yesButton;

	/**
	 * Create the panel.
	 */
	public ConfirmationPanel(String action) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{178, 69, 0};
		gridBagLayout.rowHeights = new int[]{16, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to " + action + " all? ");
		GridBagConstraints gbc_lblAreYouSure = new GridBagConstraints();
		gbc_lblAreYouSure.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAreYouSure.gridwidth = 2;
		gbc_lblAreYouSure.insets = new Insets(0, 0, 5, 5);
		gbc_lblAreYouSure.anchor = GridBagConstraints.NORTH;
		gbc_lblAreYouSure.gridx = 0;
		gbc_lblAreYouSure.gridy = 0;
		add(lblAreYouSure, gbc_lblAreYouSure);
		
		yesButton = new JButton("Yes");
		GridBagConstraints gbc_yesButton = new GridBagConstraints();
		gbc_yesButton.anchor = GridBagConstraints.EAST;
		gbc_yesButton.insets = new Insets(0, 0, 5, 5);
		gbc_yesButton.gridx = 0;
		gbc_yesButton.gridy = 1;
		add(yesButton, gbc_yesButton);
		
		cancelButton = new JButton("Cancel");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.insets = new Insets(0, 0, 5, 0);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 1;
		add(cancelButton, gbc_cancelButton);

	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	public JButton getYesButton() {
		return yesButton;
	}
}
