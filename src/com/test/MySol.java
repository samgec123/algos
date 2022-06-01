package com.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MySol {


    /*
     * Complete the 'larrysArray' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY A as parameter.
     */

    public static String larrysArray(List<Integer> a) {
        // Write your code here
        int[] inputArr = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            inputArr[i] = a.get(i);
        }

        for (int i = 0; i < a.size(); i++) {
            int pos = findPosition(inputArr, i + 1);
            while (pos > i) {
                //rotate
                rotate(inputArr, pos, i - 1);
                pos--;
            }
        }


        int counter = 0;
        for (int i = 0; i < inputArr.length; i++) {
            if (correctPos(inputArr, i)) {
                counter++;
            }
        }
        if (counter == inputArr.length) {
            return ("YES");
        } else {
            return ("NO");
        }
    }

    private static int findPosition(int[] inputArr, int i) {
        for (int j = i - 1; j < inputArr.length; j++) {
            if (inputArr[j] == i) {
                return j;
            }
        }
        return -1;
    }

    public static boolean correctPos(int[] arr, int index) {
        return arr[index] == (index + 1);
    }

    public static int[] rotate(int[] arr, int pos, int correctPosLoc) {
        if (pos > arr.length - 1) {
            return arr;
        } else if ((correctPosLoc > -1) && (pos - 2 == correctPosLoc)) {
            rotate(arr, pos + 1, correctPosLoc);
        } else if (pos > 1) {
            int tmp = arr[pos];
            arr[pos] = arr[pos - 2];
            arr[pos - 2] = arr[pos - 1];
            arr[pos - 1] = tmp;
        } else if (pos == 1) {
            int tmp = arr[pos];
            arr[pos] = arr[pos + 1];
            arr[pos + 1] = arr[pos - 1];
            arr[pos - 1] = tmp;
        } else if (pos == 0) {
            int tmp = arr[pos];
            arr[pos] = arr[pos + 1];
            arr[pos + 1] = arr[pos + 2];
            arr[pos + 2] = tmp;
        }
        return arr;
    }


    public static void main(String[] args) throws IOException {
        String result = larrysArray(Arrays.asList(8, 10, 6, 11, 7, 1, 9, 12, 3, 5, 13, 4, 2));
        System.out.println(result);
    }
}