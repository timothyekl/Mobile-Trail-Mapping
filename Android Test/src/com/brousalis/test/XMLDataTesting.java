package com.brousalis.test;

import junit.framework.TestCase;
import android.util.Log;

import com.brousalis.DataHandler;
import com.brousalis.Trail;

public class XMLDataTesting extends TestCase {
	
	public static final String URL = "http://www.fernferret.com/samplexml.xml";
	public static final String TRAIL_NAME = "Heritage";
	
	public void testXMLExists() {
		DataHandler handler = new DataHandler(URL);
		handler.parseDocument();
		assertEquals(1, handler.getParsedTrails().size());
	}
	public void testBasicXML() {
		DataHandler handler = new DataHandler(URL);
		handler.parseDocument();
		Trail t = handler.getParsedTrail(TRAIL_NAME);
		t.resolveConnections();
		//HashSet<Trail> trails = handler.getParsedTrails();
		assertEquals("Trail: "+TRAIL_NAME+" (16 TrailPoints)", t.toString());
		
		assertEquals(0,t.getTrailPoint(0).getID());
		assertEquals(1,t.getTrailPoint(0).getConnections().size());
		
		assertEquals(1,t.getTrailPoint(1).getID());
		assertEquals(1,t.getTrailPoint(1).getConnections().size());
		
		assertEquals(2,t.getTrailPoint(2).getID());
		assertEquals(2,t.getTrailPoint(2).getConnections().size());
		
		assertEquals(3,t.getTrailPoint(3).getID());
		assertEquals(0,t.getTrailPoint(3).getConnections().size());
		
		assertEquals(4,t.getTrailPoint(4).getID());
		assertEquals(1,t.getTrailPoint(4).getConnections().size());
		
		assertEquals(5,t.getTrailPoint(5).getID());
		assertEquals(1,t.getTrailPoint(5).getConnections().size());
		
		assertEquals(6,t.getTrailPoint(6).getID());
		assertEquals(1,t.getTrailPoint(6).getConnections().size());
		
		assertEquals(7,t.getTrailPoint(7).getID());
		assertEquals(1,t.getTrailPoint(7).getConnections().size());
		
		assertEquals(8,t.getTrailPoint(8).getID());
		assertEquals(1,t.getTrailPoint(8).getConnections().size());
		
		assertEquals(9,t.getTrailPoint(9).getID());
		assertEquals(1,t.getTrailPoint(9).getConnections().size());
		
		assertEquals(10,t.getTrailPoint(10).getID());
		assertEquals(1,t.getTrailPoint(10).getConnections().size());
		
		assertEquals(11,t.getTrailPoint(11).getID());
		assertEquals(1,t.getTrailPoint(11).getConnections().size());
		
		assertEquals(12,t.getTrailPoint(12).getID());
		assertEquals(1,t.getTrailPoint(12).getConnections().size());
		
		assertEquals(13,t.getTrailPoint(13).getID());
		assertEquals(1,t.getTrailPoint(13).getConnections().size());
		
		assertEquals(14,t.getTrailPoint(14).getID());
		assertEquals(1,t.getTrailPoint(14).getConnections().size());
		
		assertEquals(15,t.getTrailPoint(15).getID());
		assertEquals(0,t.getTrailPoint(15).getConnections().size());
	}

}
