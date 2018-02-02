package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Affichage du menu
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Vincent Virole
 *
 */
public class MenuPanel extends JPanel {
	
	private static final long serialVersionUID = -4855834508170103703L;
	
	private MenuPanel instance = this;
	
	private JLabel title;
	private JLabel textFooter;
	private JButton jbBegin;
	private JButton jbInfos;
	private JButton jbExit;
	
	private int TITLE_WIDTH = 900;
	private int TITLE_HEIGHT = 200;
	
	private int TEXT_FOOTER_WIDTH = 450;
	private int TEXT_FOOTER_HEIGHT = 15;
	
	private int BTN_WIDTH = GUIConstants.WIDTH/5;
	private int BTN_HEIGHT = GUIConstants.HEIGHT/13;

	
	public MenuPanel(){
		setLayout(null);
		setBackground(GUIConstants.WHITE);
		initComponents();
		initFont();
	}

	private void initComponents() {
		
		title = new JLabel("Trains is Coming");
		textFooter = new JLabel("Licence 3 Informatique 2018 - Gestion de Projet Informatique");	
		jbBegin = new JButton("Lancer la simulation");
		jbInfos = new JButton("Informations");
		jbExit = new JButton("Quitter");
			
		jbBegin.addActionListener(new ActionBegin());
		jbInfos.addActionListener(new ActionInfos());
		jbExit.addActionListener(new ActionExit());
		
		
		// Positions //
		
		title.setSize(TITLE_WIDTH, TITLE_HEIGHT);
		title.setLocation(GUIConstants.WIDTH/6 - TITLE_WIDTH/5, GUIConstants.HEIGHT/6 - TITLE_HEIGHT/2);
		
		textFooter.setSize(TEXT_FOOTER_WIDTH, TEXT_FOOTER_HEIGHT);
		textFooter.setLocation(GUIConstants.WIDTH/2 - TEXT_FOOTER_WIDTH/2, GUIConstants.HEIGHT*93/100);
	
		
		jbBegin.setBounds(GUIConstants.WIDTH/5 - BTN_WIDTH/2, GUIConstants.HEIGHT/3 - BTN_HEIGHT + BTN_HEIGHT*14/10, BTN_WIDTH, BTN_HEIGHT);
		jbInfos.setBounds(GUIConstants.WIDTH/5 - BTN_WIDTH/2, GUIConstants.HEIGHT/3 - BTN_HEIGHT + 2*BTN_HEIGHT*14/10, BTN_WIDTH, BTN_HEIGHT);
		jbExit.setBounds(GUIConstants.WIDTH/5 - BTN_WIDTH/2, GUIConstants.HEIGHT/3 - BTN_HEIGHT + 3*BTN_HEIGHT*14/10, BTN_WIDTH, BTN_HEIGHT);
		
		add(title);
		add(textFooter);
		add(jbBegin);
		add(jbInfos);
		add(jbExit);
	}
	
	private void initFont() {
		
		Font ftTitle = new Font(Font.DIALOG, Font.BOLD, 60);
		Font ftFooter = new Font(Font.DIALOG, Font.BOLD, 15);
		Font ftButton = new Font(Font.DIALOG, Font.BOLD, 20);
		
		title.setFont(ftTitle);
		title.setForeground(GUIConstants.BLACK);
		
		textFooter.setFont(ftFooter);
		textFooter.setForeground(GUIConstants.BLACK);
		
		jbBegin.setFont(ftButton);
		jbInfos.setFont(ftButton);
		jbExit.setFont(ftButton);
		
		jbBegin.setBackground(GUIConstants.LIGHT_COLOR);
		jbInfos.setBackground(GUIConstants.LIGHT_COLOR);
		jbExit.setBackground(GUIConstants.LIGHT_COLOR);
		
	}
	
	public void paintComponent(Graphics g){
		
		try {
			Image img = ImageIO.read(new File("pictures/fond_menu.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public class ActionBegin implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel("parameter");
		}
	}
	
	public class ActionInfos implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel("information");
		}
	}
	
	/*public class ActionInfos implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel("about");
		}
	}*/
	
	public class ActionExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int exit = JOptionPane.showConfirmDialog(instance, "Êtes-vous sûre de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if (exit == 0){
				System.exit(0);
			}
		}
	}
}

