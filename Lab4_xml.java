import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Lab4_xml {

	public void readXML(String fileName)
	{
		try {
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("Borrowed_by");
			List<String> a = null;
			PrintWriter p = new PrintWriter("ParsedData.txt");
			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element sectionNode = (Element) fstNode;
					
					NodeList memberIdElementList = sectionNode.getElementsByTagName("MemberID");
					Element memberIdElmnt = (Element) memberIdElementList.item(0);
					NodeList memberIdNodeList = memberIdElmnt.getChildNodes();
					//System.out.println("MemberID : "  + ((Node) memberIdNodeList.item(0)).getNodeValue().trim());
					
					p.write(((Node) memberIdNodeList.item(0)).getNodeValue().trim() + ' ');
					//p.write(((Node) memberIdNodeList.item(0)).getNodeValue().trim());
					
					NodeList secnoElementList = sectionNode.getElementsByTagName("ISBN");
					Element secnoElmnt = (Element) secnoElementList.item(0);
					NodeList secno = secnoElmnt.getChildNodes();
					//System.out.println("ISBN : "  + ((Node) secno.item(0)).getNodeValue().trim());
					p.write(((Node) secno.item(0)).getNodeValue().trim() + ' ');
					
					NodeList codateElementList = sectionNode.getElementsByTagName("Checkout_date");
					Element codElmnt = (Element) codateElementList.item(0);
					NodeList cod = codElmnt.getChildNodes();
					//System.out.println("Checkout_date : "  + ((Node) cod.item(0)).getNodeValue().trim());
					p.write(((Node) cod.item(0)).getNodeValue().trim() + ' ');
					
					NodeList cidateElementList = sectionNode.getElementsByTagName("Checkin_date");
					Element cidElmnt = (Element) cidateElementList.item(0);
					NodeList cid = cidElmnt.getChildNodes();
					//System.out.println("Checkin_date : "  + ((Node) cid.item(0)).getNodeValue().trim());
					p.write(((Node) cid.item(0)).getNodeValue().trim() + '\n');
					System.out.println();
					
					//System.out.println("print list: " + a);
				}

			}
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String args[]){
		try {

			Lab4_xml showXML = new Lab4_xml();
			showXML.readXML ("/s/bach/a/class/cs430dl/Current/more_assignments/LabData/Libdata.xml");
		}catch( Exception e ) {
			e.printStackTrace();

		}//end catch

	}//end main

}//end class 
