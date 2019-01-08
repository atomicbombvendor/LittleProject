package com.company.Thread;

import java.util.Scanner;

/**
 * Created by eli9 on 11/3/2017./
 */
public class Banker {

    private static final int MaxProcessNum = 3;
    private static final int MaxResourceKinds = 3;

    private int[] Available = {10, 8, 7};//系统可用的资源
    private int[][] Max = new int[MaxProcessNum][MaxResourceKinds];//各进程所需各类资源的最大需求
    private int[][] Allocation = new int[MaxProcessNum][MaxResourceKinds];//已分配的资源
    private int[][] Need = new int[MaxProcessNum][MaxResourceKinds];//各进程还需要的资源
    private int[][] Request = new int[MaxProcessNum][MaxResourceKinds];//

    private int num = 0;
    private Scanner in = new Scanner(System.in);

    void setSystemVariable(){//设置各初始系统变量，并判断是否处于安全状态
        setMax();
        setAllocation();
        printSystemVariable();
        securityAlgorithm();
    }

    private void setMax(){
        System.out.println("请设置各进程的最大需求矩阵Max: ");
        for(int i = 0; i< MaxProcessNum; i++){
            System.out.println("请输入进程P"+i+"的最大资源需求量");
            for(int j = 0; j < MaxResourceKinds; j++){
                Max[i][j] = in.nextInt();
            }
        }
    }

    private void setAllocation(){
        System.out.println("请设置各进程分配矩阵Allocation: ");
        for(int i = 0; i< MaxProcessNum; i++){
            System.out.println("请输入进程P"+i+"的分配资源量");
            for (int j = 0; j< MaxResourceKinds; j++){
                Allocation[i][j] = in.nextInt();
            }
        }

        System.out.println("Available = Available - Allocation");
        System.out.println("Need = Max - Allocation");
        for(int i = 0; i< MaxResourceKinds; i++){
            for(int j = 0; j< MaxProcessNum; j++){
                Available[i] = Available[i] - Allocation[j][i];
            }
        }

        for(int i = 0; i< MaxProcessNum; i++){
            for(int j = 0; j< MaxResourceKinds; j++){
                Need[i][j] = Max[i][j] - Allocation[i][j];
            }
        }
    }

    private void printSystemVariable(){

        System.out.println("此时资源分配量如下: ");
        System.out.println("进程  Max  Allocation Need Available");
        for(int i = 0; i< MaxProcessNum; i++){
            System.out.print("P "+i+" ");
            for(int j = 0; j< MaxResourceKinds; j++){
                System.out.print(Max[i][j]+" ");
            }
            System.out.println("| ");
            for(int j = 0; j< MaxResourceKinds; j++){
                System.out.print(Allocation[i][j] + " ");
            }
            System.out.println("| ");
            if(i==0){
                for(int j = 0; j< MaxResourceKinds; j++){
                    System.out.print(Available[j]+" ");
                }
            }
        }
        System.out.println();
    }

    /**
     * 设置请求资源量Request
     */
    void setRequest(){

        System.out.println("请输入请求资源的进程编号(0,1,2): ");
        num = in.nextInt();
        int processNum = num;
        System.out.println("请输入请求各资源的数量: ");
        for(int j = 0; j< MaxResourceKinds; j++){
            Request[num][j] = in.nextInt();
        }

        System.out.println("即进程P" + num + "对各资源请求Request：(" + Request[num][0] + ","
                + Request[num][1] + "," + Request[num][2] + ").");
        bankerAlgorithm(processNum);
    }

    private void bankerAlgorithm(int processNum){
        boolean isAccept = true;

        if (checkRequestLessThanNeed(processNum)) {//判断Request是否小于Need

            if(checkRequestLessThanAvailable(processNum)){//判断Request是否小于Alloction

                for(int i=0; i< MaxResourceKinds; i++){
                    Available[i] -= Request[processNum][i];
                    Allocation[processNum][i] += Request[processNum][i];
                    Need[processNum][i] -= Request[processNum][i];
                }
            }else{
                System.out.println("当前没有足够的资源可分配，进程P" + num + "需等待。");
                isAccept = false;
            }
        }else {
            System.out.println("进程P" + num + "请求已经超出最大需求量Need.");
            isAccept = false;
        }

        if(isAccept){
            printSystemVariable();
            System.out.println("现在进入安全算法");
            securityAlgorithm();
        }
    }

