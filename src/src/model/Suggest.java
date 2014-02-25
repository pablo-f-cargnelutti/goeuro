package src.model;

import src.parser.CSV;
import src.parser.CSVData;


public class Suggest implements CSVData{
	String type;
	int _id;
	String name;
	String _type;
	GeoPosition geo_position;
	
	public String getType() {
		return type;
	}
	public void setType(final String type) {
		this.type = type;
	}
	public int getId() {
		return _id;
	}
	public void setId(final int id) {
		this._id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(final String _type) {
		this._type = _type;
	}
	public GeoPosition getGeoPosition() {
		return geo_position;
	}
	public void setGeoPosition(final GeoPosition geoPosition) {
		this.geo_position = geoPosition;
	}
	/**
	 * Requested format:
	 * _type, _id, name, type, latitude, longitude
	 */
	@Override
	public CSV toCSV() {
		CSV csv = CSV.emptyCSV();
		csv.addStringValue(_type);
		csv.addIntValue(_id);
		csv.addStringValue(name);
		csv.addStringValue(type);		
		csv.addObjectValue(geo_position);
		return csv;
	}
	
	
}