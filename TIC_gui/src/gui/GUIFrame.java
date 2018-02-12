package gui;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;

import gui.GUIConstants;

/**
 * Fenêtre graphique du logiciel
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
 *
 */
public class GUIFrame extends JFrame {
	
	private static final long serialVersionUID = 8353688867398342579L;
	
	private static CardLayout cardLayout = new CardLayout();
	private static JPanel contentPane = new JPanel();
		

	public GUIFrame(){
		super("Trains is coming");
		initLayout();
		GUIUtility.updateWindowComponent(this);
	}
	
	/**
	 * Initialisation des composants
	 */
	private void initLayout(){
		
		setSize(GUIConstants.WIDTH, GUIConstants.HEIGHT);
		setLocationRelativeTo(null); // Met la fenêtre au centre
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		contentPane.setLayout(cardLayout);
		setContentPane(contentPane);
			
		JPanel menu = new MenuPanel();
		JPanel parameter = new ParamPanel();
		JPanel information = new InformationPanel();
	
		contentPane.add(menu, "menu");
		contentPane.add(parameter, "parameter");
		contentPane.add(information, "information");
		
		cardLayout.show(contentPane, "menu");
		
	}
	
	/**
	 * Change la page
	 * @param panel un String associé à un JPanel
	 */
	public static void setCurrentPanel(String panel){
		if (panel.equals("simulation")){
			JPanel simulation = new SimulationPanel();
			contentPane.add(simulation, "simulation");
		}
		cardLayout.show(contentPane, panel);
	}
	
}
