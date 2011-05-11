package final_project.view;

import java.awt.Color;

import net.java.balloontip.styles.*;


public class NotificationBalloonStyle extends ModernBalloonStyle {

	//Default style used by all tooltips in the program
	//Seaglass
//	public static final Color topFillColor = new Color(240, 253, 255);
//	public static final Color bottomFillColor = new Color(214, 233, 247);
//	public static final Color borderColor = new Color(140, 176, 219);
	//Nimbus
	//public static final Color topFillColor = new Color(245,245,245);
	//public static final Color bottomFillColor = new Color(204,211,224);
	//Notification
//	public static final Color topFillColor = new Color(242,242,189);
//	public static final Color bottomFillColor = new Color(255,220,35);
//	public static final Color borderColor = new Color(255, 220, 35);
	//Warning
	public static final Color topFillColor = new Color(255, 160, 122);
	public static final Color bottomFillColor = new Color(255, 99, 71);
	public static final Color borderColor = new Color(255, 0, 0);
	
	public static final int arcWidth = 8;
	public static final int arcHeight = 8;
	
	public NotificationBalloonStyle() {
		super(arcWidth, arcHeight, topFillColor, bottomFillColor, borderColor);
		this.setCornerStyles(true, true, true, true);
		this.enableAntiAliasing(true);
	}
}
