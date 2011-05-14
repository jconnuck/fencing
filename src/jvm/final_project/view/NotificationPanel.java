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


public class NotificationPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public NotificationPanel() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JMarqueeLabel techMessage = new JMarqueeLabel("John Smith - \"Medical Help on Strip 15\"");
		techMessage.setBackground(new Color(255, 69, 0));
		techMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(techMessage);
		
		JMarqueeLabel techMessage2 = new JMarqueeLabel("Miranda Steele - \"Technical Help on Strip 5\"");
		techMessage2.setBackground(Color.ORANGE);
		techMessage2.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(techMessage2);
		
		JMarqueeLabel helpMessage = new JMarqueeLabel("Josh Grill - \"Help me!\"");
		helpMessage.setBackground(Color.ORANGE);
		helpMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(helpMessage);
		
		JMarqueeLabel marqueeLabel = new JMarqueeLabel("William Zimrin - We need a janitor!");
		marqueeLabel.setBackground(new Color(0, 191, 255));
		marqueeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(marqueeLabel);
		
		JMarqueeLabel helpMessage2 = new JMarqueeLabel("Jon Leavitt - \"Medical Staff needed on strip 5\"");
		helpMessage2.setAlignmentX(Component.LEFT_ALIGNMENT);
		helpMessage2.setBackground(new Color(255, 69, 0));
		add(helpMessage2);
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
	}
}
