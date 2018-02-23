package gui;

import static gui.GUIConstants.CANTON_STROKE;
import static gui.GUIConstants.INCIDENT_STROKE;
import static gui.GUIConstants.LINE_COLOR;
import static gui.GUIConstants.LINE_STROKE;
import static gui.GUIConstants.LINE_X;
import static gui.GUIConstants.LINE_Y;
import static gui.GUIConstants.LONG_TRAIN_COLOR;
import static gui.GUIConstants.LONG_TRAIN_STROKE;
import static gui.GUIConstants.RESERVE_TRAIN_COLOR;
import static gui.GUIConstants.RESERVE_TRAIN_STROKE;
import static gui.GUIConstants.SHORT_TRAIN_COLOR;
import static gui.GUIConstants.SHORT_TRAIN_STROKE;
import static gui.GUIConstants.SIZE_STATION;
import static gui.GUIConstants.STATION_COLOR;
import static gui.GUIConstants.STATION_STROKE;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;
import core.utility.RandomUtility;

/**
 * Draw elements that a line is composed
 * 
 * @author Maxime
 *
 */
public class LinePrinter {
	
	// Colors used for the cantons (the key is the id of canton and value the color associated)
	private static Map<String, Color> colors = new HashMap<String, Color>();
	
	// Default font
	private static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 15);
	
	/**
	 * Print the line
	 * @param line
	 * @param g2
	 */
	public static void printLine(Line line, Graphics2D g2){
		g2.setColor(LINE_COLOR);
		g2.setStroke(new BasicStroke(LINE_STROKE));
		g2.drawLine(LINE_X, LINE_Y, LINE_X + line.getLength(), LINE_Y);
		//line.getCantons().forEach(canton -> printCanton(canton, g2));
		for(Canton canton : line.getCantons()){
			printCanton(canton, g2);
		}
		//line.getCantons().forEach(canton -> printStation(canton.getStation(), g2));
		for(Canton canton : line.getCantons()){
			printStation(canton.getStation(), g2);
		}
		for (Entry<Incident, Integer> entry : line.listIncidents().entrySet()){
			printIncident(entry.getKey(), g2);
		}
	}
	
	/**
	 * Print a canton
	 * @param canton
	 * @param g2
	 */
	private static void printCanton(Canton canton, Graphics2D g2){
		if (!colors.containsKey(canton.getId())){
			int r = RandomUtility.rand(0, 200);
			int g = RandomUtility.rand(0, 200);
			int b = RandomUtility.rand(0, 200);
			colors.put(canton.getId(), new Color(r, g, b));
		}
		g2.setColor(colors.get(canton.getId()));		
		g2.setStroke(new BasicStroke(CANTON_STROKE));
		
		// We draw lines that color the different cantons
		printLinesCanton(canton, canton.getLength()/10, g2);
		
		// We draw the name of the canton
		String s = "Canton " + canton.getId(); 
		int widthS = g2.getFontMetrics().stringWidth(s);
		g2.setFont(DEFAULT_FONT);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); // Opacity of 100%
		g2.drawString(s, LINE_X + canton.getStartPoint() + canton.getLength()/2 - widthS/2, LINE_Y + 2 * SIZE_STATION);
	}
	
	/**
	 * Draw the sub lines of a canton to color it
	 * @param canton
	 * @param n the number of lines to draw
	 */
	private static void printLinesCanton(Canton canton, int n, Graphics2D g2){
		for (int i = 0 ; i < n ; i++) {
			g2.drawLine(LINE_X + canton.getStartPoint() + (i * 10), LINE_Y - LINE_STROKE/2 + 1, 
						LINE_X + canton.getStartPoint() + (i * 10), LINE_Y + LINE_STROKE/2 - 1
			);
		}
	}
	
	/**
	 * Print a station
	 * @param station
	 * @param g2
	 */
	private static void printStation(Station station, Graphics2D g2){
		int size = SIZE_STATION/2 + (station.getCrowdLevel() * SIZE_STATION)/100;
		g2.setColor(STATION_COLOR);
		g2.setFont(DEFAULT_FONT);
		g2.setStroke(new BasicStroke(STATION_STROKE));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); // Opacity of 100%
		
		g2.fillOval(LINE_X + station.getPosition() - size/2, LINE_Y - size/2, size, size);
		int widthS = g2.getFontMetrics().stringWidth(station.getName());
		g2.drawString(station.getName(), LINE_X + station.getPosition() - widthS/2, LINE_Y - 2 * SIZE_STATION);
		printInformationStation(station, g2);
	}
	
	/**
	 * Draw the trains
	 * @param trains a list of trains
	 * @param g2
	 */
	public static void printTrains(ConcurrentLinkedQueue<Train> trains, Graphics2D g2){
		for (Train train : trains){
			switch (train.getType()){
				case Train.SHORT_TYPE : 	g2.setColor(SHORT_TRAIN_COLOR); 
											g2.setStroke(new BasicStroke(SHORT_TRAIN_STROKE));
											break;
				case Train.LONG_TYPE : 		g2.setColor(LONG_TRAIN_COLOR); 
											g2.setStroke(new BasicStroke(LONG_TRAIN_STROKE));
											break;
				case Train.RESERVE_TYPE : 	g2.setColor(RESERVE_TRAIN_COLOR); 
											g2.setStroke(new BasicStroke(RESERVE_TRAIN_STROKE));
											break;
				default : Logger.getAnonymousLogger().info("Unknow type of train"); return;
			}
			
			// We draw the line that represents the train
			g2.drawLine(LINE_X + train.getCurrentPosition(), LINE_Y - SIZE_STATION/4, 
						LINE_X + train.getCurrentPosition(), LINE_Y + SIZE_STATION/4
			);
			
			// We display the number of passengers
			g2.setColor(Color.BLACK);
			g2.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
			String values = train.getCurrentPassenger() + "/" + train.getMaxPassengers();
			int widthVal = g2.getFontMetrics().stringWidth(values);
			g2.drawString(values, LINE_X + train.getCurrentPosition() - widthVal/2, LINE_Y - (2 * SIZE_STATION)/3);
		}
	}
	
	/**
	 * Print an incident
	 * @param incident
	 * @param g2
	 */
	private static void printIncident(Incident incident, Graphics2D g2){
		int dim = SIZE_STATION/4;
		int location = incident.getLocation();
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(INCIDENT_STROKE));
		g2.drawLine(LINE_X + location - dim, LINE_Y - dim, LINE_X + location + dim, LINE_Y + dim);
		g2.drawLine(LINE_X + location + dim, LINE_Y - dim, LINE_X + location - dim, LINE_Y + dim);
	}
	
	/**
	 * Draw the station informations
	 * @param station
	 * @param g2
	 */
	private static void printInformationStation(Station station, Graphics2D g2){
		int width = 100;
		int height = 135;
		final int marginTop = 20;
		final int posX = LINE_X + station.getPosition() - width/2;
		final int posY = LINE_Y + 3 * SIZE_STATION;
		Font font = new Font(Font.DIALOG, Font.BOLD, 11);
		
		// ------------------------------------------------------------- //
		
		// We draw the rect that contains informations
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1));
		g2.drawRect(posX, posY, width, height);
		
		// We draw the name of station
		g2.setFont(font);
		int widthName = g2.getFontMetrics().stringWidth(station.getName());
		g2.drawString(station.getName(), LINE_X + station.getPosition() - widthName/2, posY + marginTop);
		
		// ------------------------------------------------------------- //
		
		// Draw the bar that illustrate the numbers about the station
		int widthBar = 20;
		int heightBar = 60;
		int marginBar = (width/2 - widthBar)/2;
		
		// Bar showing the current number of passengers into the station
		drawBar("Pass.", posX + marginBar, posY + 2 * marginTop, widthBar, heightBar, 
				station.getCurrentPassenger(), station.getMaxPassengers(), new Color(26, 188, 156), g2);
		
		// Bar showing the current satisfaction level of the station
		drawBar("Satis.", posX + width/2 + marginBar, posY + 2 * marginTop, widthBar, heightBar, 
				station.getSatisfaction(), 100, new Color(203, 67, 53), g2);
	}
	
	/**
	 * Draw a bar that represents a value
	 * @param name of the bar
	 * @param x of the bar
	 * @param y of the bar
	 * @param widthBar the width of the bar
	 * @param heightBar the height the bar
	 * @param valueBar the current value display with the bar
	 * @param valueMaxBar the max value that we can display
	 * @param colorBar the color of the bar
	 * @param g2
	 */
	private static void drawBar(String name, int x, int y, int widthBar, int heightBar, int valueBar, 
			int valueMaxBar, Color colorBar, Graphics2D g2){

		// We draw the value bar (corresponding of the current number of travelers into the station)
		g2.setColor(colorBar);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Opacity of 50%
		int heightPxl = (valueBar * heightBar) / valueMaxBar;
		g2.fillRect(x, y + (heightBar - heightPxl), widthBar, heightPxl);
		
		// ------------------------------------------------------------- //
		
		// A background for having a good effect
		g2.setColor(Color.BLACK);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f)); // Opacity of 50%
		g2.fillRect(x, y, widthBar, heightBar);
		
		// ------------------------------------------------------------- //
		
		// Draw the string
		g2.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); // No opacity
		int widthName = g2.getFontMetrics().stringWidth(name);
		g2.drawString(name, x + widthBar/2 - widthName/2, y + heightBar + 15);
		
		// ------------------------------------------------------------- //
		
		// Draw the value of bar
		g2.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		String values = valueBar + "/" + valueMaxBar;
		int widthVal = g2.getFontMetrics().stringWidth(values);
		g2.drawString(values, x + widthBar/2 - widthVal/2, y + heightBar + 30);
		
	}
}
