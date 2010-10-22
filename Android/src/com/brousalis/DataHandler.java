package com.brousalis;

import java.util.HashSet;
import java.util.Iterator;

import org.jdom.input.DOMBuilder;

import com.google.android.maps.GeoPoint;

public class DataHandler {

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
	static String XML_FILE = "http://www.fernferret.com/samplexml.xml";
	static String XML_SCHEMA = "http://www.fernferret.com/mtmSchema.xsd";

	/**
	 * Empty Constructor, Currently does nothing.
	 */
	public DataHandler() {
		_trails = new HashSet<Trail>();
		DOMBuilder builder = new DOMBuilder();
		//StreamSource xmlSource = new StreamSource(XML_FILE);
	/*	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Document doc = null;
		try {
			URL schemaDoc = new URL(XML_SCHEMA);
			URL xmlDoc = new URL(XML_FILE);
			Schema schema = schemaFactory.newSchema(schemaDoc);
			//factory.setSchema(schema);
			javax.xml.validation.Validator validator = schema.newValidator();
			validator.validate(xmlSource);

			builder = factory.newDocumentBuilder();
			doc = builder.parse(new InputSource(xmlDoc.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//extractTrails(doc);
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
