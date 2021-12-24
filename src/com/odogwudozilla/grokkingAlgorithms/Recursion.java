package com.odogwudozilla.grokkingAlgorithms;

import java.util.Arrays;
import java.util.List;

public class Recursion {

	public static void main(String[] args) {
		List<Integer> myList = Arrays.asList(5, 3, 43, 78, 6, 1, 2, 10, 96);
		Recursion recursion = new Recursion();
		System.out.println("The sum of numbers is: " + recursion.sumListRecursively(myList));

	}

	private int sumListRecursively (List<Integer> listToBeSummed) {
		if (listToBeSummed.size() == 0) return 0;
		if (listToBeSummed.size() == 1) return listToBeSummed.get(0);
		int baseCase = listToBeSummed.get(0);
		return baseCase + sumListRecursively(listToBeSummed.subList(1, listToBeSummed.size()));
	}
}
