package com.company.DesignMode.Consumer_Producer;

/**
 * Created by atomic on 8/28/2017.
 */
public class Product {
    private int id;
    public Product(int id){
        this.id = id;
    }

    public String toString(){
        return "Product: "+this.id;
    }
}
