package final_project.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import final_project.control.TournamentController;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MessageRefPanel extends JPanel {
	private JTextField textField;
	private JButton btnSend;
	private JButton btnCancel;
	private TournamentController tournament;
	private Collection<Integer> refs;
	/**
	 * Create the panel.
	 */
	public MessageRefPanel(TournamentController t, Collection<Integer> r) {
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
	
	public JButton getTextField() {
		return textField;
	}
}
