package model;

import java.util.List;

import converter.Csv;
import converter.CsvData;

public class Results implements CsvData{
	private List <Suggest> results;

	public List <Suggest> getResults() {
		return results;
	}

	public void setResults(final List <Suggest> results) {
		this.results = results;
	}

	@Override
	public Csv toCSV() {
		Csv csv = Csv.emptyCSV();
		for (Suggest suggest : results) {
			csv.addObjectValue(suggest);
			csv.addNewLine();
		}
		return csv;
	}
}