package com.test;

public class PalindromTest {
    public static void isPalindrom(String prefix, String suffix) {
        if (prefix.equals("") && suffix.equals("")) {
            System.out.println("" + true);
        } else {
            if (prefix.charAt(prefix.length() - 1) == suffix.charAt(0)) {
                isPalindrom(prefix.substring(0, prefix.length() - 1), suffix.substring(1));
            } else {
                System.out.println("" + false);
            }
        }
    }

    public static void main(String[] args) {
        String a = "cabcbac";
        int middle = a.length() / 2;
        if (a.length() % 2 == 0) {
            isPalindrom(a.substring(0, middle), a.substring(middle));
        } else {

            isPalindrom(a.substring(0, middle), a.substring(middle + 1));
        }
    }
}
