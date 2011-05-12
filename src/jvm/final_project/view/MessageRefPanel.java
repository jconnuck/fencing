package final_project.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MessageRefPanel extends JPanel {
	private JTextField textField;
	private JButton btnSend;
	private JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public MessageRefPanel() {
		JLabel lblMessage = new JLabel("Message:");
		add(lblMessage);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		add(btnSend);
		
		btnCancel = new JButton("Cancel");
		add(btnCancel);
		

	}

	public JButton getSendButton() {
		return btnSend;
	}
	public JButton getCancelButton() {
		return btnCancel;
	}
	
	public JTextField getTextField() {
		return textField;
	}
}
