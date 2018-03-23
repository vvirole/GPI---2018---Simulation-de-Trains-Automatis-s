package gui;

import java.awt.CardLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import gui.panel.InformationPanel;
import gui.panel.MenuPanel;
import gui.panel.Panels;
import gui.panel.ParamPanel;
import gui.panel.SimulationPanel;
import gui.panel.StatPanel;

/**
 * Graphic frame using for the simulation
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
 *
 */
public class GUIFrame extends JFrame {
	
	private static final String VERSION = "2.0";
	
	private static final long serialVersionUID = 8353688867398342579L;	
	private static CardLayout cardLayout = new CardLayout();
	private static JPanel contentPane = new JPanel();	
		
	/**
	 * Default constructor
	 */
	public GUIFrame(){
		super("Trains Is Coming - " + VERSION);
		setSize(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT);
		setLocationRelativeTo(null); // Set the frame at the center of window
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);	
			setIconImage(ImageIO.read(new File("pictures/logo.png"))); // Frame Icon
		} catch (Exception e) {
			e.printStackTrace();
		}
		initLayout();
	}
	
	/**
	 * Layout initialisation
	 */
	private void initLayout(){
		
		contentPane.setLayout(cardLayout);
		setContentPane(contentPane);
			
		JPanel menu = new MenuPanel();
		JPanel parameter = new ParamPanel();
		JPanel information = new InformationPanel();
	
		contentPane.add(menu, Panels.MENU);
		contentPane.add(parameter, Panels.PARAMETERS);
		contentPane.add(information, Panels.INFORMATION);
		cardLayout.show(contentPane, Panels.MENU);	
	}
	
	/**
	 * Change the panel that is displayed
	 * @param panel a string choosing in the list of panels constants
	 */
	public static void setCurrentPanel(String panel){
		if (panel.equals(Panels.SIMULATION)){
			SimulationPanel simulation = new SimulationPanel();
			contentPane.add(simulation, Panels.SIMULATION);
		}
		if (panel.equals(Panels.STATISTICS)){
			StatPanel stat = new StatPanel();
			contentPane.add(stat, Panels.STATISTICS);
		}
		cardLayout.show(contentPane, panel);
	}	
}
