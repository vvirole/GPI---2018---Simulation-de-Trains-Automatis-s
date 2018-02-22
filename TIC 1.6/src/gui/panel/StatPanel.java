package gui.panel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.GUIConstants;


/**
 * Affichage des résultats de la simulation sous forme de graphiques
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Vincent Virole
 * @author Thomas Re
 *
 */

public class StatPanel extends JPanel{
	
	
	/*➢ Le nombre de voyageurs moyen dans chaque train sera indiqué dans la simulation pour rendre
	compte de la densité de voyageur ainsi que l’utilisation et donc la justification de la mise en
	circulation d’un nombre de train donné.
	➢ Le nombre de problèmes intervenus pendant la simulation sera indiqué dans le but de compléter
	l’affichage de la satisfaction étant donné que ces deux statistiques sont en corrélations directes.
	➢ Le temps moyen de résolution d’un problème permettra de modéliser le malus moyen subit par
	les gares pendant la durée des incidents.
	➢ La satisfaction générale de la ligne et la satisfaction de chaque gare seront indiqué pour
	visualiser la répartition à plusieurs échelles de la satisfaction des voyageurs.
	➢ L’évolution du nombre de voyageur sur la ligne au cours du temps qui permettra de mettre en
	avant les périodes de haute fréquentation et ainsi visualiser à contrario les périodes creuses.
	➢ Le nombre de voyageurs total ayant transité sur la ligne aura pour but de comptabiliser selon la
	durée de la simulation ou la densité maximale de voyageur définie, le nombre total d’usagers.*/
	
	private static final long serialVersionUID = -9180441865099755196L;
	
	private StatPanel instance = this;
	
	private JPanel NbIncidentPanel; 
	private JPanel SatisfactionStationPanel;
	private JPanel NumberTravelerPanel; //Superposition de panel afin d'être plus ergonomique(à voir)
	private JPanel buttonPanel;
	
	private JLabel NumberTotalTraveler;
	private JLabel NumberTotalIncident;
	private JLabel TimeResolveIncident;
	
	private JButton jbMenu;
	private JButton jbNew;
	private JButton jbExit;
	private JButton jbNextStation;
	private JButton jbPreviousStation;
	
	private SimulationData simulationData = SimulationData.getInstance();
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	public StatPanel() {	
		initComponent();
		initFont();	
	}
	
	
	private void initComponent() {
		
		
		setLayout(new GridBagLayout()) ;
		setBackground(GUIConstants.DARK_GREY_COLOR);
		
		
		NbIncidentPanel = new JPanel(); 
		SatisfactionStationPanel = new JPanel(); 
		NumberTravelerPanel = new JPanel(); 
		buttonPanel = new JPanel(); 
		
		NbIncidentPanel.setLayout(new BorderLayout()) ;
		SatisfactionStationPanel.setLayout(new BorderLayout()) ;
		NumberTravelerPanel.setLayout(new BorderLayout()) ;
		buttonPanel.setLayout(new BorderLayout()) ;
		
		NbIncidentPanel.setPreferredSize(new Dimension(400, 200));
		SatisfactionStationPanel.setPreferredSize(new Dimension(400, 200));
		NumberTravelerPanel.setPreferredSize(new Dimension(400, 200));
		buttonPanel.setPreferredSize(new Dimension(400, 200));
		
		NbIncidentPanel.setBackground(Color.WHITE);
		SatisfactionStationPanel.setBackground(Color.WHITE);
		NumberTravelerPanel.setBackground(Color.WHITE);
		buttonPanel.setBackground(Color.WHITE);
		
		NbIncidentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		SatisfactionStationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		NumberTravelerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		
		
		
		
		// Button
		
		jbNextStation.addActionListener(new ActionNew());
		jbPreviousStation.addActionListener(new ActionExit());
		jbMenu.addActionListener(new ActionMenu());
		jbNew.addActionListener(new ActionNew());
		jbExit.addActionListener(new ActionExit());
		
		
		// Add Panel and place it
		
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0; gbc.gridy = 0;
		add(NumberTravelerPanel, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		add(SatisfactionStationPanel, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		add(NbIncidentPanel, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		add(buttonPanel, gbc);
		
		
	}
	
	
	private void initFont(){
		
		
	}
	
	
	
	public class ActionNext implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			
		}
	}
	
	
	public class ActionPrevious implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			
		}
	}
	
	
	public class ActionMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			gui.GUIFrame.setCurrentPanel(Panels.MENU);
		}
	}
	
	public class ActionNew implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel(Panels.PARAMETERS);
		}
	}
	
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
