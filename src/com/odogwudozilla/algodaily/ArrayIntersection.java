package com.odogwudozilla.algodaily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertArrayEquals;

/**
 * Can you write a function that takes two arrays as inputs and returns to us their intersection? We can call the method intersection. Let's return the intersection of the two
 * inputs in the form of an array. As a reminder, the definition of an intersection of two sets A and B is the set containing all elements of A that also belong to B
 * (or equivalently, all elements of B that also belong to A).
 * Note that all elements in the final result should be unique. Here's one example:
 *
 * JAVASCRIPT
 * const nums1 = [1, 2, 2, 1];
 * const nums2 = [2, 2];
 *
 * intersection(nums1, nums2);
 * // [2]
 * And here's another one:
 *
 * JAVASCRIPT
 * const nums1 = [4,9,5];
 * const nums2 = [9,4,9,8,4];
 *
 * intersection(nums1, nums2);
 * // [9, 4]
 * Constraints
 * Length of the array <= 100000
 * The values in the array will be in the range -1000000000 and 1000000000
 * Expected time complexity: O(n+m) where n and m are the lengths of the array.
 * Expected space complexity: O(max(n,m)).
 */
public class ArrayIntersection {

    public static Integer[] arrayIntersection(int[] arr1, int[] arr2) {

        List<Integer> intersects = new ArrayList<>();
        HashMap<Integer, Integer> hashOfArray1 = new HashMap<>();

        // Push the elements of the first array to a hashmap
        for (Integer elem : arr1) {
            if(!hashOfArray1.containsKey(elem)) hashOfArray1.put(elem, elem);
        }

        for (Integer elem : arr2) {
            // Compare each element in arr2 with the keys in arr1 hashmap
            if(hashOfArray1.containsKey(elem) && !intersects.contains(elem)) {
                // Only add non-duplicate matches to the list
                intersects.add(elem);
            }
        }

        return intersects.toArray(Integer[]::new);
    }

    // tests

    @Test
    public void getArrayIntersection() {
        assertArrayEquals(
                this.arrayIntersection(
                        new int[] { 6, 0, 12, 10, 16 },
                        new int[] { 3, 15, 18, 20, 15 }
                ),
                new Integer[] {}
        );
    }

    @Test
    public void singleIntersection() {
        assertArrayEquals(
                this.arrayIntersection(
                        new int[] { 1, 5, 2, 12, 6 },
                        new int[] { 13, 10, 9, 5, 8 }
                ),
                new Integer[] { 5 }
        );
    }

    @Test
    public void multipleIntersections() {
        Integer[] result =
                this.arrayIntersection(
                        new int[] { 4, 17, 4, 4, 15, 16, 17, 6, 7 },
                        new int[] { 15, 2, 6, 20, 17, 17, 8, 4, 5 }
                );
        Integer[] expected = new Integer[] { 15, 6, 17, 4 };
        Arrays.sort(result);
        Arrays.sort(expected);
        assertArrayEquals(result, expected);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ArrayIntersection.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
