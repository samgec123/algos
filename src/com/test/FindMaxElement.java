package com.test;

public class FindMaxElement {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 15, 6, 7, 8};
        int result = a[0];
        System.out.println("Max is :" + max(result, a, 1));
    }

    private static int max(int result, int[] a, int i) {
        if (i > a.length - 1) {
            return result;
        } else {
            if (result < a[i]) {
                result = a[i];
            }
            return max(result, a, ++i);
        }

    }
}
