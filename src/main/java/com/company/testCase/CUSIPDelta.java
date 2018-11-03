package com.company.testCase;

import java.io.File;
import java.net.URL;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * Created by atomic on 3/6/2017.
 */
public class CUSIPDelta {
    public static void main(String[] args) {
        String TransactionTimeOfLastRun = "2017030101121212";
        CUSIPDelta cusipDelta = new CUSIPDelta();
        URL cusipUrl = cusipDelta.getURL(TransactionTimeOfLastRun);
        if(cusipUrl != null){
            cusipDelta.getCUSIPEntityListFromURL(cusipUrl);
        }
    }

    public URL getURL(String TransactionTimeOfLastRun) {
        String SessionStarDate = "";
        try {
            String urlStr ="http://ownership.company.com/ownershipdata/API/GetDataChange.aspx?changetype=summary&mostrecent=true&startdate=20170101010101&enddate=20170301000000";
            URL url = new URL(urlStr);
            return url;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void getCUSIPEntityListFromURL(URL url){
        List<CUSIPEntity> cusipEntityList = new ArrayList<CUSIPEntity>();
        File inputXml = new File(String.valueOf(url));
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(url);
            Element root = document.getRootElement();

            //document.selectNodes()

            //List<Element> listElement = root.elements("");
            Element cusipsElement = root.element("Cusips");//获取根节点下面的节点
            List<Element> cusipList = cusipsElement.elements("CUSIP");//获取上级节点下面的CUSIP节点
//            for(Element e: cusipList){
//                System.out.println("当前节点名称：" + e.getName());// 当前节点名称
//                System.out.println("当前节点的内容：" + e.getTextTrim());// 当前节点内容
//            }

            List l = document.selectNodes("/Result/Cusips/CUSIP");
            Iterator it = l.iterator();
            while(it.hasNext()){
                Element e = (Element)it.next();
                System.out.println("当前节点名称：" + e.getName());// 当前节点名称
                System.out.println("当前节点的内容：" + e.getTextTrim());// 当前节点内容
                System.out.println("#############################");
            }

//            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
//            String SessionStarDate = date.format(new Date()).toString();
//            System.out.println(SessionStarDate);
    } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
