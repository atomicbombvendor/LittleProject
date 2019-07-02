package com.company.arithmetic.eightSorts;

public class HeadSort {

    public static void main(String[] args) {

        headSort(new int[]{2, 4, 44, 19, 33, 10, 8, 5, 3, 1, 4});
    }

    public static void headSort(int arr[]){

        for (int i=arr.length; i>0; i++){
            maxHead(arr, i);

            int temp = arr[0];      //堆顶元素(第一个元素)与Kn交换
            arr[0] = arr[i-1];
            arr[i-1] = temp;
        }
    }

    private static void maxHead(int arr[], int limit){

        if (arr.length<=0 || arr.length<limit) return;

        int parentIdx = limit/2;
        for (; parentIdx >=0; parentIdx--){
            if (parentIdx*2 >= limit){
                continue;
            }

            int left = parentIdx * 2;
            int right = (left+1)>= limit? left : (left+1);
            int maxChildId = arr[left] >= arr[right] ? left : right;
            if (arr[maxChildId] > arr[parentIdx]){
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
    }
}
