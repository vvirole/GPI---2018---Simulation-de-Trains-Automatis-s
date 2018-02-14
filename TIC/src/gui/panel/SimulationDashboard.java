package gui.panel;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import core.Constants;
import core.entity.Canton;
import core.entity.Line;
import core.entity.Train;
import gui.GUIConstants;
import gui.LinePrinter;

public class SimulationDashboard extends JPanel implements Runnable {

	private static final long serialVersionUID = -6106553604954976038L;
	
	// Current cycle of the simulation
	private int time = 0;
	
	// If the simulation is running
	private boolean run = false;
	
	@Override
	public void run() {
		while(time <= GUIConstants.MAX_DURATION && run){
			if (time % 12 == 0){
				Line line = Line.getInstance();
				Canton startCanton = line.getCantons().get(0);
				if (startCanton.isFree()){
					double random = (Math.random()*(2 - 0 + 1)) + 0;
					if(random <= 1) {
						Train newTrain = new Train(startCanton, 50, null, Constants.TRAIN_BASIC_SPEED, Train.LONG_TYPE);
						line.addTrain(newTrain);
						newTrain.start();
					}
					else {
						Train newTrain = new Train(startCanton, 50, null, Constants.TRAIN_BASIC_SPEED, Train.SHORT_TYPE);
						line.addTrain(newTrain);
						newTrain.start();
					}
				}
			}
			repaint();
			try {
				Thread.sleep(GUIConstants.TIME_UNIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time++;
		}
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
		g2.setStroke(new BasicStroke(8));
		Line line = Line.getInstance();
		LinePrinter.printLine(line, g2);
		LinePrinter.printTrains(line.getTrains(), g2);
	}
	
	public int getTime(){
		return time;
	}

	public boolean getRun(){
		return run;
	}
	
	public void setRun(boolean run) {
		this.run = run;
	}

}
