package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.utility.RandomUtility;

/**
 * @author RE Thomas
 */

public class UtilityTest {

	@Test
	public void testRand() {
		int min = 0, max = 100;
		for(int i = 0 ; i < 1000 ; i++){
			int result = RandomUtility.rand(min, max);
			assertTrue(min <= result);
			assertTrue(max >= result);
		}
	}
}
