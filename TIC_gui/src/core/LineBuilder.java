package core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import core.controller.Canton;
import core.controller.Line;
import core.controller.Station;
import core.dom.XMLParser;

public class LineBuilder {
	
	// We instanciate a new XML parser that allows to get datas of line
	private static XMLParser parser;
			
	// The length of the line
	private static int lengthLine = 0;
	
	/**
	 * Build a line thanks to a XML file
	 * @param file the XML file
	 */
	public static void buildLine(File file) {
		
		 parser = new XMLParser(file);
		
		// We get the informations of stations and cantons
		Map<String, Map<String, String>> cantonsData = parser.getListInfos("canton"); 
		Map<String, Map<String, String>> stationsData = parser.getListInfos("station"); 
		
		// List of cantons
		List<Canton> cantonList = new ArrayList<Canton>();
		
		// We get the numbers of cantons
		int numCantons = parser.getCount("cantons");
		
		for(Entry<String, Map<String, String>> entry : cantonsData.entrySet()){
			
			Map<String, String> cantonInfos = entry.getValue();
			int idCanton = Integer.parseInt(entry.getKey());
			int lengthCanton = Integer.parseInt(cantonInfos.get("length"));
			
			// We consider that for each canton, there is one station having the same id
			Map<String, String> stationInfos = stationsData.get(entry.getKey());
			
			Station station = new Station(
							Integer.parseInt(stationInfos.get("maxPassengers")),
							Integer.parseInt(stationInfos.get("reserveTrain")),
							Integer.parseInt(stationInfos.get("crowdLevel"))
			);
			
			lengthLine += lengthCanton;
			
			// We add the canton to the line
			cantonList.add(new Canton(idCanton, (lengthLine - lengthCanton), lengthCanton, station));
		}
		
		// We create the line and add the cantons
		Line line = new Line(lengthLine, numCantons);
		cantonList.forEach(canton -> line.addCanton(canton));
		Line.setInstance(line);
	}
}
