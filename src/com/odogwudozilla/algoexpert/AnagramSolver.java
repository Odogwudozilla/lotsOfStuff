package com.odogwudozilla.algoexpert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramSolver {
    public static void main(String[] args) {
        AnagramSolver anagramSolver = new AnagramSolver();

        System.out.println(anagramSolver.checkIsAnagramByHash("nameless", "salesmen"));
        System.out.println(anagramSolver.checkIsAnagramBySort("nameless", "salesmen"));

    }

    private boolean checkIsAnagramByHash (String firstString, String secondString) {
        // if both strings are not the same length, they cannot be anagrams
        if (firstString.length() != secondString.length()) {
            return false;
        }

        char[] firstStringArray = firstString.toCharArray();
        char[] secondStringArray = secondString.toCharArray();

        Map<Character, Integer> firstStringHash = new HashMap<>();
        Map<Character, Integer> secondStringHash = new HashMap<>();

        // Fill the has maps
        for (char elem : firstStringArray) {
            if (!firstStringHash.containsKey(elem)) {
                // The key does not exist. Create it.
                firstStringHash.put(elem, 1);
            } else {
                // Otherwise, increment the value
                int count = firstStringHash.getOrDefault(elem, 0);
                firstStringHash.put(elem, ++count);
            }
        }
        System.out.println(firstStringHash.entrySet());

        for (char elem : secondStringArray) {
            if (!secondStringHash.containsKey(elem)) {
                secondStringHash.put(elem, 1);
            } else {
                int count = secondStringHash.getOrDefault(elem, 0);
                secondStringHash.put(elem, ++count);
            }
        }
        System.out.println(secondStringHash.entrySet());

        return firstStringHash.equals(secondStringHash);
    }


    private boolean checkIsAnagramBySort(String firstString, String secondString) {
        char[] firstStringArray = firstString.toCharArray();
        char[] secondStringArray = secondString.toCharArray();

        // sort both char arrays
        Arrays.sort(firstStringArray);
        System.out.println(firstStringArray);
        Arrays.sort(secondStringArray);
        System.out.println(secondStringArray);

        return Arrays.equals(firstStringArray, secondStringArray);
    }
}
