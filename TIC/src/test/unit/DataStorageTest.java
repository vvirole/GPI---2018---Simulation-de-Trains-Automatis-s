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
	public void TestGetTimeIndex(){
		dataStorage.clear();
		dataStorage.getTimes().add((float) 100);
		dataStorage.getTimes().add((float) 200);
		System.out.println(dataStorage.getTimeIndex(200));
		System.out.println(dataStorage.getTimeIndex(100));
		assertTrue(dataStorage.getTimeIndex(100)==0);
		assertTrue(dataStorage.getTimeIndex(200)==1);
		assertFalse(dataStorage.getTimeIndex(200)==0);
		assertFalse(dataStorage.getTimeIndex(300)==0);
		assertTrue(dataStorage.getTimeIndex(300)==-1);
	}
	
	@Test
	public void TestGeneral(){
		dataStorage.addData(100, 100, 1, 100);
		assertTrue(dataStorage.getNbPassenger(100)==100);
		assertTrue(dataStorage.getNbIncident(100)==1);
		assertTrue(dataStorage.getLevelSatisfaction(100)==100);
		dataStorage.clear();
		assertTrue(dataStorage.getPassengerData().size()==0);
		assertTrue(dataStorage.getIncidentData().size()==0);
		assertTrue(dataStorage.getSatisfactionData().size()==0);
	}
}
