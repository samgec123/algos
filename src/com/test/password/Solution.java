package com.test.password;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
        List<String> myList =
                myList = findPassword(passwords, loginAttempt);

        if (myList.size() > 0) {
            String str = myList.stream().reduce("", (s, s2) -> {
                return (s + " " + s2);
            }).substring(1);
            if (str.contains(" ...")) {
                str = str.substring(0, str.indexOf(" ..."));
            } else {
                return "WRONG PASSWORD";
            }
            return str;
        }
        return "WRONG PASSWORD";
    }

    public static List<String> findPassword(List<String> passwords, String loginAttempt) {
        List<String> list = new ArrayList<>();
        if (loginAttempt.length() <= 0) {
            list.add("...");
            return list;
        } else {
            //if a prefix matches with one of passwords
            PriorityQueue<String> passwordQueue = new PriorityQueue<>(3 * passwords.size(), new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length() > o2.length()) {
                        return -1;
                    } else if (o1.length() == o2.length()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            for (int i = 0; i < passwords.size(); i++) {
                if (loginAttempt.startsWith(passwords.get(i))) {
                    passwordQueue.add(passwords.get(i));
                }
            }
            //List<String> result = passwords.stream().filter(s -> loginAttempt.startsWith(s)).collect(Collectors.toList());
            if (passwordQueue.peek() != null) {

                int size = list.size();
                if (size > 0 && list.get(size - 1).equals("...")) {
                    //already found. return;
                    return list;
                } else {
                    String password = passwordQueue.poll();
                    list.add(password);
                    list.addAll(findPassword(passwords, loginAttempt.substring(password.length())));
                }
            }
            return list;
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> passwords = List.of("a" ,"b" ,"ab");
        String result = Result.passwordCracker(passwords, "abbababbaabababbababaabbbbaabababbaabaaaabaabbbabababbbaababbabababbabbbbbababaabaabaabbabbabaababbababbabbbbaababaaabbababaaababababbababbaabbabaaabbabaabbababaaaaaaabbaaababababaababaabbabaaabbababbababaaababaababaababbababaabbaabaabaababaabbabbabaabbaabaabbababaacbbaaabbababbaaababaaaabbabababbbbababababbaabbaaababababbbabbaabbaabababaabaaababaaaababbbaabababababbabababbaaababbaabaabaababababbaaabababaabbabaababbbababbaabbbababaababababaababbbbabaaabababaabbabaabaabbbaabababaababbaaabbabaababbbabaabbaababaaabbababbabaaabababababaaababbabababbababaaabababababbabbabaabaabbabaabaabaabababbbabbabababaaabbbaabaababbbaaaabaabababaaaabbababbaabababbbababababababaababaaabbbabababbaaaababbbabbaababababababababababaabbaaabbbabbababaaaabaaabbabaaaababbaabaaaabbababbabbabbbabbabbababaabababbaabbababbabbabbaabaabbabbbabababaababbaababbaababaabbabbbbbababbabbabaabaababbabbaaababbbabababbababaaabaabbababababbababbababaababaababababbbabaaaababababaaabbabbbabababbabbbabababbaabbabbabbabababaaabbabababababbaabababababaabaababbbabaababbbabaabbbbababbbabaabbabaabaabbbabbbbababbabbabaaabaababbbabababaaabbaabaaabbbbaaabbbabaaabbbbbbabababbaaaabbabababaabbabbbaaabbbababbaababbbababaaababbababbabababaaabaabaabaaaababbbabaabbabaaabbbabbabababababaaaabbaabababbbabababbbaabbaababbaaababbaabaaaabbabababaaabaababbbaabaabaabaaababaababaababababbbbabbbbababbaababaaabaababababbbabbabababababaaabababaabbbabbbabababaabbabababbbaabababbaaababbaabbbabababbabbababbababaababaabaaaabbaababababbababbbababbbbabbbaababaaaababbabbabaabbbabbaabababababbbabababbbbabaabababbbabaaaababababbbabaaabbaabababbbbaabaababbababaaababbabaabaabbabababababababbababababaababaaababaabababbababababaabbabaabaaabbbabbabaabaaabababaabaabababbbbbbbbababababaabbabbabbaababbaabbababaabbbabababaabaabbaaabbabbaaabbbbabaabbabbbbabbabaabbaabaaabbbababbbbbbababaababbaabababbababababbaaabaabbabaaaaaaabbababaabaabbababbabbabbaaabbabbabaabaaababaabbbaabbabbbabababaabaaaabbbabaaaababaabaababaaababbaabaabbbaababababbbbaaabababbbaaabaaaba");
        System.out.println(result);
    }
}
