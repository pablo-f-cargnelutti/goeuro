package conversor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Json2CsvConversorTest {

	private static final String TEST_DATA_PATH = "./test/main/resources/testdata/";
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testOneElementParsing() throws JsonParserException {
		// Given
		Json2CsvConversor parser = Json2CsvConversor.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "oneElement.json"));
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location,40.4165,-3.70256", csv.toString());
	}
	
	@Test
	public void testMultipleElementsParsing() throws JsonParserException {
		// Given
		Json2CsvConversor parser = Json2CsvConversor.getInstance();
		
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
	public void testEmptyJsonParsing() throws JsonParserException {
		// Given
		Json2CsvConversor parser = Json2CsvConversor.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "empty.json"));
		
		// Then
		assertTrue(csv.toString().isEmpty());
	}
	
	@Test
	public void testOneIncompleteElementWithoutTypeParsing() throws JsonParserException {
		// Given
		Json2CsvConversor parser = Json2CsvConversor.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "oneIncompleteElement.json"));
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,40.4165,-3.70256", csv.toString());
	}
	
	@Test
	public void testOneIncompleteElementWithoutGeoPositionParsing() throws JsonParserException {
		// Given
		Json2CsvConversor parser = Json2CsvConversor.getInstance();
		
		// When
		CSV csv = parser.fromFile(new File(TEST_DATA_PATH + "oneElementWithoutGeoPosition.json"));
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location", csv.toString());
	}
	
	@Test
	public void testOneElementWithInvalidFormatParsing() throws JsonParserException {
		// Expect
		expectedException.expect(JsonParserException.class);
		
		// Given
		Json2CsvConversor parser = Json2CsvConversor.getInstance();
		
		// When
		parser.fromFile(new File(TEST_DATA_PATH + "oneElementWithInvalidFormat.json"));		
	}


}
