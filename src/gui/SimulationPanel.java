package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import core.Counter;
import gui.SimulationData;
import log.LogUtility;
import map.Map;
import map.Mouse;
import test.Parameters;*/

/**
 * Page où la simulation a lieu
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
 *
 */
public class SimulationPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1722047581362200550L;
	
	private SimulationPanel instance = this;
	
	
	private JPanel userPanel;
	
	private JLabel jlTitle;
	private JLabel jlTurn;
	
	private JButton jbStart;
	private JButton jbPause;
	private JButton jbStop;
	
	private ImageIcon imageStart;
	private ImageIcon imageBack;
	private ImageIcon imageStop;
	
	
	private GridBagConstraints gbc = new GridBagConstraints();
	

	public SimulationPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(GUIConstants.BLACK);
		initComponent();
		initFont();
	}
	
	
	private void initComponent() {
		
		//Sous Panels
		userPanel = new JPanel();
		userPanel.setLayout(new GridBagLayout());
		userPanel.setBackground(GUIConstants.WHITE);
		
		
		//Titre et informations
		
		jlTitle = new JLabel("Train Is Coming");
		jlTurn = new JLabel("Cycle : ");
		
		imageStart = new ImageIcon(new ImageIcon("pictures/play_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		imageBack = new ImageIcon(new ImageIcon("pictures/pause_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		imageStop = new ImageIcon(new ImageIcon("pictures/stop_button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		jbStart = new JButton(imageStart);
		jbPause = new JButton(imageBack);
		jbStop = new JButton(imageStop);
		
		jbStart.addActionListener(new ActionStart());
		jbPause.addActionListener(new ActionPause());
		jbStop.addActionListener(new ActionStop());
		
		jbStart.setPreferredSize(new Dimension(50, 50));
		jbPause.setPreferredSize(new Dimension(50, 50));
		jbStop.setPreferredSize(new Dimension(50, 50));
		
		jbStart.setBackground(GUIConstants.GREY);
		jbPause.setBackground(GUIConstants.GREY);
		jbStop.setBackground(GUIConstants.GREY);
		
		
		//Ajouts et Positionnement
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0; gbc.gridy = 0;
		userPanel.add(jbStart, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		userPanel.add(jbPause, gbc);
		gbc.gridx = 2; gbc.gridy = 0;
		userPanel.add(jbStop, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		userPanel.add(jlTitle, gbc);
		gbc.gridx = 1; gbc.gridy = 2;
		userPanel.add(jlTurn, gbc);
		
		add(userPanel);
		
	}
	
	
	private void initFont() {
	
	Font titleFont = new Font(Font.DIALOG, Font.BOLD, 25);
	Font btnFont = new Font(Font.DIALOG, Font.BOLD, 20);
	Font infoFont = new Font(Font.DIALOG, Font.BOLD, 15);
	
	jlTitle.setFont(titleFont);
	jlTurn.setFont(infoFont);
	
	jbStart.setFont(btnFont);
	jbPause.setFont(btnFont);
	jbStop.setFont(btnFont);
	
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	


	
	// Actions des boutons du panel de control //
	
		public class ActionStart implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				/*if (!usePower){
					run = true;	
					simulationThread = new Thread(instance);
					simulationThread.start();
				}
				else{
					JOptionPane.showMessageDialog(instance, "Erreur ! Vous êtes en train de modifier des paramètres, veuiller confirmer les nouveaux paramètres.");
				}*/
			}
		}
		
		/**
		 * Met en pause la simulation
		 */
		public class ActionPause implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				//run = false;
			}
		}
		
		/**
		 * Stoppe la simulation et affiche les graphiques des résultats statistiques
		 */
		public class ActionStop implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*run = false;
				gui.GUIFrame.setCurrentPanel("graphics");
				LogUtility.closeLoggers();*/
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
