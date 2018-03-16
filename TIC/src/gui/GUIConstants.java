package gui;

import java.awt.Color;

public class GUIConstants {
	
	/* * * * * * * * * * * * WINDOW * * * * * * * * * * * * */
	
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 600;
	
	/* * * * * * * * * * * * COLORS * * * * * * * * * * * * */
	
	public static final Color LINE_COLOR = new Color(200, 200, 200);
	public static final Color STATION_COLOR = new Color(203, 67, 53);
	public static final Color SHORT_TRAIN_COLOR = new Color(220, 118, 51);
	public static final Color LONG_TRAIN_COLOR = new Color(200, 60, 30);
	public static final Color RESERVE_TRAIN_COLOR = new Color(230, 142, 100);
	
	/* * * * * * * * * * * * SIMULATION CONSTANTS * * * * * * * * * * * */
	
	public static final int LINE_LENGTH = 1000;
	public static final int LINE_X = WINDOW_WIDTH/2 - LINE_LENGTH/2;
	public static final int LINE_Y = 200;
	public static final int SIZE_STATION = 20;
	
	// -------------------------------------------------------------- //
	
	public static final int LINE_STROKE = 10;
	public static final int CANTON_STROKE = 2;
	public static final int STATION_STROKE = 5;
	public static final int SHORT_TRAIN_STROKE = 5;
	public static final int LONG_TRAIN_STROKE = 8;
	public static final int RESERVE_TRAIN_STROKE = 6;
	public static final int INCIDENT_STROKE = 5;
	
	// -------------------------------------------------------------- //
	
	public static int MAX_DURATION = 100;
	public static int START_HOURS = 5;
		
}
