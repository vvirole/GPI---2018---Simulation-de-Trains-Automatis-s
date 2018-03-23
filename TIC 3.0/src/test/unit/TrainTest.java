package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.entity.Canton;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;
import core.xml.UnvalidFileException;

/**
 * @author RE Thomas
 */

public class TrainTest {

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
	 *  Verifying that the train changes position during its run time
	 */
	@Test
	public void testRun() {
		Train train = new Train(canton, 0, 0, 0);
		train.setSpeed(50);
		train.run();
		assertTrue(train.getCurrentPosition() >= 0);
	}

	/**
	 * Verifying the number of passengers who can not get on the train if there is no destination (last station)
	 */
	@Test
	public void testAddPassengersNoDestination() {
		Train train = new Train(canton, 20, 0, 0);
		for (Station station : line.getStationList()){
			train.removeDestination(station);
		}
		assertTrue(train.addPassengers(30) == 30);
	}
	
	/**
	 * Verifying the number of passengers who can not get on the train if the number of arrivals is right
	 */
	@Test
	public void testAddPassengersFull() {
		Train train = new Train(canton, 20, 0, 0);
		assertTrue(train.addPassengers(30) == 0);
		assertTrue(train.getCurrentPassenger() == 50);
	}
	
	/**
	 * Verifying the number of passengers who can not get on the train if the number of arrivals is a random number
	 */
	@Test
	public void testAddPassengers() {
		Train train = new Train(canton, 20, 0, 0);
		assertTrue(train.addPassengers(40) == 0);
		assertTrue(train.getCurrentPassenger() == 60);
	}

	/**
	 * Verifying the position change of the train according to its speed
	 */
	@Test
	public void testUpdatePosition() {
		Train train = new Train(canton, 20, 0, 0);
		for (int i = 0; i <= 10 ; i++){
			train.setSpeed(i);
			train.setCurrentPosition(0);
			train.updatePosition();
			assertTrue(train.getCurrentPosition() == i);
		}
	}
	
	/**
	 * Verifying the number of passengers in a train decreases well
	 */
	@Test
	public void testGetOffPassengers() {
		station = canton.getStation();
		Train train = new Train(canton, 20, 50, 0);
		train.getOffPassengers(station);
		assertTrue(train.getCurrentPassenger() <= 20);
	}

}
