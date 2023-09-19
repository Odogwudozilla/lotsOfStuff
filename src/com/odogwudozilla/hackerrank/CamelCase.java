package com.odogwudozilla.hackerrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelCase {
    private static final Logger logger = LoggerFactory.getLogger(CamelCase.class);

    public static void main(String[] args) {
        logger.info(String.valueOf(countWords("oneTwoThree")));
    }

    public static int countWords(String s) {
        if (s.isEmpty() || s.length() == 1) return s.length();

        char[] sArr = s.toCharArray();
        int frequencyCamelCase = 0;

        for (char elem : sArr) {
            if(String.valueOf(elem).equals(String.valueOf(elem).toUpperCase())) frequencyCamelCase++;
        }

        return frequencyCamelCase + 1;
    }
}
