package com.odogwudozilla.algoexpert;

import java.util.Arrays;
import java.util.Hashtable;

public class TwoNumberSum {
	public static void main(String[] args) {
		int[] myArray = new int[] {3,5,-4,8,11,1,-1,6};
		TwoNumberSum tn = new TwoNumberSum();
		System.out.println(Arrays.toString(tn.findByHash(myArray, 10)));
		System.out.println(Arrays.toString(tn.findByDivide(myArray, 10)));

	}

	private int[] findByHash (int[] inputArray, Integer targetSum) {

		Hashtable<Integer, Boolean> checkHash = new Hashtable<>();

		for (Integer element : inputArray) {
			// 'y' is that number that complements the current element for a sum of the targetSum
			Integer y = targetSum - element;
			if (checkHash.containsKey(y)) {
				// the complement already exists in the hashtable. We are done.
				System.out.println("second number found in hashTable: " + y + ". Current element: " + element);
				checkHash.replace(y, false, true);
				System.out.println(checkHash);
				return new int []{element, y};
			} else {
				// the complement is not in the hashtable yet, add the current number.
				checkHash.put(element, false);
				System.out.println(checkHash);
			}
		}
		return new int[] {};

	}

	private int[] findByDivide (int[] inputArray, Integer targetSum) {
		//Sort the input
		Arrays.sort(inputArray);

		int left = 0;
		int right = inputArray.length - 1;

		while (left < right) {
			int currentsum = inputArray[left] + inputArray[right];
			if (currentsum > targetSum) {
				// The currentSum is greater than the targetSum sum only because the righterMost integer is too large. Decrement the index from the right.
				right--;
			} else if (currentsum < targetSum) {
				// The currentSum is smaller than the targetSum sum only because the leftMost integer is too small. Increment the index from the left.
				left++;
			} else {
				// they are equal
				System.out.println("matching numbers found: " + inputArray[left] + " " + inputArray[right]);
				return new int[] {inputArray[left] , inputArray[right]};
			}
		}
		return new int[] {};

	}
}
