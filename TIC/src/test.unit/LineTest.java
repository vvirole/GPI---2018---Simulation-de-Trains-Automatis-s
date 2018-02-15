package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;

public class LineTest {
	
	Line line = new Line(null, 0, 0);
	Station station = new Station(null, 0, 0, 0, 0);
	Canton canton = new Canton(0, 0, 0, station);
	Train train = new Train(canton, 0, 0, 0);
	Incident incident = new Incident(0, 0);
	
	@Test
	public void testAddCanton() {
		line.addCanton(canton);
		assertTrue(line.getCanton(0)==canton);
	}

	@Test
	public void testAddTrain() {
		line.addTrain(train);
		assertTrue(line.getTrains()==train);
	}

	@Test
	public void testAddIncident() {
		line.addIncident(incident);
		assertTrue(line.getIncidents()==incident);
	}

	@Test
	public void testIsWorking() {
		assertTrue(line.isWorking()==false);
	}

}
