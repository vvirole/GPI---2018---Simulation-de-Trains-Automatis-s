package TestJUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.entity.*;

public class CantonFreeTest {

	Station S1 = new Station("first",1,1,1,1);
	Line line = new Line(null, 0, 0);
	Canton C1 = new Canton(0, 0, 0, S1);
	Train T1 = new Train(C1, 0, null, 0, 0);
	
	@Test
	public void test() {
		
	}

}
