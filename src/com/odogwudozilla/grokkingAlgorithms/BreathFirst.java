package com.odogwudozilla.grokkingAlgorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BreathFirst {
	List<String> people = Arrays.asList("Anuj", "Bob", "Peggy", "Alice", "Claire", "Johnny", "Thom", "Odogwu");
	Map<String, List<String>> map1 = new HashMap<>();

	public static void main(String[] args) {



	}

	private String determineMangoSeller(Queue<List<Map>> input) {
		return "testing";
	}

	private void mapPeople(List<String> people) {
		map1.put("Odogwu", Arrays.asList("alice", "bob", "claire"));
		map1.put("Bob", Arrays.asList("Anuj", "Peggy"));
		map1.put("Alice", Arrays.asList("Peggy"));
		map1.put("Claire", Arrays.asList("Thom", "Johnny"));
	}
}
