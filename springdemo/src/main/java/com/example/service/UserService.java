package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void add() {

        System.out.println("正在添加数据");
        // 手动触发异常，不能被try{}catch(){}
        int i = 1 / 0;
    }
}
