package com.odogwudozilla.grokkingAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSort {

	public static void main(String[] args) {
		List<Integer> myList = Arrays.asList(5, 3, 43, 78, 6, 1, 2, 10, 96);
		QuickSort quickSort = new QuickSort();
		System.out.println(quickSort.sortByQuickSort(myList));
	}

	/**
	 * Sorts a given list recursively.
	 * @param toBeSorted the list to be sorted.
	 * @return the sorted list.
	 */
	private List<Integer> sortByQuickSort(List<Integer> toBeSorted) {
		// List with less than two elements is already sorted.
		if (toBeSorted.size() < 2) return toBeSorted;
		// Starting point of the sorting. The middle is better.
		int pivot = toBeSorted.get(toBeSorted.size()/2);

		// Define two lists to store elements smaller-than or bigger-than the pivot.
		List<Integer> less = new ArrayList<>();
		List<Integer> greater = new ArrayList<>();

		List<Integer> pivotArray = Arrays.asList(pivot);

		for (int elem : toBeSorted) {
			if (elem < pivot) {
				less.add(elem);
			}
			if (elem > pivot) {
				greater.add(elem);
			}
		}

		// Recursively sort both lower and upper bound lists and then merge.
		return  Stream.of(sortByQuickSort(less), pivotArray, sortByQuickSort(greater))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

	}
}

