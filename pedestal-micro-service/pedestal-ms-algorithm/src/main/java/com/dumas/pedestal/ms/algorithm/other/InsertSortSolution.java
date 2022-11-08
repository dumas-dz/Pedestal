package com.dumas.pedestal.ms.algorithm.other;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author dumas
 * @date 2021/12/17 2:41 PM
 */
public class InsertSortSolution {

    public static void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 5};
        insertSort(arr);
        System.out.println("直接插入排序结果：" + Arrays.toString(arr));
    }

    public static void insertSortX(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
}
