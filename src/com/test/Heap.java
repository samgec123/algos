package com.test;

import java.util.Arrays;

public class Heap {
    public static void main(String[] args) {
        int[] nums = {3, 46, 1, 2, 3, 6, 8, 3, 34, 67, 98, 456, 890, 1};
        int[] results = Arrays.copyOfRange(nums, 0, nums.length);
        int[] sorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            results = heapify(results);
            sorted[i] = results[0];
            results = Arrays.copyOfRange(results, 1, results.length);
        }
        Arrays.stream(sorted).forEach(System.out::println);
    }

    //head has parent at i so child comes at 2i+1 and 2i+2
    private static int[] heapify(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            while (j > 0) {
                int parentIndex = 0;
                if (j % 2 == 0) {
                    parentIndex = (j - 2) / 2;
                } else {
                    parentIndex = (j - 1) / 2;
                }
                if (nums[j] > nums[parentIndex]) {
                    //swap
                    int tmp = nums[j];
                    nums[j] = nums[parentIndex];
                    nums[parentIndex] = tmp;
                    j = parentIndex;
                    continue;
                }
                j = 0;
            }
        }
        return nums;
    }
}
