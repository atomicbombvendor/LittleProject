package com.company.arithmetic.eightSorts;

public class InsertSort {

    /**
     * 时间复杂度计算:
     * 第1次比较1次
     * 第2次比较2次
     * 第3次比较3次
     * ....
     * 第n-1次比较n-1次
     * 由上所得，总共比较n*(n-1)/2约等于n2
     * @param arr
     */
    public static void insertionSort(Integer[] arr){

        for (int i=1; i< arr.length; i++){
            int temp = arr[i];
            //从后往前
            for (int j=i; j>=0; j--){
                if (j>0 && arr[j-1] > temp){
                    //后移一位
                    arr[j] = arr[j-1];
                }else {
                    // 插入新元素
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        insertionSort(new Integer[]{8, 4, 3, 6, 9, 1});
    }
}
