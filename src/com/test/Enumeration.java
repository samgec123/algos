package com.test;

public class Enumeration {
    public static void main(String[] args) {
        int[] set = {1, 2, 3, 4, 5, 6};
        System.out.println(list(set[0], set, 1));
    }

    private static int list(int c, int[] set, int index) {
        if (index > set.length - 1) {
            System.out.println(set[index - 1]);
            System.out.print(":" + c);
            return c;
        } else {
            System.out.println(c);
            return list(set[index], set, ++index);
        }

    }
}
