package com.odogwudozilla.adventOfCode.binaryDiagnostic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.linear.MatrixUtils;

public class BinaryDiagnostic {
	// the absolute path to the input data
	private static final String filepath = "C:\\Users\\cnnachor\\lotsOfStuff\\src\\com\\odogwudozilla\\adventOfCode\\binaryDiagnostic\\inputBinaryDiagnostic.txt";
	private Integer gammaRate;
	private Integer epsilonRate;
	private List<String>  individualChars = new ArrayList<>();


	public void calculateGammaAndEpsilonRate () {

		determineGammaRate(copyFromFile());
	}

	private void determineGammaRate(List<String> copiedFromFile) {
		List<List<String>> listOfList = new ArrayList<>();
		for (int i = 0; i < copiedFromFile.size(); i++) {
			listOfList.add(Arrays.asList(copiedFromFile.get(i)));
		}

		System.out.println(transpose(listOfList));


	}

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
