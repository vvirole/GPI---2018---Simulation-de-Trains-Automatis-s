package gui;

import java.awt.Color;

public class GUIConstants {
	
	/* * * * * * * * * * * * WINDOW * * * * * * * * * * * * */
	
	public final static int WINDOW_WIDTH = 1200;
	public final static int WINDOW_HEIGHT = 600;
	
	/* * * * * * * * * * * * COLORS * * * * * * * * * * * * */
	
	public final static Color LINE_COLOR = new Color(30, 20, 190);
	public final static Color STATION_COLOR = new Color(145, 55, 120);
	public final static Color TRAIN_COLOR = new Color(250, 20, 15);
	public final static Color BACKGROUND_SATISFACTION = new Color(0,0,0);
	public final static Color SATISFACTION = new Color(250,0,0);
	
	/* * * * * * * * * * * * SIMULATION CONSTANTS * * * * * * * * * * * */
	
	public static final int LINE_LENGTH = 1100;
	public static final int LINE_X = WINDOW_WIDTH/2 - LINE_LENGTH/2;
	public static final int LINE_Y = 150;
	
	public final static int MAX_DURATION = 10000;
	public static final int TIME_UNIT = 50;
	public final static int SIZE_STATION = 20;
	
	
}
