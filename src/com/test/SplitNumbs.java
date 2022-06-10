package com.test;

public class SplitNumbs {
    public static int splitNums(int num, int power) {
        int sol = 0;
        if (num > 0) {
            for (int i = 33; i > 0; i--) {
                if (raiseToPower(i, power) <= num) {
                    sol = sol + calculateSol(num, power, i);
                }
            }
        }
        return sol;
    }

    public static int calculateSol(int num, int power, int limit) {
        int remainder = num - raiseToPower(limit, power);
        int sol = 0;
        if (remainder == 0) {
            return 1;
        } else if (remainder > 0) {
            for (int j = limit - 1; j > 0; j--) {
                sol = sol + calculateSol(remainder, power, j);
            }
        }
        return sol;
    }

    public static int raiseToPower(int base, int power) {
        int ans = 1;
        if (power < 1) {
            return 1;
        }
        while (power-- > 0) {
            ans = ans * base;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(splitNums(800, 2));
    }

}
