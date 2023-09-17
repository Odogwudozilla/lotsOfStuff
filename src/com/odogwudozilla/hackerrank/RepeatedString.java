package com.odogwudozilla.hackerrank;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class RepeatedString {
    public static final long N = 6765456400L;
    private static String LETTER_A = "a";
    private static String INPUT_STRING = "kmretasscityylpdhuwjirnqimlkcgxubxmsxpypgzxtenweirknjtasxtvxemtwxuarabssvqdnktqadhyktagjxoanknhgilnm";


    public static void main(String[] args) {
        repeatedStringEfficient(INPUT_STRING, N);
    }


    public static long repeatedString(String s, long n) {

        long numberOfSs = 0;
        if(LETTER_A.equals(s)) return n;
        Queue<String> collectStrings = new ArrayDeque<>();
        Queue<String> fillerList = new ArrayDeque<>();

        List<String> letterArray = Arrays.asList(s.split(""));

        while (collectStrings.size() < n) {

            if (fillerList.isEmpty()) {
                // Fill the filler when empty
                fillerList.addAll(letterArray);
            }

            String currentItem = fillerList.remove();
            // Add it to the main list
            collectStrings.offer(currentItem);
            if (LETTER_A.equals(currentItem)) {
                numberOfSs++;
            }
        }
        System.out.println(collectStrings);
        System.out.println(numberOfSs);
        return numberOfSs;

    }


    public static long repeatedStringEfficient(String s, long n) {
        // Write your code here

        int stringLength = s.length();
        long quotient = 0, rem = 0;
        quotient = n / stringLength;
        rem = n % stringLength;
        long partialStringLength = rem;
        long numOfAs = quotient * getLetterCount(s, s.length()) + getLetterCount(s, partialStringLength);
        System.out.println("Total occurrences is: " + numOfAs);
        return numOfAs;
    }

    private static long getLetterCount(String str, long strLength) {
        long currentCount = 0;
        for (int i = 0; i < strLength; i++) {
            if (str.charAt(i) == 'a') {
                currentCount++;
            }
        }
        return currentCount;
    }
}
