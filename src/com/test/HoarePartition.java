package com.test;

import java.util.Arrays;
import java.util.Random;

public class HoarePartition {
    public static int partition(int[] arr, int lower, int higher, int pivotIndex) {
        if (lower > higher) {
            return -1;
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
            if (foundPivotIndex == pivotIndex) {
                return arr[foundPivotIndex];
            } else if (foundPivotIndex < pivotIndex) {
                return partition(arr, foundPivotIndex + 1, higher, pivotIndex);
            } else {
                return partition(arr, lower, foundPivotIndex - 1, pivotIndex);
            }
        }
    }

    public static void main(String... args) {
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(1000000);
        }
        int pivotIndex = 3;
        Arrays.stream(arr).forEach(System.out::println);
        System.out.println("#######################");
        System.out.println(partition(arr, 0, arr.length - 1, pivotIndex));
    }
}
