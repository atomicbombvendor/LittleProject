package com.company.RandomNumber;

/**
 * Created by ZZ on 2017/4/11.
 */

/**
 * 为了测试条件退出。当满足条件的时候，就退出，不然执行程序直到条件满足
 */
public class conditionsExit {

    public static void main(String[] args) {
        conditionsExit.exitWhenFillConditionFor();
    }

    /**
     * 当条件满足是退出循环
     */
    public static void exitWhenFillCondition() {
        boolean flag = true;
        int result = 0;
        while (flag) {
            try {
                result = RandomNum.getRandom1To10ThrowException();
            } catch (Exception e) {

            }
            if (result == 200) {
                flag = false;
            }
        }
        if (flag == false) {
            System.out.println("exit!");
        }
    }

    public static void exitWhenFillConditionFor() {
        int runTimes = 0;
        int reRunTimes = 10;
        int result = 0;
        while (runTimes < reRunTimes) {
            try {
                result = RandomNum.getRandom1To10ThrowException();
            } catch (Exception e) {
                //此处并不捕获异常情况。
            }finally {
                System.out.println("this is finally!");
            }
            System.out.println("runTimes is "+runTimes);
            if (result == 200) {
                break;
            }
            runTimes++;
        }

        if (result == 200) {
            System.out.println("200 OK!");
        }else{
            System.out.println("runTimes: " +runTimes+" should be more than 10 times!");
        }
    }
}
