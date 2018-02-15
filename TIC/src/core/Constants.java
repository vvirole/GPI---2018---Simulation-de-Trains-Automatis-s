package core;

/**
 * Constants about the line
 * 
 * @author Maxime
 *
 */
public class Constants {
	
	// Maximum number of passenger on the line.
	public static final int MAX_PASSENGER = 1000;
	
	// Basic speed of trains
	public static final int TRAIN_BASIC_SPEED = 5;
	
	// Numbers of persons that a short train can welcome
	public static final int SHORT_TRAIN_CAPACITY = 150;
	
	// Numbers of persons that a long train can welcome
	public static final int LONG_TRAIN_CAPACITY = 300;
	
	// Default time to resolve an incident on the line (number of cycles)
	public static final int DEFAULT_INCIDENT_RESOLUTION_TIME = 50;
	
	// Time of critique sections
	public static final int TIME_UNIT = 50;
	
	// Frequence of arrival train on the line
	public static final int ARRIVAL_TRAIN_UNIT = 20;
}
