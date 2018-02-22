package core;

import java.util.Observable;
import java.util.Observer;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Train;
import core.utility.RandomUtility;
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
	
	// Max duration of the simulation
	private int duration = GUIConstants.MAX_DURATION;
	
	// Current cycle of the simulation
	private int time = 0;
	
	public LineController(int duration) {
		this.duration = duration;
	}
	
	public LineController(Observer observer){
		addObserver(observer);
	}

	@Override
	public void run() {
		
		// We start the running of the line
		line.setWorking(true);
		runTrains();
	
		while(line.isWorking() && time <= duration){
			if (time % Constants.ARRIVAL_TRAIN_UNIT == 0){
				Canton startCanton = line.getCantons().get(0);
				if (startCanton.isFree() && !startCanton.hasIncident()){
					String currentPeriod = line.getPeriod();
					Train newTrain = new Train(startCanton, 0, Constants.TRAIN_BASIC_SPEED, 
								(currentPeriod.equals(Line.PERIOD_FULL) ? Train.LONG_TYPE : Train.SHORT_TYPE));
					line.addTrain(newTrain);
					newTrain.start();
				}
			}	
			
			int i = RandomUtility.rand(0, line.getNbCanton() - 1);
			Canton canton = line.getCanton(i);
			if (!canton.hasIncident() && RandomUtility.rand(0, 500) < Constants.INCIDENT_RATIO){
				line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
			}
			
			if (line.hasIncident()){
				line.resolveIncident();
			}
			
			// Notify the simulation panel that there is a change (repaint needed)
			setChanged();
			notifyObservers(); 

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
	
	/*********************************************************************/
	
	/**
	 * @return the current cycle of simulation
	 */
	public int getTime(){
		return time;
	}

}
