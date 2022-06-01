package com.test;

import java.util.Arrays;

public class MergeSort {
    //given two sorted arrays a and b, produce a new arrays which merges both
    public static int[] merge(int[] a, int startIndex, int middleIndex, int lastIndex) {
        int[] c = new int[lastIndex - startIndex + 1];
        int i = startIndex, j = middleIndex + 1;
        int k = 0;
        while (i <= middleIndex && j <= lastIndex) {
            if (a[i] > a[j]) {
                c[k++] = a[j++];
            } else {
                c[k++] = a[i++];
            }
        }

        //copy rest of the b
        while (j < lastIndex + 1) {
            c[k++] = a[j++];
        }
        while (i < middleIndex + 1) {
            c[k++] = a[i++];
        }
        k = 0;
        for (i = startIndex; i <= lastIndex; i++) {
            a[i] = c[k++];
        }
        return a;
    }

    public static void mergeSort(int[] a, int startIndex, int lastIndex) {
        int middle = (int) (startIndex + lastIndex) / 2;
        if (startIndex < lastIndex) {
            mergeSort(a, startIndex, middle);
            mergeSort(a, middle + 1, lastIndex);
            a = merge(a, startIndex, middle, lastIndex);
        }
    }

    public static void main(String... args) {
        int[] a = {2, 4, 34, 9, 8, 2, 234234, 34, 23, 4234, 23, 54, 456, 865, 342, 234, 12, 31, 3, 54, 3456, 456, 3, 41, 23, 123};
        mergeSort(a, 0, a.length - 1);
        Arrays.stream(a).forEach(System.out::println);
    }
}
