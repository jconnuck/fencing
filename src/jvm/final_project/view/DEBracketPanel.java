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

public class DEBracketPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	
	//TODO when a result is texted in, it should be added and a new DEBracketPanel should be created and displayed
	public DEBracketPanel(TournamentController tournament) {	
		setLayout(null);
		
		//TODO delete mock data
		Result[] matches = new Result[15];
		CompleteResult mockComplete = new CompleteResult(new PlayerResult(1, 5), new PlayerResult(2, 3));
		for (int z = 0; z < (matches.length)/2; ++z)
			matches[z] = mockComplete;
		IncompleteResult mockIncomplete = new IncompleteResult(0, 1, 5);
		for (int z = (matches.length)/2; z < matches.length; ++z)
			matches[z] = mockIncomplete;
			
		//Result[] matches = tournament.getDEMatches(Constants.EVENT_ID);
		int k = 0, startx = 0, starty = 0, width = 150, height = 0;
		int log = (int) (Math.log10(matches.length + 1)/Math.log10(2));
		for (int i = 0; i < log; ++i) {
			startx = 150 * i;
			for (int j = 0; j < Math.pow(2, log - (i+1)); ++j) {
				if (i == 0) {
					height = 150;
					starty = 160 * j;
				}
				else {
					height = (int) Math.pow(2, i) * 80;
					starty = (int) (((Math.pow(2, i -1) - 1) * 80) + 75 + (2 * j * height));
				}
				++k;
				DEBracketUnitPanel temp = new DEBracketUnitPanel(tournament, matches[matches.length - k]);
				//height must be one greater than the calculated value in order to line up properly
				temp.setBounds(startx, starty, width, height + 1);
				add(temp);
			}
		}
		setPreferredSize(new Dimension(150 * log, (int)(160 * (Math.pow(2, log - 1)))));
	}
}
