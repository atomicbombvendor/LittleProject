package com.company.DesignMode.Consumer_Producer;

/**
 * Created by atomic on 8/28/2017.
 */
public class Consumer implements Runnable {

    private String name;
    private Storage storage = null;

    public Consumer(String name, Storage storage){
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println(name + " ready to use product");
                Product product = storage.pop();
                System.out.println(name + " had consume "+product.toString());
                System.out.println("=======");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
