package com.odogwudozilla.grokkingAlgorithms;

import java.util.Arrays;

/**
 * Sorts the numbers in the given array in ascending order.
 * This will be better implemented with Lists, but I wanted to do it the hard way via arrays
 */
public class SelectionSort {

	public static void main(String[] args) {
		// Input array.
		int myArray[] = {5, 3, 43, 78, 6, 1, 2, 10, 96};

		SelectionSort selectionSort = new SelectionSort();
		System.out.println(Arrays.toString(selectionSort.selectionSortArray(myArray)));
	}


	/**
	 * Determines the index of the smallest element in the given array.
	 *
	 * @param arr the given array.
	 * @return the smallest index.
	 */
	private int determineSmallestElement(int arr[]) {
		int initialElement = arr[0];
		int smallestIndex = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < initialElement) {
				initialElement = arr[i];
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}

	/**
	 * Sorts a given array by iteratively determining the smallest element.
	 *
	 * @param toBeSorted the given array.
	 * @return the sorted array.
	 */
	private int[] selectionSortArray(int toBeSorted[]) {
		// Size of initial input
		int arraySize = toBeSorted.length;
		// define the sorted array
		int sortedArray[] = new int[arraySize];

		for (int i = 0; i < arraySize; i++) {
			//Determine the smallest index
			int smallestIndex = determineSmallestElement(toBeSorted);
			// Fill the sorted list with the element on this index
			sortedArray[i] = toBeSorted[smallestIndex];
			// Create a new array for making a copy of the input array
			int newArray[] = new int[toBeSorted.length - 1];
			int indexCounter = 0;

			for (int j = 0; j < toBeSorted.length; j++) {
				// We don't want to copy the smallest element we just put in the sorted array.
				if (j == smallestIndex) {
					continue;
				}
				// Fill the new array with the element in the current index and increment the counter.
				newArray[indexCounter] = toBeSorted[j];
				indexCounter++;

			}
			// We are through making a copy, replace the toBeSorted array with this array.
			toBeSorted = new int[newArray.length];
			for (int k = 0; k < newArray.length; k++) {
				toBeSorted[k] = newArray[k];
			}

		}

		return sortedArray;
	}
}
