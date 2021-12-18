package com.odogwudozilla.adventOfCode.binaryDiagnostic;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

public class BinaryDiagnostic implements IinitiateGeneralFunctions {
	// the absolute path to the input data
	private static final Path filepath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\binaryDiagnostic\\inputBinaryDiagnostic.txt");
	private Integer gammaRate;
	private StringBuilder gammaRateList = new StringBuilder();
	private Integer epsilonRate;
	private StringBuilder epsilonRateList = new StringBuilder();

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
	List<List<String>> listOfLists = new ArrayList<>();

	public Integer calculateGammaAndEpsilonRate () {

		determineGammaRate(copyFromFile(filepath));

		gammaRate = Integer.parseInt(gammaRateList.toString(), 2);
		epsilonRate = Integer.parseInt(epsilonRateList.toString(), 2);

		System.out.println(gammaRateList);
		System.out.println(gammaRate);
		System.out.println(epsilonRateList);
		System.out.println(epsilonRate);

		return  (gammaRate * epsilonRate);
	}

	private void determineGammaRate(List<String> copiedFromFile) {


		for (String i : copiedFromFile) {
			seperateChars(List.of(i.split("(?!^)")));
		}
		listOfLists.addAll(Arrays.asList(char1List, char2List, char3List, char4List,
				char5List, char6List, char7List, char8List,
				char9List, char10List, char11List, char12List));

		for (List liste : listOfLists) {
			determineListValues(liste);
		}

	}

	private void determineListValues(List<String> liste) {
		int zerosCount = 0;
		int onesCount = 0;

		for (String kar : liste) {
			switch (kar) {
				case "0": zerosCount++; break;
				case "1": onesCount++; break;
			default: throw new IllegalArgumentException("does not exist");
			}
		}

		if (zerosCount == Math.max(zerosCount, onesCount)) {
			gammaRateList.append('0');
			epsilonRateList.append('1');
		} else {
			gammaRateList.append('1');
			epsilonRateList.append('0');
		}

	}

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

//	 public List<String> copyFromFile() {
//		List<String> linez = new ArrayList<>();
//		// pull data from file
//		try (Stream<String> lines = Files.lines(filepath)) {
//			// Populate data from the file into the list
//			linez = lines.collect(Collectors.toList());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//			return linez;
//	}
}
