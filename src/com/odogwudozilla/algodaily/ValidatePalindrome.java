package com.odogwudozilla.algodaily;

import java.util.Iterator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ValidatePalindrome {
    public ValidatePalindrome() {
    }

    public static boolean isPalindrome(String str) {
        if (str.isEmpty()) {
            return false;
        } else if (str.length() == 1) {
            return true;
        } else {
            String newStr = str.replaceAll("\\s", "").toLowerCase();
            String reversedStr = (new StringBuilder(newStr)).reverse().toString();
            return reversedStr.equals(newStr);
        }
    }

    @Test
    public void notPalindromeTest() {
        Assertions.assertFalse(isPalindrome("gold"));
    }

    @Test
    public void singleCharacterTest() {
        Assertions.assertTrue(isPalindrome("a"));
    }

    @Test
    public void longerPalindromeTest() {
        Assertions.assertTrue(isPalindrome("racecar"));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(new Class[]{ValidatePalindrome.class});
        Iterator var2 = result.getFailures().iterator();

        while(var2.hasNext()) {
            Failure failure = (Failure)var2.next();
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }

    }
}
