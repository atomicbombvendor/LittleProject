package com.company.arithmetic.eightSorts;

import java.util.Arrays;

public class MergingSort {

    public static int[] merginSort(int a[]){
        if (a.length <= 1){
            return a;
        }

        // 除2
        int num = a.length>>2;
        int[] left = Arrays.copyOfRange(a, 0, num);
        int[] right = Arrays.copyOfRange(a, num, a.length);
        return mergeTwoArray(merginSort(left), merginSort(right));
    }

    /**
     * 两端遍历比较大小
     * @param a1
     * @param a2
     * @return
     */
    private static int[] mergeTwoArray(int[] a1, int[] a2){
        int i = 0, j = 0, k = 0;
        int[] result = new int[a1.length + a2.length];
        while (i<a1.length && j<a2.length){
            if (a1[i] <= a2[j]){
                result[k++] = a1[i++];
            }else{
                result[k++] = a2[j++];
            }
        }

        // 移入多余的元素
        while(i < a1.length){
            result[k++] = a1[i++];
        }

        while(j < a2.length){
            result[k++] = a2[j++];
        }
        return result;
    }
}
