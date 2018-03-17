package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.utility.RandomUtility;

public class RandomUtilityTest {

	@Test
	public void testRand() {
		for(int i=0; i<1000;i++){
			int min =0;
			int max =100;
			int result = RandomUtility.rand(min, max);
			assertTrue(min<=result);
			assertTrue(max>=result);
		}
	}
}
