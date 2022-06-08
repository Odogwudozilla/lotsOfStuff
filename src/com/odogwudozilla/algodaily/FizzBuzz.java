package com.odogwudozilla.algodaily;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

/**
 * We're given a number in the form of an integer n.
 *
 * Write a function that returns the string representation of all numbers from 1 to n based on the following rules:
 *
 * If it's a multiple of 3, represent it as "fizz".
 *
 * If it's a multiple of 5, represent it as "buzz".
 *
 * If it's a multiple of both 3 and 5, represent it as "fizzbuzz".
 *
 * If it's neither, just return the number itself.
 *
 * As such, calling fizzBuzz(15) would result in '12fizz4buzzfizz78fizzbuzz11fizz1314fizzbuzz'.
 */
public class FizzBuzz {
    static String FIZZ = "fizz";
    static String BUZZ = "buzz";
    static String FIZZ_BUZZ = "fizzbuzz";

    public static String fizzBuzz(Integer num) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i<= num; i++) {
            // Check for the modulus on each of the cases (when a number is divided by its factors, the remainder is always zero)
            if(i%15 == 0) {
                result.append(FIZZ_BUZZ);
            } else if (i%5 == 0) {
                result.append(BUZZ);
            } else if (i%3 == 0){
                result.append(FIZZ);
            } else {
                result.append(i);
            }

        }

        System.out.println(result);
        return result.toString();
    }

    // tests

    @Test
    public void firstTest() {
        assertEquals(FizzBuzz.fizzBuzz(1), "1");
    }

    @Test
    public void secondTest() {
        assertEquals(
                FizzBuzz.fizzBuzz(15),
                "12fizz4buzzfizz78fizzbuzz11fizz1314fizzbuzz"
        );
    }

    @Test
    public void thirdTest() {
        assertEquals(fizzBuzz(0), "");
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FizzBuzz.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
