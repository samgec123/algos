package com.test;

import java.io.*;
import java.nio.Buffer;



/*
 * Complete the 'highestValuePalindrome' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING s
 *  2. INTEGER n
 *  3. INTEGER k
 */


public class Palindrom {
    public static String highestValuePalindrome(String s, int n, int k) {
        // Write your code here
        if (k >= n) {
            s = substituteAll(s);
        }
        if (n % 2 == 0) {
            s = substituteAndReplaceForEvenLength(s, k);
        } else {
            s = substituteAndReplaceForOddLength(s, k);
        }
        System.out.println(s);
        if (isPalindrom(s)) {
            return s;
        } else {
            return "-1";
        }
    }

    public static String substituteAll(String str) {
        int strLen = str.length();
        char[] characters = str.toCharArray();
        for (int i = 0; i < strLen; i++) {
            characters[i] = '9';
        }
        return String.valueOf(characters);
    }

    public static boolean isPalindrom(String str) {
        int strLen = str.length();
        for (int i = 0; i < strLen / 2; i++) {
            if (str.charAt(i) != str.charAt(strLen - (i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static String substituteAndReplaceForEvenLength(String str, int k) {
        int strLen = str.length();
        char[] characters = str.toCharArray();
        for (int i = 0; i < strLen / 2 && k > 0; i++) {
            if (str.charAt(i) != '9' && str.charAt(strLen - (i + 1)) != '9' && k > 1) {
                characters[i] = '9';
                characters[strLen - (i + 1)] = '9';
                k -= 2;
            } else if (str.charAt(i) != str.charAt(strLen - (i + 1))) {
                if (str.charAt(i) >= str.charAt(strLen - (i + 1))) {
                    characters[strLen - (i + 1)] = str.charAt(i);
                } else {
                    characters[i] = characters[strLen - (i + 1)];
                }
                k--;
            }
        }
        return String.valueOf(characters);
    }

    public static String substituteAndReplaceForOddLength(String str, int k) {

        int strLen = str.length();
        char[] characters = str.toCharArray();
        for (int i = 0; i < strLen / 2 && k > 0; i++) {
            if (str.charAt(i) != '9' && str.charAt(strLen - (i + 1)) != '9' && k > 1) {
                characters[i] = '9';
                characters[strLen - (i + 1)] = '9';
                k -= 2;
            } else if (str.charAt(i) != str.charAt(strLen - (i + 1))) {
                if (str.charAt(i) >= str.charAt(strLen - (i + 1))) {
                    characters[strLen - (i + 1)] = str.charAt(i);
                } else {
                    characters[i] = characters[strLen - (i + 1)];
                }
                k--;
            }
        }
        if (str.charAt(strLen / 2) != '9' && k == 1) {
            characters[strLen / 2] = '9';
        }
        return String.valueOf(characters);
    }

    public static void main(String[] args) throws IOException {
        byte [] myByte = new byte[77543];
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("src/test/resources/stringtoolong.txt")));
            myByte = bufferedInputStream.readAllBytes();
        }catch(Exception ex){

        }
        String s = new String(myByte);
        System.out.println(s.toString());
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        int n = 77543;
        int k = 58343;
        String result = highestValuePalindrome(s.toString(), n, k);
        System.out.println(result.length());
    }
}