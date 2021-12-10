package com.odogwudozilla.adventOfCode.binaryDiagnostic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * --- Day 3: Binary Diagnostic ---
 * AdventOfcode: https://adventofcode.com/2021/day/3
 */
public class BinaryDiagnostic {
	// the absolute path to the input data
	private static final String filepath = "C:\\Users\\cnnachor\\lotsOfStuff\\src\\com\\odogwudozilla\\adventOfCode\\binaryDiagnostic\\inputBinaryDiagnostic1.txt";
	static final String CHAR_ZERO = "0";
	static final String CHAR_ONE = "1";
	private Integer gammaRate;
	private StringBuilder gammaRateList = new StringBuilder();
	private Integer epsilonRate;
	private StringBuilder epsilonRateList = new StringBuilder();
	private boolean isForPowerConsumption;

	/*
	* Individual lists containing the individual character from each main list item
	 */
	List<String> char1List = new ArrayList<>();
	List<String> char2List = new ArrayList<>();
	List<String> char3List = new ArrayList<>();
	List<String> char4List = new ArrayList<>();
	List<String> char5List = new ArrayList<>();
	List<String> char6List = new ArrayList<>();
	List<String> char7List = new ArrayList<>();
	List<String> char8List = new ArrayList<>();
	List<String> char9List = new ArrayList<>();
	List<String> char10List = new ArrayList<>();
	List<String> char11List = new ArrayList<>();
	List<String> char12List = new ArrayList<>();
	// List of transposed elements as a list
	List<List<String>> listOfTransposedLists = new ArrayList<>();

	List<String> originalList = new ArrayList<>();
	List<String> oxygenList = new ArrayList<>();
	List<String> c02List = new ArrayList<>();

	private int currentIndex;


	/**
	 * --- Day 3: Binary Diagnostic ---
	 * The submarine has been making some odd creaking noises, so you ask it to produce a diagnostic report just in case.
	 *
	 * The diagnostic report (your puzzle input) consists of a list of binary numbers which, when decoded properly, can tell you many useful things about the conditions of the submarine. The first parameter to check is the power consumption.
	 *
	 * You need to use the binary numbers in the diagnostic report to generate two new binary numbers (called the gamma rate and the epsilon rate). The power consumption can then be found by multiplying the gamma rate by the epsilon rate.
	 *
	 * Each bit in the gamma rate can be determined by finding the most common bit in the corresponding position of all numbers in the diagnostic report. For example, given the following diagnostic report:
	 *
	 * 00100
	 * 11110
	 * 10110
	 * 10111
	 * 10101
	 * 01111
	 * 00111
	 * 11100
	 * 10000
	 * 11001
	 * 00010
	 * 01010
	 * Considering only the first bit of each number, there are five 0 bits and seven 1 bits. Since the most common bit is 1, the first bit of the gamma rate is 1.
	 *
	 * The most common second bit of the numbers in the diagnostic report is 0, so the second bit of the gamma rate is 0.
	 *
	 * The most common value of the third, fourth, and fifth bits are 1, 1, and 0, respectively, and so the final three bits of the gamma rate are 110.
	 *
	 * So, the gamma rate is the binary number 10110, or 22 in decimal.
	 *
	 * The epsilon rate is calculated in a similar way; rather than use the most common bit, the least common bit from each position is used. So, the epsilon rate is 01001, or 9 in decimal. Multiplying the gamma rate (22) by the epsilon rate (9) produces the power consumption, 198.
	 *
	 * Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate, then multiply them together. What is the power consumption of the submarine? (Be sure to represent your answer in decimal, not binary.)
	 * @return  power consumption of the submarine
	 * @param isForPowerConsumption
	 */
	public Integer calculateGammaAndEpsilonRate (boolean isForPowerConsumption) {

		this.isForPowerConsumption = isForPowerConsumption;

		originalList = copyFromFile();

		processInputData(copyFromFile());

		gammaRate = Integer.parseInt(gammaRateList.toString(), 2);
		epsilonRate = Integer.parseInt(epsilonRateList.toString(), 2);

		System.out.println(gammaRateList);
		System.out.println(gammaRate);
		System.out.println(epsilonRateList);
		System.out.println(epsilonRate);

		return  (gammaRate * epsilonRate);
	}

