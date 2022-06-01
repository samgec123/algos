package com.test;

import java.util.Arrays;
import java.util.Date;

public class InsertionSort {
    public static void main(String[] args) {

        int[] input = new int[10000];
        // Random random = new Random();
        // random.setSeed(23456789);
        Date startDate = new Date();
        for (int i = 0; i < 10000; i++) {
            //input[i] = random.nextInt(200000);
            input[i] = 1000000 - i;
        }
        int[] output = insertionSort(input);
        Date endDate = new Date();
        System.out.println("Total time taken is:" + (endDate.getTime() - startDate.getTime()));
        Arrays.stream(output).forEach(System.out::println);
    }

    private static int[] insertionSort(int[] input) {
        if (input.length <= 0) throw new IllegalArgumentException("Arrays should contain more than 1 element");
        if (input.length == 1) {
            return input;
        }
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    int tmp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = tmp;
                }
            }
        }
        return input;
    }
}
