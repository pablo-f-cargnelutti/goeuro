package src.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import src.model.Results;
import src.util.Validate;

import com.google.gson.Gson;

public class JSON2SCSVParser {

	public static JSON2SCSVParser getInstance() {
		return new JSON2SCSVParser();
	}
	
	private JSON2SCSVParser() {	
	}

	public CSV fromString(final String jsonString) throws JsonParserException {
		Validate.notNull(jsonString, "jsonString cannot be null");
		return parse(new StringReader(jsonString));
	}
	
	public CSV fromFile(final File fileName) throws JsonParserException {
		Validate.notNull(fileName, "fileName cannot be null");
		Reader fileReader;
		
		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			throw new JsonParserException(e.getMessage());
		}
		
		return parse(fileReader);
	}

	private CSV parse(final Reader reader) throws JsonParserException {
		Gson gson = new Gson();
		try {			
			Results results = gson.fromJson(reader, Results.class);
			return results.toCSV();			
		} catch (Exception e) {
			throw new JsonParserException(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new JsonParserException(e.getMessage());
			}
		}
	}
}
