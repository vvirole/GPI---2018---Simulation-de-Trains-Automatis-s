package test.unit;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.entity.Canton;
import core.entity.Incident;
import core.entity.Line;
import core.entity.Train;
import core.xml.UnvalidFileException;

public class IncidentTest {

	private Line line;
	private Canton canton, canton2, canton3;
	
	@Before
	public void linePreparation() throws UnvalidFileException{
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		line = Line.getInstance();
		canton = line.getCanton(0);
		canton2 = line.getCanton(1);
		canton3 = line.getCanton(2);
	}
	
	
	/**
	 * Verification du temps de résolution d'un accident
	 */
	@Test
	public void testGetTimeToResolve() {
		line.newIncident(canton, 0);
		Incident incident = line.getIncident(canton);
		line.setPeriod("creuse");
		assertTrue(incident.getTimeToResolve()>=100);
		assertTrue(incident.getTimeToResolve()<=200);
		line.setPeriod("normale");
		assertTrue(incident.getTimeToResolve()>=100);
		assertTrue(incident.getTimeToResolve()<=300);
		line.setPeriod("pleine");
		assertTrue(incident.getTimeToResolve()>=200);
		assertTrue(incident.getTimeToResolve()<=300);
	}
	
	/**
	 * Vérification que la ligne sache qu'un train est avant ou apres un accident
	 */
	
	@Test
	public void testIsLocatedBefore() {
		Train train = new Train(canton, 0,100, 0);
		line.newIncident(canton, 1);
		Incident incident = line.getIncident(canton);
		canton2.enter(train);
		assertTrue(incident.isLocatedBefore(train)==true);
		line.newIncident(canton2, 0);
		canton3.enter(train);
		assertTrue(incident.isLocatedBefore(train)==true);
	}

}
