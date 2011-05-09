package final_project.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MessageRefPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MessageRefPanel() {
		
		JLabel lblMessage = new JLabel("Message:");
		add(lblMessage);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		add(btnSend);
		
		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

	}

}
