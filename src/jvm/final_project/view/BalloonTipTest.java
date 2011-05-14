package final_project.view;

import javax.swing.JPanel;
import net.java.balloontip.BalloonTip;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.Color;

public class BalloonTipTest extends JPanel {

	/**
	 * Create the panel.
	 */
	public BalloonTipTest() {
		
		JButton button = new JButton("Click Me");
		add(button);
		
		BalloonTip balloonTip = new BalloonTip(button, "this is a test", new NotificationBalloonStyle(), false);
		balloonTip.getContents().setForeground(Color.WHITE);
		add(balloonTip);

	}

}
