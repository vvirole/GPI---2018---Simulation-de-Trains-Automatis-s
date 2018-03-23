package core;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;
import core.utility.Clock;
import core.utility.DataStorage;
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
	
	// Clock of the simulation
	private Clock clock;
	
	public LineController(int duration) {
		this.duration = duration;
		this.clock = Clock.newInstance(GUIConstants.START_HOURS);
	}
	
	public LineController(Observer observer){
		addObserver(observer);
		this.clock = Clock.newInstance(GUIConstants.START_HOURS);
	}

	@Override
	public void run() {
		
		// We start the running of the line
		line.setWorking(true);
		runTrains();
	
		while(line.isWorking() && clock.getCounter() <= duration){
			if (clock.getCounter() % Constants.ARRIVAL_TRAIN_UNIT == 0){
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
			
			// Store data of this current cycle
			storeData();
			
			// Notify the simulation panel that there is a change (repaint needed)
			setChanged();
			notifyObservers(); 

			try {
				Thread.sleep(Constants.TIME_UNIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clock.incrementClock();
		}
		// We stop the current trains on the line
		stopTrains();
	}
	
	/**
	 * 
	 */
	private void storeData() {
		int satisfaction = 0;
		int passenger = 0;
		List<Station> stations = line.getStationList();
		for (Station station : stations) {
			satisfaction += station.getSatisfaction();
			passenger += station.getCurrentPassenger();
		}
		satisfaction = satisfaction / line.getNbCanton();
		DataStorage.getInstance().addPassengerData(getTime(), passenger);
		DataStorage.getInstance().addSatisfactionData(getTime(), satisfaction);
		
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
		return clock.getCounter();
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}

}
