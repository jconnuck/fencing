package final_project.view;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.UIManager;

import final_project.view.PoolObserverPanel.Status;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingConstants;


public class NotificationPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public NotificationPanel() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNotifications.setMaximumSize(new Dimension(300, 16));
		lblNotifications.setPreferredSize(new Dimension(300, 16));
		lblNotifications.setMinimumSize(new Dimension(300, 16));
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNotifications);
	}

	public void sendNotification(Status status, String message, long time) {
		JMarqueeLabel marqueeMessage = new JMarqueeLabel(message);
		if (status == PoolObserverPanel.Status.MEDICAL)
			marqueeMessage.setBackground(new Color(255, 69, 0));
		else if (status == PoolObserverPanel.Status.TECHNICAL)
			marqueeMessage.setBackground(Color.ORANGE);
		else
			marqueeMessage.setBackground(new Color(0, 191, 255));
		marqueeMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(marqueeMessage);
		validate();
		repaint();
	}
}
