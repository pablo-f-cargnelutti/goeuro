package conversor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import model.Results;
import util.Validate;

import com.google.gson.Gson;

public class Json2CsvConversor {

	public static Json2CsvConversor getInstance() {
		return new Json2CsvConversor();
	}
	
	private Json2CsvConversor() {	
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
