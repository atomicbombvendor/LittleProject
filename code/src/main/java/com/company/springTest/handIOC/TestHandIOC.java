package com.company.springTest.handIOC;

public class TestHandIOC {

    public static void main(String[] args) throws Exception {

//        String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();

        SimpleIOC test = new SimpleIOC("C:\\Users\\haoyu\\Documents\\Project\\LittleProject\\code\\src\\main\\java\\com\\company\\springTest\\handIOC\\ioc.xml");
        Wheel wheel = (Wheel) test.getBean("wheel");
        System.out.println(wheel);

        Car car = (Car) test.getBean("car");
        System.out.println(car);
    }
}
