import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.adapters.*;
import org.w3c.dom.Document;
import org.apache.xerces.parsers.*;


public class JDOMTester {
	private URL _xmlFile;
	private URL _xmlSchema;
	//private StreamSource _xmlSource;
	private SAXBuilder _builder;
	//private DocumentBuilderFactory _factory;
	//private DocumentBuilder _builder;
	public JDOMTester(String xmlFile, String xmlSchema) {
		try {
			_xmlFile = new URL(xmlFile);
			_xmlSchema = new URL(xmlSchema);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		_builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser", true);
		_builder.setFeature("http://apache.org/xml/features/validation/schema", true);
		_builder.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "http://www.fernferret.com/mtmSchema.xsd");
		try {
			_builder.build(_xmlFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
