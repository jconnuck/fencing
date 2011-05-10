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

public class DEBracketPanel extends JPanel {
	/**
	 * Create the panel.
	 */
	
	//TODO when a result is texted in, it should be added and a new DEBracketPanel should be created and displayed
	public DEBracketPanel(TournamentController tournament) {
		setLayout(null);
		//TODO delete mock data
		Result[] matches = new Result[16];
		CompleteResult mockComplete = new CompleteResult(new PlayerResult(1, 5), new PlayerResult(2, 3));
		for (int z = 0; z < (matches.length)/2; ++z)
			matches[z] = mockComplete;
		IncompleteResult mockIncomplete = new IncompleteResult(0, 1, 5);
		for (int z = (matches.length)/2; z < matches.length; ++z)
			matches[z] = mockIncomplete;
			
		//Result[] matches = tournament.getDEMatches(Constants.EVENT_ID);
		int k = 0, startPos, lastPos = 0, lastHeight = 0, height = 150, gap;
		for (int i = 0; i < Math.log10(matches.length)/Math.log10(2); ++i) {
			startPos = lastPos + (lastHeight/2);
			gap = (150 * i) + 10;
			for (int j = 0; j < matches.length / (2 * (i + 1)); ++j) {
				++k;
				DEBracketUnitPanel temp = new DEBracketUnitPanel(tournament, matches[matches.length - k]);
				temp.setBounds((150 * i), startPos + ((height + gap) * j), 150, height + 1);
				add(temp);
			}
			lastPos += startPos;
			lastHeight = height;
			height += gap;
		}	
	}
}
