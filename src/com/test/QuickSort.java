package com.test;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static int[] quicksort(int[] arr, int lower, int higher) {
        if (lower > higher) {
            return arr;
        } else {
            int middle = (lower + higher) / 2;
            int pivotEl = arr[middle];
            int i = 0, j = higher;
            boolean loop = true;
            int foundPivotIndex = 0;
            while (loop) {
                while (arr[i] < pivotEl) {
                    i++;
                }
                while (arr[j] > pivotEl) {
                    j--;
                }
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                if (i >= j) {
                    foundPivotIndex = j;
                    loop = false;
                }
            }
            quicksort(arr, lower, foundPivotIndex - 1);
            quicksort(arr, foundPivotIndex + 1, higher);
        }
        return arr;
    }

    public static void main(String... args) {
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(1000000);
        }
        quicksort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
