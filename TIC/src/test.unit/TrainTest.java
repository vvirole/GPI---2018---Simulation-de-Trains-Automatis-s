package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.entity.*;

public class TrainTest {
		Line line = new Line(null, 0, 0);
		Station station = new Station(null, 0, 0, 0, 0);
		Canton canton = new Canton(0, 0, 0, station);
		Train train = new Train(canton, 0, 0, 0);
		
	/**test positionnement du train**/
	@Test
	public void testUpdatePosition() {
		int position = 100;
		train.setCurrentPosition(1);
		train.setSpeed(position);
		train.updatePosition();
		assertTrue(train.getCurrentPosition()==101);
	}

	/** test si le train ne run pas de base**/
	@Test
	public void testNotRunning() {
		assertTrue(train.isRunning()==false);
	}

}
