package final_project.view;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;

import java.awt.Dimension;

import final_project.control.*;

public class MainWindow {

	private JFrame frmFencingManager;
	private static TournamentController tournamentController;

	public static TournamentController getTournamentController() {
		return tournamentController;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
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
		tournamentController = new TournamentController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Override key bindings to make enter press buttons
		InputMap im = (InputMap)UIManager.get("Button.focusInputMap");
		im.put(KeyStroke.getKeyStroke( "ENTER" ), "pressed");
		im.put(KeyStroke.getKeyStroke( "released ENTER" ), "released");
		
		frmFencingManager = new JFrame();
		frmFencingManager.setTitle("Fencing Manager");
		frmFencingManager.setBounds(100, 100, 900, 600);
		frmFencingManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 200, 200, 0};
		gridBagLayout.rowHeights = new int[]{258, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frmFencingManager.getContentPane().setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		SignInPanel signInPanel = new SignInPanel();
		tabbedPane.addTab("Sign In", null, signInPanel, null);
		splitPane.setDividerLocation(300);
	}
}
