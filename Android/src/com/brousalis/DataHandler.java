package com.brousalis;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.util.Log;

import com.google.android.maps.GeoPoint;

public class DataHandler {

	private Trail _trail;
	private HashSet<Trail> _trails;
	private TrailPoint _trailPoint;
	
	static String XML_FILE = "http://www.fernferret.com/samplexml.xml";
	static String XML_SCHEMA = "http://www.fernferret.com/mtmSchema.xsd";

	private DocumentBuilderFactory _factory;
	private DocumentBuilder _builder;
	private URL _xmlFile;
	private Document _doc;
	
	/**
	 * Initializes the DataHandler values
	 */
	public DataHandler() {
		_trails = new HashSet<Trail>();
		_factory = DocumentBuilderFactory.newInstance();
		try {
			_xmlFile = new URL(XML_FILE);
			_builder = _factory.newDocumentBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses the document into trails and points.
	 */
	public void parseDocument() {
		try {
			_doc = _builder.parse(new InputSource(_xmlFile.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		extractTrails(_doc);
	}
	
	/**
	 * Extracts trail information from the DOM.  This will retrieve all trails currently
	 * @param doc
	 */
	private void extractTrails(Document doc) {
		NodeList itemList = doc.getElementsByTagName("trail");
		Node currentNode = itemList.item(0);
		while (currentNode != null) {
			if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
				Log.w("MTM", "MTM: Trail ID   : " + currentNode.getAttributes().getNamedItem("id").getNodeValue());
				Log.w("MTM", "MTM: Trail Name : " + currentNode.getAttributes().getNamedItem("name").getNodeValue());
				extractTrail(currentNode);
			}
			currentNode = currentNode.getNextSibling();
		}
	}
	
	/**
	 * Iterates through a single trail
	 * @param currentNode
	 */
	private void extractTrail(Node currentNode) {
		Node point = null;
		point = currentNode.getFirstChild().getNextSibling().getFirstChild().getNextSibling();
		while (point != null) {
			if (point.getNodeType() == Node.ELEMENT_NODE) {
				getPointInfo(point);
			}
			point = point.getNextSibling();
		}
	}
	
	private void getPointInfo(Node point) {
		Node localPoint = null;
		Log.w("MTM", "MTM: Point         : " + point.getNodeName());
		Log.w("MTM", "MTM: Point ID      : " + point.getAttributes().getNamedItem("id").getNodeValue());
		Log.w("MTM", "MTM: Point Content : " + point.getNodeValue());
		localPoint = point.getFirstChild().getNextSibling();
		while(localPoint != null) {
			if(localPoint.getNodeType() == Node.ELEMENT_NODE && localPoint.getNodeName() != "connections") {
				Log.w("MTM", "MTM Value: " + localPoint.getNodeName() + " : " + localPoint.getFirstChild().getNodeValue());// + //localPoint.getTextContent());
				if(localPoint.getNodeName() == "category") {
					Log.w("MTM", "MTM: - ID: " + localPoint.getAttributes().getNamedItem("id").getNodeValue());
				}
			}
			if(localPoint.getNodeName() == "connections") {
				localPoint = localPoint.getFirstChild();
				Log.w("MTM","Found the connections node");
			}
			else {
				localPoint = localPoint.getNextSibling();
			}
		}
	}
	
	/**
	 * Performs validation on the document
	 * This is not yet implemented
	 */
	public boolean validateDocument() {
		return false;
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
