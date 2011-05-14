package final_project.view;

import java.awt.Graphics;

import javax.swing.*;

public class JMarqueeLabel extends JPanel {
    public static final int MARQUEE_SPEED_DIV = 20;
    public static final int REPAINT_WITHIN_MS = 20;

    private static final long serialVersionUID = -7737312573505856484L;

    public JMarqueeLabel(String text) {
        MLabel label = new MLabel(this, text);
        add(label);
        // TODO Auto-generated constructor stub
    }
    
    private class MLabel extends JLabel {
    	private JComponent parent;
    	private long startTime;
		private boolean resize;
    	
    	public MLabel(JComponent parent, String text) {
    		super(text);
    		this.parent = parent;
    		this.startTime = System.currentTimeMillis();
    		this.setSize(this.getPreferredSize());
    	}

        @Override
        protected void paintComponent(Graphics g) {
        	if (this.getPreferredSize().getWidth() > parent.getSize().getWidth()) {
        		if (resize == true) {
        			resize = false;
        			startTime = System.currentTimeMillis();
        		}
        		g.translate(getWidth() - (int)(((System.currentTimeMillis() - startTime) / MARQUEE_SPEED_DIV) % (parent.getSize().getWidth() * 2)), 0);
        	} else {
        		resize = true;
        	}
            super.paintComponent(g);
            repaint(REPAINT_WITHIN_MS);
        }
    }
}