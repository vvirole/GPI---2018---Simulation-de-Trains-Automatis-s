package gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import gui.GUIConstants;

/**
 * Informations about the software
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
 *
 */
public class InformationPanel extends JPanel {

	private static final long serialVersionUID = -9180441865099755196L;
	
	private JLabel title;
	private JPanel infosPanel;
	private JTextPane infosText;
	private JLabel infosImage;
	private JButton jbBack;
	
	
	public InformationPanel(){
		setLayout(new GridBagLayout());
		setBackground(Color.DARK_GRAY);
		initComponents();
		initFont();
	}

	/**
	 * Init components
	 */
	private void initComponents() {
		
		title = new JLabel("Informations");	
		infosPanel = new JPanel();
		infosText = new JTextPane();
		infosImage = new JLabel(new ImageIcon("pictures/train_infos.png"));
		
		/****************** BUTTON ************************/
		
		jbBack = new JButton("Revenir au menu");
		jbBack.addActionListener(new ActionBack());
		
		/****************** SUB PANEL **********************/
		
		infosPanel.setPreferredSize(new Dimension (GUIConstants.WINDOW_WIDTH *85/100, GUIConstants.WINDOW_HEIGHT *85/100));
		infosPanel.setLayout(new GridBagLayout());
		infosPanel.setBackground(Color.WHITE);
		infosPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		/********************* TEXT **************************/
		
		infosText.setPreferredSize(new Dimension(400, 230));
		infosText.setBackground(Color.WHITE);
		infosText.setText("Ce logiciel permet de simuler une ligne de train. "
				+ "Vous avez la possibilité de choisir des paramêtres initiaux qui influeront sur la simulation de cette "
				+ "ligne de train. Une fois la simulation lancée les trains partent des gares mais gare aux accidents."
				+ " Amusez-vous bien !");
		infosText.setEditable(false);
		
		/********************* POSITION *************************/
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.gridx = 0; gbc.gridy = 0;
		infosPanel.add(title, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		infosPanel.add(infosText, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		infosPanel.add(infosImage, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		infosPanel.add(jbBack, gbc);		
		
		add(infosPanel);	
	}

	/**
	 * Init fonts that will be used
	 */
	private void initFont() {
		
		Font titleFont = new Font(Font.DIALOG, Font.BOLD, 25);
		Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
		Font btnFont = new Font(Font.DIALOG, Font.BOLD, 15);
		
		title.setFont(titleFont);
		infosText.setFont(font);
		jbBack.setFont(btnFont);
		
	}
	
	public class ActionBack implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel(Panels.MENU);
		}
	}

}
