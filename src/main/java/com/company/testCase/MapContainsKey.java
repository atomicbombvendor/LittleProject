package com.company.testCase;

import com.company.Entity.ExchangeEntity;
import com.company.Entity.Person;

import java.util.*;

/**
 * Created by atomic on 8/10/2017.
 */
public class MapContainsKey {

    public static boolean testContainsKey(Person person){
        Map<Person, String> map = new HashMap<>();
        Person<String> p1 = new Person<>("name1", 11, "Value");
        Person<String> p2 = new Person<>("name2", 11, "Value");
        Person<String> p3 = new Person<>("name3", 11, "Value");

        map.put(p1, "value1");
        map.put(p2, "value2");
        map.put(p3, "value3");

        Set set = map.entrySet();
        List<Map.Entry<Person, String>> list = new ArrayList<>(map.entrySet());
        Set s = map.entrySet();
        for (Object o : s) {
            ((Map.Entry)o).getKey();
        }
        Person p = list.get(0).getKey();

        return map.containsKey(person);
    }

    public static void main(String[] args) {
        Person<String> t1 = new Person<>("name1", 11, "Value");
        Person<Integer> t2 = new Person<>("name1", 11, 13);

        System.out.println(testContainsKey(t1));
        System.out.println(testContainsKey(t2));

        ExchangeEntity entity = new ExchangeEntity();
        entity.setCountryName("Name");
        ExchangeEntity entity2 = new ExchangeEntity();
        entity2.setCountryId("C1");

        System.out.println(testContainsKey(entity));
        System.out.println(testContainsKey(entity2));
    }

    public static boolean testContainsKey(ExchangeEntity entity){
        Map<ExchangeEntity, String> map = new HashMap<>();
        ExchangeEntity e1 = new ExchangeEntity();e1.setCountryId("C1");
        ExchangeEntity e2 = new ExchangeEntity();e1.setCountryId("c2");
        ExchangeEntity e3 = new ExchangeEntity();e1.setCountryId("c3");

        System.out.println("Put1: "+map.put(e1, "value1"));
        System.out.println("Put2: "+map.put(e2, "value2"));
        System.out.println("Put3: "+map.put(e3, "value3"));

        System.out.println("Put1: "+map.put(e1, "value4"));

        return map.containsKey(entity);
    }
}
