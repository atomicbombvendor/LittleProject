package com.company.DesignMode.Consumer_Producer;

/**
 * Created by atomic on 8/28/2017.
 */
public class Producer implements Runnable {
    private String name;
    private Storage storage = null;

    public Producer(String name, Storage s){
        this.name = name;
        this.storage = s;
    }

    @Override
    public void run() {
        try{
            while(true){
                Product product = new Product((int) (Math.random()*1000));
                System.out.println(name + " ready to create " + product.toString());
                this.storage.push(product);
                System.out.println(name + " had produced "+product.toString());
                System.out.println("=======");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
