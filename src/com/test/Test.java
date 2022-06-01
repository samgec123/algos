package com.test;

/**
 * This is an example of binary search
 */
public class Test {
    public static void main(String... args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int numToSearch = 3;
        int index = search(nums, numToSearch, 0, nums.length-1);
        if (index == -1) {
            System.out.println("Not found");
        } else {
            System.out.println("found " + nums[index] + " at " + index);
        }

    }

    private static int search(int[] nums, int numToSearch, int low, int high) {
        int currentIndex = (low + high) / 2;
        if (low > high) {
            return -1;
        }
        if (numToSearch == nums[currentIndex]) {
            return currentIndex;
        } else if (numToSearch < nums[currentIndex]) {
            return search(nums, numToSearch, low, currentIndex - 1);
        } else {
            return search(nums, numToSearch, currentIndex + 1, high);
        }
    }
}
