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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import core.utility.DataStorage;


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
	private JPanel SatisfactionPanel;
	private JPanel NumberTravelerPanel; //Superposition de panel afin d'être plus ergonomique(à voir)
	private JPanel buttonPanel;
	
	private ChartPanel NumberTravelerChartPanel;
	private ChartPanel SatisfactionChartPanel;
	private ChartPanel NbIncidentChartPanel;
	
	private JButton jbMenu;
	private JButton jbNew;
	private JButton jbExit;
	
	private JFreeChart XYChartTraveler;
	private XYSeriesCollection seriesCollectionTraveler;
	private JFreeChart XYChartSatisfaction;
	private XYSeriesCollection seriesCollectionSatisfaction;
	
	private DataStorage dataStorage = DataStorage.getInstance();
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	public StatPanel() {	
		initComponent();
		initFont();	
	}
	
	
	private void initComponent() {
		
		setLayout(new GridBagLayout()) ;
		setBackground(Color.DARK_GRAY);	
		
		NbIncidentPanel = new JPanel(); 
		SatisfactionPanel = new JPanel(); 
		NumberTravelerPanel = new JPanel(); 
		buttonPanel = new JPanel(); 
		
		NbIncidentPanel.setLayout(new BorderLayout()) ;
		SatisfactionPanel.setLayout(new BorderLayout()) ;
		NumberTravelerPanel.setLayout(new BorderLayout()) ;
		buttonPanel.setLayout(new GridBagLayout()) ;
		
		NbIncidentPanel.setPreferredSize(new Dimension(500, 250));
		SatisfactionPanel.setPreferredSize(new Dimension(500, 250));
		NumberTravelerPanel.setPreferredSize(new Dimension(500, 250));
		buttonPanel.setPreferredSize(new Dimension(500, 250));
		
		NbIncidentPanel.setBackground(Color.WHITE);
		SatisfactionPanel.setBackground(Color.WHITE);
		NumberTravelerPanel.setBackground(Color.WHITE);
		buttonPanel.setBackground(Color.WHITE);
		
		NbIncidentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		SatisfactionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		NumberTravelerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		XYChartTraveler = createXYChartTraveler();
		NumberTravelerChartPanel = new ChartPanel(XYChartTraveler);
		
		XYChartSatisfaction = createXYChartSatisfaction();
		SatisfactionChartPanel = new ChartPanel(XYChartSatisfaction);
		
		// Buttons
		
		jbMenu = new JButton("Menu");
		jbNew = new JButton("Nouvelle partie");
		jbExit = new JButton("Quitter");
		
		jbMenu.addActionListener(new ActionMenu());
		jbNew.addActionListener(new ActionNew());
		jbExit.addActionListener(new ActionExit());
		
		jbMenu.setPreferredSize(new Dimension(200, 50));
		jbNew.setPreferredSize(new Dimension(200, 50));
		jbExit.setPreferredSize(new Dimension(200, 50));
		
		
		// Add Panel and place it
		
		NumberTravelerPanel.add(NumberTravelerChartPanel, BorderLayout.CENTER);
		SatisfactionPanel.add(SatisfactionChartPanel, BorderLayout.CENTER);
		
		
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0; gbc.gridy = 0;
		buttonPanel.add(jbMenu, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		buttonPanel.add(jbNew, gbc);
		gbc.gridx = 0; gbc.gridy = 2;
		buttonPanel.add(jbExit, gbc);
		
		
		gbc.insets = new Insets(10, 20, 10, 20);
		gbc.gridx = 0; gbc.gridy = 0;
		add(NumberTravelerPanel, gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		add(SatisfactionPanel, gbc);
		gbc.gridx = 0; gbc.gridy = 1;
		add(NbIncidentPanel, gbc);
		gbc.gridx = 1; gbc.gridy = 1;
		add(buttonPanel, gbc);	
	}
		
	private void initFont(){		
		Font buttonFont = new Font(Font.DIALOG, Font.BOLD, 20);
		jbMenu.setFont(buttonFont);
		jbNew.setFont(buttonFont);
		jbExit.setFont(buttonFont);		
	}
		
	private JFreeChart createXYChartTraveler() {
		
		XYSeries graph = new XYSeries("Evolution du nombre de voyageur sur la ligne");
		Map<Integer, Integer> data = dataStorage.getPassengerData();
		Integer key, value;
		
		for(Entry<Integer, Integer> entry : data.entrySet()){ 		
			key = entry.getKey(); // get key
			value = entry.getValue(); // get value
			graph.add(key, value);	
		}

		seriesCollectionTraveler = new XYSeriesCollection();
		seriesCollectionTraveler.addSeries(graph);
		return ChartFactory.createXYLineChart("Évolution du nombre de voyageur", "temps (minutes)", "Nombre de voyageur total", seriesCollectionTraveler, PlotOrientation.VERTICAL, true, true, false);
	
	}
	
	
	private JFreeChart createXYChartSatisfaction() {
		
		XYSeries graph = new XYSeries("Evolution du taux de satisfaction moyen");
		Map<Integer, Integer> data = dataStorage.getSatisfactionData();
		Integer key, value;
		
		for(Entry<Integer, Integer> entry : data.entrySet()){ 		
			key = entry.getKey(); // get key
			value = entry.getValue(); // get value
			graph.add(key, value);	
		}

		seriesCollectionSatisfaction = new XYSeriesCollection();
		seriesCollectionSatisfaction.addSeries(graph);
		return ChartFactory.createXYLineChart("Évolution du taux de satisfaction moyen", "temps (minutes)", "Taux de satisfaction moyen", seriesCollectionSatisfaction, PlotOrientation.VERTICAL, true, true, false);
	
	}
	
	
	
	public class ActionNext implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			
		}
	}
	
	public class ActionMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			gui.GUIFrame.setCurrentPanel(Panels.MENU);
			DataStorage.getInstance().clear();
		}
	}
	
	public class ActionNew implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.GUIFrame.setCurrentPanel(Panels.PARAMETERS);
			DataStorage.getInstance().clear();
		}
	}
	
	public class ActionExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int exit = JOptionPane.showConfirmDialog(instance, "Êtes-vous sûre de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.OK_OPTION){
				System.exit(0);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
