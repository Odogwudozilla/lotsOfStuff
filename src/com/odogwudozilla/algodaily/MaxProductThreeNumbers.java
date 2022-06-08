package com.odogwudozilla.algodaily;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, can you write a method maxProductOfThree(unsorted: array) to find the largest product from three of the numbers? For example, given the
 * following array:
 *
 * [-1, 9, 22, 3, -15, -7]
 *
 * The largest product of three numbers is 2310. This results from -15 * -7 * 22.
 */
public class MaxProductThreeNumbers {

    public static int maxProductOfThree(int[] unsorted) {

        int largestProduct = 0;
        //Sort the array
        Arrays.sort(unsorted);

        int twoPossibleNegatives = unsorted[0] * unsorted[1] * unsorted[unsorted.length-1];
        int lastThreeHighest = unsorted[unsorted.length-3] * unsorted[unsorted.length-2] * unsorted[unsorted.length-1];

        largestProduct = Math.max(twoPossibleNegatives, lastThreeHighest);

        return largestProduct;
    }


}
