package test.unit;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.utility.DataStorage;
import core.xml.UnvalidFileException;

public class DataStorageTest {

	private DataStorage dataStorage;

	@Before
	public void linePreparation() throws UnvalidFileException{
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		dataStorage = DataStorage.getInstance();
	}
	
	@Test
	public void testGeneral() {
		dataStorage.addData(0.0f, 200, 10, 50);
		assertTrue(dataStorage.getNbPassenger(0.0f) == 200);
		assertTrue(dataStorage.getNbIncident(0.0f) == 10);
		assertTrue(dataStorage.getLevelSatisfaction(0.0f) == 50);	
		assertTrue(dataStorage.getPassengerData().size() == 1);
	}
	
	@Test
	public void testClear(){
		DataStorage.getInstance().clear();
		assertTrue(dataStorage.getPassengerData().isEmpty());
		assertTrue(dataStorage.getSatisfactionData().isEmpty());
		assertTrue(dataStorage.getIncidentData().isEmpty());
		assertTrue(dataStorage.getTimes().isEmpty());
	}
}
