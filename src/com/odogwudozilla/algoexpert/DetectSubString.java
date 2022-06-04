package com.odogwudozilla.algoexpert;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

/**
 * If the substring can be found in the string, return the index at which it starts. Otherwise, return -1.
 * Important-- do not use the native String class's built-in substring or substr method. This exercise is to understand the underlying implementation of that method.
 *
 * Constraints
 * Length of both the given strings <=100000
 * The strings would never be null
 * The strings will only consist of lowercase letters
 * Expected time complexity : O(n)
 * Expected space complexity : O(1)
 */

public class DetectSubString {
    public static int detectSubstring(String a, String b) {

        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        int bCounter = 0;
        char[] founds = new char[bArr.length];

        for (int i=0; i<aArr.length; i++) {
            // We do not start checking until we encounter a matching first letter;
            if(aArr[i] != bArr[0]) continue;

            for (int j=0; j<bArr.length; j++) {
                if (aArr[i+bCounter] == bArr[bCounter]) {
                    // both letters match, add it to the 'found' array
                    founds[bCounter] = aArr[i+bCounter];
                    // increment the counter, so we can check the next letter immediately
                    bCounter++;
                }
            }

            if (Arrays.equals(founds, bArr)) {
                // We already found all the letters in the required sequence, stop checking any further
                return a.indexOf(founds[0], i);
            }
            bCounter = 0;

        }

        return -1;
    }

    // tests

    @Test
    public void firstTest() {
        assertEquals(DetectSubString.detectSubstring("theflepigflewwow", "flew"), 9);
    }

    @Test
    public void secondTest() {
        assertEquals(DetectSubString.detectSubstring("twocanplay", "two"), 0);
    }

    @Test
    public void thirdTest() {
        assertEquals(DetectSubString.detectSubstring("wherearemyshorts", "pork"), -1);
    }

    @Test
    public void fourthTest() {
        assertEquals(DetectSubString.detectSubstring("wherearemyshporkts", "pork"), 12);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DetectSubString.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
