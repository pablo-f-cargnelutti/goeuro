package model;

import converter.Csv;
import converter.CsvData;

public class GeoPosition implements CsvData{	
	double latitude;
	double longitude;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(final double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(final double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	/**
	 * Request format:
	 * latitude, longitude
	 */
	public Csv toCSV() {
		final Csv csv = Csv.emptyCSV();
		csv.addDoubleValue(latitude);
		csv.addDoubleValue(longitude);
		return csv;
	}
}