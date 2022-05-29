package com.odogwudozilla.algoexpert;

import java.util.Arrays;

/**
 * Moves all occurrences of the target number to the end of the array.
 * O(n) Time | O(1) Space
 */
public class MoveElementToTheEnd {
    public static void main(String[] args) {
        int[] myArray = {2,1,2,7,2,2,2,8,2,5,4,5,2,2,2,2,1,2,5,4,2,3,4,2};
        MoveElementToTheEnd moveElementToTheEnd = new MoveElementToTheEnd();
        System.out.println(Arrays.toString(moveElementToTheEnd.moveElementsToTheEndOfArray(myArray, 2)));
    }

    private int[] moveElementsToTheEndOfArray (int[] inputArray, int targetNumber) {

        int pointerLeft = 0;
        int pointerRight = inputArray.length-1;

        while (pointerLeft < pointerRight) {
            int currentLeftItem = inputArray[pointerLeft];
            int currentRightItem = inputArray[pointerRight];

            if (currentRightItem == targetNumber) {
                // Move the right pointer leftwards if the current element is equal to the target number
                pointerRight--;
            }

            if (currentLeftItem != targetNumber ) {
                // Move to the right regardless because we are not interested in numbers not equal to the target
                pointerLeft++;
            }

            if (currentLeftItem == targetNumber && currentRightItem != targetNumber) {
                // Swap both numbers
                System.out.println("Swapping element '" + currentLeftItem + "' with element '" + currentRightItem + "'");
                inputArray[pointerLeft] = currentRightItem;
                inputArray[pointerRight] = currentLeftItem;
                // Move both pointers now.
                pointerLeft++;
                pointerRight--;
            }
        }

        return inputArray;
    }
}
