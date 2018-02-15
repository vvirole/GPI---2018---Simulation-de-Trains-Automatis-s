package core.entity;

public class Canton {

	//Canton identity
	private int id;
	
	//Length of the canton on the frame
	private int length;
	
	// Position where the canton begins
	private int startPoint;
	
	//If an accident occurred on the Canton
	private boolean accident = false;
	
	//The station at the end the canton
	private Station station;
	
	// The train in the canton
	private Train occupyingTrain = null;

	
	public Canton(int id, int startPoint, int length, Station station) {
		this.id = id;
		this.startPoint = startPoint;
		this.length = length;	
		this.station = station;
	}
	
	public synchronized void enter(Train train) {
		if (occupyingTrain != null) {
			train.setCurrentPosition(startPoint);
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			Canton oldCanton = train.getCurrentCanton();
			train.setCurrentCanton(this);
			train.updatePosition();
			oldCanton.exit();
			occupyingTrain = train;
		}
	}

	public synchronized void exit() {
		occupyingTrain = null;
		notify();
	}
	
	//GETTER AND SETTER

	public int getId() {
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
	
	public boolean isFree(){
		return (occupyingTrain == null);
	}

	public boolean isAccident() {
		return accident;
	}

	public void setAccident(boolean accident) {
		this.accident = accident;
	}

	public Station getStation() {
		return station;
	}
	
	public void setStation(Station station) {
		this.station = station;
	}
	
}