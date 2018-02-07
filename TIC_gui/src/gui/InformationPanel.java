package gui;

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

/**
 * Page d'information général sur le logiciel
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
	
	// Composants //
	
	private JLabel title;
	private JPanel infosPanel;
	private JTextPane infosText;
	private JLabel infosImage;
	private JButton jbBack;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	public InformationPanel(){
		setLayout(new GridBagLayout());
		setBackground(GUIConstants.LIGHT_COLOR);
		initComponents();
		initFont();
	}


	private void initComponents() {
		
		title = new JLabel("Informations");
		
		infosPanel = new JPanel();
		infosText = new JTextPane();
		infosImage = new JLabel(new ImageIcon("pictures/train_infos.png"));
		
		// Bouton //
		
		jbBack = new JButton("Revenir au menu");
		jbBack.addActionListener(new ActionBack());
		jbBack.setBackground(GUIConstants.LIGHT_COLOR);
		
		// Sous Panel //
		
		infosPanel.setPreferredSize(new Dimension (GUIConstants.WIDTH*85/100, GUIConstants.HEIGHT*85/100));
		infosPanel.setLayout(new GridBagLayout());
		infosPanel.setBackground(GUIConstants.WHITE);
		infosPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// Texte //
		
		infosText.setPreferredSize(new Dimension(400, 230));
		infosText.setBackground(GUIConstants.WHITE);
		infosText.setText("Ce logiciel permet de simuler une ligne de train. "
				+ "Vous avez la possibilité de choisir des paramètres initiaux qui influeront sur la simulation de cette "
				+ "ligne de train. Une fois la simulation lancé les trains partent des gares mais attention des accidents sont si vite arrivés."
				+ " Amusez-vous bien !");
		infosText.setEditable(false);
		
		// Positionnement //
		
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
			gui.GUIFrame.setCurrentPanel("menu");
		}
	}

}
