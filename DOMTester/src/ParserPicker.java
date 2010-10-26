
public class ParserPicker {


	public static final String XML_FILE = "http://www.fernferret.com/samplexml.xml";
	public static final String XML_FILE2 = "http://137.112.112.211:4567/point/get";
	public static final String XML_FILE3 = "http://www.fernferret.com/heritage.xml";
	public static final String XML_SCHEMA = "http://www.fernferret.com/mtmSchema.xsd";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOMTester dTest = new DOMTester(XML_FILE, XML_SCHEMA);
		dTest.parse();
		//JDOMTester jTest = new JDOMTester(XML_FILE, XML_SCHEMA);
		
	}
}