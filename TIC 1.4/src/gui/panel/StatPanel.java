package gui.panel;



import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


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
	
	private JPanel IncidentTimePanel; 
	private JPanel SatisfactionStationPanel;
	private JPanel NumberTravelerStationPanel; //Superposition de panel afin d'être plus ergonomique
	private JPanel buttonPanel;
	
	private JLabel NumberTotalTraveler;
	private JLabel NumberTotalIncident;
	private JLabel TimeResolveIncident;
	
	private JButton jbMenu;
	private JButton jbNew;
	private JButton jbExit;
	private JButton jbNextStation;
	private JButton jbPreviousStation;
	
	
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	public StatPanel() {	
		initComponent();
		initFont();	
	}
	
	
	private void initComponent() {
		
		
		
		jbNextStation.addActionListener(new ActionNew());
		jbPreviousStation.addActionListener(new ActionExit());
		jbMenu.addActionListener(new ActionMenu());
		jbNew.addActionListener(new ActionNew());
		jbExit.addActionListener(new ActionExit());
		
		
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
