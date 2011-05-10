package final_project.view;

import javax.swing.JPanel;

import final_project.control.*;
import final_project.model.Result;

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
	public DEBracketPanel(TournamentController tournament) {
		setLayout(null);
		Result[] matches = tournament.getDEMatches(Constants.EVENT_ID);
		int k = 0;
		for (int i = 0; i < Math.log10(matches.length)/Math.log10(2); ++i) {
			for (int j = 0; j < matches.length / (2 * (i + 1)); ++j) {
				++k;
				DEBracketUnitPanel temp = new DEBracketUnitPanel(tournament, tournament.getNameFromId(matches[matches.length - k].getPlayer1()),
						tournament.getNameFromId(matches[matches.length - k].getPlayer2()));
				temp.setBounds((150 * i), (75 * i) + (j * (150 + (10 * i))), 150, 150 + (10 * i));
			}
		}	
	}
}
