package final_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainWindow {

	private JFrame frame;
	private JTable signInTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel signInPanel = new JPanel();
		tabbedPane.addTab("Sign In", null, signInPanel, null);
		
		signInTable = new JTable();
		signInTable.setCellSelectionEnabled(true);
		signInTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"John Connuck", "Referee", "NY Yankees", "(212) 300-7360", Boolean.TRUE},
				{"Josh Grill", "Fencer", "Fencing Club", "(555) 555-5555", null},
				{"William Zimrin", "Referee", null, "300 333-2222", null},
				{"Miranda Steele", "Fencer", "Boston Red Sox", "4442222222", null},
				{"Jon Leavitt", "Fencer", "Chicago Cubs", null, null},
			},
			new String[] {
				"Name", "Group", "Club", "Phone #", "Signed In"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		signInTable.getColumnModel().getColumn(0).setPreferredWidth(98);
		signInTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		signInTable.getColumnModel().getColumn(3).setPreferredWidth(88);
		signInPanel.add(signInTable);
	}

}
