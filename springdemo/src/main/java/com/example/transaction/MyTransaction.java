package com.example.transaction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MyTransaction {

    private String status = "";

    private String name = "";

    public void begin(){
        System.out.println(name + "开启事务");
        this.status = "开启事务";
    }

    public void commit(){
        System.out.println(name + "提交事务");
        this.status = "提交事务";
    }

    public void rollback(){
        System.out.println(name + "回滚事务");
        this.status = "回滚事务";
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
