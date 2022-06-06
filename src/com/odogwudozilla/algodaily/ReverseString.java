package com.odogwudozilla.algodaily;

import java.util.Stack;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

/**
 * Can you write a function that reverses an inputted string without using the built-in Array#reverseList method?
 *
 * Let's look at some examples. So, calling:
 *
 * reverseString("jake") should return "ekaj".
 *
 * reverseString("reverseastring") should return "gnirtsaesrever".
 */
public class ReverseString {
    public static String reverseString(String str) {
        // Return an empty string if the input is empty
        if (str.isEmpty()) return "";

        char[] strArray = str.toCharArray();
        String reversedString;

        Stack<String> newStack = new Stack<>();

        for (char elem : strArray){
            // Push the elements in the stack.
            newStack.push(String.valueOf(elem));
        }
        // Assign reversedString to first popped element to eliminate the null value
        reversedString = newStack.pop();

        while (!newStack.isEmpty()) {
            reversedString += newStack.pop();
        }

        return reversedString;
    }

    // tests

    @Test
    public void firstTest() {
        assertEquals(reverseString("timbuktu12"), "21utkubmit");
    }

    @Test
    public void secondTest() {
        assertEquals(
                reverseString("njnschnjkdasn j32 uida"),
                "adiu 23j nsadkjnhcsnjn"
        );
    }

    @Test
    public void thirdTest() {
        assertEquals(
                ReverseString.reverseString("njnschnjkdasn j32 uida"),
                "adiu 23j nsadkjnhcsnjn"
        );
    }

    @Test
    public void fourthTest() {
        assertEquals(ReverseString.reverseString("timbuktu12"), "21utkubmit");
    }

    @Test
    public void fifthTest() {
        assertEquals(ReverseString.reverseString(""), "");
    }

    @Test
    public void sixthTest() {
        assertEquals(ReverseString.reverseString("reverseastring"), "gnirtsaesrever");
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ReverseString.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
