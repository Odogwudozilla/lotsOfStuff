package com.odogwudozilla.algoexpert;

import java.util.Arrays;

public class SmallestDifference {
    public static void main(String[] args) {
        int[] array1 = {-1,5,10,20,28,3};
        int[] array2 = {26,134,135,15,17};

        SmallestDifference smallestDifference = new SmallestDifference();
        System.out.println("smallestDifference: " + smallestDifference.findSmallestDifference(array1, array2));

    }

    private int findSmallestDifference (int[] firstArray, int[] secondArray) {

        Arrays.sort(firstArray);
        Arrays.sort(secondArray);
        // Initialise the difference tracker
        int smallestDifference = Integer.MAX_VALUE;
        // Initialise the pointers for each array;
        int pointerA = 0, pointerB = 0;

        while (pointerA < firstArray.length && pointerB < secondArray.length) {
            // get the absolute value of the difference.
            int currentDifference = Math.abs(firstArray[pointerA] - secondArray[pointerB]);
            System.out.println("currentDifference = " + currentDifference);

            // if both numbers are equal, the difference is zero. We are done.
            if (firstArray[pointerA] == secondArray[pointerB]) return currentDifference;

            // Assign the smallest difference to the current difference iff the currentDifference is smaller.
            if (currentDifference < smallestDifference) smallestDifference = currentDifference;

            if (firstArray[pointerA] < secondArray[pointerB]) {
                // Move the pointer of first array to the right if the value is smaller.
                pointerA++;
            } else {
                // Move the pointer of second array to the right if the value is smaller.
                pointerB++;
            }
        }

        return smallestDifference;
    }

}
