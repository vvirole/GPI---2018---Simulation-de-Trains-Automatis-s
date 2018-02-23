package core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import core.entity.Canton;
import core.entity.Line;
import core.entity.Station;
import core.xml.UnvalidFileException;
import core.xml.XMLParser;
import gui.GUIConstants;

/**
 * Build the line according to the informations stored in a XML file
 * 
 * @author Maxime
 *
 */
public class LineBuilder {
	
	// We instanciate a new XML parser that allows to get datas of line
	private static XMLParser parser;
	
	/**
	 * Build a line thanks to a XML file
	 * @param file the XML file
	 * @throws UnvalidFileException 
	 */
	public static void buildLine(File file) throws UnvalidFileException {
		
		 parser = new XMLParser(file);
		 
		 if (!parser.getRootTextNode().equals("line")){
			 throw new UnvalidFileException();
		 }
		
		// We get the informations of stations and cantons
		Map<String, Map<String, String>> cantonsData = parser.getListInfos("canton"); 
		Map<String, Map<String, String>> stationsData = parser.getListInfos("station"); 
		
		// List of cantons
		List<Canton> cantonList = new ArrayList<Canton>();
		
		// The length of the line
		int usedLength = 0;
		
		for(Entry<String, Map<String, String>> entry : cantonsData.entrySet()){
			
			Map<String, String> cantonInfos = entry.getValue();
			String idCanton = entry.getKey();
			int lengthCanton = Integer.parseInt(cantonInfos.get("length"));
			
			// We consider that for each canton, there is one station having the same id
			Map<String, String> stationInfos = stationsData.get(entry.getKey());
			
			// Position of the station is at the end of the canton
			int positionStation = usedLength + lengthCanton;		
			Station station = new Station(
							stationInfos.get("nameStation"),
							positionStation,
							Integer.parseInt(stationInfos.get("maxPassengers")),
							Integer.parseInt(stationInfos.get("reserveTrain")),
							Integer.parseInt(stationInfos.get("crowdLevel"))
			);
			
			// We add the canton to the line
			cantonList.add(new Canton(idCanton, usedLength, lengthCanton, station));
			// We update the length used
			usedLength += lengthCanton;
		}
		
		// We create the line and add the cantons
		String nameLine = parser.getTextNode("nameLine");
		Line line = new Line(nameLine, usedLength);
		//cantonList.forEach(canton -> line.addCanton(canton));
		for (Canton canton : cantonList){
			line.addCanton(canton);
		}
		
		// Adjustment of the line according to the constants LINE_LENGTH defined in Constants.java
		double adjust = (double) GUIConstants.LINE_LENGTH / (double) usedLength;
		Line.setInstance(adjustLines(line, adjust));
	}
	
	/**
	 * Adjust the dimensions of line
	 * @param line
	 * @param delta
	 * @return the resized line
	 */
	public static Line adjustLines(Line line, double delta){	
		int usedLength = 0;
		
		for (Canton canton : line.getCantons()){	
			int newLength = (int) Math.ceil((double) canton.getLength() * delta);	
			int newPosition = usedLength + newLength;		
			Station oldStation = canton.getStation();
			Station adjustStation = new Station(oldStation.getName(), 
											newPosition,
											oldStation.getMaxPassengers(),
											oldStation.getNumReserveTrain(),
											oldStation.getCrowdLevel()
									);
			canton.setLength(newLength);
			canton.setStartPoint(usedLength);
			canton.setStation(adjustStation);		
			usedLength += newLength;
		}
		
		line.setLength(usedLength);
		return line;
	}
}
