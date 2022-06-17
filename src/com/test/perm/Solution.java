package com.test.perm;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

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
        int maxIndex = -1;

        //find max and min
        for (int i = 0; i < arr.size(); i++) {
            if (k > 0) {
                maxIndex = findMaxElIndex(arr, i);
                //put that in i location
                int tmp = arr.get(maxIndex);
                arr.set(maxIndex, arr.get(i));
                arr.set(i, tmp);
                k--;
            } else {
                break;
            }
        }


        return arr;

    }

    public static int findMaxElIndex(List<Integer> list, int index) {
        int maxIndexElement = list.get(index);
        int maxIndex = index;
        int i = index + 1;
        while (i < list.size()) {
            if (maxIndexElement < list.get(i)) {
                maxIndex = i;
                maxIndexElement = list.get(i);
            }
            i++;
        }
        return maxIndex;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\sbhalera\\OneDrive - Adobe\\Desktop\\candies\\arr.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\sbhalera\\OneDrive - Adobe\\Desktop\\candies\\arr_out.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.largestPermutation(k, arr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
