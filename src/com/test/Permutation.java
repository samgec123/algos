package com.test;

public class Permutation {
    public static void permutate(String ch, String rest) {
        if (rest.equals("")) {
            System.out.println(ch);
        } else {
            permutate(ch + rest.substring(0, 1), rest.substring(1));
            for (int i = 0; i < rest.length(); i++) {
                System.out.println(rest.substring(0, i) + ch + rest.substring(i));
            }
        }
    }

    public static void main(String... args) {
        permutate("", "abc");
    }
}
