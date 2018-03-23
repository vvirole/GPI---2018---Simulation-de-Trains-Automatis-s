package test.unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import core.LineBuilder;
import core.entity.Canton;
import core.entity.Line;
import core.entity.Station;
import core.xml.UnvalidFileException;

public class LineBuilderTest {

	private Line line;
	private Canton canton, canton2;
	
	@Before
	public void linePreparation() throws UnvalidFileException{
		File file = new File("line.xml");
		LineBuilder.buildLine(file);
		line = Line.getInstance();
		canton = line.getCanton(0);
		canton2 = line.getCanton(1);
	}
	
	@Test
	public void testBuildLine() {
		Station station = canton.getStation();
		Station station2 = canton2.getStation();
		assertTrue(station.getPosition()==canton.getEndPoint());
		assertTrue(station.getName()!=station2.getName());
	}

	@Test
	public void testAdjustLines() {
		Station station = canton.getStation();
		Station station2 = canton2.getStation();
		assertTrue(station.getPosition()<=station2.getPosition());
	}

}
