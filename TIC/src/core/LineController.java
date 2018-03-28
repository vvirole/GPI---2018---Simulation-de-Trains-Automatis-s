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
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
 *
 */
public class LineController extends Observable implements Runnable {
	
	// The line instance
	private Line line = Line.getInstance();
	
	// Max duration of the simulation (secondes)
	private int duration;
	
	// Current cycle of the simulation
	private Clock clock;
	
	// Number of accident
	private int nbIncident;
	
	public LineController(int duration) {
		this.duration = duration;
		this.clock = Clock.newInstance(this);
		this.clock.start();
		this.nbIncident = 0;
	}
	
	public LineController(Observer observer){
		this(GUIConstants.MAX_DURATION);
		addObserver(observer);
	}

	@Override
	public void run() {
		
		// We start the running of the line
		line.setWorking(true);
		runTrains();
	
		while(line.isWorking() && clock.getCounter() <= duration){
			if (clock.getCounter() % Constants.ARRIVAL_TRAIN_UNIT == 0){
				
				/**
				 * New trains that enter on the line
				 */
				Canton startCanton = line.getCantons().get(0);
				if (startCanton.isFree() && !startCanton.hasIncident()){
					String currentPeriod = line.getPeriod();
					Train newTrain = new Train(startCanton, 0, Constants.TRAIN_BASIC_SPEED, 
								(currentPeriod.equals(Line.PERIOD_FULL) ? Train.LONG_TYPE : Train.SHORT_TYPE));
					line.addTrain(newTrain);
					newTrain.start();
				}
			}
			
			if (line.hasIncident()){
				for (Canton canton : line.getCantonList()){
					if (canton.hasIncident())
						throwReserveTrain(canton);
				}
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
		}
		
		setChanged();
		notifyObservers(); 
		stopTrains(); // We stop the current trains on the line
		clock.close();
	}
	
	/**
	 * 
	 */
	public void update(){
		// Storage of data
		storeData();
		
		// Update the period of journey
		line.updatePeriod(clock.getHour());
		
		for (Station station : line.getStationList()){
			station.updatePassengers();
		}
		
		// Probability to have a new incident
		int i = RandomUtility.rand(0, line.getNbCanton() - 1);
		Canton canton = line.getCanton(i);
		if (!canton.hasIncident() && RandomUtility.rand(0, 20) < Constants.INCIDENT_RATIO){
			if (RandomUtility.rand(0, 4) == 0){
				line.newIncident(canton, Incident.PASSENGER_INCIDENT);
				System.out.println("\nAn incident occured in the station " + canton.getStation().getName());
			}
			else {
				line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
				System.out.println("\nAn incident occured in the canton " + canton.getId());
			}
			nbIncident++;
		}
	}
	
	/**
	 * Update statisfaction data of station
	 */
	public void updateSatisfaction(){
		for (Station station : line.getStationList()){
			station.updateSatisfaction();
		}
	}

	/**
	 * Run a reserve train of a station of a accidented canton
	 * @param accidentedCanton a canton that has an incident
	 */
	public void throwReserveTrain(Canton accidentedCanton) {
			
		if (!accidentedCanton.isLastCanton()) {
			Canton nextCanton = line.getCanton(Integer.parseInt(accidentedCanton.getId()) + 1);
			Station station = accidentedCanton.getStation();
				
			if (nextCanton.isFree() && !nextCanton.hasIncident() && accidentedCanton.isFree() && !accidentedCanton.hasTrainBlocked() 
					&& station.hasAvailableReserveTrain() && station.isFree() && !station.hasIncident()){
				Train reserveTrain = new Train(accidentedCanton, 0, Constants.TRAIN_BASIC_SPEED, Train.RESERVE_TYPE);
				reserveTrain.setCurrentPosition(station.getPosition());			
				line.addTrain(reserveTrain);
				station.useReserveTrain();
				reserveTrain.start();
			}
		}
	}

	/**
	 * Store the data of the current cycle of the simulation
	 */
	private void storeData() {
		int satisfaction = 0;
		int passenger = 0;
		List<Station> stations = line.getStationList();
		for (Station station : stations) {
			satisfaction += station.getSatisfaction();
			passenger += station.getCurrentPassenger();
		}
		satisfaction /= line.getNbCanton();
		float time = Clock.getInstance().getFormattedTime();
		DataStorage.getInstance().addData(time, passenger, nbIncident, satisfaction);
	}

	/*********************************************************************/
	
	/**
	 * Stop the traffic on the line
	 */
	public void stopTrains(){
		for (Train train : line.getTrains()){
			train.setRunning(false);
		}
	}
	
	/**
	 * Run the traffic on the line
	 */
	public void runTrains(){
		for (Train train : line.getTrains()){
			train.setRunning(true);
		}
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
	
	public Clock getClock(){
		return clock;
	}

}
