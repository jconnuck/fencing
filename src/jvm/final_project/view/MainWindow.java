package final_project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.UIManager;

public class MainWindow {

	private JFrame frmFencingManager;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFencingManager = new JFrame();
		frmFencingManager.setTitle("Fencing Manager");
		frmFencingManager.setBounds(100, 100, 600, 300);
		frmFencingManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmFencingManager.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		SignInPanel signInPanel = new SignInPanel();
		tabbedPane.addTab("Sign In & Registration", null, signInPanel, null);
	}

}
