package final_project.view;

import java.awt.Color;

import net.java.balloontip.styles.*;


public class DefaultBalloonStyle extends ModernBalloonStyle {

	//Default style used by all tooltips in the program
	public static final Color topFillColor = new Color(240, 253, 255);
	public static final Color bottomFillColor = new Color(214, 233, 247);
	public static final Color borderColor = new Color(140, 176, 219);
	public static final int arcWidth = 8;
	public static final int arcHeight = 8;
	
	public DefaultBalloonStyle() {
		super(arcWidth, arcHeight, topFillColor, bottomFillColor, borderColor);
		this.setCornerStyles(true, true, true, true);
		this.enableAntiAliasing(true);
	}
}
