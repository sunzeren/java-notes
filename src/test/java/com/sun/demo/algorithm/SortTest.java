package com.sun.demo.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author by Sun, Date on 2020/8/21.
 * PS: Not easy to write code, please indicate.
 * 算法排序
 */
public class SortTest {

    @Test
    public void sortTet() {
        int[] arr = {0, 1, 3, 4, 67, 8, 24};

        BubbleSort(arr);

        System.out.println(Arrays.toString(arr));

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public void BubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];

                    arr[j] = arr[j + 1];

                    arr[j + 1] = temp;
                }
            }
        }
    }


}
