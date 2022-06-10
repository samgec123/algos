package com.test;

import java.util.ArrayList;
import java.util.List;

public class ProceduralPerm {
    public static void main(String[] args) {
        String myStr = "123";
        permute(myStr).forEach(System.out::println);
    }

    private static List<String> permute(String myStr) {
        List<String> arr = new ArrayList<>();
        if (myStr.length() <= 1) {
            arr.add(myStr);
            return arr;
        }
        List<String> initial = new ArrayList<>();
        List<String> finalStr = new ArrayList<>();
        initial.add(myStr.substring(myStr.length() - 1));
        for (int i = myStr.length() - 2; i >= 0; i--) {
            int j = initial.size();
            while (j > 0) {
                String str = initial.get(j - 1);
                finalStr.addAll(rotate(myStr.charAt(i), str));
                j--;
            }
            initial.clear();
            initial.addAll(finalStr);
            finalStr.clear();
        }
        return initial;
    }

    public static List<String> rotate(char a, String b) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < b.length() + 1; i++) {
            list.add(b.substring(0, i) + a + b.substring(i, b.length()));
        }
        return list;
    }
}
