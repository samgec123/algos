package com.test;

import java.util.ArrayList;
import java.util.List;

public class PermutationRecursive {
    public static List<String> permute(String inputStr) {
        if (inputStr.length() <= 1) {
            return List.of(inputStr);
        } else {
            char firstChar = inputStr.charAt(0);
            List<String> list = permute(inputStr.substring(1, inputStr.length()));
            List<String> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                newList.addAll(rotate(firstChar, list.get(i)));
            }
            return newList;
        }
    }

    public static List<String> rotate(char ch, String inputStr) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= inputStr.length(); i++) {
            list.add(inputStr.substring(0, i) + ch + inputStr.substring(i));
        }
        return list;
    }

    public static void main(String[] args) {
        String inputString = "abcdef";
        permute(inputString).stream().forEach(System.out::println);
    }

}
