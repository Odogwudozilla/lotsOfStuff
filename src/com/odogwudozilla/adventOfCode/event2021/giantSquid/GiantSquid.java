package com.odogwudozilla.adventOfCode.event2021.giantSquid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.odogwudozilla.adventOfCode.IinitiateGeneralFunctions;

/**
 * --- Day 3: Giant Squid ---
 * AdventOfcode: https://adventofcode.com/2021/day/4
 */
public class GiantSquid implements IinitiateGeneralFunctions {
	// the absolute path to the input data
	private static final Path filePath = Paths.get(ABSOLUTE_PATH + "\\src\\com\\odogwudozilla\\adventOfCode\\event2021\\giantSquid\\giantSquid1.txt");

	int[] drawnNumbers = new int[100];

	int matrix1[][] = new int[5][5];
	int matrix2[][] = new int[5][5];

	public void determineWinningBoard() {

		copyFromFile(filePath);
	}



	@Override
	public List<String> copyFromFile(Path filepath) {
		List<String> linez = new ArrayList<>();
		// pull data from file
		try (Stream<String> lines = Files.lines(filepath)) {
			// Populate data from the file into the list
			linez = lines.collect(Collectors.toList());

			String firstLine[] = linez.get(0).split(",");

			for (int i = 0; i < firstLine.length; i ++) {
				drawnNumbers[i] = Integer.parseInt(firstLine[i]);
			}
			int spacesCount = 0;
			int currentLine = 1;
			int incrementer = 0;

			for (; currentLine < linez.size(); currentLine++) {

				while (incrementer < 6) {

					if (linez.get(currentLine).isEmpty()) {
						spacesCount++;
						incrementer++;
						currentLine++;
						continue;
					}

					switch (spacesCount) {
					case 1:
						String currentLineArray[] = linez.get(currentLine).trim().split("\\s+");
						for (int i = 0; i < currentLineArray.length; i++) {
							matrix1[incrementer - 1][i] = Integer.parseInt(currentLineArray[i]);

						}
						incrementer++;
						currentLine++;
						break;
					}

				}
				if (currentLine < linez.size()) {
					incrementer = 0;
					currentLine--;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return linez;
	}

	void computeArrayMatrices () {

	}

}