    private void securityAlgorithm() {//安全算法

        int[] ProcessDoneWillFree = new int[MaxProcessNum];

        boolean[] isFinish = {false, false, false};
        int hadDoneProcessOrder = 0;//完成进程数
        int circle = 0;//循环圈数
        int[] ProcessFinishOrder = new int[MaxProcessNum];

        System.arraycopy(Available, 0, ProcessDoneWillFree, 0, MaxProcessNum);

        boolean isAllProcessHadDone = true;

        while (hadDoneProcessOrder < MaxProcessNum) {
            if (isAllProcessHadDone) {
                System.out.println("    进程  " + "     Work  " + "     Allocation " + "    Need  " + "     ProcessDoneWillFree ");
            }
            isAllProcessHadDone = false;//按照进程的顺序P0->P1 -> P2的顺序，找到可以完成的进程。把进程的标记号保存到ProcessFinishOrder
            for (int i = 0; i < MaxProcessNum; i++) {
                if (checkFinishValid(i, ProcessDoneWillFree, isFinish)) {
                    System.out.print("P" + i + " ");
                    for (int k = 0; k < MaxResourceKinds; k++) {
                        System.out.print(ProcessDoneWillFree[k] + " ");
                    }

                    System.out.print("|  ");

                    for (int j = 0; j < MaxResourceKinds; j++) {
                        ProcessDoneWillFree[j] += Allocation[i][j];
                    }

                    isFinish[i] = true;//当前进程能满足时
                    ProcessFinishOrder[hadDoneProcessOrder] = i;//设置当前序列排号

                    hadDoneProcessOrder++;//满足进程数加1

                    for (int j = 0; j < MaxResourceKinds; j++) {
                        System.out.print(Allocation[i][j] + "  ");
                    }
                    System.out.print("|  ");
                    for (int j = 0; j < MaxResourceKinds; j++) {
                        System.out.print(Need[i][j] + "  ");
                    }
                    System.out.print("|  ");
                    for (int j = 0; j < MaxResourceKinds; j++) {
                        System.out.print(ProcessDoneWillFree[j] + "  ");
                    }
                    System.out.println();
                }
            }

            hadDoneProcessOrder++;//在最后一次的循环中，所有前面所有的进程都已经结束了，所以最后一个进程一定可以满足。

            if (hadDoneProcessOrder == MaxProcessNum) {
                System.out.print("此时存在一个安全序列：");
                for (int i = 0; i < MaxProcessNum; i++) {//输出安全序列
                    System.out.print("P" + ProcessFinishOrder[i] + " ");
                }
                System.out.println("故当前可分配！");
                break;//跳出循环
            }

            if (hadDoneProcessOrder < circle) {//判断完成进程数是否小于循环圈数
                System.out.println("当前系统处于不安全状态，故不存在安全序列。");
                break;//跳出循环
            }
        }
    }

    private boolean checkRequestLessThanAvailable(int processNum){

        boolean isRequestValid = true;
        for (int i = 0; i < MaxResourceKinds; i++) {
            if(Request[processNum][i] > Available[i]){
                isRequestValid = false;
            }
        }
        return isRequestValid;
    }

    private boolean checkRequestLessThanNeed(int processNum){

        boolean isRequestValid = true;
        for (int i = 0; i < MaxResourceKinds; i++) {
            if(Request[processNum][i] > Need[processNum][i]){
                isRequestValid = false;
            }
        }
        return isRequestValid;
    }

    private boolean checkFinishValid(int processNum, int[] ProcessDoneWillFree, boolean[] isFinish){

        boolean isRequestValid = true;
        for (int i = 0; i < MaxProcessNum; i++) {
            if(!isFinish[processNum] && Need[processNum][i] > ProcessDoneWillFree[i]){
                isRequestValid = false;
            }
        }
        return isRequestValid;

    }
}
