package com.test;

public class PowerCalculation {
    public static void main(String[] args) {
        int base = 3, power = 5;
        System.out.println(cal(3, 5));
    }

    private static int cal(int i, int i1) {
        if (i1 > 0)
            return i * cal(i, --i1);
        return 1;
    }
}
