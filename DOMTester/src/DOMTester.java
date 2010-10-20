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

	static String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	static String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	static String SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StreamSource xmlSource = new StreamSource("http://www.fernferret.com/samplexml.xml");
		//factory.setNamespaceAware(true);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//factory.setValidating(true);
		DocumentBuilder builder = null;
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);


		
		try {
			URL schemaDoc = new URL("http://www.fernferret.com/mtmSchema.xsd");
			URL xmlDoc = new URL("http://www.fernferret.com/samplexml.xml");
			Schema schema = schemaFactory.newSchema(schemaDoc);
			factory.setSchema(schema);
			Validator validator = schema.newValidator();
			validator.validate(xmlSource);
			//validator.validate(new StreamSource("samplexml.xml"));
			builder = factory.newDocumentBuilder();
			System.out.println(builder.isValidating());
			Document doc = builder.parse(new InputSource(xmlDoc.openStream()));

			processDocument(doc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void processDocument(Document doc) {
		NodeList l = doc.getElementsByTagName("trail");
		int i = 0;
		Node currentNode = l.item(i);
		NodeList points = null;
		Node point = null;
		while (currentNode != null) {

			System.out.println("Node Name : " + currentNode.getNodeName());
			System.out.println("Node ID   : "
					+ currentNode.getAttributes().getNamedItem("id")
							.getNodeValue());
			System.out.println("Node Type : " + currentNode.getNodeType());
			System.out.println("Node Value: " + currentNode.getNodeValue());
			points = currentNode.getChildNodes().item(1).getChildNodes();
			int j = 0;
			point = points.item(j);
			while (point != null) {
				if (point.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println("Point       : "
							+ point.getNodeName());
					System.out.println("Point ID    : "
							+ point.getAttributes().getNamedItem("id")
									.getNodeValue());
					// while(point != null) {

					// }
					System.out.println("First Child : "
							+ point.getFirstChild().getNextSibling()
									.getNodeName());
					System.out.println("First Value : "
							+ point.getFirstChild().getNextSibling()
									.getTextContent());
					System.out.println("Second Child : "
							+ point.getFirstChild().getNextSibling()
									.getNextSibling().getNextSibling()
									.getNodeName());
					System.out.println();
				}
				j++;
				point = points.item(j);
			}
			System.out.println();
			// System.out.println("Node" + l.)
			i++;
			currentNode = l.item(i);
		}
		System.out.println();
	}

}
