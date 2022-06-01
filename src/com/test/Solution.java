package com.test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        if(arr.size()!=5) return;
        long [] sumArr = new long[5];
        long minSum = 0;
        long maxSum = 0;
        for(int i=0;i<5;i++){
            if(arr.get(i)<1 || arr.get(i)>1000000000) return;
            long tmpSum = 0;
            for(int j=0;j<5;j++){
                if(i!=j){ //we need to skip when i==j
                    //skip this
                    tmpSum+=(long)arr.get(j);
                }
            }
            sumArr[i] = tmpSum;
        }
        maxSum = sumArr[0];
        minSum = sumArr[0];
        for(int i=1;i<sumArr.length;i++){
            if(sumArr[i] >= maxSum){
                maxSum = sumArr[i];
            }
            if(sumArr[i] < maxSum){
                minSum = sumArr[i];
            }
        }
        System.out.print(minSum+" ");
        System.out.print(maxSum);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
*/
        List<Integer> arr = Stream.of(140638725,436257910,953274816,734065819,362748590)
                .collect(toList());

        Result.miniMaxSum(arr);

       // bufferedReader.close();
    }
}
