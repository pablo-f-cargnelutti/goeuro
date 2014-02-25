package src.model;

import java.util.List;

import src.parser.CSV;
import src.parser.CSVData;

public class Results implements CSVData{
	private List <Suggest> results;

	public List <Suggest> getResults() {
		return results;
	}

	public void setResults(final List <Suggest> results) {
		this.results = results;
	}

	@Override
	public CSV toCSV() {
		CSV csv = CSV.emptyCSV();
		for (Suggest suggest : results) {
			csv.addObjectValue(suggest);
			csv.addNewLine();
		}
		return csv;
	}
}