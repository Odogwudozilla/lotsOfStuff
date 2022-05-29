package com.odogwudozilla.algoexpert;

import java.util.ArrayList;
import java.util.List;

public class SpiralTraverse {
    public static void main(String[] args) {
        SpiralTraverse spiralTraverse = new SpiralTraverse();
        int[][] myArray = {{1,2,3,4}, {12,13,14,5}, {11,16,15,6}, {10,9,8,7}};
        spiralTraverse.traverseArrayInSpiral(myArray);
    }

    private List<Integer> traverseArrayInSpiral(int[][] inputArray) {
        List<Integer> resultArray = new ArrayList<>();

        int arraySize = inputArray.length;

        int startingRowStartingColumn = inputArray[0][0];
        int startingRowEndingColumn = inputArray[0][inputArray[0].length-1];
        int endingRowStartingColumn = inputArray[arraySize-1][0];
        int endingRowEndingColumn = inputArray[arraySize-1][inputArray[arraySize-1].length-1];

        System.out.println("startingRowStartingColumn = " + startingRowStartingColumn);
        System.out.println("startingRowEndingColumn = " + startingRowEndingColumn);
        System.out.println("endingRowStartingColumn = " + endingRowStartingColumn);
        System.out.println("endingRowEndingColumn = " + endingRowEndingColumn);
//        for (int i=0; i< inputArray.length; i++) {
//
//            for (int j=0; j<inputArray[i].length; j++) {
//            }
//        }

        return resultArray;
    }

}
