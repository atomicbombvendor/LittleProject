package com.company.Thread;

import java.util.Scanner;

/**
 * Created by eli9 on 11/6/2017.\
 */
public class TestBanker {

    public static void main(String[] args) {
        // 不能添加新的进程，进程的数量和进程需要的最多的资源也是固定的，资源的总量也是固定的。
        // 只能添加资源的请求的数量。
        boolean Choose = true;
        String C;
        Scanner in = new Scanner(System.in);
        Banker banker = new Banker();
        System.out.println("这是一个三个进程，初始系统可用三类资源为{10,8,7}的银行家算法：");

        banker.setSystemVariable();
        while (Choose) {
            banker.setRequest();
            System.out.println("您是否还要进行请求：y/n?");
            C = in.nextLine();
            if (C.endsWith("n")) {
                Choose = false;
            }
        }
    }
}

//这是一个三个进程，初始系统可用三类资源为{10,8,7}的银行家算法：
//        请设置各进程的最大需求矩阵Max：
//        请输入进程P0的最大资源需求量：
//        8 7 5
//        请输入进程P1的最大资源需求量：
//        5 2 5
//        请输入进程P2的最大资源需求量：
//        6 6 2
//        请设置请各进程分配矩阵Alloction：
//        晴输入进程P0的分配资源量：
//        3 2 0
//        晴输入进程P1的分配资源量：
//        2 0 2
//        晴输入进程P2的分配资源量：
//        1 3 2
//        Available=Available-Alloction.
//        Need=Max-Alloction.
//        此时资源分配量如下：
//        进程 Max Alloction Need Available
//        P0 8 7 5 | 3 2 0 | 5 5 5 | 4 3 3
//        P1 5 2 5 | 2 0 2 | 3 2 3 |
//        P2 6 6 2 | 1 3 2 | 5 3 0 |
//        进程 Work Alloction Need Work+Alloction
//        P1 4 3 3 | 2 0 2 | 3 2 3 | 6 3 5
//        P2 6 3 5 | 1 3 2 | 5 3 0 | 7 6 7
//        P0 7 6 7 | 3 2 0 | 5 5 5 | 10 8 7
//        此时存在一个安全序列：P1 P2 P0 故当前可分配！
//        请输入请求资源的进程编号：
//        0
//        请输入请求各资源的数量：
//        1 0 0
//        即进程P0对各资源请求Request：(1,0,0).
//        此时资源分配量如下：
//        进程 Max Alloction Need Available
//        P0 8 7 5 | 4 2 0 | 4 5 5 | 3 3 3
//        P1 5 2 5 | 2 0 2 | 3 2 3 |
//        P2 6 6 2 | 1 3 2 | 5 3 0 |
//        现在进入安全算法：
//        进程 Work Alloction Need Work+Alloction
//        P1 3 3 3 | 2 0 2 | 3 2 3 | 5 3 5
//        P2 5 3 5 | 1 3 2 | 5 3 0 | 6 6 7
//        P0 6 6 7 | 4 2 0 | 4 5 5 | 10 8 7
//        此时存在一个安全序列：P1 P2 P0 故当前可分配！