package core.entity;

public class Canton {

	//Canton identity
	private String id;
	
	//Length of the canton on the frame
	private int length;
	
	// Position where the canton begins
	private int startPoint;
	
	//The station at the end the canton
	private Station station;
	
	// The train in the canton
	private Train occupyingTrain = null;
	
	// Indicate if there is an incident
	private boolean incident = false;
	
	// Indicate if there is train blocked by an incident
	private boolean trainBlocked = false;

	
	public Canton(String id, int startPoint, int length, Station station) {
		this.id = id;
		this.startPoint = startPoint;
		this.length = length;	
		this.station = station;
	}
	
	public synchronized void enter(Train train) {
		if (occupyingTrain != null) {
			// If the next canton is occupied, the train wait
			train.setCurrentPosition(startPoint);
			try {
				wait(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			// The train leave his current canton and the station
			Canton oldCanton = train.getCurrentCanton();
			oldCanton.getStation().exit();
			oldCanton.exit();
			// He is positionned now on this canton
			train.setCurrentCanton(this);
			train.updatePosition();
			occupyingTrain = train;
		}
	}

	public synchronized void exit() {
		occupyingTrain = null;
		notify();
	}
	
	/**
	 * @return if the canton is free
	 */
	public boolean isFree(){
		return (occupyingTrain == null);
	}
	
	/*********************************************************/

	public String getId() {
		return id;
	}
	
	public int getStartPoint(){
		return startPoint;
	}
	
	public void setStartPoint(int startPoint){
		this.startPoint = startPoint;
	}
	
	public int getEndPoint(){
		return startPoint + length;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	public Station getStation() {
		return station;
	}
	
	public void setStation(Station station) {
		this.station = station;
	}

	public boolean hasIncident() {
		return incident;
	}

	public void setIncident(boolean incident) {
		this.incident = incident;
	}
	
	public boolean hasTrainBlocked() {
		return trainBlocked;
	}

	public void setTrainBlocked(boolean trainBlocked) {
		this.trainBlocked = trainBlocked;
	}
	
}
