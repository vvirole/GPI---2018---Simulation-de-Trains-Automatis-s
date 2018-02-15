package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.entity.Canton;
import core.entity.Line;
import core.entity.Station;
import core.entity.Train;

public class CantonTest {

	Line line = new Line(null, 0, 0);
	Station station = new Station(null, 0, 0, 0, 0);
	Canton canton = new Canton(0, 0, 0, station);
	Train train = new Train(canton, 0, 0, 0);
	
	
	/** test si le canton n'est pas libre**/
	@Test
	public void testIsFree() {
		assertTrue(canton.isFree()==false);
	}

	/** test si le canton ne subit pas d'accident **/
	@Test
	public void testIsNotAccident() {
		assertTrue(canton.isAccident()==false);
	}


}
