package controller;

public class Canton {

	//Canton identity
	private int id;
	
	//Length of the canton on the frame
	private int length;
	
	//If a Train is on the Canton
	private boolean occuped = false;
	
	//If an accident occurred on the Canton
	private boolean accident = false;
	
	//The station on the canton
	private Station station;

	public Canton(int id, int length) {
		this.id = id;
		this.length = length;
		
		//this.station = new Station();
		
	}
	
	
	
	//GETTER AND SETTER

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isOccuped() {
		return occuped;
	}

	public void setOccuped(boolean occuped) {
		this.occuped = occuped;
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
