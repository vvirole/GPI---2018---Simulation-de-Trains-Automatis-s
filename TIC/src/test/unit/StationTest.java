package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.Constants;
import core.LineBuilder;
import core.TerminusException;
import core.entity.Canton;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;
import core.xml.UnvalidFileException;

public class StationTest {

	private Line line;
	private Canton canton;
	private Station station;

	
	@Before
	public void linePreparation() throws UnvalidFileException{
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		line = Line.getInstance();
		canton = line.getCanton(0);
	}
	
	/**
	 *  vérification qu'en cas d'arriver de train vide, la station diminue en passager
	 * @throws TerminusException
	 */
	@Test
	public void testEnter() throws TerminusException {
		station = canton.getStation();
		Train train = new Train(canton, 0, 0, 0);
		station.enter(train);
		assertTrue(station.getCurrentPassenger() <= Constants.INITIAL_PASSENGER_STATION);
	}

	/**
	 *  Vérification qu'en cas de sortie du train de la Station, il n'y a bien plus de train dans la station
	 * @throws TerminusException
	 */
	@Test
	public void testExit() throws TerminusException {
		station = canton.getStation();
		Train train = new Train(canton, 0, 0, 0);
		station.enter(train);
		station.exit();
		assertTrue(station.getTrain() == null);
	}

	/*@Test
	public void testUpdatePassengers() {
		
	}*/

}
