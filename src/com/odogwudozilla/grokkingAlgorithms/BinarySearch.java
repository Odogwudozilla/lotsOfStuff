package com.odogwudozilla.grokkingAlgorithms;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BinarySearch {

	private static int iterationCount;
	static LocalTime startTime;
	public static void main(String[] args) {
		List<Integer> myList = new ArrayList<>();
		IntStream.iterate(1, n -> n+1).filter(n -> n%2!=0).limit(50).forEach(x -> myList.add(x));
		List<Integer> myList1 = new ArrayList<>();
		IntStream.iterate(1, n -> n+1).filter(n -> n%2!=0).limit(100000000).forEach(x -> myList1.add(x));

		System.out.println("Item in index: " + binarySearch0Logn(myList1, 99357) + " was found in iteration: " + iterationCount);
		System.out.println("Process completed in " + Duration.between(startTime, LocalTime.now()).toMillis() + " milliSeconds");

	}

	/**
	 * Calculates the number of times it takes to find a given element in a list in 0(log n) time.
	 * @param listToSearch the list input containing the elements.
	 * @param itemToSearch the element to be found.
	 * @return the index of the element.
	 */
	public static Integer binarySearch0Logn(List<Integer> listToSearch , Integer itemToSearch) {
		startTime = LocalTime.now();
		int lowestIndex = 0;
		int highestIndex = listToSearch.size() - 1;

		while (lowestIndex <= highestIndex) {

			iterationCount++;
			int middleIndex = (lowestIndex + highestIndex) / 2;
			int guess = listToSearch.get(middleIndex);

			if (guess == itemToSearch) {
				return middleIndex;
			}

			if (guess > itemToSearch) {
				highestIndex = --middleIndex;
			} else {
				lowestIndex = ++middleIndex;
			}
		}
		throw new IllegalStateException("The binary search could not find the item '" + itemToSearch
											+ "' in the provided list, after iteration " + iterationCount);
	}
}
