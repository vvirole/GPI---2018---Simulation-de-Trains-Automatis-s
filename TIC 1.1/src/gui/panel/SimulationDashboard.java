package gui.panel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Constants;
import core.LineController;
import core.entity.Canton;
import core.entity.Line;
import core.entity.Train;
import core.utility.Counter;
import gui.GUIConstants;
import gui.LinePrinter;

public class SimulationDashboard extends JPanel implements Runnable {

	private static final long serialVersionUID = -6106553604954976038L;
	
	// The parent component
	private SimulationPanel parent;
	
	// The line
	private Line line = Line.getInstance();
	
	// The controller
	private LineController controller = new LineController(line);
	
	// Counter of the simulation
	private Counter counter = Counter.newInstance();
	
	// If the simulation is running
	private boolean running = false;
	
	private static final int MARGIN = 80;
	
	
	private JLabel jlPeriod ;
	
	public SimulationDashboard(SimulationPanel parent) {
		this.parent = parent;
		setLayout(null);
		initComponent();
		initFont();
	}
	
	
	private void initComponent(){
		
		// Initiate jlPeriod
		jlPeriod = new JLabel("Période : " /*+ line.getPeriod()*/);
		jlPeriod.setForeground(Color.DARK_GRAY);
		jlPeriod.setHorizontalAlignment(JLabel.CENTER);
		jlPeriod.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		jlPeriod.setLocation(900,20);
		
		FontMetrics mPeriod = getFontMetrics(jlPeriod.getFont());
		int wTitle = mPeriod.stringWidth(jlPeriod.getText()) + 2 * MARGIN;
		jlPeriod.setSize(new Dimension(wTitle, 50));
		
		add(jlPeriod);
		
		
	}
	private void initFont(){
		
		Font infoFont = new Font(Font.DIALOG, Font.BOLD, 20);
		
		jlPeriod.setFont(infoFont);
		
		
	}

	@Override
	public void run() {
		running = true;
		controller.runTrains();
		while(running && counter.getValue() <= GUIConstants.MAX_DURATION){
			if (counter.getValue() % 12 == 0){
				Canton startCanton = line.getCantons().get(0);
				if (startCanton.isFree()){
					Train newTrain = new Train(startCanton, 50, null, Constants.TRAIN_BASIC_SPEED, Train.SHORT_TYPE);
					line.addTrain(newTrain);
					newTrain.start();
				}
			}
			repaint();
			try {
				Thread.sleep(GUIConstants.TIME_UNIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter.increment();
			parent.update();
		}
		controller.stopTrains();
	}
	
	/**
	 * Draw the line and the trains
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Dialog", Font.PLAIN, 15));
		g2.setStroke(new BasicStroke(5));
		LinePrinter.printLine(line, g2);
		LinePrinter.printTrains(line.getTrains(), g2);
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}

}
