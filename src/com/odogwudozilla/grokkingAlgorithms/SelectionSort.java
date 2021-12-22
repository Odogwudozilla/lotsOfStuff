package com.odogwudozilla.grokkingAlgorithms;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int myArray[] = {5, 3, 6, 2, 10};
		SelectionSort selectionSort = new SelectionSort();
		System.out.println(Arrays.toString(selectionSort.selectionSortArray(myArray)));
	}
	private int determineSmallestElement (int arr[]) {
		int initialElement = arr[0];
		int smallestIndex = 0;

		for (int i =0; i < arr.length; i++) {
			if (arr[i] < initialElement) {
				initialElement = arr[i];
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}

	private int[] selectionSortArray(int toBeSorted[]) {
		int arraySize = toBeSorted.length;
		int sortedArray[] = new int[arraySize];
		int iterations = toBeSorted.length;

		for (int i =0; i < iterations; i++) {
			int smallestIndex = determineSmallestElement(toBeSorted);
			sortedArray[i] = toBeSorted[smallestIndex];

			int newArray[] = new int[toBeSorted.length - 1];
			for (int j =0; j < toBeSorted.length; j++) {
				if(j == smallestIndex){
					continue;
				}
				if (j > newArray.length - 1){
					newArray[j-1] = toBeSorted[j];
				} else {
					newArray[j] = toBeSorted[j];
				}
			}

			toBeSorted = new int[newArray.length];
			for (int k =0; k < newArray.length; k++) {
				toBeSorted[k] = newArray[k];
			}

		}

		return sortedArray;

	}
}
