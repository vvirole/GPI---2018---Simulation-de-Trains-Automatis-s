package gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.entity.Line;
import gui.LinePrinter;

public class SimulationDashboard extends JPanel {

	private static final long serialVersionUID = 1881603318378368505L;
	
	// The instance of the line
	private Line line = Line.getInstance();
	
	// Display the current period of the simulation
	private JLabel jlPeriod;
	
	private static final int MARGIN = 50;
	
	
	public SimulationDashboard(){
		setLayout(null);
		init();
	}
	
	private void init(){
		// Initiate the label
		jlPeriod = new JLabel("Période : " + line.getPeriod());
		jlPeriod.setForeground(Color.DARK_GRAY);
		jlPeriod.setHorizontalAlignment(JLabel.CENTER);
		jlPeriod.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		jlPeriod.setLocation(850,20);
		
		// Font for the label
		Font font = new Font(Font.DIALOG, Font.BOLD, 20);
		jlPeriod.setFont(font);
				
		FontMetrics mPeriod = getFontMetrics(jlPeriod.getFont());
		int wPeriod = mPeriod.stringWidth(jlPeriod.getText()) + 2 * MARGIN;
		jlPeriod.setSize(new Dimension(wPeriod, 50));	
		add(jlPeriod);
	}
	
	/**
	 * Draw the line and the trains
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		jlPeriod.updateUI(); // A VERIFIER => DOIT MODIFIER LA PERIODE
		LinePrinter.printLine(line, g2);
		LinePrinter.printTrains(line.getTrains(), g2);
	}

}
