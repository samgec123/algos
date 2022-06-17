package com.test.candies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result {

    /*
     * Complete the 'candies' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */

    public static long candies(int n, List<Integer> arr) {
        // Write your code here
        List<Integer> tmp = new ArrayList<>(n);
        List<Integer> candyArr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            candyArr.add(i, 0);
        }
        tmp.addAll(arr);
        for (int i = 0; i < n; i++) {
            //find smalles from the list
            int smallestInd = findSmallestIndex(tmp);
            giveCandy(arr, candyArr, smallestInd);
        }
        candyArr.stream().forEach(System.out::println);
        return (long) candyArr.stream().reduce(0, (s1, s2) -> s1 + s2);
    }

    public static List<Integer> giveCandy(List<Integer> rankArr, List<Integer> candyArr, int index) {
        if (index == 0) {
            if (candyArr.get(index + 1) == 0) {
                candyArr.set(index, 1);
            } else {
                if (rankArr.get(index) > rankArr.get(index + 1)) {
                    candyArr.set(index, candyArr.get(index + 1) + 1);
                } else {
                    candyArr.set(index, 1);
                }
            }
        } else if (index == rankArr.size() - 1) {
            if (candyArr.get(index - 1) == 0) {
                candyArr.set(index, 1);
            } else {
                if (rankArr.get(index) > rankArr.get(index - 1)) {
                    candyArr.set(index, candyArr.get(index - 1) + 1);
                } else {
                    candyArr.set(index, 1);
                }
            }
        } else {
            if (candyArr.get(index - 1) == 0 && candyArr.get(index + 1) == 0) {
                candyArr.set(index, 1);
            } else if (candyArr.get(index - 1) == 0 && candyArr.get(index + 1) != 0) {
                if (rankArr.get(index) > candyArr.get(index + 1)) {
                    candyArr.set(index, candyArr.get(index + 1) + 1);
                } else {
                    candyArr.set(index, 1);
                }
            } else if (candyArr.get(index - 1) != 0 && candyArr.get(index + 1) == 0) {
                if (rankArr.get(index) > candyArr.get(index - 1)) {
                    candyArr.set(index, candyArr.get(index - 1) + 1);
                } else {
                    candyArr.set(index, 1);
                }
            } else {
                if (rankArr.get(index) > candyArr.get(index - 1) && rankArr.get(index) > candyArr.get(index - 1)) {
                    if (candyArr.get(index - 1) > candyArr.get(index + 1)) {
                        candyArr.set(index, candyArr.get(index - 1) + 1);
                    } else {
                        candyArr.set(index, candyArr.get(index + 1) + 1);
                    }
                    candyArr.set(index, candyArr.get(index - 1));
                }
            }
        }

        return candyArr;
    }

    public static int findSmallestIndex(List<Integer> arr) {
        int smallest = arr.get(0);
        int index = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < smallest) {
                smallest = arr.get(i);
                index = i;
            }
        }
        arr.set(index, Integer.MAX_VALUE);
        return index;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
   /*     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\sbhalera\\OneDrive - Adobe\\Desktop\\candies\\candies.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\sbhalera\\OneDrive - Adobe\\Desktop\\candies-output.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());
*/
        long result = Result.candies(6, Arrays.asList(4, 6, 4, 5, 6, 2));
        System.out.print(result);
/*
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close(); */
    }
}

