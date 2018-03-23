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
	
	// Max duration of the simulation (secondes)
	private int duration;
	
	// Current cycle of the simulation
	private Clock clock;
	
	public LineController(int duration) {
		this.duration = duration;
		this.clock = Clock.newInstance();
		this.clock.start();
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
				
				
				for (Canton canton : line.getCantonList()) {
					if (canton.hasIncident() && Integer.parseInt(canton.getId()) != (line.getCantonList().size() - 1)) {
						//System.out.println("début**************************************");
						int idCanton = Integer.parseInt(canton.getId());
						//System.out.println("id acc: " + idCanton);
						
						Canton nextCanton = canton;
						try {
							//System.out.println("nexCanton");
							nextCanton = line.getCantonById(idCanton + 2);
							//System.out.println("id nexCanton : " + Integer.parseInt(nextCanton.getId()));
						} catch (TerminusException e) {
							e.printStackTrace();
						}
						//System.out.println("is free : " + nextCanton.isFree() + " has incident : " + nextCanton.hasIncident());
						if (nextCanton.isFree() && !nextCanton.hasIncident() && nextCanton.getStation().getNumReserveTrain() > 0 && ( canton.isFree() || canton.hasTrainBlocked())){
							//System.out.println("new train reserve");
							nextCanton.getStation().setNumReserveTrain(nextCanton.getStation().getNumReserveTrain() - 1);
							Train newTrain = new Train(canton, 0, Constants.TRAIN_BASIC_SPEED, Train.RESERVE_TYPE);
							line.addTrain(newTrain);
							newTrain.setCurrentPosition(canton.getStation().getPosition());
							try {
								canton.getStation().enter(newTrain);
							} catch (TerminusException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							newTrain.start();
						}
						//System.out.println("fin**************************************");

					}
				}
				
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
				line.resolveIncident();
			}
			
			// Each minute
			if (clock.getCounter() % 60 == 0){
				
				// Store data
				storeData(clock.getElapsedMinute()); 
				
				// Update the period of journey
				line.updatePeriod(clock.getHour());
				
				int i = RandomUtility.rand(0, line.getNbCanton() - 1);
				Canton canton = line.getCanton(i);
				if (!canton.hasIncident() && RandomUtility.rand(0, 20) < Constants.INCIDENT_RATIO){
					line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
				}
			}
			
			// Eeach 2 minutes
			if (clock.getCounter() % 120 == 0){
				for (Station station : line.getStationList()){
					station.updatePassengers();
					station.updateSatisfaction();
				}
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
	}

	/**
	 * Store the data of the current cycle of the simulation
	 * @minute the number of minute elapsed
	 */
	private void storeData(int minute) {
		int satisfaction = 0;
		int passenger = 0;
		List<Station> stations = line.getStationList();
		for (Station station : stations) {
			satisfaction += station.getSatisfaction();
			passenger += station.getCurrentPassenger();
		}
		satisfaction = satisfaction / line.getNbCanton();
		DataStorage.getInstance().addPassengerData(minute, passenger);
		DataStorage.getInstance().addSatisfactionData(minute, satisfaction);
		
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

}
