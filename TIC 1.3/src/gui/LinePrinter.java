package gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.List;

import core.entity.Canton;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;
import static gui.GUIConstants.*;

/**
 * Draw elements that a line is composed
 * 
 * @author Maxime
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Thomas Re
 * @author Vincent Virole
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
		g2.drawLine(LINE_X, LINE_Y - 10, LINE_X + line.getLength(), LINE_Y - 10);
		g2.drawLine(LINE_X, LINE_Y + 10, LINE_X + line.getLength(), LINE_Y + 10);
		line.getCantons().forEach(canton -> printCanton(canton, g2));
	}
	
	/**
	 * Print a canton
	 * @param canton
	 * @param g2
	 */
	private static void printCanton(Canton canton, Graphics2D g2){
		g2.setColor(CANTON_COLOR);
		g2.setStroke(new BasicStroke(3));
		String s = "Canton " + canton.getId(); 
		int widthS = g2.getFontMetrics().stringWidth(s);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); // Opacity of 100%
		int length = canton.getLength();
		int nbline = length / 10 ;
		for(int i=0; i < nbline; i++) {
			g2.drawLine(LINE_X + canton.getStartPoint() + i*10, LINE_Y - 14, 
						LINE_X + canton.getStartPoint() + i*10, LINE_Y + 14
						);
		}
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
		g2.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1));
		g2.setStroke(new BasicStroke(5));
		g2.drawOval(LINE_X + station.getPosition() - 5, LINE_Y - 10, 10, 20);
		int widthS = g2.getFontMetrics().stringWidth(station.getName());
		g2.drawString(station.getName(), LINE_X + station.getPosition() - widthS/2, LINE_Y - 2 * SIZE_STATION);
		printInformationStation(station, g2);
	}
	
	/**
	 * Draw the trains
	 * @param trains a list of trains
	 * @param g2
	 */
	public static void printTrains(List<Train> trains, Graphics2D g2){
		g2.setColor(GUIConstants.TRAIN_COLOR);
		g2.setStroke(new BasicStroke(5));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); // Opacity of 100%
		for (Train train : trains){
			g2.drawLine(LINE_X + train.getCurrentPosition(), LINE_Y - SIZE_STATION/3, 
						LINE_X + train.getCurrentPosition(), LINE_Y + SIZE_STATION/3
			);
		}
	}
	
	
	/**
	 * Draw Station info
	 * @param station
	 * @param g2
	 */
	public static void printInformationStation(Station station, Graphics2D g2){
		int i = 0 ;
		g2.setColor(GUIConstants.LINE_COLOR); 
		g2.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		if(i%2 == 0) {
			g2.drawString("Nbr passager : " + station.getCurrentPassenger(), station.getPosition() - 20, 
																			LINE_Y + SIZE_STATION*5 -10);
		}
		else {
			g2.drawString("Nbr passager : " + station.getCurrentPassenger(), station.getPosition(), 
																			LINE_Y + SIZE_STATION*5 +10);
		}
		i++;
		
		g2.setColor(GUIConstants.SATISFACTION);
		g2.setStroke(new BasicStroke(20));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1)); // Opacity of 100%
		g2.drawLine(LINE_X + station.getPosition(), LINE_Y + SIZE_STATION*4 + 150 - station.getSatisfaction(), 
					LINE_X + station.getPosition(), LINE_Y + SIZE_STATION*4 + 150
					); 
		
		g2.setColor(GUIConstants.BACKGROUND_SATISFACTION);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f)); // Opacity of 20%
		g2.drawLine(LINE_X + station.getPosition(), LINE_Y + SIZE_STATION*4 + 50, 
					LINE_X + station.getPosition(), LINE_Y + SIZE_STATION*4 + 150
					); 
		
	}
	
}
