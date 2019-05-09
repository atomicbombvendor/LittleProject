package com.company.arithmetic.eightSorts;

public class SimpleSelectSort {

    public void simpleSelectSort(Integer arr[]){

        for (int i=0; i<arr.length-1; i++){
            int min = i;
            for (int j=i+1; j< arr.length; j++){
                if (arr[i] > arr[j]){
                    min = j;
                }
            }
            if (min != i){
                int temp = arr[min];      //交换操作
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

}
