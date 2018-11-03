package com.company.DesignMode.ProxyDesign;

/**
 * Created by atomic on 8/29/2017.
 */
public class ProxyImage implements Image {

    RealImage realImage;
    String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null) {
            realImage = new RealImage(this.fileName);
        }
        realImage.display();
    }
}
