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

public class StripArrangementPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StripArrangementPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 220, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel stripArrangementLabel = new JLabel("<html><b>Strip Arrangement</b><html>\n");
		GridBagConstraints gbc_stripArrangementLabel = new GridBagConstraints();
		gbc_stripArrangementLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_stripArrangementLabel.gridwidth = 2;
		gbc_stripArrangementLabel.anchor = GridBagConstraints.NORTH;
		gbc_stripArrangementLabel.insets = new Insets(0, 0, 5, 0);
		gbc_stripArrangementLabel.gridx = 0;
		gbc_stripArrangementLabel.gridy = 0;
		add(stripArrangementLabel, gbc_stripArrangementLabel);
		
		JLabel lblStripNumbers = new JLabel("Strip Numbers:");
		GridBagConstraints gbc_lblStripNumbers = new GridBagConstraints();
		gbc_lblStripNumbers.insets = new Insets(0, 0, 5, 5);
		gbc_lblStripNumbers.anchor = GridBagConstraints.WEST;
		gbc_lblStripNumbers.gridx = 0;
		gbc_lblStripNumbers.gridy = 1;
		add(lblStripNumbers, gbc_lblStripNumbers);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
//		MaskFormatter formatter = null;
//		try {
//		    formatter = new MaskFormatter("##*{'-'##*, ##*}*");
//		    formatter.setPlaceholderCharacter('*');
//		} catch (java.text.ParseException e) {
//		}
		
		DecimalFormat decimalFormat = new DecimalFormat("0* ,");
		NumberFormatter formatter = new NumberFormatter(decimalFormat);
		formatter.setOverwriteMode(true);
		formatter.setAllowsInvalid(false);

	}

}
