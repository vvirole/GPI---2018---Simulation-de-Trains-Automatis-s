package core;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Train;
import core.utility.RandomUtility;
import gui.GUIConstants;
import gui.panel.SimulationData;

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
				if (startCanton.isFree() && !startCanton.hasIncident()){
					String currentPeriod = line.getPeriod();
					Train newTrain = new Train(startCanton, 0, Constants.TRAIN_BASIC_SPEED, 
								(currentPeriod.equals(Line.PERIOD_FULL) ? Train.LONG_TYPE : Train.SHORT_TYPE));
					line.addTrain(newTrain);
					newTrain.start();
				}
			}	
			
			SimulationData.getInstance().addTravelerData(getTime(), 50);// dès implémentation à mettre en dynamique
			SimulationData.getInstance().addSatisfactionData(getTime(), getTime());
			
			
			int i = RandomUtility.rand(0, line.getNbCanton() - 1);
			Canton canton = line.getCanton(i);
			if (!canton.hasIncident() && RandomUtility.rand(0, 100) < Constants.INCIDENT_RATIO){
				line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
			}
			
			if (line.hasIncident()){
				resolveIncident();
			}
			
			// Notify the simulation panel that there is a change (repaint needed)
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
	 * Run the resolution procedure to resolve the incidents on the line
	 */
	public void resolveIncident(){
		Map<Incident, Integer> incidents = line.listIncidents();
		for (Entry<Incident, Integer> entry : incidents.entrySet()){
			Incident incident = entry.getKey();
			Integer remainingTime = entry.getValue();
			incidents.put(incident, --remainingTime);
			if (remainingTime == 0){
				line.removeIncident(incident);
			}
		}
	}

	
	/*********************************************************************/
	
	/**
	 * @return the current cycle of simulation
	 */
	public int getTime(){
		return time;
	}

}
