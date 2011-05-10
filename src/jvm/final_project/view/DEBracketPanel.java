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
		Result[] matches = new Result[8];
		IncompleteResult mock = new IncompleteResult(0, 1, 5);
		for (int z = 0; z < matches.length; ++z)
			matches[z] = mock;
			
		//Result[] matches = tournament.getDEMatches(Constants.EVENT_ID);
		int k = 0;
		for (int i = 0; i < Math.log10(matches.length)/Math.log10(2); ++i) {
			for (int j = 0; j < matches.length / (2 * (i + 1)); ++j) {
				++k;
				DEBracketUnitPanel temp = new DEBracketUnitPanel(tournament, tournament.getNameFromId(matches[matches.length - k].getPlayer1()),
						tournament.getNameFromId(matches[matches.length - k].getPlayer2()));
				temp.setBounds((150 * i), (10 * j) + ((150 + (10 * i))/2)//(((150 + (10 * (i+1)))/2) * i) + (10 * j) + (j * (i+1) * (150 + (10 * (i+1)))), 150, 150 + (10 * (i+1)));
				add(temp);
			}
		}	
	}
}
