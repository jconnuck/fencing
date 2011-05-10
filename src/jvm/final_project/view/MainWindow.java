package final_project.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;

import java.awt.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import final_project.control.*;
import final_project.input.*;
import final_project.model.store.IDataStore;

public class MainWindow {

	private JFrame frmFencingManager;
	private CheckInPanel checkInPanel;
	private TournamentController tournamentController;
	private IDataStore dataStore;
	private JSplitPane splitPane;
	private SubscriberAdminPanel subscriberAdminPanel;

	/**
	 * Create the application.
	 */

	public MainWindow(ITournamentInfo info, String username, String password) {
		tournamentController = new TournamentController(username,password,info, this);
		dataStore = info.getDataStore();
		initialize();
		frmFencingManager.setVisible(true);
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

		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);

		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 3;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		frmFencingManager.getContentPane().add(splitPane, gbc_splitPane);

		subscriberAdminPanel = new SubscriberAdminPanel(tournamentController);
		subscriberAdminPanel.setOpaque(true);
		subscriberAdminPanel.setSize(new Dimension(273, 58));
		subscriberAdminPanel.setMinimumSize(new Dimension(0, 58));
		splitPane.setLeftComponent(subscriberAdminPanel);

		checkInPanel = new CheckInPanel(tournamentController);
		splitPane.setRightComponent(checkInPanel);


		//PoolSizeInfoPanel poolSizeInfoPanel = new PoolSizeInfoPanel();
		//tabbedPane.addTab("New tab", null, poolSizeInfoPanel, null);
		splitPane.setDividerLocation(300);
	}
	
	public void loadRightPanel(JPanel panel) {
		splitPane.setRightComponent(panel);
	}
	
	public void hideAllBalloons() {
		checkInPanel.hideAllBalloons();
		subscriberAdminPanel.hideAllBalloons();
	}
}
