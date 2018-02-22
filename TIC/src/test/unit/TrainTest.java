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
	 *  Vérification que le train change bien de position durant son temps de run
	 */
	@Test
	public void testRun() {
		Train train = new Train(canton, 0, 0, 0);
		train.setSpeed(50);
		train.run();
		assertTrue(train.getCurrentPosition() >= 0);
	}
	
	/** vérification que le train ne change pas de position si il y a un accident sur son canton**/
	/** voir comment limiter le nombre de boucle de train.run**/
	/**@Test
	public void testRunWithIncident() {
		Train train = new Train(canton, 0, 0, 0);
		train.setSpeed(50);
		canton.setIncident(true);
		train.run();
		assertTrue(train.getCurrentPosition()==0);
	}**/

	/**
	 * Vérification du nombre de passager qui ne peuvent pas rentrer dans le train si il n'y a pas de destination (derniere station)
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
	 * Vérification du nombre de passager qui ne peuvent pas rentrer dans le train si le nombre d'arrivant est pile le bon
	 */
	@Test
	public void testAddPassengersFull() {
		Train train = new Train(canton, 20, 0, 0);
		assertTrue(train.addPassengers(30) == 0);
		assertTrue(train.getCurrentPassenger() == 50);
	}
	
	/**
	 * Vérification du nombre de passager qui ne peuvent pas rentrer dans le train si le nombre d'arrivant est un nombre au hasard
	 */
	@Test
	public void testAddPassengers() {
		Train train = new Train(canton, 20, 0, 0);
		assertTrue(train.addPassengers(40) == 10);
		assertTrue(train.getCurrentPassenger() == 50);
	}

	/**
	 * Vérification du changement de position du train en fonction de sa vitesse
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
	
	@Test
	public void testGetOffPassengers() {
		station = canton.getStation();
		Train train = new Train(canton, 20, 50, 0);
		train.getOffPassengers(station);
		assertTrue(train.getCurrentPassenger() <= 20);
	}

}
