package final_project.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import final_project.control.TournamentController;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.*;

public class SubscriberAdminPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private SubscriberTableModel model;
	private javax.swing.table.TableRowSorter<SubscriberTableModel> sorter;
	private JSearchTextField searchField;
	private BalloonTip addNewSubscriberTip;
	private AddNewSubscriberPanel addNewSubscriberPane;
	private JButton addSubscriber;
	private JScrollPane scrollPane;
	private JLabel manageSubscribers;
	private TournamentController tournament;

	
	/**
	 * Create the panel.
	 */
	public SubscriberAdminPanel(TournamentController t) {
		tournament = t;
		
		setOpaque(false);
		initializeGridBagLayout();
		initializeTable();
		initializeComponents();
		initializeSearch();
		initializeBalloons();
	}

	private void initializeBalloons() {
		addNewSubscriberPane = new AddNewSubscriberPanel();
		addNewSubscriberTip = new BalloonTip(addSubscriber, addNewSubscriberPane, new DefaultBalloonStyle(), false);
		addNewSubscriberTip.setOpacity(0.9f);
		tournament.getMainWindow().registerBalloon(addNewSubscriberTip);
		addNewSubscriberTip.setVisible(false);
		addNewSubscriberPane.getCancelButton().addActionListener(this);
		addNewSubscriberPane.getDoneButton().addActionListener(this);
	}

	private void initializeComponents() {
		manageSubscribers = new JLabel("Manage Subscribers");
		GridBagConstraints gbc_manageSubscribers = new GridBagConstraints();
		gbc_manageSubscribers.gridwidth = 2;
		gbc_manageSubscribers.insets = new Insets(0, 0, 5, 0);
		gbc_manageSubscribers.gridx = 0;
		gbc_manageSubscribers.gridy = 0;
		add(manageSubscribers, gbc_manageSubscribers);
		
		addSubscriber = new JButton("Add Subscriber");
		addSubscriber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewSubscriberPane.setNoResults(false);
				addNewSubscriberPane.getNameTextField().requestFocusInWindow();
	        	addNewSubscriberPane.getNameTextField().setText("");
	        	addNewSubscriberPane.getPhoneNumberTextField().setText("");
				addNewSubscriberTip.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnAddSubscriber = new GridBagConstraints();
		gbc_btnAddSubscriber.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddSubscriber.anchor = GridBagConstraints.EAST;
		gbc_btnAddSubscriber.gridx = 1;
		gbc_btnAddSubscriber.gridy = 1;
		add(addSubscriber, gbc_btnAddSubscriber);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 0;
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
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridx = 0;
		gbc_searchTextField.gridy = 1;
		add(searchField, gbc_searchTextField);
	}

	private void initializeTable() {
		//Set up table with custom sorter
		model = new SubscriberTableModel();
		sorter = new TableRowSorter<SubscriberTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setFillsViewportHeight(true);
	}

	private void initializeGridBagLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}
	
	private void filter() {
		RowFilter<SubscriberTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + searchField.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
		if (table.getRowCount() == 0) {
			//Make tooltip visible
        	addNewSubscriberTip.setVisible(true);
        	//Clear any old text
        	addNewSubscriberPane.setNoResults(true);
        	addNewSubscriberPane.getNameTextField().setText("");
        	addNewSubscriberPane.getPhoneNumberTextField().setText("");
        	//Decide whether entered text is a name or phone number
			try {
				Long.parseLong(searchField.getText());
				addNewSubscriberPane.getPhoneNumberTextField().setText(searchField.getText());
				searchField.setNextFocusableComponent(addNewSubscriberPane.getNameTextField());
			} catch (NumberFormatException e) {
				addNewSubscriberPane.getNameTextField().setText(searchField.getText());
				searchField.setNextFocusableComponent(addNewSubscriberPane.getPhoneNumberTextField());
			}
        } else {
        	addNewSubscriberTip.setVisible(false);
        }
	}

	public void updateSubscriberTable() {
		Object[][] newData = tournament.giveSubscriberTableInfo();
		model.setData(newData);
	}
	
	class SubscriberTableModel extends AbstractTableModel{
		private static final long serialVersionUID = 1L;
		
		private  String[] columnNames = {"Name", "Phone Number", "Subscribed To"};
		
		private Object[][] data = tournament.giveSubscriberTableInfo();
		
		public void setData(Object[][] newData) {
			data = newData;
			this.fireTableDataChanged();
		}
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
			return "".getClass();
		}
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}
		@Override
		public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addNewSubscriberPane.getCancelButton()) {
			addNewSubscriberTip.setVisible(false);
		}
		else if (e.getSource() == addNewSubscriberPane.getDoneButton()) {
			String phoneNumber = addNewSubscriberPane.getPhoneNumberTextField().getText();
			String name = addNewSubscriberPane.getNameTextField().getText();
			String firstName = "", lastName = ""; 
			//TODO validate phone number properly, including a check for repeat in database
			if (phoneNumber.equals("(***)-***-****")) {
	                //Create tooltip warning user that required fields are blank
	                BalloonTip blankFieldTip = new BalloonTip(addNewSubscriberPane.getDoneButton(), new JLabel("Sorry, one or more required field is blank."), new NotificationBalloonStyle(), Orientation.RIGHT_BELOW, AttachLocation.SOUTH, 8, 8, false);
	                blankFieldTip.setVisible(true);
	                //Highlight missing fields pink
	                Color highlightColor = new Color(255, 160, 122); 	
	                if (phoneNumber.equals("(***)-****-***")) {	 	
	                    addNewSubscriberPane.getPhoneNumberTextField().setBackground(highlightColor);
	                    addNewSubscriberPane.getPhoneNumberTextField().requestFocusInWindow();	 	
	                } else {
	                    addNewSubscriberPane.getPhoneNumberTextField().setBackground(Color.WHITE);
	                }
	            } else {
					addNewSubscriberTip.setVisible(false);
					int nameSplit = name.lastIndexOf(' ');
					if (nameSplit > 0) {
						firstName = name.substring(0, nameSplit);
						lastName = name.substring(nameSplit +1, name.length());
					} else {
						firstName = name;
						lastName = "";
					}
					//Registering this new spectator and updating the model table to reflect the new subscriber
					phoneNumber = phoneNumber.replaceAll("\\D", "");
					Object[][] newData = tournament.registerSpectator(phoneNumber, firstName, lastName);
					model.setData(newData);
					searchField.setText("");
	            }
		}
	}
}
