package model;

import conversor.CSV;
import conversor.CSVData;

public class GeoPosition implements CSVData{	
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
	public CSV toCSV() {
		final CSV csv = CSV.emptyCSV();
		csv.addDoubleValue(latitude);
		csv.addDoubleValue(longitude);
		return csv;
	}
}