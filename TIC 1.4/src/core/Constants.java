package core;

/**
 * Constants about the line
 * 
 * @author Maxime
 *
 */
public class Constants {
	
	// Initial number of passengers by station
	public static int INITIAL_PASSENGER_STATION = 200;
	
	// Basic speed of trains
	public static int TRAIN_BASIC_SPEED = 5;
	
	// Numbers of persons that a short train can welcome
	public static int SHORT_TRAIN_CAPACITY = 50;
	
	// Numbers of persons that a long train can welcome
	public static int LONG_TRAIN_CAPACITY = 100;
	
	// Default time to resolve an incident on the line (number of cycles)
	public static int DEFAULT_INCIDENT_RESOLUTION_TIME = 200;
	
	// Probability that an incident happens on the line
	public static int INCIDENT_RATIO = 2;
	
	// Time of critique sections
	public static final int TIME_UNIT = 50;
	
	// Default duration of a train's pause into a station 
	public static final int DEFAULT_PAUSE_STATION = 1000;
	
	// Average frequence of arrival train on the line
	public static final int ARRIVAL_TRAIN_UNIT = 20;
}