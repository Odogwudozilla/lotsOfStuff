package com.odogwudozilla.adventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IinitiateGeneralFunctions {

	String ABSOLUTE_PATH = Path.of("").toAbsolutePath().toString();

	default List<String> copyFromFile(Path filepath) {
		List<String> linez = new ArrayList<>();
		// pull data from file
		try (Stream<String> lines = Files.lines(filepath)) {
			// Populate data from the file into the list
			linez = lines.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return linez;
	}
}
