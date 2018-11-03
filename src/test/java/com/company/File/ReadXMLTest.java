package com.company.File;

import org.junit.Test;

/**
 * Created by atomic on 4/28/2017.
 */
public class ReadXMLTest {
    @Test
    public void xpathDom4jComplex() throws Exception {
        ReadXML.xpathDom4jComplex();
    }

    @Test
    public void xPathDoc() throws Exception {
        ReadXML.xPathDom4j();
    }

    @Test
    public void xPathRead() throws Exception {
        ReadXML.xPathRead();
    }

    @Test
    public void getExchangeList(){
        ReadXML.getExchangeList();
    }

}