package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.LineController;
import core.entity.Canton;
import core.entity.Line;
import core.xml.UnvalidFileException;
import core.Constants;
public class LineControllerTest {

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
	 * vérification de la bonne résolution des accidents si il en a le temps
	 */
	@Test
	public void testRun() {
		Constants.INCIDENT_RATIO=0;
		for (int i=0; i<=10; i++){
			line.newIncident(canton, 0);
			LineController lineController = new LineController(300);
			lineController.run();
			assertTrue(line.hasIncident(canton)==false);
			line.newIncident(canton2, 0);
			lineController.setDuration(100);
			lineController.run();
			assertFalse(line.hasIncident(canton2)==false);
		}
	}



}
