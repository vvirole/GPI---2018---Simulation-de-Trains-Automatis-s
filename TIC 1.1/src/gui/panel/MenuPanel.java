package gui.panel;

import java.awt.Color;
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

import gui.GUIConstants;

/**
 * Affichage du menu
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
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
	
	private int BTN_WIDTH = GUIConstants.WINDOW_WIDTH/5;
	private int BTN_HEIGHT = GUIConstants.WINDOW_HEIGHT/13;

	
	public MenuPanel(){
		setLayout(null);
		initComponents();
		initFont();
	}

	private void initComponents() {
		
		title = new JLabel("Trains Is Coming");
		textFooter = new JLabel("Licence 3 Informatique 2018 - Gestion de Projet Informatique");	
		jbBegin = new JButton("Commencer");
		jbInfos = new JButton("Informations");
		jbExit = new JButton("Quitter");
			
		jbBegin.addActionListener(new ActionBegin());
		jbInfos.addActionListener(new ActionInfos());
		jbExit.addActionListener(new ActionExit());
		
		
		// Positions //
		
		title.setSize(TITLE_WIDTH, TITLE_HEIGHT);
		title.setForeground(Color.BLACK);
		title.setLocation(GUIConstants.WINDOW_WIDTH/6 - TITLE_WIDTH/5, GUIConstants.WINDOW_HEIGHT/6 - TITLE_HEIGHT/2);
		
		textFooter.setSize(TEXT_FOOTER_WIDTH, TEXT_FOOTER_HEIGHT);
		textFooter.setForeground(Color.BLACK);
		textFooter.setLocation(GUIConstants.WINDOW_WIDTH/2 - TEXT_FOOTER_WIDTH/2, GUIConstants.WINDOW_HEIGHT*92/100);
	
		jbBegin.setBounds(GUIConstants.WINDOW_WIDTH/60 - BTN_WIDTH + BTN_WIDTH*11/10, GUIConstants.WINDOW_HEIGHT/3 - BTN_HEIGHT + 10, BTN_WIDTH, BTN_HEIGHT);
		jbInfos.setBounds(GUIConstants.WINDOW_WIDTH/60 - BTN_WIDTH + 2*BTN_WIDTH*11/10, GUIConstants.WINDOW_HEIGHT/3 - BTN_HEIGHT + 10, BTN_WIDTH, BTN_HEIGHT);
		jbExit.setBounds(GUIConstants.WINDOW_WIDTH/60 - BTN_WIDTH + 3*BTN_WIDTH*11/10, GUIConstants.WINDOW_HEIGHT/3 - BTN_HEIGHT + 10, BTN_WIDTH, BTN_HEIGHT);
		
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
		textFooter.setFont(ftFooter);	
		jbBegin.setFont(ftButton);
		jbInfos.setFont(ftButton);
		jbExit.setFont(ftButton);		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try {
			Image img = ImageIO.read(new File("pictures/fond_menu.png"));
			g.drawImage(img, 0, 0, getWidth(), getHeight() - 20, this);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public class ActionBegin implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel(Panels.PARAMETERS);
		}
	}
	
	public class ActionInfos implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel(Panels.INFORMATION);
		}
	}
	
	public class ActionExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int exit = JOptionPane.showConfirmDialog(instance, "Étes-vous sûre de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
	}
}

