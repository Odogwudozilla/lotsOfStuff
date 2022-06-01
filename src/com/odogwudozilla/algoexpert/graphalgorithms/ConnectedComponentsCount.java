package com.odogwudozilla.algoexpert.graphalgorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConnectedComponentsCount {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(new Integer[]{8, 1, 5}));
        graph.put(1, List.of(new Integer[]{0}));
        graph.put(5, List.of(new Integer[]{0,8}));
        graph.put(8, List.of(new Integer[]{0,5}));
        graph.put(2, List.of(new Integer[]{3,4}));
        graph.put(3, List.of(new Integer[]{2,4}));
        graph.put(4, List.of(new Integer[]{3,2}));

        ConnectedComponentsCount connectedComponentsCount = new ConnectedComponentsCount();

        System.out.println(connectedComponentsCount.connectedCount(graph));
    }

    private Integer connectedCount(Map<Integer, List<Integer>> graph) {
        Set<Integer> visitedNodes = new HashSet<>();
        Integer count = 0;

        for (Integer node : graph.keySet()) {
            if(exploreNodes(graph, node, visitedNodes)) {
                count++;
            }
        }
        return count;
    }

    private boolean exploreNodes(Map<Integer, List<Integer>> graph, Integer currentNode, Set<Integer> visitedNodes) {

        if(visitedNodes.contains(currentNode)) return false;

        visitedNodes.add(currentNode);

        for (Integer neighbour : graph.get(currentNode)) {
            exploreNodes(graph, neighbour, visitedNodes);
        }

        return true;

    }
}
