package final_project.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class RegisterNewPlayerPanel extends JPanel {
	private JTextField nameTextField;
	private JButton cancelButton;
	private JFormattedTextField phoneNumberTextField;
	JLabel lblNoResultsFound;
	private JTextField rankField;
	private JButton doneButton;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public RegisterNewPlayerPanel() {
		setOpaque(false);
		setPreferredSize(new Dimension(301, 200));
		setSize(new Dimension(270, 200));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{87, 55, 143, 0, 0};
		gridBagLayout.rowHeights = new int[]{26, 0, 32, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblNoResultsFound = new JLabel("<html><i>No Results Found</i></html>");
		GridBagConstraints gbc_lblNoResultsFound = new GridBagConstraints();
		gbc_lblNoResultsFound.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNoResultsFound.gridwidth = 4;
		gbc_lblNoResultsFound.insets = new Insets(0, 0, 5, 0);
		gbc_lblNoResultsFound.gridx = 0;
		gbc_lblNoResultsFound.gridy = 0;
		add(lblNoResultsFound, gbc_lblNoResultsFound);
		
		JLabel lblAddNewPerson = new JLabel("<html><b>Register new person</b></html>");
		GridBagConstraints gbc_lblAddNewPerson = new GridBagConstraints();
		gbc_lblAddNewPerson.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddNewPerson.gridwidth = 4;
		gbc_lblAddNewPerson.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddNewPerson.gridx = 0;
		gbc_lblAddNewPerson.gridy = 1;
		add(lblAddNewPerson, gbc_lblAddNewPerson);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		add(lblName, gbc_lblName);
		
		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.gridwidth = 3;
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 2;
		add(nameTextField, gbc_nameTextField);
		
		JLabel lblPhoneNumber = new JLabel("Phone #:");
		GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
		gbc_lblPhoneNumber.anchor = GridBagConstraints.EAST;
		gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNumber.gridx = 0;
		gbc_lblPhoneNumber.gridy = 3;
		add(lblPhoneNumber, gbc_lblPhoneNumber);
		
		MaskFormatter formatter = null;
		try {
		    formatter = new MaskFormatter("(###)-###-####");
		    formatter.setPlaceholderCharacter('*');
		} catch (java.text.ParseException e) {
		}
		phoneNumberTextField = new JFormattedTextField(formatter);
		GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
		gbc_phoneNumberTextField.gridwidth = 3;
		gbc_phoneNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberTextField.gridx = 1;
		gbc_phoneNumberTextField.gridy = 3;
		add(phoneNumberTextField, gbc_phoneNumberTextField);
		
		JLabel lblGroup = new JLabel("Group:");
		GridBagConstraints gbc_lblGroup = new GridBagConstraints();
		gbc_lblGroup.anchor = GridBagConstraints.EAST;
		gbc_lblGroup.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroup.gridx = 0;
		gbc_lblGroup.gridy = 4;
		add(lblGroup, gbc_lblGroup);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 4;
		add(comboBox, gbc_comboBox);
		
		JLabel rank = new JLabel("Rank:");
		GridBagConstraints gbc_rank = new GridBagConstraints();
		gbc_rank.anchor = GridBagConstraints.EAST;
		gbc_rank.insets = new Insets(0, 0, 5, 5);
		gbc_rank.gridx = 0;
		gbc_rank.gridy = 5;
		add(rank, gbc_rank);
		
		rankField = new JTextField();
		GridBagConstraints gbc_rankField = new GridBagConstraints();
		gbc_rankField.gridwidth = 3;
		gbc_rankField.insets = new Insets(0, 0, 5, 0);
		gbc_rankField.fill = GridBagConstraints.HORIZONTAL;
		gbc_rankField.gridx = 1;
		gbc_rankField.gridy = 5;
		add(rankField, gbc_rankField);
		rankField.setColumns(10);
		
		doneButton = new JButton("Done");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.anchor = GridBagConstraints.EAST;
		gbc_doneButton.gridx = 2;
		gbc_doneButton.gridy = 6;
		add(doneButton, gbc_doneButton);
		
		cancelButton = new JButton("Cancel");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.EAST;
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 6;
		add(cancelButton, gbc_cancelButton);

	}
	
	public void setNoResults(boolean noResults) {
		if (noResults) {
			lblNoResultsFound.setVisible(true);
		}
		else 
			lblNoResultsFound.setVisible(false);
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}

	public JTextField getPhoneNumberTextField() {
		return phoneNumberTextField;
	}
	public JButton getDoneButton() {
		return doneButton;
	}
	public JComboBox getGroup() {
		return comboBox;
	}
}

