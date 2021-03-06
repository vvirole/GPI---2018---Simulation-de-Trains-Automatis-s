package core.entity;

import java.util.logging.Logger;

import core.Constants;
import core.utility.RandomUtility;

public class Incident {
	
	// Incident on a canton
	public static final int INFRASTRUCTURE_INCIDENT = 0;
	// Incident on a station
	public static final int PASSENGER_INCIDENT = 1;
	
	// Fluctuation of time to resolve an incident
	private static final float FLUCTUATION_RESOLUTION_TIME_RATIO = 0.5f;
	
	// The location x of incident on the line
	private int location;
	
	// Type of incident
	private int type;
	
	public Incident(int location, int type){
		this.location = location;
		this.type = type;
	}
	
	/**
	 * Get the time to resolve this incident
	 * @return the number of cycles to resolve this incident
	 */
	public int getTimeToResolve(){
		String period = Line.getInstance().getPeriod();
		int defaultTime = Constants.DEFAULT_INCIDENT_RESOLUTION_TIME;
		switch(period){
			case Line.PERIOD_VOID : 	return defaultTime - RandomUtility.rand(0, Math.round(defaultTime * FLUCTUATION_RESOLUTION_TIME_RATIO)); 
			case Line.PERIOD_FULL : 	return defaultTime + RandomUtility.rand(0, Math.round(defaultTime * FLUCTUATION_RESOLUTION_TIME_RATIO));
			case Line.PERIOD_NORMAL :	return RandomUtility.rand(
													defaultTime - Math.round(defaultTime * FLUCTUATION_RESOLUTION_TIME_RATIO/2), 
													defaultTime + Math.round(defaultTime * FLUCTUATION_RESOLUTION_TIME_RATIO/2)
										); 
			default : 	Logger.getAnonymousLogger().info("Unknow period");
						return Constants.DEFAULT_INCIDENT_RESOLUTION_TIME;
		}
	}
	
	/**
	 * Indicate if the incident is located before the position of train
	 * @param train
	 * @return true if the incident is located before, else false
	 */
	public boolean isLocatedBefore(Train train){
		return (location < train.getCurrentPosition());
	}

	public int getLocation() {
		return location;
	}

	public int getType() {
		return type;
	}

}
