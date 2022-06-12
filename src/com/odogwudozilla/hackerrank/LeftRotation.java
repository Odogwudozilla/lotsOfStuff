package com.odogwudozilla.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class LeftRotation {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        rotateLeft(4, myList);
    }

    /*
     * Complete the 'rotateLeft' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER d
     *  2. INTEGER_ARRAY arr
     */

    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        // Return same array for input size less than two
        if (arr.size() < 2) return arr;

        int n = arr.size();
        List<Integer> rotatedArray = new ArrayList<>();
        int[] newArr = new int[n];

        for(int i=0; i<n; i++) {
            int setter = (i+n-d)%n;
            newArr[setter] = arr.get(i);

        }

        for(int i=0; i<newArr.length; i++) {
            rotatedArray.add(newArr[i]);
        }



//        for (Integer elem : arr) {
//            // Push the elements in the stack.
//            newQueue.add(elem);
//        }
//        int iterations = (d * arr.size()) - 1;
//        while (iterations > 0) {
//
//            newQueue.add(newQueue.remove());
//
//            iterations--;
//        }
//
//        rotatedArray.addAll(new ArrayList<>(newQueue));

        return rotatedArray;

    }
}
