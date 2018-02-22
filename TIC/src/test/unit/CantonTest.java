package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.*;
import core.entity.*;
import core.xml.UnvalidFileException;


public class CantonTest {
	
	
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
	 * Vérification qu'un canton n'est plus libre si un train est dedans
	 */
	@Test
	public void testEnter() {
		Train train = new Train(canton, 0, 0, 0);
		canton2.enter(train);
		assertFalse(canton2.isFree());
	}
	
	/**
	 * Vérification qu'un canton redevient libre si le train qui l'occupé s'en va
	 */
	@Test
	public void testExit() {
		Train train = new Train(canton, 0, 0, 0);
		canton2.enter(train);
		canton2.exit();
		assertTrue(canton2.isFree());
	}

	/**
	 * Vérification qu'en cas d'accident, le canton en prend bien compte
	 */
	@Test
	public void testHasIncident() {
		boolean incident = true;
		canton.setIncident(incident);
		assertTrue(canton.hasIncident());
	}


}
