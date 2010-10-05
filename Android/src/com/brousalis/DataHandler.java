package com.brousalis;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.android.maps.GeoPoint;

public class DataHandler extends DefaultHandler {

	private Trail _t;
	private HashSet<Trail> _trails;
	private TrailPoint _trailPoint;
	
	private static final String TRAIL = "trail";
	private static final String TRAIL_POINT = "trailpoint";
	private static final String TRAIL_CONNECTION = "connection";

	/**
	 * Empty Constructor, Currently does nothing.
	 */
	public DataHandler() {}
	
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals(TRAIL_CONNECTION)) {
			_trailPoint.addConnectionByID(Integer.parseInt(atts.getValue("id")));
		} else if (localName.equals(TRAIL_POINT)) {
			createNewTrailPoint(Integer.parseInt(atts.getValue("id")), Double.parseDouble(atts.getValue("lat")), Double.parseDouble(atts.getValue("long")));
		} else if (localName.equals(TRAIL)) {
			createTrail(atts.getValue("name"));
		}
	}
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (localName.equals(TRAIL_POINT)) {
			saveTrailPoint();
		} else if(localName.equals(TRAIL)) {
			saveTrail();
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
//		String str = new String(ch, start, length);
	}
	
	/**
	 * Returns a single Trail
	 * @param trail The name of the trail to get.
	 * @return A Trail who's name was given by trail
	 */
	public Trail getParsedTrail(String trail) {
		Iterator<Trail> iter = _trails.iterator();
		while(iter.hasNext()) {
			Trail current = iter.next();
			if (current.getName() == trail) {
				return current;
			}
		}
		return null;
	}
	
	/**
	 * Returns all the parsed trails
	 * @return A Dictionary<String, Trail> of all the parsed trails.
	 */
	public HashSet<Trail> getParsedTrails() {
		return _trails;
	}
	
	/**
	 * Sets the current working TrailPoint.  This point will be added to the current trail
	 * once all of it's information has been read.
	 * @param id ID of the TrailPoint
	 * @param lat Latitude in double format (ex. 14.1328)
	 * @param lon Longitude in double format (ex. 12.2334)
	 */
	private void createNewTrailPoint(int id, double lat, double lon) {
		_trailPoint = new TrailPoint(id, new GeoPoint((int)(lat*1E6), (int)(lon*1E6)),new HashSet<TrailPoint>());
	}

	/**
	 * Creates a new trail
	 * @param trailName The name of the Trail
	 */
	private void createTrail(String trailName) {
		_t = new Trail(trailName);
	}
	
	/**
	 * Saves a TrailPoint to the current trail.
	 */
	private void saveTrailPoint() {
		_t.addPoint(_trailPoint);
		_trailPoint = new TrailPoint(-1, new GeoPoint(0,0), null);
	}
	
	/**
	 * Saves the current Trail to the Trail Dictionary
	 */
	private void saveTrail() {
		_trails.add(_t);
	}
}
