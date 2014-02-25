package util;

import model.Results;
import converter.Csv;
import converter.Json2CsvConverter;
import converter.JsonConverterException;

public class Main {

	private static final String GO_EURO_SUGGEST_SERVICE_URL = "https://api.goeuro.com/api/v1/suggest/position/en/name/";

	public static void main(final String[] args) {
		Csv csv = Csv.emptyCSV();
		try {
			validateArguments(args);
			
			String url = GO_EURO_SUGGEST_SERVICE_URL + args[0];
			
			String jsonResponse = WebRequest.callHttpsSelfSigned(url);
			
			Json2CsvConverter parser = Json2CsvConverter.getInstance();
			csv = parser.convertFromString(jsonResponse, Results.class);
			
			System.out.println(csv);
			
		} catch (ConnectionException ce) {
			reportError(ce, "There is a problem with the connection.");
		} catch (JsonConverterException jpe) {
			reportError(jpe, "There is a problem during the parsing process.");
		} catch (IllegalArgumentException iae) {
			reportError(iae);
		}
	}

	private static void validateArguments(final String[] args) {
		Validate.notNullOrEmpty(args, "No argument found. Please add one.");
		Validate.notNullOrEmpty(args[0], "No argument found. Please add one. The call should look like: goEuroTest.jar Argentina");
	}

	private static void reportError(final Exception e, final String message) {
		System.err.println(message +"\n"+ e.getMessage());
	}

	private static void reportError(final Exception e) {
		reportError(e, "");	
	}
	
}
