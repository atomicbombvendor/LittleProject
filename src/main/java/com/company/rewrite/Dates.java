package com.company.rewrite;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by eli9 on 3/31/2017.
 */
@Named
public class Dates {

    @Inject
    public static MyData myData;

    public static void main(String[] args) {
        MyData myData = Dates.myData;
        System.out.println(myData == null);
        System.out.print(myData.getAge());
    }

}
