package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Page de sélection des paramètres de la simulation
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Vincent Virole
 *
 */
public class ParamPanel extends JPanel {

	
private static final long serialVersionUID = -4855834508170103703L;
	
	private ParamPanel instance = this;
	
	private int SPACING = 5;
	
	
	// Sous Panels //
	
	private JPanel NbParameters;
	private JPanel timeParameters;
	private JPanel navigation;
	
	
	// Infos //
	
	private JLabel jlNumberStation;
	private JLabel jlNumberMaxTraveler;
	private JLabel jlNumberReserveTrain;
	
	private JLabel jlLuckAccident;
	private JLabel jlTimetoResolveProblem;
	private JLabel jlTimeofSimulation;
	
	private JLabel jlespace;
	//private ImageIcon imageStart;
	
	private JButton jbBack;
	private JButton jbStart;
	
	
	// Saisie //
	
	private JSlider jsNumberStation;
	private JTextField jtfNumberMaxTraveler;
	private JSlider jsNumberReserveTrain;
	
	private JSlider jsLuckAccident;
	private JTextField jtfTimetoResolveProblem;
	private JTextField jtfTimeofSimulation;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	public ParamPanel(){
		setBackground(GUIConstants.LIGHT_COLOR);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(new Dimension(970, 730));
		initComponents();
		initFont();
		initParam();
	}
	
	
	
