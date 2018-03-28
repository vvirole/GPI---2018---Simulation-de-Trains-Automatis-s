package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import core.utility.Clock;

public class ClockTest {

	@Test
	public void testIncrement(){
		Clock clock = new Clock();
		clock.setSeconde(2);
		clock.increment();
		assertTrue(clock.getSeconde()==3);
		clock.setSeconde(59);
		clock.increment();
		assertTrue(clock.getSeconde()==0);
		assertTrue(clock.getMinute()==1);
		clock.setSeconde(59);
		clock.setMinute(59);
		clock.increment();
		assertTrue(clock.getSeconde()==0);
		assertTrue(clock.getMinute()==0);
		assertTrue(clock.getHour()==8);
		clock.setSeconde(59);
		clock.setMinute(59);
		clock.setHour(23);
		clock.increment();
		assertTrue(clock.getSeconde()==0);
		assertTrue(clock.getMinute()==0);
		assertTrue(clock.getHour()==0);
		assertTrue(clock.getDay()==1);
	}
}
