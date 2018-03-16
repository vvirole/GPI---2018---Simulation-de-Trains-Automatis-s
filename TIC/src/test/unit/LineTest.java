package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.LineController;
import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;
import core.xml.UnvalidFileException;


/**
 * @author RE Thomas
 */

public class LineTest{
	
	private Line line;
	private Canton canton, canton2;
	Canton canton3 = new Canton(null, 0, 0, null); 

	@Before
	public void linePreparation() throws UnvalidFileException {
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		line = Line.getInstance();
		canton = line.getCanton(0);
		canton2 = line.getCanton(1);
	}
	
	/**
	 *  Verification that in the event of an infrastructure accident on the line in normal period, this one realizes it
	 */
	@Test
	public void testHasIncidentInfrastructurePeriodeNormal() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		line.setPeriod("normale");
		assertTrue(line.hasIncident());	
	}
	
	/**
	 *  Verification that in the event of an infrastructure accident on the line in void period, this one realizes it
	 */
	@Test
	public void testHasIncidentInfrastructurePeriodeVoid() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		line.setPeriod("creuse");
		assertTrue(line.hasIncident());	
	}
	
	/**
	 *  Verification that in the event of an infrastructure accident on the line in full period, this one realizes it
	 */
	@Test
	public void testHasIncidentInfrastructurePeriodeFull() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		line.setPeriod("pleine");
		assertTrue(line.hasIncident());	
	}
	
	
	/**
	 * Verification that in the event of a passenger accident on the line, it is well aware
	 */
	@Test
	public void testHasIncidentPassager() {
		line.newIncident(canton, Incident.PASSENGER_INCIDENT);
		assertTrue(line.hasIncident());	
	}

	/**
	 * Verification that in the event of an accident on a given block, the line is able to identify which
	 */
	@Test
	public void testHasIncidentCanton() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		assertTrue(line.hasIncident(canton));
		assertFalse(line.hasIncident(canton2));
	}
		
	/**
	 *  Verification that in case of addition of train on the line, it is on the line
	 */
	@Test
	public void testAddTrain() {
		Train train = new Train(canton, 0, 0, 0);
		line.addTrain(train);
		assertEquals(line.getTrains().element(), train);
	}

	/**
	 *  Verification that in case of addition of canton on the line, it is on the line
	 */
	@Test
	public void testAddCanton() {
		line.addCanton(canton3);
		assertTrue(line.getCantons().contains(canton3));
	}

	/**
	 *  Verification that if the software is running, the line is working
	 */
	@Test
	public void testIsWorking() {
		LineController lineController = new LineController(100);
		lineController.run();
		lineController.stopTrains();
		assertTrue(line.isWorking());
	}

	/**
	 *  Verification that the line has the right number of passengers
	 */
	@Test
	public void testGetTotalPassenger() {
		for (Station station : line.getStationList()){
			station.setCurrentPassenger(0);
		}
		Station station = canton.getStation();
		station.setCurrentPassenger(200);
		assertTrue(line.getTotalPassengers()==200);
		Train train = new Train(canton, 100, 0, 0);
		line.addTrain(train);
		assertTrue(line.getTotalPassengers()==300);
	}
}
