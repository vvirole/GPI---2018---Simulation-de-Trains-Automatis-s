package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.entity.Line;
import gui.GUIConstants;

/**
 * Simulation panel
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
 *
 */
public class SimulationPanel extends JPanel {

	private static final long serialVersionUID = 1722047581362200550L;
	
	private JPanel userPanel;
	private JLabel jlTitle;
	private JLabel jlTurn;
	
	private JButton jbStart;
	private JButton jbPause;
	private JButton jbStop;
	
	private ImageIcon imageStart;
	private ImageIcon imageBack;
	private ImageIcon imageStop;
	
	// Sub panel that displays the line
	private SimulationDashboard dashboard;
	
	// Thread of simulation
	private Thread simulationThread;
	
	// If the simulation is running
	private boolean run = false;

	
	public SimulationPanel(){
		setLayout(new BorderLayout());
		initComponent();
		initFont();
	}
	
	/**
	 * Components initialisation
	 */
	private void initComponent() {
		
		/**************** PANELS ********************/
		
		userPanel = new JPanel();
		userPanel.setLayout(new GridBagLayout());
		userPanel.setBackground(Color.DARK_GRAY);
		userPanel.setPreferredSize(new Dimension(GUIConstants.WINDOW_WIDTH, 150));
		
		dashboard = new SimulationDashboard();
		dashboard.setPreferredSize(new Dimension(GUIConstants.LINE_LENGTH, 400));
		simulationThread = new Thread(dashboard);
		
		/***************** LABELS *****************/
		
		jlTitle = new JLabel(Line.getInstance().getName());
		jlTurn = new JLabel("Cycle : " + dashboard.getTime());
		jlTitle.setForeground(Color.WHITE);
		jlTurn.setForeground(Color.WHITE);
		
		/******************* BUTTONS ********************/
		
		imageStart = new ImageIcon(new ImageIcon("pictures/play_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		imageBack = new ImageIcon(new ImageIcon("pictures/pause_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		imageStop = new ImageIcon(new ImageIcon("pictures/stop_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		jbStart = new JButton(imageStart);
		jbPause = new JButton(imageBack);
		jbStop = new JButton(imageStop);
		
		jbStart.addActionListener(new ActionStart());
		jbPause.addActionListener(new ActionPause());
		jbStop.addActionListener(new ActionStop());
		
		jbStart.setPreferredSize(new Dimension(50, 50));
		jbPause.setPreferredSize(new Dimension(50, 50));
		jbStop.setPreferredSize(new Dimension(50, 50));
		
		/*************** POSITIONS ************************/
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(25, 20, 25, 20);
		
		gbc.gridx = 0; gbc.gridy = 0;
		userPanel.add(jlTitle, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		userPanel.add(jbStart, gbc);
		gbc.gridx = 2; gbc.gridy = 0;
		userPanel.add(jbPause, gbc);
		gbc.gridx = 3; gbc.gridy = 0;
		userPanel.add(jbStop, gbc);
		gbc.gridx = 4; gbc.gridy = 0;
		userPanel.add(jlTurn, gbc);
		
		/**************** ADD **********************/
		
		add(dashboard, BorderLayout.NORTH);
		add(userPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Init fonts and set these fonts on the components
	 */
	private void initFont() {		
		Font titleFont = new Font(Font.DIALOG, Font.BOLD, 25);
		Font btnFont = new Font(Font.DIALOG, Font.BOLD, 20);
		Font infoFont = new Font(Font.DIALOG, Font.BOLD, 20);
		
		jlTitle.setFont(titleFont);
		jlTurn.setFont(infoFont);
		
		jbStart.setFont(btnFont);
		jbPause.setFont(btnFont);
		jbStop.setFont(btnFont);
	}
	
	
	/***********************************************************************************
									ACTION LISTENERS
	************************************************************************************/
	
	/**
	 * Start the simulation
	 */
	public class ActionStart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!run){
				simulationThread.start();
			}
		}
	}
		
	/**
	 * Set a pause during the simulation
	 */
	public class ActionPause implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			run = false;
		}
	}
		
	/**
	 * Stop the simulation
	 */
	public class ActionStop implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			run = false;
		}
	}

}
