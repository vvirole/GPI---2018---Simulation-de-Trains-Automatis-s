package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.LineController;
import core.entity.Canton;
import core.entity.Line;
import core.entity.Station;
import core.utility.Clock;
import core.xml.UnvalidFileException;
import gui.GUIConstants;
import core.Constants;
public class LineControllerTest {

/**
 * @author RE Thomas
 */
	
	private Line line;
	private Canton canton, canton2;
	
	@Before
	public void linePreparation() throws UnvalidFileException{
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		line = Line.getInstance();
		canton = line.getCanton(0);
		canton2 = line.getCanton(1);
	}
	
	/**
	 * verifying the correct resolution of accidents if he has the time to do it
	 */
	@Test
	public void testIncidentResolution() {
		line.newIncident(canton, 0);
		for (int i = 0 ; i < Constants.DEFAULT_INCIDENT_RESOLUTION_TIME * 2 ; i++){
			line.resolveIncident();
		}
	}
	
	/**
	 * verifying the running of the simulation with the controller
	 */
	@Test
	public void testRun(){
		LineController controller = new LineController(1000);
		Clock clock = controller.getClock();
		clock.setRunning(true);
		controller.run();
		assertTrue(!clock.isRunning());
	}
	
	@Test
	public void testThrowReserveTrain(){
		LineController controller = new LineController(1000);
		Clock clock = controller.getClock();
		clock.setRunning(true);
		Station station = canton.getStation();
		int nbReserveTrainBefore = station.getNumReserveTrain();
		controller.throwReserveTrain(canton);
		assertTrue(nbReserveTrainBefore >= station.getNumReserveTrain());
		
		
	}
}
