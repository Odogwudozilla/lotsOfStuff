package com.odogwudozilla.codility;

import java.util.Arrays;

public class SmallestPositiveInteger {
    public static void main(String[] args) {
        solution(new int[]{1, 3, 6, 4, 1, 2});
    }

    //Write a function:
    //
    //class Solution { public int solution(int[] A); }
    //
    //that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
    //
    //For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
    //
    //Given A = [1, 2, 3], the function should return 4.
    //
    //Given A = [−1, −3], the function should return 1.
    //
    //Write an efficient algorithm for the following assumptions:
    //
    //N is an integer within the range [1..100,000];
    //each element of array A is an integer within the range [−1,000,000..1,000,000].
    public static int solution(int[] A) {
        // write your code in Java SE 8
        Arrays.sort(A);
        System.out.print(Arrays.toString(A));

        int lowestNum = Integer.MAX_VALUE;

        int startNum = A[0];
        int indexCount = 1;
        boolean hasChanged = false;

        while (indexCount < A.length) {
            if (startNum < 1 || A[indexCount] < 1) {
                indexCount++;
                continue;
            }
            int difference = A[indexCount] - startNum;

            if (difference > 1) {
                lowestNum = Math.min(lowestNum, (A[indexCount] -1));
            }
            hasChanged = true;
            startNum = A[indexCount];
            indexCount++;
        }

        if (hasChanged && (lowestNum == Integer.MAX_VALUE))  lowestNum = A[A.length-1] + 1;
        if (!hasChanged && lowestNum == Integer.MAX_VALUE) lowestNum = 1;
        System.out.println(lowestNum);
        return lowestNum;
    }
}
