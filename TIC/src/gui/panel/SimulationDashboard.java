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
	
	// List of trains that are on the line
	private List<Train> trains = new ArrayList<Train>();

	@Override
	public void run() {
		while(time <= GUIConstants.MAX_DURATION){
			if (time % 12 == 0){
				Line line = Line.getInstance();
				Canton startCanton = line.getCantons().get(0);
				if (startCanton.isFree()){
					Train newTrain = new Train(startCanton, 50, null, Constants.TRAIN_BASIC_SPEED, Train.SHORT_TYPE);
					trains.add(newTrain);
					newTrain.start();
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
		g2.setStroke(new BasicStroke(5));
		LinePrinter.printLine(Line.getInstance(), g2);
		LinePrinter.printTrains(trains, g2);
	}
	
	public int getTime(){
		return time;
	}

}
