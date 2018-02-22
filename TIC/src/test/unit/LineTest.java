package test.unit;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.LineController;
import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Train;
import core.xml.UnvalidFileException;


public class LineTest {
	
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
	 *  Vérification qu'en cas d'accident d'infrastructure sur la ligne en periode normale, celle-ci s'en rends bien compte
	 */
	@Test
	public void testHasIncidentInfrastructurePeriodeNormal() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		line.setPeriod("normale");
		assertTrue(line.hasIncident());	
	}
	
	/**
	 *  Vérification qu'en cas d'accident d'infrastructure sur la ligne en periode normale, celle-ci s'en rends bien compte
	 */
	@Test
	public void testHasIncidentInfrastructurePeriodeVoid() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		line.setPeriod("creuse");
		assertTrue(line.hasIncident());	
	}
	
	/**
	 *  Vérification qu'en cas d'accident d'infrastructure sur la ligne en periode normale, celle-ci s'en rends bien compte
	 */
	@Test
	public void testHasIncidentInfrastructurePeriodeFull() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		line.setPeriod("pleine");
		assertTrue(line.hasIncident());	
	}
	
	
	/**
	 * Vérification qu'en cas d'accident de passager sur la ligne, celle-ci s'en rends bien compte**/
	@Test
	public void testHasIncidentPassager() {
		line.newIncident(canton, Incident.PASSENGER_INCIDENT);
		assertTrue(line.hasIncident());	
	}

	/**
	 * Vérification qu'en cas d'accident sur un canton donné, la ligne est capable d'identifier lequel
	 */
	@Test
	public void testHasIncidentCanton() {
		line.newIncident(canton, Incident.INFRASTRUCTURE_INCIDENT);
		assertTrue(line.hasIncident(canton));
		assertFalse(line.hasIncident(canton2));
	}
		
	/**
	 *  Vérification qu'en cas de rajout de train sur la ligne, celui-ci est bien sur la ligne
	 */
	@Test
	public void testAddTrain() {
		Train train = new Train(canton, 0, 0, 0);
		line.addTrain(train);
		assertEquals(line.getTrains().element(), train);
	}

	/**
	 *  Vérification qu'en cas de rajout de canton sur la ligne, celui-ci est bien sur la ligne
	 */
	@Test
	public void testAddCanton() {
		line.addCanton(canton3);
		assertTrue(line.getCantons().contains(canton3));
	}

	/**
	 *  Vérification qu'en cas de mise en marche du logiciel, la ligne est bien en fonctionnement
	 */
	@Test
	public void testIsWorking() {
		LineController lineController = new LineController(100);
		lineController.run();
		lineController.stopTrains();
		assertFalse(line.isWorking());
	}

}
