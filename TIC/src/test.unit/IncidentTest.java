package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;

public class IncidentTest {
	
	Line line = new Line(null, 0, 0);
	Station station = new Station(null, 0, 0, 0, 0);
	Canton canton = new Canton(0, 0, 0, station);
	Train train = new Train(canton, 0, 0, 0);
	Incident incident = new Incident(0, 0);
	
	@Test
	public void testGetTimeToResolve() {
		/** test en période creuse**/
		assertTrue("le temps de résolution est inférieur à 50 cycle",incident.getTimeToResolve(Line.PERIOD_VOID)<=50);
		assertTrue("le temps de résolution est supérieur à 25 cycle",incident.getTimeToResolve(Line.PERIOD_VOID)>=25);
		/** test en période normal**/
		assertTrue("le temps de résolution est supérieur à 25 cycle",incident.getTimeToResolve(Line.PERIOD_NORMAL)>=25);
		assertTrue("le temps de résolution est inférieur à 75 cycle",incident.getTimeToResolve(Line.PERIOD_NORMAL)<=75);
		/** test en période pleine**/
		assertTrue("le temps de résolution est supérieur à 50 cycle",incident.getTimeToResolve(Line.PERIOD_FULL)>=50);
		assertTrue("le temps de résolution est inférieur à 75 cycle",incident.getTimeToResolve(Line.PERIOD_FULL)<=75);
	}

}
