package com.odogwudozilla.testing.algodaily;

import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.odogwudozilla.algodaily.MaxProductThreeNumbers;

import static org.junit.jupiter.api.Assertions.*;

class MaxProductThreeNumbersTest {
    // tests

    @Test
    public void simpleArrayProductTest() {
        assertEquals(MaxProductThreeNumbers.maxProductOfThree(new int[] {0, 1, 2, 3}), 6);
    }

    @Test
    public void longerArrayProductTest() {
        assertEquals(MaxProductThreeNumbers.maxProductOfThree(new int[] {-12, -7, -1, 11, 17}), 1428);
    }

    @Test
    public void complexArrayProductTest() {
        assertEquals(MaxProductThreeNumbers.maxProductOfThree(new int[] {0, 4, -9, 19, 7, -5}), 855);
    }

    @Test
    public void hugeProductTest() {
        assertEquals(MaxProductThreeNumbers.maxProductOfThree(new int[] {-1, 9, 22, 3, -15, -7}), 2310);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MaxProductThreeNumbers.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

}