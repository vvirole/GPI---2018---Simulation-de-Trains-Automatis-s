package core;

import java.util.Observable;
import java.util.Observer;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Train;
import gui.GUIConstants;

/**
 * Controller of the line that use the design pattern Observer to notify
 * the dashboard (that contains the line) to update the render of line on the
 * frame.
 * 
 * @author Maxime
 *
 */
public class LineController extends Observable implements Runnable {
	
	// The line instance
	private Line line = Line.getInstance();
	
	// Counter of the simulation
	private int time = 0;
	
	public LineController(Observer observer){
		addObserver(observer);
	}
	
	@Override
	public void run() {
		
		// We start the running of the line
		line.setWorking(true);
		runTrains();
		
		while(line.isWorking() && time <= GUIConstants.MAX_DURATION){
			if (time % Constants.ARRIVAL_TRAIN_UNIT == 0){
				Canton startCanton = line.getCantons().get(0);
				if (startCanton.isFree()){
					Train newTrain;
					if (line.getPeriod().equals(Line.PERIOD_FULL)){
						newTrain = new Train(startCanton, 50, Constants.TRAIN_BASIC_SPEED, Train.LONG_TYPE);
					}
					else {
						newTrain = new Train(startCanton, 50, Constants.TRAIN_BASIC_SPEED, Train.SHORT_TYPE);
					}
					line.addTrain(newTrain);
					newTrain.start();
				}
			}
			
			// Notify the simulation panel that there is a change
			setChanged();
			notifyObservers(); 
			clearChanged();

			try {
				Thread.sleep(Constants.TIME_UNIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time++;
		}
		// We stop the current trains on the line
		stopTrains();
	}
	
	/*********************************************************************/
	
	/**
	 * Stop the traffic on the line
	 */
	public void stopTrains(){
		line.getTrains().forEach(train -> train.setRunning(false));
	}
	
	/**
	 * Run the traffic on the line
	 */
	public void runTrains(){
		line.getTrains().forEach(train -> train.setRunning(true));
	}
	
	/**
	 * Actions linked to the resolution of an incident 
	 */
	public void resolveIncident(Incident incident){
		
	}
	
	/*********************************************************************/
	
	/**
	 * @return the current cycle of simulation
	 */
	public int getTime(){
		return time;
	}

}
