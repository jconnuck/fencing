package final_project.view;

import javax.swing.JPanel;

import final_project.control.*;
import final_project.model.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;

public class DEBracketPanel extends JPanel implements DERoundObserver {
	/**
	 * Create the panel.
	 */

    TournamentController _tournament;
	
	//TODO when a result is texted in, it should be added and a new DEBracketPanel should be created and displayed
	public DEBracketPanel(TournamentController tournament) {	
		setLayout(null);
        _tournament = tournament;
        tournament.addDEObserver(this,0);
		
        drawBracket();
    }

    public void bracketUpdated() {
    	drawBracket();
    }

    public void drawBracket() {
    	removeAll();
    	repaint();
        System.out.println("Drawing");
        
		Result[] matches = _tournament.getDEMatches(Constants.EVENT_ID);
		int k = 0, startx = 0, starty = 20, width = 200, height = 0;
		int log = (int) (Math.log10(matches.length + 1)/Math.log10(2));
		for (int i = 0; i < log; ++i) {
			startx = 200 * i;
			for (int j = 0; j < Math.pow(2, log - (i+1)); ++j) {
				if (i == 0) {
					height = 150;
					starty = 200 * j + 20;
				}
				else {
					height = (int) Math.pow(2, i) * 100;
					starty = (int) (((Math.pow(2, i -1) - 1) * 100) + 75 + (2 * j * height) + 20);
				}
				++k;

				DEBracketUnitPanel temp = new DEBracketUnitPanel(_tournament, matches[matches.length - k]);
				//height must be one greater than the calculated value in order to line up properly
				temp.setBounds(startx, starty, width, height + 1);
				add(temp);
			}
		}
        /*My failed attempt to add a label for the overall winner of the tournament

          int extraWidth = 0;
        if (matches[0] != null &&
            matches[0] instanceof CompleteResult) {
            JLabel winnerLabel = new JLabel(_tournament.getNameFromId(((CompleteResult) matches[0]).getWinner()));
            winnerLabel.setLocation(startx+width, starty-(height/2));
            add(winnerLabel);
            extraWidth = winnerLabel.getPreferredSize().width + 20;
        }
		setPreferredSize(new Dimension((200 * log) + extraWidth, (int)(200 * (Math.pow(2, log - 1)))));*/
		setPreferredSize(new Dimension(200 * log, (int)(200 * (Math.pow(2, log - 1)))));
        System.out.println("components: "+getComponentCount());
        //the repaint call here makes everything disappear until the window is resized
        repaint();
        //_tournament.getMainWindow().getRightPanel().repaint();
	}
}
