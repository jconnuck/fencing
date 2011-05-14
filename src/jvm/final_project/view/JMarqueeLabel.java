package final_project.view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.awt.Font;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class JMarqueeLabel extends JPanel implements MouseListener, ActionListener{
    public static final int MARQUEE_SPEED_DIV = 20;
    public static final int REPAINT_WITHIN_MS = 20;

    private static final long serialVersionUID = -7737312573505856484L;
    private MLabel label;
    private JButton btnClose;

    public JMarqueeLabel(String text) {
    	Component horizontalStrut = Box.createHorizontalStrut(20);
    	horizontalStrut.setPreferredSize(new Dimension(5, 0));
    	horizontalStrut.setMinimumSize(new Dimension(5, 0));
        horizontalStrut.setMaximumSize(new Dimension(5, 0));
        add(horizontalStrut);
    	
    	btnClose = new JButton(new ImageIcon(JMarqueeLabel.class.getResource("/net/java/balloontip/images/close_pressed.png")));
        btnClose.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnClose.setContentAreaFilled(false);
        btnClose.addActionListener(this);
        add(btnClose);
        
        Component horizontalStrut2 = Box.createHorizontalStrut(20);
        horizontalStrut2.setPreferredSize(new Dimension(5, 0));
        horizontalStrut2.setMinimumSize(new Dimension(5, 0));
        horizontalStrut2.setMaximumSize(new Dimension(5, 0));
        add(horizontalStrut2);
    	
        label = new MLabel(this, text);
    	label.setFont(new Font("Score Board", Font.PLAIN, 17));
        label.addMouseListener(this);
        add(label);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
    
    private class MLabel extends JLabel {
		public boolean scrolling;
		
    	private JComponent parent;
    	private long startTime;
		private boolean resize;
    	
    	public MLabel(JComponent parent, String text) {
    		super(text);
    		this.parent = parent;
    		this.startTime = System.currentTimeMillis();
    		this.setSize(this.getPreferredSize());
    	}
    	
    	public void resetStartTime() {
    		startTime = System.currentTimeMillis();
    	}

        @Override
        protected void paintComponent(Graphics g) {
        	if (scrolling) {
	        	if (this.getPreferredSize().getWidth() > parent.getSize().getWidth()) {
	        		int x = getWidth() - (int)((((System.currentTimeMillis() - startTime) / MARQUEE_SPEED_DIV) + this.getWidth()) % (this.getWidth() * 2));
	        		g.translate(x, 0);
	        	}
        	}
            super.paintComponent(g);
            repaint(REPAINT_WITHIN_MS);
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		label.resetStartTime();
		label.scrolling = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		label.scrolling = false;
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		
	}


}