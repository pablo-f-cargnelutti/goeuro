package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import util.Validate;

import com.google.gson.Gson;

public class Json2CsvConverter {

	private Json2CsvConverter() {
	}

	public static Json2CsvConverter getInstance() {
		return new Json2CsvConverter();
	}

	public Csv convertFromString(final String jsonString, final Class<? extends CsvData> resultClazz) throws JsonConverterException {
		Validate.notNull(jsonString, "jsonString cannot be null");
		return convert(new StringReader(jsonString), resultClazz);
	}

	public Csv convertFromFile(final File fileName, final Class<? extends CsvData> resultClazz)
			throws JsonConverterException {
		Validate.notNull(fileName, "fileName cannot be null");
		Reader fileReader;

		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			throw new JsonConverterException(e.getMessage());
		}

		return convert(fileReader, resultClazz);
	}

	private Csv convert(final Reader reader, final Class<? extends CsvData> resultClazz) throws JsonConverterException {
		Gson gson = new Gson();
		try {			
			CsvData results = gson.fromJson(reader, resultClazz);
			return results.toCSV();			
		} catch (Exception e) {
			throw new JsonConverterException(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new JsonConverterException(e.getMessage());
			}
		}
	}
}