	private void initComponents(){
	
		NbParameters = new JPanel();
		timeParameters = new JPanel();
		navigation = new JPanel();
	
		//imageStart = new ImageIcon("pictures/go_button2.png");
		
		jbBack = new JButton("Retour au menu");
		jbStart = new JButton("Lancer la simulation"); //imageStart
		jbBack.addActionListener(new ActionBack());
		jbStart.addActionListener(new ActionStart());
		jbBack.setBackground(GUIConstants.LIGHT_COLOR);
		jbStart.setBackground(GUIConstants.LIGHT_COLOR);
		
		
		//Paramètre Label
		jlNumberStation = new JLabel ("Nombre de Gare :");
		jlNumberMaxTraveler = new JLabel ("Nombre max de voyageur sur la ligne :");
		jlNumberReserveTrain = new JLabel ("Nombre de train de réserve par gare :");
		
		jlLuckAccident = new JLabel ("Chance d'avoir un accident :");
		jlTimetoResolveProblem = new JLabel ("Temps moyen pour résoudre un problème :");
		jlTimeofSimulation = new JLabel ("Durée de la simulation (en ...) :");
		
		jlespace = new JLabel ("                                                                                                                                                                   ");
		
		
		//Choix des valeurs
		jsNumberStation = new JSlider(JSlider.HORIZONTAL, 0, 20, 8);
		jtfNumberMaxTraveler = new JTextField(5);
		jsNumberReserveTrain = new JSlider(JSlider.HORIZONTAL, 0, 5, 3);
		
		jsLuckAccident = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		jtfTimetoResolveProblem = new JTextField(5);
		jtfTimeofSimulation = new JTextField(5);
		
		
		// Paramètres des sous-JPanel
		NbParameters.setPreferredSize(new Dimension (GUIConstants.WIDTH/2 - 2*SPACING - 15, 325));
		timeParameters.setPreferredSize(new Dimension (GUIConstants.WIDTH/2 - 2*SPACING - 15, 325));
		navigation.setPreferredSize(new Dimension (GUIConstants.WIDTH - 2*SPACING - 35, 225));
		
		NbParameters.setLayout(new GridBagLayout());
		timeParameters.setLayout(new GridBagLayout());
		navigation.setLayout(new GridBagLayout());
		
		NbParameters.setBackground(GUIConstants.WHITE);
		timeParameters.setBackground(GUIConstants.WHITE);
		navigation.setBackground(GUIConstants.WHITE);
		
		NbParameters.setBorder(BorderFactory.createTitledBorder(null, "Initialisation ligne de train",
			      TitledBorder.DEFAULT_JUSTIFICATION,
			      TitledBorder.DEFAULT_POSITION, null, Color.BLACK));
		timeParameters.setBorder(BorderFactory.createTitledBorder(null, "Paramètre temps et accidents",
			      TitledBorder.DEFAULT_JUSTIFICATION,
			      TitledBorder.DEFAULT_POSITION, null, Color.BLACK));
		navigation.setBorder(BorderFactory.createTitledBorder(null, "Navigation",
			      TitledBorder.DEFAULT_JUSTIFICATION,
			      TitledBorder.DEFAULT_POSITION, null, Color.BLACK));
		
		
		//Propriétés des JTextField
		jtfNumberMaxTraveler.setHorizontalAlignment(JTextField.CENTER);
		jtfTimetoResolveProblem.setHorizontalAlignment(JTextField.CENTER);
		jtfTimeofSimulation.setHorizontalAlignment(JTextField.CENTER);
		
		
		//Propriétés des JSlider
		jsNumberStation.setPaintTicks(true);
		jsNumberStation.setPaintLabels(true);
		jsNumberStation.setMinorTickSpacing(2);
		jsNumberStation.setMajorTickSpacing(5);
		jsNumberStation.setBackground(GUIConstants.WHITE);
		
		jsNumberReserveTrain.setPaintTicks(true);
		jsNumberReserveTrain.setPaintLabels(true);
		jsNumberReserveTrain.setMinorTickSpacing(1);
		jsNumberReserveTrain.setMajorTickSpacing(1);
		jsNumberReserveTrain.setBackground(GUIConstants.WHITE);
		
		jsLuckAccident.setPaintTicks(true);
		jsLuckAccident.setPaintLabels(true);
		jsLuckAccident.setMinorTickSpacing(1);
		jsLuckAccident.setMajorTickSpacing(2);
		jsLuckAccident.setBackground(GUIConstants.WHITE);
		
		
		// Rangement et positionnement des JLabel, JSlider et JButton dans leur JPanel respectif
		
		gbc.insets = new Insets(SPACING, SPACING, SPACING, SPACING);
		
		gbc.gridx = 0; gbc.gridy = 0;
		NbParameters.add(jlNumberStation, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		NbParameters.add(jsNumberStation, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		NbParameters.add(jlNumberMaxTraveler, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		NbParameters.add(jtfNumberMaxTraveler, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		NbParameters.add(jlNumberReserveTrain, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		NbParameters.add(jsNumberReserveTrain, gbc);
		
		
		gbc.gridx = 0; gbc.gridy = 0;
		timeParameters.add(jlTimetoResolveProblem, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		timeParameters.add(jtfTimetoResolveProblem, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		timeParameters.add(jlLuckAccident, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		timeParameters.add(jsLuckAccident, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		timeParameters.add(jlTimeofSimulation, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		timeParameters.add(jtfTimeofSimulation, gbc);
		
		
		gbc.gridx = 0; gbc.gridy = 0;
		navigation.add(jbBack, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		navigation.add(jlespace, gbc);
		gbc.gridx = 2; gbc.gridy = 0;
		navigation.add(jbStart, gbc);
		
		
		// Ajout des sous Panels au Panel principal
		
		add(NbParameters);
		add(timeParameters);
		add(navigation);
	
	}
	
	
	
	private void initFont() {
		
		Font font = new Font(Font.DIALOG, Font.BOLD, 15);
		
		jlNumberStation.setFont(font);
		jlNumberMaxTraveler.setFont(font);
		jlNumberReserveTrain.setFont(font);
		jlLuckAccident.setFont(font);
		jlTimetoResolveProblem.setFont(font);
		jlTimeofSimulation.setFont(font);
		jlespace.setFont(font);
		
		jbBack.setFont(font);
		jbStart.setFont(font);
		
	}
	
	
	
	private void initParam() {
		
		jtfNumberMaxTraveler.setText("10000");
		jtfTimetoResolveProblem.setText("30");
		jtfTimeofSimulation.setText("10000");
		
	}
	
	
	
	
	/**
	 * Renvoit au menu
	 */
	public class ActionBack implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int cancel = JOptionPane.showConfirmDialog(instance, "Êtes-vous sûre de vouloir abandonner l'initialisation des paramètres ?", 
					"Précédent", JOptionPane.YES_NO_OPTION);
			if (cancel == 0){
				gui.GUIFrame.setCurrentPanel("menu");
			}
		}
	}
	
	/**
	 * Lance la simulation
	 */
	public class ActionStart implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				//inputParam();	
				//log.LogUtility.prepare(); // Préparation des journaux des souris
				gui.GUIFrame.setCurrentPanel("simulation");
			} catch (Exception ex){
				JOptionPane.showMessageDialog(instance, "Erreur : veuillez modifier les valeurs !");
			}
		}
	}
	
	
}

