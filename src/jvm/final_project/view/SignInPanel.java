package final_project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.event.*;
import javax.swing.table.*;

import net.java.balloontip.BalloonTip;


public class SignInPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private SignInTableModel model;
	private TableRowSorter<SignInTableModel> sorter;
	private JScrollPane scrollPane;
	private JSearchTextField searchField;
	private JButton signInAll, unsignInAll, importXml;
	private Component verticalStrut;
	private Collection<BalloonTip> balloons;
	private BalloonTip signInPlayerTip, registerNewPlayerTip, signInAllTip, unsignInAllTip;
	private SignInPlayerPanel signInPlayerPane;
	private RegisterNewPlayerPanel registerNewPlayerPane;
	private ConfirmationPanel signInAllPane, unsignInAllPane;
	private JButton registerPersonButton;
	private JButton btnStartPoolRound;

	/**
	 * Create the panel.
	 */
	public SignInPanel() {
		super();
		initializeGridBagLayout();
		initializeTable();
		initializeComponents();
		initializeSearch();
		initializeBalloons();
	}

	private void initializeComponents() {
		signInAll = new JButton("Sign In All");
		signInAll.addActionListener(this);
		GridBagConstraints gbc_signInAll = new GridBagConstraints();
		gbc_signInAll.anchor = GridBagConstraints.EAST;
		gbc_signInAll.insets = new Insets(0, 0, 5, 5);
		gbc_signInAll.gridx = 1;
		gbc_signInAll.gridy = 1;
		add(signInAll, gbc_signInAll);
		
		unsignInAll = new JButton("Unsign In All");
		unsignInAll.addActionListener(this);
		GridBagConstraints gbc_unsignInAll = new GridBagConstraints();
		gbc_unsignInAll.anchor = GridBagConstraints.WEST;
		gbc_unsignInAll.insets = new Insets(0, 0, 5, 5);
		gbc_unsignInAll.gridx = 2;
		gbc_unsignInAll.gridy = 1;
		add(unsignInAll, gbc_unsignInAll);
		
		registerPersonButton = new JButton("Register Person");
		GridBagConstraints gbc_registerPersonButton = new GridBagConstraints();
		gbc_registerPersonButton.insets = new Insets(0, 0, 5, 5);
		gbc_registerPersonButton.gridx = 5;
		gbc_registerPersonButton.gridy = 1;
		add(registerPersonButton, gbc_registerPersonButton);
		
		registerPersonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerNewPlayerPane.setNoResults(false);
				registerNewPlayerPane.getNameTextField().requestFocusInWindow();
				registerNewPlayerPane.getNameTextField().setText("");
				registerNewPlayerPane.getPhoneNumberTextField().setText("");
				hideAllBalloons();
				registerNewPlayerTip.setVisible(true);
			}
		});
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(table);
	}

	private void initializeSearch() {
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
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 2;
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 3;
		gbc_txtSearch.gridy = 1;
		add(searchField, gbc_txtSearch);
		searchField.setColumns(10);
		
		importXml = new JButton("Import XML");
		GridBagConstraints gbc_btnImportXml = new GridBagConstraints();
		gbc_btnImportXml.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportXml.gridx = 1;
		gbc_btnImportXml.gridy = 3;
		add(importXml, gbc_btnImportXml);
		importXml.addActionListener(this);
		
		btnStartPoolRound = new JButton("Start Pool Round");
		GridBagConstraints gbc_btnStartPoolRound = new GridBagConstraints();
		gbc_btnStartPoolRound.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartPoolRound.gridx = 5;
		gbc_btnStartPoolRound.gridy = 3;
		add(btnStartPoolRound, gbc_btnStartPoolRound);
	}

	private void initializeTable() {
		//Set up table with custom sorter
		model = new SignInTableModel();
		sorter = new TableRowSorter<SignInTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
	}

	private void initializeGridBagLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 88, 102, 102, 102, 102, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.fill = GridBagConstraints.VERTICAL;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 0;
		add(verticalStrut, gbc_verticalStrut);
	}

	private void initializeBalloons() {
		balloons = new ArrayList<BalloonTip>();
		
		//setup tooltips
		signInPlayerPane = new SignInPlayerPanel();
		signInPlayerTip = new BalloonTip(registerPersonButton, signInPlayerPane, new DefaultBalloonStyle(), false);
		signInPlayerTip.setOpacity(0.9f);
		signInPlayerTip.setVisible(false);
		signInPlayerPane.getCancelButton().addActionListener(this);
		signInPlayerPane.getSignInButton().addActionListener(this);

		registerNewPlayerPane = new RegisterNewPlayerPanel();
		registerNewPlayerTip = new BalloonTip(registerPersonButton, registerNewPlayerPane, new DefaultBalloonStyle(), false);
		registerNewPlayerTip.setOpacity(0.9f);
		registerNewPlayerTip.setVisible(false);
		registerNewPlayerPane.getCancelButton().addActionListener(this);
		registerNewPlayerPane.getDoneButton().addActionListener(this);
		
		signInAllPane = new ConfirmationPanel("sign in");
		signInAllTip = new BalloonTip(signInAll, signInAllPane, new DefaultBalloonStyle(), false);
		signInAllTip.setOpacity(0.9f);
		signInAllTip.setVisible(false);
		signInAllPane.getCancelButton().addActionListener(this);
		signInAllPane.getYesButton().addActionListener(this);
		
		unsignInAllPane = new ConfirmationPanel("un-sign in");
		unsignInAllTip = new BalloonTip(unsignInAll, unsignInAllPane, new DefaultBalloonStyle(), false);
		unsignInAllTip.setOpacity(0.9f);
		unsignInAllTip.setVisible(false);
		unsignInAllPane.getCancelButton().addActionListener(this);
		unsignInAllPane.getYesButton().addActionListener(this);
		
		balloons.add(signInPlayerTip);
		balloons.add(registerNewPlayerTip);
		balloons.add(signInAllTip);
		balloons.add(unsignInAllTip);
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
        if (table.getRowCount() == 0) {
        	//Hide other tooltips
        	hideAllBalloons();
			//Make tooltip visible
        	registerNewPlayerTip.setVisible(true);
        	//Clear any old text
        	registerNewPlayerPane.setNoResults(true);
        	registerNewPlayerPane.getNameTextField().setText("");
        	registerNewPlayerPane.getPhoneNumberTextField().setText("");
        	//Decide whether entered text is a name or phone number
			try {
				Long.parseLong(searchField.getText());
				registerNewPlayerPane.getPhoneNumberTextField().setText(searchField.getText());
				searchField.setNextFocusableComponent(registerNewPlayerPane.getNameTextField());
			} catch (NumberFormatException e) {
				registerNewPlayerPane.getNameTextField().setText(searchField.getText());
				searchField.setNextFocusableComponent(registerNewPlayerPane.getPhoneNumberTextField());
			}
        }
        else if (table.getRowCount() == 1) {
        	//Hide other tooltip
        	registerNewPlayerTip.setVisible(false);
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
        	hideAllBalloons();
        	signInPlayerTip.setVisible(true);
        }
        else {
        	hideAllBalloons();
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
		if (e.getSource() == importXml) {
			//Import xml file
		}
		else if (e.getSource() == signInPlayerPane.getCancelButton()) {
			hideAllBalloons();
		}
		else if (e.getSource() == signInPlayerPane.getSignInButton()) {
			hideAllBalloons();
			//TODO sign in user
			//id should be tied to row in model?
			//MainWindow.getTournamentController().signInUser(id);
		}
		else if (e.getSource() == registerNewPlayerPane.getCancelButton()) {
			hideAllBalloons();
		}
		else if (e.getSource() == registerNewPlayerPane.getDoneButton()) {
			hideAllBalloons();
			//TODO register & sign in user
			//id should be tied to row in model?
			//MainWindow.getTournamentController().registerUser(id);
		}
		else if (e.getSource() == signInAll) {
			//Make new signInAllTooltip
			hideAllBalloons();
			signInAllTip.setVisible(true);
		}
		else if (e.getSource() == unsignInAll) {
			hideAllBalloons();
			unsignInAllTip.setVisible(true);
		}
		else if (e.getSource() == signInAllPane.getCancelButton() || e.getSource() == unsignInAllPane.getCancelButton()) {
			hideAllBalloons();
		}
		else if (e.getSource() == signInAllPane.getYesButton()) { 
			hideAllBalloons();
			//TODO sign in all
		}
		else if (e.getSource() == unsignInAllPane.getYesButton()) {
			hideAllBalloons();
			//TODO unsign in all
		}
	}
	
	public JSearchTextField getSearchField() {
		return searchField;
	}
	
	public JButton getAddFencerButton() {
		return registerPersonButton;
	}
	
	private void hideAllBalloons() {
		for (BalloonTip b : balloons) 
			b.setVisible(false);
	}
}
