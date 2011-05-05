package final_project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.FadingUtils;


public class SignInPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private TableRowSorter<SignInTableModel> sorter;
	private JSearchTextField searchField;
	private JButton button;
	private JButton btnUnsignInAll;
	private Component verticalStrut;
	BalloonTip signInPlayerTip;
	SignInPlayerPanel signInPlayerPane;
	private JButton addFencerButton;

	/**
	 * Create the panel.
	 */
	public SignInPanel() {
		super();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 88, 102, 102, 102, 102, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Set up table with custom sorter
		SignInTableModel model = new SignInTableModel();
		sorter = new TableRowSorter<SignInTableModel>(model);
		
		searchField = new JSearchTextField();
		searchField.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        filter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        filter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        filter();
                    }
                });
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.fill = GridBagConstraints.VERTICAL;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 0;
		add(verticalStrut, gbc_verticalStrut);
		
		button = new JButton("Sign In All");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		add(button, gbc_button);
		
			btnUnsignInAll = new JButton("Unsign In All");
			GridBagConstraints gbc_btnUnsignInAll = new GridBagConstraints();
			gbc_btnUnsignInAll.anchor = GridBagConstraints.WEST;
			gbc_btnUnsignInAll.insets = new Insets(0, 0, 5, 5);
			gbc_btnUnsignInAll.gridx = 2;
			gbc_btnUnsignInAll.gridy = 1;
			add(btnUnsignInAll, gbc_btnUnsignInAll);
		
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 3;
		gbc_txtSearch.gridy = 1;
		add(searchField, gbc_txtSearch);
		searchField.setColumns(10);
		
		addFencerButton = new JButton("Add Fencer");
		GridBagConstraints gbc_addFencerButton = new GridBagConstraints();
		gbc_addFencerButton.insets = new Insets(0, 0, 5, 5);
		gbc_addFencerButton.gridx = 5;
		gbc_addFencerButton.gridy = 1;
		add(addFencerButton, gbc_addFencerButton);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(table);
		
		JButton btnImportXml = new JButton("Import XML");
		GridBagConstraints gbc_btnImportXml = new GridBagConstraints();
		gbc_btnImportXml.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportXml.gridx = 5;
		gbc_btnImportXml.gridy = 3;
		add(btnImportXml, gbc_btnImportXml);
		
		//setup tooltips
		signInPlayerPane = new SignInPlayerPanel();
		signInPlayerTip = new BalloonTip(addFencerButton, signInPlayerPane, new DefaultBalloonStyle(), false);
		signInPlayerTip.setOpacity(0.9f);
		signInPlayerTip.setVisible(false);
		signInPlayerPane.getCancelButton().addActionListener(this);
		signInPlayerPane.getSignInButton().addActionListener(this);
	}
	
	private void filter() {
		RowFilter<SignInTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + searchField.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
        if (table.getRowCount() == 1) {
        	//select this row
        	ListSelectionModel selectionModel = table.getSelectionModel();
        	selectionModel.setSelectionInterval(0, 0);
        	//Make tooltip visible
        	signInPlayerPane.getResultLabel().setText("<html><i>Exact Match Found:</i> <b>" + table.getValueAt(0, 0) + "</b></html>");
        	searchField.addKeyListener
        		(new KeyAdapter() {
        			public void keyPressed(KeyEvent e) {
        				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        					signInPlayerTip.setVisible(false);
        					searchField.setText("");
        					//TODO Sign In Fencer
        				}
        			}
        		});
        	searchField.setNextFocusableComponent(signInPlayerPane.getSignInButton());
        	signInPlayerTip.setVisible(true);
        }
        else {
        	signInPlayerTip.setVisible(false);
        }
	}
	
	class SignInTableModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		
		private  String[] columnNames = {"Name", "Team", "Group", "Signed In"};
		
		private Object[][] data = {
			{"John Connuck", "New York Yankees", "Referee", new Boolean(true)},
			{"Greg Maddux", "Chicago Cubs", "Fencer", new Boolean(false)},
			{"Michael Jordan", "Chicago White Sox", "Referee", new Boolean(false)},
			{"Daryl Strawberry", "New York Mets", "Fencer", new Boolean(false)},
			{"Mariano Rivera", "New York Yankees", "Fencer", new Boolean(false)},
			{"Mark McGuire", "St. Louis Cardinals", "Fencer", new Boolean(false)},
			{"Sammy Sosa", "Chicago Cubs", "Fencer", new Boolean(false)},
		};
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		@Override
		public int getRowCount() {
			return data.length;
		}
		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}
		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
		@Override
		public boolean isCellEditable(int row, int col) {
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}
		@Override
		public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signInPlayerPane.getCancelButton()) {
			signInPlayerTip.setVisible(false);
		}
	}
	public JSearchTextField getSearchField() {
		return searchField;
	}
	public JButton getAddFencerButton() {
		return addFencerButton;
	}
}
