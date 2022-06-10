package com.test.password;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Result {

    /*
     * Complete the 'passwordCracker' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY passwords
     *  2. STRING loginAttempt
     */

    public static String passwordCracker(List<String> passwords, String loginAttempt) {
        // Write your code here
        List<String> myList = new ArrayList<>();
        if (loginAttempt.length() > 0) {
            myList = findPassword(passwords, loginAttempt, myList);
        }
        if (myList.size() > 0) {
            String str = myList.stream().reduce("", (s, s2) -> {
                return (s + " " + s2);
            }).substring(1);
            if (str.contains(" ...")) {
                str = str.substring(0, str.indexOf(" ..."));
            }
            return str;
        }
        return "WRONG PASSWORD";
    }

    public static List<String> findPassword(List<String> passwords, String loginAttempt, List<String> list) {
        if (loginAttempt.length() == 0) {
            list.add("...");
            return list;
        } else {
            //if a prefix matches with one of passwords
            List<String> result = passwords.stream().filter(s -> loginAttempt.startsWith(s)).collect(Collectors.toList());
            if (result.size() > 0)
                for (int i = 0; i < result.size(); i++) {
                    int size = list.size();
                    if (size > 0 && list.get(size - 1).equals("...")) {
                        list.remove(size - 1);
                        return list;
                    }
                    list.add(result.get(i));
                    findPassword(passwords, loginAttempt.substring(result.get(i).length()), list);
                }
            else {
                int size = list.size();
                list.remove(size - 1);
            }
        }
        return list;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> passwords = List.of("ab", "abcd", "cd");
        String result = Result.passwordCracker(passwords, "abcd");
        System.out.println(result);
    }
}
