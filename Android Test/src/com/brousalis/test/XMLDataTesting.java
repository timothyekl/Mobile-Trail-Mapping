package com.brousalis.test;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import junit.framework.TestCase;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.brousalis.DataHandler;

public class XMLDataTesting extends TestCase {
	
	public static final String URL = "http://github.com/lithium3141/Mobile-Trail-Mapping/raw/AndroidMain/sampledata.xml";
	public static final String TRAIL_NAME = "MyTrail 1";
	public void testBasicXML() {
		DataHandler handler = new DataHandler();
		try {
			URL url = new URL(URL);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			
			XMLReader xr = sp.getXMLReader();
			
			
			xr.setContentHandler(handler);
			
			xr.parse(new InputSource(url.openStream()));
			
			Log.w("MTM", "MTM"+handler.getParsedTrail(TRAIL_NAME));
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Trail: FernFerret (8 TrailPoints)", handler.getParsedTrail(TRAIL_NAME).toString());
		handler.getParsedTrail(TRAIL_NAME).resolveConnections();
		assertEquals(0,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(0).getID());
		assertEquals(1,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(0).getConnections().size());
		
		assertEquals(1,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(1).getID());
		assertEquals(2,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(1).getConnections().size());
		
		assertEquals(2,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(2).getID());
		assertEquals(0,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(2).getConnections().size());
		
		assertEquals(3,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(3).getID());
		assertEquals(1,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(3).getConnections().size());
		
		assertEquals(4,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(4).getID());
		assertEquals(1,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(4).getConnections().size());
		
		assertEquals(5,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(5).getID());
		assertEquals(1,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(5).getConnections().size());
		
		assertEquals(6,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(6).getID());
		assertEquals(1,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(6).getConnections().size());
		
		assertEquals(7,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(7).getID());
		assertEquals(0,handler.getParsedTrail(TRAIL_NAME).getTrailPoint(7).getConnections().size());
	}

}
