package com.odogwudozilla.hackerrank;

public class CamelCase {
    public static void main(String[] args) {
        System.out.println(countWords("oneTwoThree"));
    }

    public static int countWords(String s) {
        if (s.length() == 0 || s.length() == 1) return s.length();

        char[] sArr = s.toCharArray();
        int frequencyCamelCase = 0;

        for (char elem : sArr) {
            if(String.valueOf(elem).equals(String.valueOf(elem).toUpperCase())) frequencyCamelCase++;
        }

        return frequencyCamelCase + 1;
    }
}
