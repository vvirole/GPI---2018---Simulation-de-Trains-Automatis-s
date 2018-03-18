package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.*;
import core.entity.*;
import core.xml.UnvalidFileException;

/**
 * @author RE Thomas
 */

/** 
 * in the class "Canton" put in the function "enter" a value to wait otherwise the test loop to infinity.
 */

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
	 * Verifying that a canton is no longer free if a train is in it
	 * put a value to the wait
	 */
	@Test
	public void testEnter() {
		Train train = new Train(canton, 0, 0, 0);
		Train train2 = new Train(canton, 0, 0, 0);
		assertTrue(canton2.isFree());
		canton2.enter(train);
		assertFalse(canton2.isFree());
		//canton2.setOccupyingTrain(null);
		canton2.enter(train2);
		assertTrue(train2.getCurrentPosition()==canton2.getStartPoint());
		assertTrue(train.getCurrentCanton().getId()!=train2.getCurrentCanton().getId());
	}
	
	/**
	 * Verifying that a canton will become free again if the train that occupies it goes away
	 */
	@Test
	public void testExit() {
		Train train = new Train(canton, 0, 0, 0);
		canton2.enter(train);
		canton2.exit();
		assertTrue(canton2.isFree());
	}

	/**
	 * Verifying that in the event of an accident, the canton takes it into account
	 */
	@Test
	public void testHasIncident() {
		boolean incident = true;
		canton.setIncident(incident);
		assertTrue(canton.hasIncident());
	}


}
