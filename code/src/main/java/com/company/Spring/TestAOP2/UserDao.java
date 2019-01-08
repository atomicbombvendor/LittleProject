package com.company.Spring.TestAOP2;

import org.springframework.stereotype.Component;

/**
 * Created by eli9 on 8/24/2017.
 */
@Component // 加入IOC容器
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("-----核心业务：保存！！！------");
    }
}
