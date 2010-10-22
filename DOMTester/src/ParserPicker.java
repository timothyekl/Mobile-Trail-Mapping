
public class ParserPicker {


	public static final String XML_FILE = "http://www.fernferret.com/samplexml.xml";
	public static final String XML_SCHEMA = "http://www.fernferret.com/mtmSchema.xsd";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOMTester dTest = new DOMTester(XML_FILE, XML_SCHEMA);
		dTest.parse();
	}

}
