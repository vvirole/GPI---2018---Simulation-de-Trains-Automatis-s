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
	private JLabel jlNbInitPassenger;
	private JLabel jlNbShortTrain;
	private JLabel jlNbLongTrain;
	
	private JLabel jlIncidentRation;
	private JLabel jlTimeResolveProblem;
	private JLabel jlTimeSimulation;
	private JLabel jlTrainBasicSpeed;
	
	private ImageIcon imageStart;
	private ImageIcon imageBack;
	
	private JButton jbBack;
	private JButton jbChooser;
	private JButton jbStart;
	
	// ----------------- INPUTS ------------------- //
	
	private JSlider jsIncidentRatio;
	private JSlider jsTrainBasicSpeed;
	
	private JTextField jtfInitPassenger;
	private JTextField jtfNbShortTrain;
	private JTextField jtfNbLongTrain;
	private JTextField jtfTimetoResolveProblem;
	private JTextField jtfTimeofSimulation;
	
	// ----------------- CONSTANTS ------------------- //
	
	private static final int TEXTFIELD_SIZE = 7;
	private static final int SPACING = 7;
	
	
	
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
		
		nbParameters.setBorder(BorderFactory.createTitledBorder(null, "Paramètre de la ligne de train",
			      TitledBorder.DEFAULT_JUSTIFICATION,
			      TitledBorder.DEFAULT_POSITION, null, Color.BLACK));
		timeParameters.setBorder(BorderFactory.createTitledBorder(null, "Gestion du temps et des incidents",
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
		jlNbInitPassenger = new JLabel ("• Nombre initial de voyageurs dans les gares :");
		jlNbShortTrain = new JLabel ("• Nombre max de voyageurs dans un train court :");
		jlNbLongTrain = new JLabel ("• Nombre max de voyageurs dans un train long :");
		
		jlIncidentRation = new JLabel ("• Ratio d'apparition d'incident sur la ligne :");
		jlTimeResolveProblem = new JLabel ("• Temps moyen pour résoudre un problème :");
		jlTimeSimulation = new JLabel ("• Durée de la simulation (en heures) :");
		jlTrainBasicSpeed = new JLabel ("• Vitesse par défault des trains :");
		
		/************************* TEXTFIELDS ******************************/
		
		jtfInitPassenger = new JTextField(TEXTFIELD_SIZE);
		jtfNbShortTrain = new JTextField(TEXTFIELD_SIZE);
		jtfNbLongTrain = new JTextField(TEXTFIELD_SIZE);
		jtfTimetoResolveProblem = new JTextField(TEXTFIELD_SIZE);
		jtfTimeofSimulation = new JTextField(TEXTFIELD_SIZE);
		
		jtfInitPassenger.setHorizontalAlignment(JTextField.CENTER);
		jtfNbShortTrain.setHorizontalAlignment(JTextField.CENTER);
		jtfNbLongTrain.setHorizontalAlignment(JTextField.CENTER);
		jtfTimetoResolveProblem.setHorizontalAlignment(JTextField.CENTER);
		jtfTimeofSimulation.setHorizontalAlignment(JTextField.CENTER);
	
		
		/********************** SLIDERS *********************************/
		
		jsIncidentRatio = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		jsIncidentRatio.setPaintTicks(true);
		jsIncidentRatio.setPaintLabels(true);
		jsIncidentRatio.setMinorTickSpacing(1);
		jsIncidentRatio.setMajorTickSpacing(1);
		jsIncidentRatio.setBackground(Color.WHITE);
		jsIncidentRatio.setValue(2);
		
		jsTrainBasicSpeed = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		jsTrainBasicSpeed.setPaintTicks(true);
		jsTrainBasicSpeed.setPaintLabels(true);
		jsTrainBasicSpeed.setMinorTickSpacing(1);
		jsTrainBasicSpeed.setMajorTickSpacing(1);
		jsTrainBasicSpeed.setBackground(Color.WHITE);
		jsTrainBasicSpeed.setValue(2);
		
		
		/*********************** POSITIONS *******************************/
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(SPACING, SPACING, SPACING, SPACING);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0; gbc.gridy = 0;
		nbParameters.add(jlChooser, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		nbParameters.add(jlNbInitPassenger, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		nbParameters.add(jlNbShortTrain, gbc);	
		gbc.gridx = 0; gbc.gridy = 3;
		nbParameters.add(jlNbLongTrain, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1; gbc.gridy = 0;
		nbParameters.add(jbChooser, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		nbParameters.add(jtfInitPassenger, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		nbParameters.add(jtfNbShortTrain, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		nbParameters.add(jtfNbLongTrain, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0; gbc.gridy = 0;
		timeParameters.add(jlTimeSimulation, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		timeParameters.add(jlTimeResolveProblem, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		timeParameters.add(jlTrainBasicSpeed, gbc);
		gbc.gridx = 0; gbc.gridy = 3;
		timeParameters.add(jlIncidentRation, gbc);
	
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1; gbc.gridy = 0;
		timeParameters.add(jtfTimeofSimulation, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		timeParameters.add(jtfTimetoResolveProblem, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		timeParameters.add(jsTrainBasicSpeed, gbc);
		gbc.gridx = 1; gbc.gridy = 3;
		timeParameters.add(jsIncidentRatio, gbc);
		
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
		jlNbInitPassenger.setFont(font);
		jlNbShortTrain.setFont(font);
		jlNbLongTrain.setFont(font);
		jlIncidentRation.setFont(font);
		jlTimeResolveProblem.setFont(font);
		jlTimeSimulation.setFont(font);
		jlTrainBasicSpeed.setFont(font);
		jbBack.setFont(font);
		jbStart.setFont(font);	
	}
	
	
	/**
	 * Initiate Parameters with constants defined in package core.constants
	 * @see core.constants
	 */
	private void initParam() {	
		jtfInitPassenger.setText(String.valueOf(Constants.INITIAL_PASSENGER_STATION));
		jtfNbShortTrain.setText(String.valueOf(Constants.SHORT_TRAIN_CAPACITY));
		jtfNbLongTrain.setText(String.valueOf(Constants.LONG_TRAIN_CAPACITY));
		jtfTimetoResolveProblem.setText(String.valueOf(Constants.DEFAULT_INCIDENT_RESOLUTION_TIME));
		jtfTimeofSimulation.setText(String.valueOf(GUIConstants.MAX_DURATION));
	}
	
	/**
	 * 
	 */
	private void applyParam(){
		GUIConstants.MAX_DURATION					= (int) (Float.parseFloat(jtfTimeofSimulation.getText()) * 3600);
		Constants.INITIAL_PASSENGER_STATION 		= Integer.parseInt(jtfInitPassenger.getText());
		Constants.SHORT_TRAIN_CAPACITY				= Integer.parseInt(jtfNbShortTrain.getText());
		Constants.LONG_TRAIN_CAPACITY				= Integer.parseInt(jtfNbLongTrain.getText());
		Constants.DEFAULT_INCIDENT_RESOLUTION_TIME	= Integer.parseInt(jtfTimetoResolveProblem.getText());
		Constants.TRAIN_BASIC_SPEED					= jsTrainBasicSpeed.getValue();
		Constants.INCIDENT_RATIO					= jsIncidentRatio.getValue();
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
					applyParam();
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