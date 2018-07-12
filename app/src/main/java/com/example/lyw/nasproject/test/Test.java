package com.example.lyw.nasproject.test;

/**
 * Created by Administrator on 2018/6/26.
 */

public class Test {

    public static void main(String[] args){
        int[] arr = {1,4,5,2,5,6};
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}
