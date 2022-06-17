package com.test;

public class Candies {
    public static void main(String[] args) {
        int[] arr = new int[]{
                7, 6, 4, 5, 9, 2
        };

        for (int i = 0; i < arr.length; i++) {
            //find indexes if lowest to highest
            System.out.println(getElemIndex(arr, 0, 5, i));
        }
    }

    public static int getElemIndex(int[] arr, int low, int high, int index) {
        if (low > high) {
            return -1;
        } else {
            int mid = (low + high) / 2;
            int i = low;
            int j = high;

            boolean loopContinue = true;
            int foundIndex = -1;
            while (loopContinue) {
                while (arr[i] < arr[mid]) {
                    i++;
                }
                while (arr[j] > arr[mid]) {
                    j--;
                }
                //swap
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }
                if (i >= j) {
                    //index is at j is the
                    foundIndex = j;
                    loopContinue = false;
                }
            }
            if (foundIndex == index) {
                return foundIndex;
            } else if (foundIndex > index) {
                return getElemIndex(arr, low, foundIndex - 1, index);
            } else {
                return getElemIndex(arr, foundIndex + 1, high, index);
            }
        }
    }
}
