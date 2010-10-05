package com.brousalis;

import java.util.HashSet;
import java.util.Iterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.android.maps.GeoPoint;

public class DataHandler extends DefaultHandler {

	private Trail _trail;
	private HashSet<Trail> _trails;
	private TrailPoint _trailPoint;
	
	private static final String TRAIL = "trail";
	private static final String TRAIL_POINT = "trailpoint";
	private static final String TRAIL_CONNECTION = "connection";
	private static final String ID = "id";
	private static final String LATITUDE = "lat";
	private static final String LONGITUDE = "long";
	private static final String NAME = "name";

	/**
	 * Empty Constructor, Currently does nothing.
	 */
	public DataHandler() {
		_trails = new HashSet<Trail>();
	}
	
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals(TRAIL_CONNECTION)) {
			this._trailPoint.addConnectionByID(Integer.parseInt(atts.getValue(ID)));
		} else if (localName.equals(TRAIL_POINT)) {
			this.createNewTrailPoint(Integer.parseInt(atts.getValue(ID)), Double.parseDouble(atts.getValue(LATITUDE)), Double.parseDouble(atts.getValue(LONGITUDE)));
		} else if (localName.equals(TRAIL)) {
			this.createTrail(atts.getValue(NAME));
		}
	}
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (localName.equals(TRAIL_POINT)) {
			this.saveTrailPoint();
		} else if(localName.equals(TRAIL)) {
			this.saveTrail();
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
		Iterator<Trail> iter = this._trails.iterator();
		while(iter.hasNext()) {
			Trail current = iter.next();
			if (current.getName().equals(trail)) {
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
		return this._trails;
	}
	
	/**
	 * Sets the current working TrailPoint.  This point will be added to the current trail
	 * once all of it's information has been read.
	 * @param id ID of the TrailPoint
	 * @param lat Latitude in double format (ex. 14.1328)
	 * @param lon Longitude in double format (ex. 12.2334)
	 */
	private void createNewTrailPoint(int id, double lat, double lon) {
		this._trailPoint = new TrailPoint(id, new GeoPoint((int)(lat*1E6), (int)(lon*1E6)),new HashSet<TrailPoint>());
	}

	/**
	 * Creates a new trail
	 * @param trailName The name of the Trail
	 */
	private void createTrail(String trailName) {
		this._trail = new Trail(trailName);
	}
	
	/**
	 * Saves a TrailPoint to the current trail.
	 */
	private void saveTrailPoint() {
		this._trail.addPoint(this._trailPoint);
		this._trailPoint = new TrailPoint(-1, new GeoPoint(0,0), null);
	}
	
	/**
	 * Saves the current Trail to the Trail Dictionary
	 */
	private void saveTrail() {
		this._trails.add(this._trail);
	}
}
