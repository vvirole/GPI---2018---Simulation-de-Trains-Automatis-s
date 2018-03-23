package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.entity.Canton;
import core.entity.Line;
import core.utility.DataStorage;
import core.xml.UnvalidFileException;

public class DataStorageTest {

	private Line line;
	private Canton canton, canton2;
	private DataStorage dataStorage;

	@Before
	public void linePreparation() throws UnvalidFileException{
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		line = Line.getInstance();
		canton = line.getCanton(0);
		canton2 = line.getCanton(1);
		dataStorage = DataStorage.getInstance();
	}
	
	@Test
	public void testGeneral() {
		dataStorage.addPassengerData(200, 200);
		assertTrue(dataStorage.getNbPassenger(200)==200);
		assertFalse(dataStorage.getNbPassenger(200)<200);
		assertFalse(dataStorage.getNbPassenger(200)>200);
		
		assertTrue(dataStorage.getPassengerData().size()==1);
	}
	
	@Test
	public void testTimeIncidentData(){
		dataStorage.addTimeIncidentData(200, 30);
		assertTrue(dataStorage.getNbIncident(200)==30);
	}
	
	@Test
	public void testSatisfactionData(){
		dataStorage.addSatisfactionData(200, 12);
		assertTrue(dataStorage.getNbSatisfaction(200)==12);
		assertFalse(dataStorage.getSatisfactionData().size()==1);
	}
	
	@Test
	public void testClear(){
		DataStorage.getInstance().clear();
		assertTrue(dataStorage.getPassengerData().size()==0);
		assertTrue(dataStorage.getSatisfactionData().size()==0);
	}
}
