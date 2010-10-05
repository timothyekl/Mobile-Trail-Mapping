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
	
	public void testBasicXML() {
		DataHandler handler = new DataHandler();
		try {
			URL url = new URL(URL);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			
			XMLReader xr = sp.getXMLReader();
			
			
			xr.setContentHandler(handler);
			
			xr.parse(new InputSource(url.openStream()));
			
			Log.w("MTM", "MTM"+handler.getParsedTrail(0));
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Trail: Heritage(6 TrailPoints)", handler.getParsedTrail(0));
	}

}
