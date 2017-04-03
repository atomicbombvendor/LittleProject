package com.morningstar.Dao.InjectTest;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by eli9 on 4/3/2017.
 */
@Named
public class House {
    @Inject
    private Person person;

    public House(){
        System.out.println("This is House construction function");
    }

    public Person getPerson() {
        return person;
    }
}
