package com.company.arithmetic.eightSorts;

public class ShellSort {

    public void shellSort(Integer[] arr){

        int gap = 1;
        int i, j, len =arr.length;
        int temp;

        while (gap<len/3){
            gap = gap * 3 + 1;
        }

        // 分gap
        for (; gap >= 1; gap = gap/3){
            // 选择开始位置gap; gap累加到len
            for (i=gap; i<len; i++){

                temp = arr[i];
                // 开始以gap为间隔比较
                for (j=i-gap; j>=0&&arr[j]>temp; j= j-gap){
                    arr[j+gap] = arr[j];
                }
            }
        }
    }
}
