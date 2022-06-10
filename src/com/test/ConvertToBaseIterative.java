package com.test;

public class ConvertToBaseIterative {
    public static String convertToBase(int number, int base) {
        boolean process = true;
        String ans = "";
        while ((number / base) >= 1) {
            ans = ans + number % base;
            number = number / base;
        }
        ans = ans + number % base;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(convertToBase(10, 2));
    }
}
