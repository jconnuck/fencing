package final_project.view;

import java.awt.*;

import javax.swing.*;

import final_project.model.*;

public class ScoreView extends JPanel {
	private PlayerResult player1, player2;

	/**
	 * Create the panel.
	 */
	public ScoreView() {
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{362, 0};
		gridBagLayout.rowHeights = new int[]{16, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.gridy = 0;
		gbc_panel1.anchor = GridBagConstraints.NORTH;
		add(panel, gbc_panel1);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblJohnConnuck = new JLabel("John Connuck");
		panel.add(lblJohnConnuck);
		lblJohnConnuck.setFont(new Font("Score Board", Font.PLAIN, 16));
		lblJohnConnuck.setForeground(Color.WHITE);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JLabel label = new JLabel("5");
		panel.add(label);
		label.setFont(new Font("Score Board", Font.PLAIN, 16));
		label.setForeground(Color.CYAN);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		JLabel label_1 = new JLabel("4");
		panel.add(label_1);
		label_1.setForeground(Color.ORANGE);
		label_1.setFont(new Font("Score Board", Font.PLAIN, 16));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_2);
		
		JLabel lblJoshGrill = new JLabel("Josh Grill");
		panel.add(lblJoshGrill);
		lblJoshGrill.setForeground(Color.WHITE);
		lblJoshGrill.setFont(new Font("Score Board", Font.PLAIN, 16));
	}

}
