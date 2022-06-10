package com.test;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static String[] rotate(char a, String b) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= b.length(); i++) {
            list.add(b.substring(0, i) + a + b.substring(i + 1));
        }
        return list.toArray(new String[0]);
    }

    public static String permutate(String prefix, String rest) {
        if (rest.length() == 0) {
            return "";
        } else if (rest.length() == 1) {
            return rest;
        } else {
            prefix = prefix + rest.charAt(0);
            String prt = permutate(prefix, rest.substring(1));
            char lastPrefixChar = prefix.charAt(prefix.length() - 1);
            String[] arr = rotate(lastPrefixChar, prt);

            for (int i = 0; i < arr.length; i++) {
              //  permutate(character,arr[i]);
            }
        }
        if (rest.length() > 1) {

            String prt = permutate(prefix, rest.substring(1));
            //take last one from prefix and rotate

            char lastPrefixChar = prefix.charAt(prefix.length() - 1);
            String restOfStr = rest.substring(1, rest.length() - 1);
            for (int i = 0; i <= prt.length(); i++) {
                String perm1 = prt.substring(0, i) + prefix.charAt(prefix.length() - 1) + prt.substring(i, restOfStr.length());
                return permutate(prefix.substring(0, prefix.length() - 1), perm1);
            }
        }
        return rest;
    }

    public static void main(String... args) {
        permutate("", "abc");
    }
}
