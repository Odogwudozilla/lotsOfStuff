package com.odogwudozilla.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a collection of input strings and a collection of query strings. For each query string, determine how many times it occurs in the list of input strings.
 * Return an array of the results.
 *
 * Function Description
 *
 * Complete the function matchingStrings in the editor below. The function must return an array of integers representing the frequency of occurrence of each query string in strings.
 *
 * matchingStrings has the following parameters:
 *
 * string strings[n] - an array of strings to search
 * string queries[q] - an array of query strings
 * Returns
 *
 * int[q]: an array of results for each query
 */
public class SparseArrays {
    public static void main(String[] args) {

    }

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {

        List<Integer> resultList = new ArrayList<>();
        Map<String, Integer> queriesMap = new HashMap<>();
        for(String elem : queries) {
            queriesMap.put(elem, 0);
        }

        for(String eleme : strings) {
            if(queriesMap.containsKey(eleme)) {

                queriesMap.computeIfPresent(eleme,(k, v) -> v + 1);
            }
        }

        for(String keys : queries) {
            if(queriesMap.containsKey(keys)) {
                resultList.add(queriesMap.get(keys));
            }
        }

        return resultList;

    }
}
