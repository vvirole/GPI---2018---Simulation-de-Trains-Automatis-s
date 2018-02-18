package gui;

import static gui.GUIConstants.LINE_COLOR;
import static gui.GUIConstants.LINE_X;
import static gui.GUIConstants.LINE_Y;
import static gui.GUIConstants.SIZE_STATION;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;

/**
 * Draw elements that a line is composed
 * 
 * @author Maxime
 *
 */
public class LinePrinter {
	
	/**
	 * Print the line
	 * @param line
	 * @param g2
	 */
	public static void printLine(Line line, Graphics2D g2){
		g2.setColor(GUIConstants.LINE_COLOR);
		g2.setStroke(new BasicStroke(GUIConstants.LINE_STROKE));
		g2.drawLine(LINE_X, LINE_Y, LINE_X + line.getLength(), LINE_Y);
		line.getCantons().forEach(canton -> printCanton(canton, g2));
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
		g2.setColor(LINE_COLOR);
		String s = "Canton " + canton.getId(); 
		int widthS = g2.getFontMetrics().stringWidth(s);
		g2.drawString(s, LINE_X + canton.getStartPoint() + canton.getLength()/2 - widthS/2, LINE_Y + 2 * SIZE_STATION);
		printStation(canton.getStation(), g2);
	}
	
	/**
	 * Print a station
	 * @param station
	 * @param g2
	 */
	private static void printStation(Station station, Graphics2D g2){
		g2.setColor(GUIConstants.STATION_COLOR);
		g2.setStroke(new BasicStroke(GUIConstants.STATION_STROKE));
		g2.fillOval(LINE_X + station.getPosition() - SIZE_STATION/2, LINE_Y - SIZE_STATION/2, SIZE_STATION, SIZE_STATION);
		int widthS = g2.getFontMetrics().stringWidth(station.getName());
		g2.drawString(station.getName(), LINE_X + station.getPosition() - widthS/2, LINE_Y - 2 * SIZE_STATION);
	}
	
	/**
	 * Draw the trains
	 * @param trains a list of trains
	 * @param g2
	 */
	public static void printTrains(List<Train> trains, Graphics2D g2){
		for (Train train : trains){
			switch (train.getType()){
				case Train.SHORT_TYPE : 	g2.setColor(GUIConstants.SHORT_TRAIN_COLOR); 
											g2.setStroke(new BasicStroke(GUIConstants.SHORT_TRAIN_STROKE));
											break;
				case Train.LONG_TYPE : 		g2.setColor(GUIConstants.LONG_TRAIN_COLOR); 
											g2.setStroke(new BasicStroke(GUIConstants.LONG_TRAIN_STROKE));
											break;
				case Train.RESERVE_TYPE : 	g2.setColor(GUIConstants.RESERVE_TRAIN_COLOR); 
											g2.setStroke(new BasicStroke(GUIConstants.RESERVE_TRAIN_STROKE));
											break;
				default : Logger.getAnonymousLogger().info("Unknow type of train"); return;
			}
			g2.drawLine(LINE_X + train.getCurrentPosition(), LINE_Y - SIZE_STATION/4, 
						LINE_X + train.getCurrentPosition(), LINE_Y + SIZE_STATION/4
			);
		}
	}
	
	/**
	 * Print an incident
	 * @param incident
	 * @param g2
	 */
	private static void printIncident(Incident incident, Graphics2D g2){
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(GUIConstants.LINE_STROKE));
		int dim = SIZE_STATION/4;
		int location = incident.getLocation();
		g2.drawLine(LINE_X + location - dim, LINE_Y - dim, LINE_X + location + dim, LINE_Y + dim);
		g2.drawLine(LINE_X + location + dim, LINE_Y - dim, LINE_X + location - dim, LINE_Y + dim);
	}
}
