package com.odogwudozilla.adventOfCode.sonarSweep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class SonarSweep {

	private static final String filepath = "C:\\Users\\cnnachor\\lotsOfStuff\\src\\com\\odogwudozilla\\adventOfCode\\sonarSweep\\inputSonarSweep.txt";

	public Integer calculateIncreaseCount() {
		// Keep track of the matched and non-matched
		AtomicReference<Integer> nonMatchCount = new AtomicReference<>(0);
		AtomicReference<Integer> matchCount = new AtomicReference<>(0);

		// Use try-with-resources
		try (Stream<String> lines = Files.lines(Path.of(filepath))) {

			lines.mapToInt(value -> Integer.parseInt(value)) // first convert to integers
					.reduce((a,b) -> {
						if ((a < b)) {
							matchCount.getAndSet(matchCount.get() + 1);
							System.out.println("Match: " + a + "<->" + b);
						} else {
							nonMatchCount.getAndSet(nonMatchCount.get() + 1);
							System.out.println("NOT a match: " + a + "<->" + b);
						}
						return b; // return the second element for next iteration.
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		// Output the totals for comparison
		System.out.println("Total: " + (matchCount.get() + nonMatchCount.get()));
		System.out.println(nonMatchCount.get());

		return matchCount.get();
	}


}
