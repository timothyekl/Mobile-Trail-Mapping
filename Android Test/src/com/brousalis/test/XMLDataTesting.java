package com.brousalis.test;

import android.content.res.Configuration;
import android.test.ActivityInstrumentationTestCase2;

import com.brousalis.DataHandler;
import com.brousalis.ShowMap;
import com.brousalis.Trail;
import com.google.android.maps.MapView;

public class XMLDataTesting extends ActivityInstrumentationTestCase2<ShowMap> {
	
public static final int GOOD_ZOOM_LEVEL = 17;
	
	private ShowMap mActivity;
	private MapView mView;
	private Configuration config;
	
	public XMLDataTesting() {
		super("com.brousalis", ShowMap.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		config = new Configuration();
		config.setToDefaults();
		
		mActivity = this.getActivity();
		mView = (MapView) mActivity.findViewById(com.brousalis.R.id.mapView);
	}
	
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
