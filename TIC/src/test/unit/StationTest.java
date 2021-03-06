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

/**
 * @author RE Thomas
 */

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
	 *  Verifying that in case of arrival of empty train, the station decreases in passenger
	 * @throws TerminusException
	 */
	@Test
	public void testEnter() throws TerminusException {
		station = canton.getStation();
		Train train = new Train(canton, 0, 100, 0);
		Train train2 = new Train(canton, 0, 100, 0);
		Train train3 = new Train(canton, 0, 0, 0);
		station.enter(train);
		assertTrue(station.getCurrentPassenger() <= Constants.INITIAL_PASSENGER_STATION); /** premiere diminution avec le premier train */
		int currentPassenger = station.getCurrentPassenger();
		station.enter(train2);
		assertTrue(station.getCurrentPassenger() <= currentPassenger); /** deuxieme diminution avec le second train */
		station.exit();
		canton.setIncident(true);
		station.enter(train3);
		assertTrue(train3.getCurrentCanton()==canton); /** en cas d'accident sur le prochain canton le train n'y vas pas */
	}
	
	
	/**
	 *  Verifying that if there is a train in the station when there is already a train in it, the first one can not go there
	 * @throws TerminusException
	 */
	@Test
	public void testEnterTrainLocation() throws TerminusException {
		station = canton.getStation();
		Train train = new Train(canton, 0, 100, 0);
		Train train2 = new Train(canton, 0, 100, 0);
		station.enter(train);
		station.enter(train2);
		assertTrue(train.getCurrentPosition()!=train2.getCurrentPosition());
	}

	/**
	 *  Verifying that if there is a train exit from the Station, there is no longer any train in the station
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
	
	/**
	 * Verifying the break time according to the period of the line
	 */
	@Test
	public void testGetPauseDuration(){
		station = canton.getStation();
		line.setPeriod("creuse");
		assertTrue(station.getPauseDuration()>=333);
		assertTrue(station.getPauseDuration()<=1000);
		line.setPeriod("normale");
		assertTrue(station.getPauseDuration()>=500);
		assertTrue(station.getPauseDuration()<=1500);
		line.setPeriod("pleine");
		assertTrue(station.getPauseDuration()>=1000);
		assertTrue(station.getPauseDuration()<=1666);
		
	}
	
	@Test
	public void testUpdatePassager(){
		station = new Station(null, 0, 100, 0, 0);
		station.setCurrentPassenger(20);
		line.setPeriod("creuse");
		station.updatePassengers();
		assertTrue(station.getCurrentPassenger()<=40);
		assertTrue(station.getCurrentPassenger()>=10);
		station.setCurrentPassenger(20);
		line.setPeriod("normale");
		station.updatePassengers();
		assertTrue(station.getCurrentPassenger()<=30);
		assertTrue(station.getCurrentPassenger()>=15);
		station.setCurrentPassenger(20);
		line.setPeriod("pleine");
		station.updatePassengers();
		assertTrue(station.getCurrentPassenger()<=60);
		assertTrue(station.getCurrentPassenger()>=0);
		station.setCurrentPassenger(201);
		station.updatePassengers();
		assertTrue(station.getCurrentPassenger()==100);
		station = new Station(null, 0, 0, 0, 0);
		station.setCurrentPassenger(-1);
		station.updatePassengers();
		assertTrue(station.getCurrentPassenger()==0);
		canton = line.getCanton(5);
		station= canton.getStation();
		station.setCurrentPassenger(20);
		station.updatePassengers();
		assertTrue(station.getCurrentPassenger()<=0);
	}
	
	@Test
	public void testUpdateSatisfaction(){
		station = new Station(null, 0, 100, 0, 0);
		station.setCurrentPassenger(51);
		station.updateSatisfaction();
		assertTrue(station.getSatisfaction()==75);
		station.setCurrentPassenger(49);
		station.setSatisfaction(75);
		station.updateSatisfaction();
		assertTrue(station.getSatisfaction()==76);
		station = canton.getStation();
		station.setCurrentPassenger(49);
		station.setSatisfaction(75);
		line.newIncident(canton, 0);
		station.updateSatisfaction();
		assertTrue(station.getSatisfaction()==74);
	}
	
	@Test
	public void testChangeSatisfaction(){
		station = new Station(null, 0, 100, 3, 0);
		station.incrementSatisfaction();
		assertTrue(station.getSatisfaction()==76);
		station.incrementSatisfaction();
		assertTrue(station.getSatisfaction()==77);
		station.decrementSatisfaction();
		assertTrue(station.getSatisfaction()==76);
		station.decrementSatisfaction();
		assertTrue(station.getSatisfaction()==75);
		station.useReserveTrain();
		assertTrue(station.getNumReserveTrain()==2);
		assertTrue(station.hasAvailableReserveTrain()==true);
	}
}
