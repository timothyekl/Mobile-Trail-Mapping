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

	static String XML_FILE = "http://www.fernferret.com/samplexml.xml";
	static String XML_SCHEMA = "http://www.fernferret.com/mtmSchema.xsd";
	public static void main(String[] args) {
		StreamSource xmlSource = new StreamSource(XML_FILE);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		Document doc = null;
		try {
			URL schemaDoc = new URL(XML_SCHEMA);
			URL xmlDoc = new URL(XML_FILE);
			Schema schema = schemaFactory.newSchema(schemaDoc);
			factory.setSchema(schema);
			Validator validator = schema.newValidator();
			validator.validate(xmlSource);

			builder = factory.newDocumentBuilder();
			doc = builder.parse(new InputSource(xmlDoc.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		extractTrails(doc);
	}

	private static void extractTrails(Document doc) {
		NodeList itemList = doc.getElementsByTagName("trail");
		Node currentNode = itemList.item(0);
		while (currentNode != null) {
			if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("Trail: ");
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
				System.out.println(localPoint.getNodeName() + " : " + localPoint.getTextContent());
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
