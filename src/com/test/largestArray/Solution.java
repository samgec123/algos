package com.test.largestArray;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Result {

    /*
     * Complete the 'largestPermutation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static List<Integer> largestPermutation(int k, List<Integer> arr) {
        // Write your code here
        int maxVal = -1;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i), i);
        }
        int arrSize = arr.size();
        //find max and min
        for (int i = 0; i < arrSize; i++) {
            if (k > 0) {
                //put that in i location
                int maxLoc = map.get(arrSize - i);
                if (maxLoc != i) {
                    int tmp = arr.get(i);
                    arr.set(i, arr.get(maxLoc));
                    arr.set(maxLoc, tmp);
                    map.put(arr.get(maxLoc), maxLoc);
                    map.put(arr.get(i), i);
                    k--;
                }
            } else {
                break;
            }

        }
        return arr;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        List<Integer> result = Result.largestPermutation(2, Arrays.asList(4, 2, 3, 5, 1));
        result.stream().forEach(System.out::println);
    }
}
