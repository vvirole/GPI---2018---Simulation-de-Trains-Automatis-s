package gui.panel;

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
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.LineBuilder;
import core.xml.UnvalidFileException;
import gui.GUIConstants;
import core.Constants;

/**
 * Selection of the parameters for the simulation
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
	
	// Instance of the panel
	private ParamPanel instance = this;
	
	// XML file that contains line datas
	private File file = null;
	
	// ----------------- SUB PANELS ------------------- //
	
	private JPanel nbParameters;
	private JPanel timeParameters;
	private JPanel navigation;
	
	// ----------------- TEXT ------------------- //
	
	private JLabel jlChooser;
	private JLabel jlNbMaxTraveler;
	private JLabel jlNbShortTrain;
	private JLabel jlNbLongTrain;
	
	private JLabel jlLuckAccident;
	private JLabel jlTimetoResolveProblem;
	private JLabel jlTimeofSimulation;
	private JLabel jlTrainBasicSpeed;
	
	private ImageIcon imageStart;
	private ImageIcon imageBack;
	
	private JButton jbBack;
	private JButton jbChooser;
	private JButton jbStart;
	
	// ----------------- INPUTS ------------------- //
	
	private JSlider jsNbStation;
	private JSlider jsLuckAccident;
	
	private JTextField jtfMaxTraveler;
	private JTextField jtfNbShortTrain;
	private JTextField jtfNbLongTrain;
	private JTextField jtfTimetoResolveProblem;
	private JTextField jtfTimeofSimulation;
	private JTextField jtfTrainBasicSpeed;
	
	// ----------------- CONSTANTS ------------------- //
	
	private static final int TEXTFIELD_SIZE = 7;
	private static final int SPACING = 5;
	
	
	
	public ParamPanel(){
		setBackground(Color.DARK_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(new Dimension(970, 730));
		initComponents();
		initFont();
		initParam();
	}
	
	
	
	private void initComponents(){
		
		/************************ PANELS **************************/
	
		nbParameters = new JPanel();
		timeParameters = new JPanel();
		navigation = new JPanel();
		
		nbParameters.setPreferredSize(new Dimension (GUIConstants.WINDOW_WIDTH/2 - 2*SPACING - 15, 325));
		timeParameters.setPreferredSize(new Dimension (GUIConstants.WINDOW_WIDTH/2 - 2*SPACING - 15, 325));
		navigation.setPreferredSize(new Dimension (GUIConstants.WINDOW_WIDTH - 2*SPACING - 35, 225));
		
		nbParameters.setLayout(new GridBagLayout());
		timeParameters.setLayout(new GridBagLayout());
		navigation.setLayout(new GridBagLayout());
		
		nbParameters.setBackground(Color.WHITE);
		timeParameters.setBackground(Color.WHITE);
		navigation.setBackground(Color.WHITE);
		
		nbParameters.setBorder(BorderFactory.createTitledBorder(null, "Initialisation ligne de train",
			      TitledBorder.DEFAULT_JUSTIFICATION,
			      TitledBorder.DEFAULT_POSITION, null, Color.BLACK));
		timeParameters.setBorder(BorderFactory.createTitledBorder(null, "Paramètres temps et accidents",
			      TitledBorder.DEFAULT_JUSTIFICATION,
			      TitledBorder.DEFAULT_POSITION, null, Color.BLACK));
		navigation.setBorder(BorderFactory.createTitledBorder(null, "Navigation",
			      TitledBorder.DEFAULT_JUSTIFICATION,
			      TitledBorder.DEFAULT_POSITION, null, Color.BLACK));
		
		/************************* BUTTONS ******************************/
		
		imageStart = new ImageIcon(new ImageIcon("pictures/go_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		imageBack = new ImageIcon(new ImageIcon("pictures/back_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		jbBack = new JButton(imageBack);
		jbStart = new JButton(imageStart);
		jbChooser = new JButton("parcourir...");
		
		jbBack.addActionListener(new ActionBack());
		jbStart.addActionListener(new ActionStart());
		jbChooser.addActionListener(new ActionChooser());
		
		/************************* LABELS ******************************/
		
		jlChooser = new JLabel ("• Choisissez le fichier XML :");
		jlNbMaxTraveler = new JLabel ("• Nombre max de voyageurs sur la ligne :");
		jlNbShortTrain = new JLabel ("• Nombre max de voyageurs dans un train court :");
		jlNbLongTrain = new JLabel ("• Nombre max de voyageurs dans un train long :");
		
		jlLuckAccident = new JLabel ("• Chance d'avoir un accident :");
		jlTimetoResolveProblem = new JLabel ("• Temps moyen pour résoudre un problème :");
		jlTimeofSimulation = new JLabel ("• Durée de la simulation (en cycles) :");
		jlTrainBasicSpeed = new JLabel ("• Vitesse initiale des trains :");
		
		/********************** SLIDERS *********************************/
		
		jsNbStation = new JSlider(JSlider.HORIZONTAL, 5, 20, 8);
		jsLuckAccident = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		
		jtfMaxTraveler = new JTextField(TEXTFIELD_SIZE);
		jtfNbShortTrain = new JTextField(TEXTFIELD_SIZE);
		jtfNbLongTrain = new JTextField(TEXTFIELD_SIZE);
		jtfTimetoResolveProblem = new JTextField(TEXTFIELD_SIZE);
		jtfTimeofSimulation = new JTextField(TEXTFIELD_SIZE);
		jtfTrainBasicSpeed = new JTextField(TEXTFIELD_SIZE);
		
		jtfMaxTraveler.setHorizontalAlignment(JTextField.CENTER);
		jtfNbShortTrain.setHorizontalAlignment(JTextField.CENTER);
		jtfNbLongTrain.setHorizontalAlignment(JTextField.CENTER);
		jtfTimetoResolveProblem.setHorizontalAlignment(JTextField.CENTER);
		jtfTimeofSimulation.setHorizontalAlignment(JTextField.CENTER);
		jtfTrainBasicSpeed.setHorizontalAlignment(JTextField.CENTER);
		
		jsNbStation.setPaintTicks(true);
		jsNbStation.setPaintLabels(true);
		jsNbStation.setMinorTickSpacing(1);
		jsNbStation.setMajorTickSpacing(5);
		jsNbStation.setBackground(Color.WHITE);
		
		jsLuckAccident.setPaintTicks(true);
		jsLuckAccident.setPaintLabels(true);
		jsLuckAccident.setMinorTickSpacing(1);
		jsLuckAccident.setMajorTickSpacing(2);
		jsLuckAccident.setBackground(Color.WHITE);
		
		
		/*********************** POSITIONS *******************************/
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(SPACING, SPACING, SPACING, SPACING);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0; gbc.gridy = 0;
		nbParameters.add(jlChooser, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		nbParameters.add(jlNbMaxTraveler, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		nbParameters.add(jlNbShortTrain, gbc);	
		gbc.gridx = 0; gbc.gridy = 3;
		nbParameters.add(jlNbLongTrain, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1; gbc.gridy = 0;
		nbParameters.add(jbChooser, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		nbParameters.add(jtfMaxTraveler, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		nbParameters.add(jtfNbShortTrain, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		nbParameters.add(jtfNbLongTrain, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0; gbc.gridy = 0;
		timeParameters.add(jlTimetoResolveProblem, gbc);	
		gbc.gridx = 0; gbc.gridy = 1;
		timeParameters.add(jlLuckAccident, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		timeParameters.add(jlTrainBasicSpeed, gbc);
		gbc.gridx = 0; gbc.gridy = 3;
		timeParameters.add(jlTimeofSimulation, gbc);
	
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1; gbc.gridy = 0;
		timeParameters.add(jtfTimetoResolveProblem, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		timeParameters.add(jsLuckAccident, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		timeParameters.add(jtfTrainBasicSpeed, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		timeParameters.add(jtfTimeofSimulation, gbc);
		
		gbc.insets = new Insets(SPACING, 5*SPACING, SPACING, 5*SPACING);
		gbc.gridx = 0; gbc.gridy = 0;
		navigation.add(jbBack, gbc);
		gbc.gridx = 2; gbc.gridy = 0;
		navigation.add(jbStart, gbc);
		
		
		/*********************** ADD ****************************/
		
		add(nbParameters);
		add(timeParameters);
		add(navigation);
	}
	
	/**
	 * Init fonts that will be used
	 */
	private void initFont() {		
		Font font = new Font(Font.DIALOG, Font.BOLD, 15);		
		jlChooser.setFont(font);
		jlNbMaxTraveler.setFont(font);
		jlNbShortTrain.setFont(font);
		jlNbLongTrain.setFont(font);
		jlLuckAccident.setFont(font);
		jlTimetoResolveProblem.setFont(font);
		jlTimeofSimulation.setFont(font);
		jlTrainBasicSpeed.setFont(font);
		jbBack.setFont(font);
		jbStart.setFont(font);	
	}
	
	
	/**
	 * Initiate Parameters with Constant on core/Constants
	 */
	private void initParam() {	
		jtfMaxTraveler.setText(String.valueOf(Constants.MAX_PASSENGER));
		jtfNbShortTrain.setText(String.valueOf(Constants.SHORT_TRAIN_CAPACITY));
		jtfNbLongTrain.setText(String.valueOf(Constants.LONG_TRAIN_CAPACITY));
		//jtfTimetoResolveProblem.setText(String.valueOf(Constants.TIME_RESOLVE_PROBLEM));
		jtfTimeofSimulation.setText(String.valueOf(GUIConstants.MAX_DURATION));
		jtfTrainBasicSpeed.setText(String.valueOf(Constants.TRAIN_BASIC_SPEED));	
	}
	
	
	/**
	 * Change parameters for the simulation panel
	 */
	private void inputParam() {
		
		//Parameters.SIZE_MAP = (String) jcbSizeMap.getSelectedItem();
		//Parameters.VELOCITY = jsVelocity.getValue();
		//Parameters.START_NUMBER_MALE_MOUSE = Integer.parseInt(jtfStartNumberMaleMouse.getText());
		
	}
	
	/***********************************************************************************
	  								ACTION LISTENER
	 ***********************************************************************************/
	
	/**
	 * Back to the menu
	 */
	public class ActionBack implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int cancel = JOptionPane.showConfirmDialog(instance, "Étes-vous sûre de vouloir abandonner l'initialisation des paramêtres ?", 
					"Précédent", JOptionPane.YES_NO_OPTION);
			if (cancel  == JOptionPane.OK_OPTION){
				gui.GUIFrame.setCurrentPanel("menu");
			}
		}
	}
	
	/**
	 * Go the the simulation
	 */
	public class ActionStart implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(file != null) {
					LineBuilder.buildLine(file) ; // Initialisation of the line according to the xml file
					gui.GUIFrame.setCurrentPanel(Panels.SIMULATION);
				}
				else {
					JOptionPane.showMessageDialog(instance, "Choisir un fichier XML", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} catch (UnvalidFileException ex){
				JOptionPane.showMessageDialog(instance, "Le fichier XML est invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Choose XML file that stored informations about the line
	 */
	public class ActionChooser implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser() ;
			chooser.setCurrentDirectory(new File("."));
			chooser.setAcceptAllFileFilterUsed(false);
			
			FileNameExtensionFilter filtrer = new FileNameExtensionFilter("(*.xml)", "xml");
			
			// Add filter into the JFileChooser
			chooser.addChoosableFileFilter(filtrer);
			chooser.setFileFilter(filtrer);
			
			int userSelection = chooser.showOpenDialog(instance);
			
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
			}
		}
	}
	
	
}