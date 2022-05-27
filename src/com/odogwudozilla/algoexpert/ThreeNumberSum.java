package com.odogwudozilla.algoexpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumberSum {
    public static void main(String[] args) {
        ThreeNumberSum threeNumberSum = new ThreeNumberSum();
        int[] myarray = {-8,-6,1,2,3,5,6,12};

        threeNumberSum.findSumByPointers(myarray, 0);
    }

    private List<int[]> findSumByPointers(int[] inputArray, int targetSum) {
        // Sort the input
        Arrays.sort(inputArray);

        List<int[]> finalArray = new ArrayList<>();
        int[] resultArray = new int[3];

        for (int i=0; i< inputArray.length-1; i++) {

            int currentItem = inputArray[i];
            // Initialise our pointers. The left pointer begins on the index of the current item + 1.
            int left = i+1;
            int right = inputArray.length - 1;

            while (left < right) {

                int nextLeftItem = inputArray[left];
                int nextRightItem = inputArray[right];
                int currentSum = currentItem + nextLeftItem + nextRightItem;
                System.out.println("currentItem = " + currentItem + " <=> nextLeftItem = " + nextLeftItem + " <=> nextRightItem = " + nextRightItem + " <=> currentSum = " + currentSum);

                if (currentSum == targetSum) {
                    resultArray[0] = currentItem;
                    resultArray[1] = nextLeftItem;
                    resultArray[2] = nextRightItem;
                    System.out.println("resultArray = " + Arrays.toString(resultArray));
                    finalArray.add(resultArray);
                    left++;
                    right--;
                }

                if (currentSum < targetSum) {
                    // the nextLeftItem is too small
                    left++;
                }
                if (currentSum > targetSum) {
                    // the nextRightItem is too big
                    right--;
                }

            }

        }
        finalArray.forEach(e -> System.out.println(Arrays.toString(e)));
        return finalArray;
    }
}