	private void processInputData(List<String> copiedFromFile) {

		// Extract each item into individual lists
		for (String i : copiedFromFile) {
			seperateChars(List.of(i.split("(?!^)")));
		}
		//
		listOfTransposedLists.addAll(Arrays.asList(char1List, char2List, char3List, char4List,
				char5List, char6List, char7List, char8List,
				char9List, char10List, char11List, char12List));

		for (List liste : listOfTransposedLists) {
			currentIndex = listOfTransposedLists.indexOf(liste);

			determineListValues(liste);
		}

	}

	/**
	 * traverses each list item and keeps counts of distinct elements
	 * fills the gammaRateList or the epsilonRateList depending on which count is bigger/smaller
	 * @param liste the transposed list
	 */
	private void determineListValues(List<String> liste) {
		int zerosCount = 0;
		int onesCount = 0;

		for (String kar : liste) {
			switch (kar) {
			case CHAR_ZERO: zerosCount++; break;
			case CHAR_ONE: onesCount++; break;
			default: throw new IllegalArgumentException("does not exist");
			}
		}

		if (isForPowerConsumption) {

			appendToGammaEpsilonList(zerosCount, onesCount);
		} else {

			processForLifeSupport(zerosCount, onesCount);
		}


	}

	private void processForLifeSupport(int zerosCount, int onesCount) {

		if (onesCount == Math.max(zerosCount, onesCount)) {
			for (String elem : originalList) {
				List<String> internalList = List.of(elem.split("(?!^)"));
				if (!CHAR_ONE.equals(internalList.get(currentIndex))){
					originalList.remove(elem);
				}
			}
		}
	}

	/**
	 * fills the gammaRateList or the epsilonRateList depending on which count is bigger/smaller
	 * @param zerosCount count of zeros
	 * @param onesCount count of ones
	 */
	private void appendToGammaEpsilonList(int zerosCount, int onesCount) {

		if (zerosCount == Math.max(zerosCount, onesCount)) {
			gammaRateList.append('0');
			epsilonRateList.append('1');
		} else {
			gammaRateList.append('1');
			epsilonRateList.append('0');
		}
	}

	/**
	 * copies each individual list item[columns] into a fresh list. This is a dirty solution of a transpose
	 * @param input the list of input data
	 */
	private void seperateChars(List<String> input) {
		for (int i = 0; i < input.size(); i++) {
			switch (i) {
				case 0: char1List.add(input.get(i)); break;
				case 1: char2List.add(input.get(i)); break;
				case 2: char3List.add(input.get(i)); break;
				case 3: char4List.add(input.get(i)); break;
				case 4: char5List.add(input.get(i)); break;
				case 5: char6List.add(input.get(i)); break;
				case 6: char7List.add(input.get(i)); break;
				case 7: char8List.add(input.get(i)); break;
				case 8: char9List.add(input.get(i)); break;
				case 9: char10List.add(input.get(i)); break;
				case 10: char11List.add(input.get(i)); break;
				case 11: char12List.add(input.get(i)); break;
			default: throw new IllegalStateException("Something is wrong with the code");
			}
		}

	}

	private

	static <T> List<List<T>> transpose(List<List<T>> table) {
		List<List<T>> ret = new ArrayList<List<T>>();
		final int N = table.get(0).size();
		for (int i = 0; i < N; i++) {
			List<T> col = new ArrayList<T>();
			for (List<T> row : table) {
				col.add(row.get(i));
			}
			ret.add(col);
		}
		return ret;
	}

	private List<String> copyFromFile() {
		List<String> linez = new ArrayList<>();
		// pull data from file
		try (Stream<String> lines = Files.lines(Path.of(filepath))) {
			// Populate data from the file into the list
			linez = lines.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
			return linez;
	}
}
