package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.LineBuilder;

/**
 * Page de sélection des paramètres de la simulation
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
 *
 */
public class ParamPanel extends JPanel {

	
private static final long serialVersionUID = -4855834508170103703L;
	
	private ParamPanel instance = this;
	
	private int SPACING = 5;
	
	private boolean choose = false ;// check if we have choose our xml file
	
	// Sous Panels //
	
	private JPanel NbParameters;
	private JPanel timeParameters;
	private JPanel navigation;
	
	
	// Infos //
	
	private JLabel jlChooser;
	private JLabel jlNumberMaxTraveler;
	private JLabel jlNumberReserveTrain;
	
	private JLabel jlLuckAccident;
	private JLabel jlTimetoResolveProblem;
	private JLabel jlTimeofSimulation;
	
	private JLabel jlespace;
	private ImageIcon imageStart;
	private ImageIcon imageBack;
	
	private JButton jbBack;
	private JButton jbChooser;
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
		
		imageStart = new ImageIcon(new ImageIcon("pictures/go_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		imageBack = new ImageIcon(new ImageIcon("pictures/back_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		jbBack = new JButton(imageBack);
		jbStart = new JButton(imageStart); //imageStart
		jbChooser = new JButton("parcourir...");
		jbBack.addActionListener(new ActionBack());
		jbStart.addActionListener(new ActionStart());
		jbChooser.addActionListener(new ActionChooser());
		jbBack.setBackground(GUIConstants.LIGHT_COLOR);
		jbStart.setBackground(GUIConstants.LIGHT_COLOR);
		jbChooser.setBackground(GUIConstants.LIGHT_COLOR);
		
		
		//Paramètre Label
		jlChooser = new JLabel ("Choisissez votre fichier XML :");
		jlNumberMaxTraveler = new JLabel ("Nombre max de voyageur sur la ligne :");
		jlNumberReserveTrain = new JLabel ("Nombre de train de réserve par gare :");
		
		jlLuckAccident = new JLabel ("Chance d'avoir un accident :");
		jlTimetoResolveProblem = new JLabel ("Temps moyen pour résoudre un problème :");
		jlTimeofSimulation = new JLabel ("Durée de la simulation (en cycle) :");
		
		jlespace = new JLabel ("                                                                                                                                                                   ");
		
		
		//Choix des valeurs
		jsNumberStation = new JSlider(JSlider.HORIZONTAL, 5, 20, 8);
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
		jsNumberStation.setMinorTickSpacing(1);
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
		NbParameters.add(jlChooser, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		NbParameters.add(jbChooser, gbc);
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
		
		jlChooser.setFont(font);
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
			if (cancel  == JOptionPane.OK_OPTION){
				gui.GUIFrame.setCurrentPanel("menu");
				choose = false ;
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
				if(choose) {
					//inputParam();	
					//log.LogUtility.prepare();
					gui.GUIFrame.setCurrentPanel("simulation");
				}
				else {
					JOptionPane.showMessageDialog(instance, "Le fichier xml n'a pas été choisi !", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(instance, "Erreur : veuillez modifier les valeurs !");
			}
		}
	}
	
	
	public class ActionChooser implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser() ;
			chooser.setCurrentDirectory(new File("."));
			chooser.setAcceptAllFileFilterUsed(false);
			
			FileNameExtensionFilter filtrer = new FileNameExtensionFilter("(*.xml)", "xml");
			
			//Add filter into the JFileChooser
			chooser.addChoosableFileFilter(filtrer);
			chooser.setFileFilter(filtrer);
			
			int userSelection = chooser.showOpenDialog(instance);
			
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				if (file.getName().equals("line.xml")) {
					LineBuilder.buildLine(file) ; //initialisation of the line according to the xml file
					choose = true ;
				}
			}
		}
	}
	
	
}

