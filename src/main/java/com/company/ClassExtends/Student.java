package com.company.ClassExtends;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by eli9 on 4/8/2017.
 */
public class Student {
    public String id;
    public String name;
    public Student() {
    }
    public Student(String id,String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object obj) {
        Student s=(Student)obj;
        return id.equals(s.id) && name.equals(s.name);
    }
    @Override
    public int hashCode() {
        String in = id + name;
        return in.hashCode();
    }

    public static void main(String[] args) {
        List<Student> stu = new ArrayList<Student>();
        stu.add(new Student("1","yi"));
        stu.add(new Student("3","san"));
        stu.add(new Student("3","san"));
        stu.add(new Student("2","er"));
        stu.add(new Student("2","er"));
        //set集合保存的是引用不同地址的对象
        Set<Student> ts = new HashSet<Student>();
        ts.addAll(stu);

        for (Student student : ts) {
            System.out.println(student.getId()+"-"+student.getName());
        }
    }
}