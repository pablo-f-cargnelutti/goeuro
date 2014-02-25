package converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import model.Results;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Json2CsvConverterTest {

	private static final String TEST_DATA_PATH = "./test/main/resources/testdata/";
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testOneElementParsing() throws JsonConverterException {
		// Given
		Json2CsvConverter converter = Json2CsvConverter.getInstance();
		
		// When
		Csv csv = converter.convertFromFile(new File(TEST_DATA_PATH + "oneElement.json"), Results.class);
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location,40.4165,-3.70256", csv.toString());
	}
	
	@Test
	public void testMultipleElementsParsing() throws JsonConverterException {
		// Given
		Json2CsvConverter converter = Json2CsvConverter.getInstance();
		
		// When
		Csv csv = converter.convertFromFile(new File(TEST_DATA_PATH + "multipleElements.json"), Results.class);
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location,40.4165,-3.70256"+
					 "\nPosition,378654,Madridejos, Spain,location,39.46823,-3.53196"+
					 "\nPosition,430256,Madridanos, Spain,location,41.47967,-5.6046"+
					 "\nPosition,378630,Las Rozas de Madrid, Spain,location,40.49292,-3.87371",
					 csv.toString());
	}
	
	@Test
	public void testEmptyJsonParsing() throws JsonConverterException {
		// Given
		Json2CsvConverter converter = Json2CsvConverter.getInstance();
		
		// When
		Csv csv = converter.convertFromFile(new File(TEST_DATA_PATH + "empty.json"), Results.class);
		
		// Then
		assertTrue(csv.toString().isEmpty());
	}
	
	@Test
	public void testOneIncompleteElementWithoutTypeParsing() throws JsonConverterException {
		// Given
		Json2CsvConverter converter = Json2CsvConverter.getInstance();
		
		// When
		Csv csv = converter.convertFromFile(new File(TEST_DATA_PATH + "oneIncompleteElement.json"), Results.class);
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,40.4165,-3.70256", csv.toString());
	}
	
	@Test
	public void testOneIncompleteElementWithoutGeoPositionParsing() throws JsonConverterException {
		// Given
		Json2CsvConverter converter = Json2CsvConverter.getInstance();
		
		// When
		Csv csv = converter.convertFromFile(new File(TEST_DATA_PATH + "oneElementWithoutGeoPosition.json"), Results.class);
		
		// Then
		assertEquals("Position,378655,Madrid, Spain,location", csv.toString());
	}
	
	@Test
	public void testOneElementWithInvalidFormatParsing() throws JsonConverterException {
		// Expect
		expectedException.expect(JsonConverterException.class);
		
		// Given
		Json2CsvConverter converter = Json2CsvConverter.getInstance();
		
		// When
		converter.convertFromFile(new File(TEST_DATA_PATH + "oneElementWithInvalidFormat.json"), Results.class);		
	}

	@Test
	public void testMultipleElementsWithNullAndMissingFields() throws JsonConverterException {
		
		// Given
		Json2CsvConverter converter = Json2CsvConverter.getInstance();
		
		// When
		Csv csv = converter.convertFromFile(new File(TEST_DATA_PATH + "multipleNullElements.json"), Results.class);
		
		// Then
		assertEquals( "Position,0,0.0,0.0" +
					"\nPosition,0,0.0,0.0" +
					"\nPosition,0,0.0,0.0" +
					"\nPosition,0,0.0,0.0" +
					"\nPosition,0,0.0,0.0", 
					csv.toString() );	
	}

}
