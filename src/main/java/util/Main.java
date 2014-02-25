package util;

import conversor.CSV;
import conversor.Json2CsvConversor;
import conversor.JsonParserException;

public class Main {

	private static final String GO_EURO_SUGGEST_SERVICE_URL = "https://api.goeuro.com/api/v1/suggest/position/en/name/";

	public static void main(final String[] args) {
		CSV csv = CSV.emptyCSV();
		try {
			validateArguments(args);
			
			String url = GO_EURO_SUGGEST_SERVICE_URL + args[0];
			
			String jsonResponse = WebRequest.callHttpsSelfSigned(url);
			
			Json2CsvConversor parser = Json2CsvConversor.getInstance();
			csv = parser.fromString(jsonResponse);
			
		} catch (ConnectionException ce) {
			reportError(ce, "There is a problem with the connection.");
		} catch (JsonParserException jpe) {
			reportError(jpe, "There is a problem during the parsing process.");
		} catch (IllegalArgumentException iae) {
			reportError(iae);
		}
		
		System.out.println(csv);
	}

	private static void validateArguments(final String[] args) {
		if ( args == null || args.length == 0 || args[0] == null || args[0].length() == 0) {
			throw new IllegalArgumentException("No argument found. Please add one.");
		}
	}

	private static void reportError(final Exception e, final String message) {
		System.err.println(message +"\n"+ e.getMessage());
	}

	private static void reportError(final Exception e) {
		reportError(e, "");	
	}
	
}
