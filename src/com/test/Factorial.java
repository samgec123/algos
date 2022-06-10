package com.test;

public class Factorial {
    public static int factorial(int n) {
        System.out.println("##" + n);
        if (n == 1) {
            return 1;
        } else {
            factorial(n - 1);
            //return n * sol;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(factorial(10));
    }
}
