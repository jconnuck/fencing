package final_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import java.awt.Dimension;

public class MainWindow {

	private JFrame frmFencingManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		//	UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmFencingManager.setVisible(true);
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
		frmFencingManager = new JFrame();
		frmFencingManager.setTitle("Fencing Manager");
		frmFencingManager.setBounds(100, 100, 900, 400);
		frmFencingManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 200, 200, 0};
		gridBagLayout.rowHeights = new int[]{258, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frmFencingManager.getContentPane().setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(11);
		splitPane.setOneTouchExpandable(true);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 3;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		frmFencingManager.getContentPane().add(splitPane, gbc_splitPane);
		
		SubscriberAdminPanel subscriberAdminPanel = new SubscriberAdminPanel();
		subscriberAdminPanel.setOpaque(true);
		subscriberAdminPanel.setSize(new Dimension(273, 58));
		subscriberAdminPanel.setMinimumSize(new Dimension(0, 58));
		splitPane.setLeftComponent(subscriberAdminPanel);
		
		SignInPanel signInPanel = new SignInPanel();
		splitPane.setRightComponent(signInPanel);
		splitPane.setDividerLocation(300);
	}
}
