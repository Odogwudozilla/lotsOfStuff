package com.odogwudozilla.adventOfCode.sonarSweep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
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

			lines.mapToInt(value -> Integer.parseInt(value)) // first convert data to integers
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

	public Integer calculateSlidingWindow() {
		// define variables to keep count
		// Keep track of the matched and non-matched
		AtomicReference<Integer> nonMatchCount = new AtomicReference<>(0);
		AtomicReference<Integer> matchCount = new AtomicReference<>(0);
		List<Integer> sumList = new ArrayList<>();


		//calculate sliding windows
		try (Stream<String> lines = Files.lines(Path.of(filepath))) {

			List<Integer> intList = new ArrayList<>();

			lines.mapToInt(value -> Integer.parseInt(value)) // first convert data to integers
					.forEach(value -> intList.add(value));

			Queue<Integer> qwewe = new ArrayDeque<>();
			for (int i=0; i < intList.size(); i++ ) {
				for ( int j = 0; j<3; j++){
					qwewe.offer(intList.get(i));
				}
				sumList.add(qwewe.stream().mapToInt(Integer::intValue).sum());
				qwewe.poll();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		sumList.stream().reduce((a,b) -> {
			if ((a < b)) {
				matchCount.getAndSet(matchCount.get() + 1);
				System.out.println("Match: " + a + "<->" + b);
			} else {
				nonMatchCount.getAndSet(nonMatchCount.get() + 1);
				System.out.println("NOT a match: " + a + "<->" + b);
			}
			return b; // return the second element for next iteration.
		});
		//return correct value
		return matchCount.get();
	}


}
