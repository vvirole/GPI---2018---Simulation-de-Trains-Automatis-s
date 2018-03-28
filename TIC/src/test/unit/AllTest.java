package test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author RE Thomas
 */



/** 
 * 
in the class "Canton" put in the function "enter" a value to wait otherwise the test loop limitless
 */

@RunWith(Suite.class)
@SuiteClasses({CantonTest.class, IncidentTest.class, LineControllerTest.class, LineBuilderTest.class, LineTest.class, UtilityTest.class, StationTest.class, TrainTest.class, RandomUtilityTest.class, DataStorageTest.class, ClockTest.class})


public class AllTest {

}
