import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DOMTester {
	
	private String _xmlFile;
	private String _xmlSchema;
	private StreamSource _xmlSource;
	private DocumentBuilderFactory _factory;
	private DocumentBuilder _builder;
	private SchemaFactory _schemaFactory;
	private Document _doc;
	
	public DOMTester(String xmlFile, String xmlSchema) {
		_xmlFile = xmlFile;
		_xmlSchema = xmlSchema;
		_xmlSource = new StreamSource(_xmlFile);
		_factory = DocumentBuilderFactory.newInstance();
		_builder = null;
		_schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		_doc = null;
		try {
			URL schemaDoc = new URL(_xmlSchema);
			URL xmlDoc = new URL(_xmlFile);
			Schema schema = _schemaFactory.newSchema(schemaDoc);

			Validator validator = schema.newValidator();
			validator.validate(_xmlSource);

			_builder = _factory.newDocumentBuilder();
			_doc = _builder.parse(new InputSource(xmlDoc.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void parse() {
		extractTrails(_doc);
	}
	private static void extractTrails(Document doc) {
		NodeList itemList = doc.getElementsByTagName("trail");
		Node currentNode = itemList.item(0);
		while (currentNode != null) {
			if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Trail: "+currentNode.getAttributes().getNamedItem("name"));
				extractTrail(currentNode);
			}
			currentNode = currentNode.getNextSibling();
		}
	}

	private static void extractTrail(Node currentNode) {
		Node point = null;
		point = currentNode.getFirstChild().getNextSibling().getFirstChild().getNextSibling();
		while (point != null) {
			if (point.getNodeType() == Node.ELEMENT_NODE) {
				getPointInfo(point);
			}
			point = point.getNextSibling();
		}
		System.out.println();
	}

	private static void getPointInfo(Node point) {
		Node localPoint = null;
		System.out.println("Point       : " + point.getNodeName());
		System.out.println("Point ID    : " + point.getAttributes().getNamedItem("id").getNodeValue());
		
		localPoint = point.getFirstChild().getNextSibling();
		while(localPoint != null) {
			if(localPoint.getNodeType() == Node.ELEMENT_NODE && localPoint.getNodeName() != "connections") {
				System.out.print(localPoint.getNodeName() + " : " + localPoint.getTextContent());
				if(localPoint.getNodeName() == "category") {
					System.out.println(" - ID: " + localPoint.getAttributes().getNamedItem("id").getTextContent());
				}
				else {
					System.out.println();
				}
			}
			if(localPoint.getNodeName() == "connections") {
				localPoint = localPoint.getFirstChild();
			}
			else {
				localPoint = localPoint.getNextSibling();
			}
		}
		System.out.println();
	}
}
