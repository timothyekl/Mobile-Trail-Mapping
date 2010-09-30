package com.brousalis;

import com.google.android.maps.GeoPoint;


public class InterestPoint {
	private int _ID;
	private GeoPoint _location;
	private String _category;
	private String _title;
	private String _summary;
	
	/**
	 * Creates a new Interest point with the required params.
	 * @param id A unique identifier of the point.
	 * @param p A GeoPoint where the point is located at
	 * @param category The unique category this point belongs to.
	 * @param title The title of the point.  Will be clickable if this is not "".
	 * @param summary A brief summary or description of the point.  This can be "".
	 */
	public InterestPoint(int id, GeoPoint p, String category, String title, String summary) {
		_ID = id;
		_location = p;
		_category = category;
		_summary = summary;
		_title = title;
	}
	
	public void setID(int id) {
		_ID = id;
	}
	public int getID() {
		return _ID;
	}
	
	public void setLocation(int latitude, int longitude) {
		_location = new GeoPoint(latitude, longitude);
	}
	public GeoPoint getLocation() {
		return _location;
	}
	
	public void setCategory(String category) {
		_category = category;
	}
	public String getCategory() {
		return _category;
	}
	
	public void setSummary(String summary) {
		_summary = summary;
	}
	public String getSummary() {
		return _summary;
	}

	public void setTitle(String title) {
		_title = title;
	}
	public String getTitle() {
		return _title;
	}
}
