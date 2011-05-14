package final_project.view;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;


public class NotificationPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public NotificationPanel() {
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		final JPanel notificationPanel = this;
		
		JLabel lblStripTechnical = new JLabel("John Smith - \"Medical Help on Strip 15\"");
		lblStripTechnical.setOpaque(true);
		lblStripTechnical.setBackground(new Color(255, 69, 0));
		lblStripTechnical.setForeground(new Color(0, 0, 0));
		lblStripTechnical.setFont(new Font("Score Board", Font.BOLD, 17));
		add(lblStripTechnical);
		
		JLabel lblStripIs = new JLabel("Miranda Steele - \"Technical Help on Strip 5\"") {
			@Override
		    protected void paintChildren(Graphics g) {
				//if (this.getWidth() > notificationPanel.getWidth()) {
			        g.translate(-(int)((System.currentTimeMillis() / 10) % (getWidth() * 2)) + getWidth(), 0);
			        super.paintComponent(g);
			        repaint(10);
				//}
//				else {
//					super.paintComponent(g);
//				}
		    }
		};
		lblStripIs.setBackground(Color.ORANGE);
		lblStripIs.setOpaque(true);
		lblStripIs.setForeground(new Color(0, 0, 0));
		lblStripIs.setFont(new Font("Score Board", Font.PLAIN, 17));
		add(lblStripIs);
		
		JLabel label = new JLabel("Josh Grill - \"Help me!\"");
		label.setOpaque(true);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Score Board", Font.PLAIN, 17));
		label.setBackground(Color.ORANGE);
		add(label);
		
		JLabel label_1 = new JLabel("William Zimrin - We need a janitor!");
		label_1.setOpaque(true);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Score Board", Font.PLAIN, 17));
		label_1.setBackground(new Color(0, 191, 255));
		add(label_1);
		
		JMarqueeLabel marquee = new JMarqueeLabel("Jon Leavitt - \"Medical Staff needed on strip 5\"");
		marquee.setAlignmentX(Component.LEFT_ALIGNMENT);
		marquee.setFont(new Font("Score Board", Font.PLAIN, 17));
		marquee.setBackground(Color.WHITE);
		add(marquee);
	}

}
