import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by eli9 on 3/1/2017.
 */
public class XML {
    private String URL = "http://ownership.morningstar.com/ownershipdata/api/GetData.aspx?function=GetOwnershipData&cusip=001084102&ownertype=MutualFund";

    public XML() {
    }

    public static void main(String[] args) {
        XML xml = new XML();
        xml.processURL();
    }

    public void processURL() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(String.valueOf(URL));

            if (document != null) {
                Element rootElement = document.getDocumentElement();
                //NodeList childNodesList = rootElement.getChildNodes();
                //System.out.println(childNodesList.getLength());

                NodeList OwnerNodeList = rootElement.getElementsByTagName("Owner");
                //int LengthOwnerNodeList = OwnerNodeList.getLength();

                for (int i = 0; i < 2; i++) {
                    Element OwnerNode = (Element) OwnerNodeList.item(i);
                    //String value = OwnerNode.getNodeName() + ":" + OwnerNode.getNodeValue();
                    //System.out.println(value);
                    int OwnerNameLenghth = OwnerNode.getElementsByTagName("OwnerName").getLength();
                    if(OwnerNameLenghth > 0){
                        String OwnerName = OwnerNode.getElementsByTagName("OwnerName").item(0).getTextContent();
                        System.out.println(OwnerName);
                    }

                    Node OwnerNameNode = OwnerNode.getElementsByTagName("OwnerName").item(0);
                    System.out.println(OwnerNameNode.getChildNodes().item(0).getNodeValue());

                    NodeList nodeList = OwnerNode.getChildNodes();
                    System.out.println(nodeList.getLength());//all child nodes length

                    String CValue = ((Element)((Element)OwnerNodeList.item(0)).getElementsByTagName("B").item((0)))
                            .getElementsByTagName("C").item(0).getTextContent();

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}