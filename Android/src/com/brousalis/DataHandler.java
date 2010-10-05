package com.brousalis;

import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.android.maps.GeoPoint;

public class DataHandler extends DefaultHandler {

	private boolean _connectionMode = false;
	private Trail _t;
	private TrailPoint _trailPoint;
	private int _currentConnection;
	
	private static final String TRAIL = "trail";
	private static final String TRAIL_POINT = "trailpoint";
	private static final String TRAIL_CONNECTION = "connection";

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals(TRAIL_CONNECTION)) {
			this._connectionMode = true;
		} else if (localName.equals(TRAIL_POINT)) {
			createNewTrailPoint(Integer.parseInt(atts.getValue("id")), Double.parseDouble(atts.getValue("lat")), Double.parseDouble(atts.getValue("long")));
		} else if (localName.equals(TRAIL)) {
			createTrail(atts.getValue("name"));
		}
	}
	
	private void createNewTrailPoint(int id, double lat, double lon) {
		_trailPoint = new TrailPoint(id, new GeoPoint((int)(lat*1E6), (int)(lon*1E6)),new HashSet<TrailPoint>());
	}

	private void createTrail(String trailName) {
		_t = new Trail(trailName);
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (localName.equals(TRAIL_CONNECTION)) {
			this._connectionMode = false;
		} else if (localName.equals(TRAIL_POINT)) {
			saveTrailPoint();
		}
	}

	private void saveTrailPoint() {
		_t.addPoint(_trailPoint);
		_trailPoint = new TrailPoint(-1, new GeoPoint(0,0), null);
	}

	@Override
	public void characters(char ch[], int start, int length) {
		if(_connectionMode) {
			_currentConnection = Integer.parseInt(new String(ch, start, length));
			_trailPoint.addConnectionByID(_currentConnection);
		}
			
	}
	
	public Trail getParsedTrail(int index) {
		// For now i'm only testing with 1 trail, Always pass in 0.
		return _t;
	}

}
