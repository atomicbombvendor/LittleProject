package com.company.DesignMode.Consumer_Producer2;

/**
 * Created by eli9 on 8/28/2017.
 */
public class Product {
    private int id;
    public Product(int id){
        this.id = id;
    }

    public String toString(){
        return "Product: "+ this.id;
    }
}