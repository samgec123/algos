package com.test;

public class ConvertToBase {
    public static void main(String... args) {
        System.out.println(convertToBase(142, 5));
    }

    public static String convertToBase(int n, int base) {
        if (n < base) {
            return "" + n;
        } else if (n == base) {
            return "10";
        } else {
            return "" + convertToBase(n / base, base) + (n % base);
        }
    }
}
