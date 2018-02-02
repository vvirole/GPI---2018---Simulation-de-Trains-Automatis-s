package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
 * @author Vincent Virole
 *
 */
public class SimulationPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1722047581362200550L;
	
	private SimulationPanel instance = this;
	
	

	public SimulationPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(GUIConstants.BLACK);
		//initComponent();
		//initFont();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
