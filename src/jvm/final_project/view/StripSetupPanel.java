package final_project.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class StripSetupPanel extends JPanel {
	private JButton cancelButton;
	private JButton doneButton;

	/**
	 * Create the panel.
	 */
	public StripSetupPanel() {
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{67, 77, 0, 84, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel stripSetupLabel = new JLabel("<html><b>Strip Arrangement</b><html>\n");
		GridBagConstraints gbc_stripSetupLabel = new GridBagConstraints();
		gbc_stripSetupLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_stripSetupLabel.gridwidth = 4;
		gbc_stripSetupLabel.anchor = GridBagConstraints.NORTH;
		gbc_stripSetupLabel.insets = new Insets(0, 0, 5, 0);
		gbc_stripSetupLabel.gridx = 0;
		gbc_stripSetupLabel.gridy = 0;
		add(stripSetupLabel, gbc_stripSetupLabel);
		
		JLabel lblRows = new JLabel("Rows:");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.anchor = GridBagConstraints.EAST;
		gbc_lblRows.insets = new Insets(0, 0, 5, 5);
		gbc_lblRows.gridx = 0;
		gbc_lblRows.gridy = 1;
		add(lblRows, gbc_lblRows);
		
		JSpinner rowSpinner = new JSpinner();
		rowSpinner.setModel(new SpinnerNumberModel(new Integer(6), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_rowSpinner = new GridBagConstraints();
		gbc_rowSpinner.anchor = GridBagConstraints.WEST;
		gbc_rowSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_rowSpinner.gridx = 1;
		gbc_rowSpinner.gridy = 1;
		add(rowSpinner, gbc_rowSpinner);
		
		JLabel lblColumns = new JLabel("Columns:");
		GridBagConstraints gbc_lblColumns = new GridBagConstraints();
		gbc_lblColumns.anchor = GridBagConstraints.EAST;
		gbc_lblColumns.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumns.gridx = 2;
		gbc_lblColumns.gridy = 1;
		add(lblColumns, gbc_lblColumns);
		
		JSpinner colSpinner = new JSpinner();
		colSpinner.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_colSpinner = new GridBagConstraints();
		gbc_colSpinner.anchor = GridBagConstraints.WEST;
		gbc_colSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_colSpinner.gridx = 3;
		gbc_colSpinner.gridy = 1;
		add(colSpinner, gbc_colSpinner);
		
		JLabel lblsetColumnsTo = new JLabel("<html><i>Note: Set columns to 0 if strips are not in a grid.</i></html>");
		GridBagConstraints gbc_lblsetColumnsTo = new GridBagConstraints();
		gbc_lblsetColumnsTo.insets = new Insets(0, 0, 5, 0);
		gbc_lblsetColumnsTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblsetColumnsTo.gridwidth = 4;
		gbc_lblsetColumnsTo.gridx = 0;
		gbc_lblsetColumnsTo.gridy = 2;
		add(lblsetColumnsTo, gbc_lblsetColumnsTo);
		
		doneButton = new JButton("Done");
		GridBagConstraints gbc_getDoneButton = new GridBagConstraints();
		gbc_getDoneButton.anchor = GridBagConstraints.EAST;
		gbc_getDoneButton.insets = new Insets(0, 0, 0, 5);
		gbc_getDoneButton.gridx = 2;
		gbc_getDoneButton.gridy = 3;
		add(doneButton, gbc_getDoneButton);
		
		cancelButton = new JButton("Cancel");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 3;
		add(cancelButton, gbc_cancelButton);

	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	public JButton getDoneButton() {
		return doneButton;
	}
}
