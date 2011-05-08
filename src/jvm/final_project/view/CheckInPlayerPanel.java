package final_project.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JTextField;

public class CheckInPlayerPanel extends JPanel {
	JLabel resultLabel;
	private JButton btnCancel;
	private JButton signInButton;

	/**
	 * Create the panel.
	 */
	public CheckInPlayerPanel() {
		setOpaque(false);
		setPreferredSize(new Dimension(254, 60));
		setSize(new Dimension(270, 200));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{143, 143, 143, 0};
		gridBagLayout.rowHeights = new int[]{26, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		resultLabel = new JLabel("<html><i>Exact Match Found:</i> <b>Person's Name</b></html>");
		GridBagConstraints gbc_resultLabel = new GridBagConstraints();
		gbc_resultLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_resultLabel.gridwidth = 3;
		gbc_resultLabel.insets = new Insets(0, 0, 5, 0);
		gbc_resultLabel.gridx = 0;
		gbc_resultLabel.gridy = 0;
		add(resultLabel, gbc_resultLabel);
		
		signInButton = new JButton("Sign In");
		signInButton.setToolTipText("Hit ENTER to Sign In");
		GridBagConstraints gbc_signInButton = new GridBagConstraints();
		gbc_signInButton.insets = new Insets(0, 0, 0, 5);
		gbc_signInButton.anchor = GridBagConstraints.EAST;
		gbc_signInButton.gridx = 1;
		gbc_signInButton.gridy = 1;
		add(signInButton, gbc_signInButton);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 1;
		add(btnCancel, gbc_btnCancel);

	}
	
	public void setNoResults(boolean noResults) {
		if (noResults) {
			resultLabel.setVisible(true);
		}
		else 
			resultLabel.setVisible(false);
	}
	public JButton getCancelButton() {
		return btnCancel;
	}
	public JLabel getResultLabel() {
		return resultLabel;
	}
	public JButton getSignInButton() {
		return signInButton;
	}
}
