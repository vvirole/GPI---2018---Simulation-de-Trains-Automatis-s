package core;

import core.entity.Line;

public class LineController {
	
	// The line
	private Line line;
	
	public LineController(Line line){
		this.line = line;
	}
	
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

}
