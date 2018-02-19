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


public class LineTest implements Observer{
	
	private Line line;
	private Canton canton, canton2;
	Canton canton3 = new Canton(null, 0, 0, null); 

	@Before
	public void linePreparation() throws UnvalidFileException{
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		line = Line.getInstance();
		canton = line.getCanton(0);
		canton2 = line.getCanton(1);
	}
	
	/** vérification qu'en cas d'accident d'infrastructure sur la ligne, celle-ci s'en rends bien compte**/
	@Test
	public void testHasIncidentInfrastructure() {
		line.newIncident(canton, 0);
		assertTrue(line.hasIncident());	
	}
	
	/** vérification qu'en cas d'accident de passager sur la ligne, celle-ci s'en rends bien compte**/
	@Test
	public void testHasIncidentPassager() {
		line.newIncident(canton, 1);
		assertTrue(line.hasIncident());	
	}

	/** vérification qu'en cas d'accident sur un canton donné, la ligne est capable d'identifier lequel**/
	@Test
	public void testHasIncidentCanton() {
		line.newIncident(canton, 1);
		assertTrue(line.hasIncident(canton));
		assertFalse(line.hasIncident(canton2));
	}
	
	/**@Test
	public void testRemoveIncident() {
		line.newIncident(canton, 0);
		
		line.removeIncident(incident);
	}**/

	/**@Test
	public void testListIncidents() {
		fail("Not yet implemented");
	}**/

	
	/** vérification qu'en cas de rajout de train sur la ligne, celui-ci est bien sur la ligne**/
	@Test
	public void testAddTrain() {
		Train train = new Train(canton, 0, 0, 0);
		line.addTrain(train);
		assertTrue(train == line.getTrains().element());
	}

	/** vérification qu'en cas de rajout de canton sur la ligne, celui-ci est bien sur la ligne **/
	@Test
	public void testAddCanton() {
		line.addCanton(canton3);
		assertTrue(line.getCantons().contains(canton3));
	}

	/** vérification qu'en cas de mise en marche du logiciel, la ligne est bien en fonctionnement**/
	/** pour un test plus rapide, initialiser GUIConstants.MAX_DURATION à 100 au lieu de 10000**/
	@Test
	public void testIsWorking() {
		LineController lineController = new LineController(this);
		lineController.run();
		assertTrue(line.isWorking());
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub	
	}

}
