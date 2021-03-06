package com.odogwudozilla.grokkingAlgorithms;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreathFirst {

	Map<String, ArrayDeque<String>> people = new HashMap<>() {{
		put("Anuj", new ArrayDeque<>(Arrays.asList("")));
		put("Bob", new ArrayDeque<>(Arrays.asList("Anuj")));
		put("Peggy", new ArrayDeque<>(Arrays.asList("")));
		put("Alice", new ArrayDeque<>(Arrays.asList("Peggy")));
		put("Claire", new ArrayDeque<>(Arrays.asList("Thom", "Johnny")));
		put("Johnny", new ArrayDeque<>(Arrays.asList("")));
		put("Thom", new ArrayDeque<>(Arrays.asList("")));
		put("Odogwu", new ArrayDeque<>(Arrays.asList("Alice", "Claire", "Bob")));
	}};

	Map<String, List<String>> map1 = new HashMap<>();

	public static void main(String[] args) {



	}

	private String determineMangoSeller(Map<String, ArrayDeque<String>> input) {
		String answer = "";
		for (String eleme : input.keySet()) {
			String currentName = eleme;

			ArrayDeque<String> toBeChecked = input.get(currentName);
			String currentValue = toBeChecked.getFirst();
			if (currentValue.isEmpty()) continue;

			switch (currentName) {
			case "Odogwu":
				if (currentValue.equals("Thom")) {
					answer = currentValue;
				} else {
					toBeChecked.pop();
				}
			}
		}
		return answer;
	}

	private void mapPeople(List<String> people) {
		map1.put("Odogwu", Arrays.asList("alice", "bob", "claire"));
		map1.put("Bob", Arrays.asList("Anuj", "Peggy"));
		map1.put("Alice", Arrays.asList("Peggy"));
		map1.put("Claire", Arrays.asList("Thom", "Johnny"));
	}
}
