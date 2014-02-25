package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import src.parser.CSV;
import src.parser.JSON2SCSVParser;
import src.parser.JsonParserException;

public class JSON2CSVParserTest {

	private static final String TEST_DATA_PATH = "./src/test/data/";

	@Test
	public void testOneElementConversion() throws JsonParserException {
		// Given
		JSON2SCSVParser parser = JSON2SCSVParser.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "oneElement.json"));
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location,40.4165,-3.70256", csv.toString());
	}
	
	@Test
	public void testMultipleElementsConversion() throws JsonParserException {
		// Given
		JSON2SCSVParser parser = JSON2SCSVParser.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "multipleElements.json"));
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location,40.4165,-3.70256"+
					 "\nPosition,378654,Madridejos, Spain,location,39.46823,-3.53196"+
					 "\nPosition,430256,Madridanos, Spain,location,41.47967,-5.6046"+
					 "\nPosition,378630,Las Rozas de Madrid, Spain,location,40.49292,-3.87371",
					 csv.toString());
	}
	
	@Test
	public void testEmptyJsonConversion() throws JsonParserException {
		// Given
		JSON2SCSVParser parser = JSON2SCSVParser.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "empty.json"));
		
		// Then
		assertTrue(csv.toString().isEmpty());
	}
	
	@Test
	public void testOneIncompleteElementWithoutTypeConversion() throws JsonParserException {
		// Given
		JSON2SCSVParser parser = JSON2SCSVParser.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "oneIncompleteElement.json"));
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,40.4165,-3.70256", csv.toString());
	}
	
	@Test
	public void testOneIncompleteElementWithoutGeoPositionConversion() throws JsonParserException {
		// Given
		JSON2SCSVParser parser = JSON2SCSVParser.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "oneElementWithoutGeoPosition.json"));
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location", csv.toString());
	}


}
