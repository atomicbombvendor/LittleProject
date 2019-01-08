package com.company.testCase;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eli9 on 3/6/2017.
 */
public class URLCUSIP {
    private URL url;

    public URLCUSIP(){
        try {
            this.url = new URL("http://ownership.company.com/ownershipdata/API/GetDataChange" +
                    ".aspx?changetype=summary&mostrecent=true&startdate=20170101010101&enddate=20170301000000");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
