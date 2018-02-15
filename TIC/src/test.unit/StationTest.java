package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;

public class StationTest {

	Line line = new Line(null, 0, 0);
	Station station = new Station(null, 0, 0, 0, 0);
	Canton canton = new Canton(0, 0, 0, station);
	Train train = new Train(canton, 0, 0, 0);
	Incident incident = new Incident(0, 0);
	
	@Test
	public void testEnter() {
		
	}

	@Test
	public void testExit() {
		
	}
	
	/** test de présence d'incident a la creation d'une station**/
	@Test
	public void testIsAccident() {
		assertTrue(station.isAccident()==false);
	}

}
