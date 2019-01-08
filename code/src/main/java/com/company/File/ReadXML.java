package com.company.File;

import com.company.Entity.ExchangeEntity;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eli9 on 4/28/2017.
 * 解析xml的方法
 */
public class ReadXML {
    private static final String filePath = "test.xml";
    private static final String idsPath = "IdList.xml";
    private static final String exchangeList = "ConfigFiles-XML4HardCode-ExchangeList.xml";
    public static void xPathRead() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            InputStream in = ReadXML.class.getClassLoader().getResourceAsStream(filePath);
            org.w3c.dom.Document doc = builder.parse(in);

            XPathFactory factory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xPath = factory.newXPath();
            String xpathDataMessage = "/ArrayOfDataMessage/DataMessage[2]";
            String xpathDataGroup = "/ArrayOfDataMessage/DataMessage[1]";
            XPathExpression expr = xPath.compile(xpathDataGroup);
            NodeList element = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dom4j的xpath方法。 xml文件是 test.xml 格式简单
     */
    public static void xPathDom4j() {
        try {
            String xpathExpr = "/ArrayOfDataMessage/DataMessage[2]";
            SAXReader reader = new SAXReader();
            InputStream in = ReadXML.class.getClassLoader().getResourceAsStream(filePath);

            org.dom4j.Document doc = reader.read(in);
            XPath xpath = new Dom4jXPath(xpathExpr);
            List results = xpath.selectNodes(doc);
            for (Object result : results) {
                if (result instanceof Element) {
                    Element node = (Element) result;
                    Iterator entityIt = node.elementIterator();

                    while(entityIt.hasNext()){
                        Element child = (Element)entityIt.next();
                        String nodeName = child.getName();

                        if("DataGroup".equals(nodeName)){
                            System.out.println("DataGroup: "+child.getStringValue());
                        }
                        if("Subject".equals(nodeName)){
                            System.out.println("Subject: "+child.getStringValue());
                        }

                    }
                } else {
                    System.out.println(result);
                }
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void xpathDom4jComplex(){
        String xpathExpr = "/MessageSet/Message/Entity";
        SAXReader reader = new SAXReader();
        InputStream in = ReadXML.class.getClassLoader().getResourceAsStream(idsPath);

        try {
            org.dom4j.Document doc = reader.read(in);
            org.jaxen.XPath xPath = new Dom4jXPath(xpathExpr);
            List results = xPath.selectNodes(doc);
            System.out.println("List Size: "+results.size());
            for(Object o: results){
                if( o instanceof Element){
                    Element element = (Element)o;
                    Iterator it = element.elementIterator();
                    while(it.hasNext()){
                        Element child = (Element)it.next();
                        String nodeName = child.getName();
                        if("Id".equals(nodeName)){
                            System.out.println("Id: "+child.getStringValue());
                        }
                        if("MessageType".equals(nodeName)){
                            System.out.println("Date: "+child.attribute("Date").getStringValue());
                            System.out.println("MessageType: "+child.getStringValue()+"\n");
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (JaxenException e) {
            e.printStackTrace();
        }
    }

    public static List<ExchangeEntity> getExchangeList(){
        String xpathExpr = "/ExchangeList/Exchange";
        SAXReader reader = new SAXReader();
        InputStream in = ReadXML.class.getClassLoader().getResourceAsStream(exchangeList);
        List<ExchangeEntity> result = new ArrayList<>();
        try{
            org.dom4j.Document doc = reader.read(in);
            XPath xPath = new Dom4jXPath(xpathExpr);
            List results = xPath.selectNodes(doc);
            System.out.println("List Size: " + results.size());
            for (Object o : results) {
                if (o instanceof Element) {
                    Element element = (Element) o;
                    List attrList = element.attributes();
                    ExchangeEntity entity = new ExchangeEntity();
                    for(int i=0; i<attrList.size(); i++){
                        Attribute attr = (Attribute)attrList.get(i);
                        System.out.println(attr.getName() + "=" + attr.getValue());
                        if("ExchangeId".equals(attr.getName())){
                            entity.setExchangeId(attr.getValue());
                        }
                        if("ExchangeGlobalId".equals(attr.getName())){
                            entity.setExchangeGlobalId(attr.getValue());
                        }
                        if("MIC".equals(attr.getName())){
                            entity.setMIC(attr.getValue());
                        }
                        if("ExchangeName".equals(attr.getName())){
                            entity.setExchangeName(attr.getValue());
                        }
                        if("CountryId".equals(attr.getName())){
                            entity.setCountryId(attr.getValue());
                        }
                        if("CountryName".equals(attr.getName())){
                            entity.setCountryName(attr.getValue());
                        }
                        if("RegionId".equals(attr.getName())){
                            entity.setRegionId(attr.getValue());
                        }
                        if("RegionName".equals(attr.getName())){
                            entity.setRegionName(attr.getValue());
                        }
                    }
                    result.add(entity);
                    System.out.println("\n");
                }
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return result;
    }
}

