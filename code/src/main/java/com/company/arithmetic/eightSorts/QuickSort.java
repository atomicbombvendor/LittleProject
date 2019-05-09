package com.company.arithmetic.eightSorts;

public class QuickSort {

    public static void quickSort(int[] arr, int low, int high){

        if (arr.length<=0) return;
        if (low >= high) return;
        int left= low;
        int right = high;

        int temp = arr[left];

        while(left < right){

            while(left<right && arr[right] >= temp){
                right--;
            }
            swap(arr, left, right);
            while (left < right && arr[left] <= temp) {
                left++;
            }
            swap(arr, left, right);
        }

        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
    }

    private static void swap(int[] L, int i, int j) {

        int temp=L[i];
        L[i]=L[j];
        L[j]=temp;
    }

    public static void main(String[] args) {

        int[] a = new int[]{475,4,6,18,94,3,7,100,28,57};
        quickSort(a, 0, a.length-1);
        System.out.println(a.toString());
    }
}
