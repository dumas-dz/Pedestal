package com.dumas.pedestal.ms.algorithm.other;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author dumas
 * @date 2021/12/17 2:19 PM
 */
public class BubbleSortSolution {

    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 5};
        bubbleSort(arr);
        System.out.println("冒泡排序结果：" + Arrays.toString(arr));
    }

    public static void bubbleSortX(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
