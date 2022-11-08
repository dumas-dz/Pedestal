package com.dumas.pedestal.ms.algorithm.other;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author dumas
 * @date 2021/12/17 4:45 PM
 */
public class SelectSortSolution {

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 每一轮选出一个最小值，然后排序
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    int temp = arr[j];
                    arr[j] = min;
                    min = temp;
                }
            }
            arr[i] = min;
        }
    }

    public static void main(String[] args) {
        int[] arr = {20, 40, 30, 10, 60, 50};
        selectSort(arr);
        System.out.println("选择排序的结果：" + Arrays.toString(arr));
    }

    public static void selectSortX(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    int temp = arr[j];
                    arr[j] = min;
                    min = temp;
                }
            }
        }
    }
}
