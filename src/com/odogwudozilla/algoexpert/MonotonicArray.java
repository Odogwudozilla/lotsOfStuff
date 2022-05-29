package com.odogwudozilla.algoexpert;

/**
 * Determines if a given array is monotonic - the array is increasing or decreasing
 * return {@code true } if the array is montonic and {@code false} otherwise;
 * O(n) Time - we traverse the array once | O(1) Space
 */
public class MonotonicArray {
    public static void main(String[] args) {
        MonotonicArray monotonicArray = new MonotonicArray();
        int[] theArray = {-1,-5,-1100,-1101,-10,-1100,-1102,-9001};

        System.out.println("monotonicArray is = " + monotonicArray.findMonotony(theArray));
    }

    private boolean findMonotony(int[] inputArray) {
        // Sort the array
        //Arrays.sort(inputArray);

        // Edge case: a one-element or empty array is certainly monotonic.
        if (inputArray.length < 2) return true;

        // Initialise flags to track if the array is increasing or decreasing. They both start at '@false'.
        boolean isIncreasing = false;
        boolean isDecreasing = false;

        for (int i=0; i<inputArray.length-1; i++) {
            int firstElement = inputArray[i];
            int secondElement = inputArray[i+1];
            // We don't care about two equal elements.
            if (firstElement == secondElement) continue;

            if (firstElement < secondElement) isIncreasing = true;
            if (firstElement > secondElement) isDecreasing = true;

        }
        // The array is monotonic only when both flags are not the same value.
        return isIncreasing != isDecreasing;
    }
}
