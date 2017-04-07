package com.company.ToJson;

import com.company.ClassExtends.Person;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

/**
 * Created by eli9 on 4/7/2017.
 */
public class ToJson {
    private List<Person> listP = new ArrayList<>();

    public List<Person> getListP() {
        return listP;
    }

    public void setListP(List<Person> listP) {
        this.listP = listP;
    }

    public void addListP(Person p){
        listP.add(p);
    }

    public void toJson(){
        for (int i = 0; i < 10 ; i++) {
            Person p = new Person();
            p.setName("Name"+i);
            p.setAge(String.valueOf(i+10));
            p.setSex("man");
            addListP(p);
        }
        String json = JSONArray.fromObject(listP).toString();
        System.out.println(json);
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
    }

    public static void main(String[] args) {
        ToJson json = new ToJson();
        json.toJson();
    }
}
