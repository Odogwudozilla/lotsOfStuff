package com.odogwudozilla.algoexpert.graphalgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UndirectedPath {
    Map<String, List<String>> graph = new HashMap<>();

    public static void main(String[] args) {
        String[][] edges = {
                {"i","j"},
                {"k","i"},
                {"m","k"},
                {"k","l"},
                {"o","n"}
        };

        UndirectedPath undirectedPath = new UndirectedPath();
        System.out.println(undirectedPath.findUndirectedPath(edges, "k", "o"));

    }

    private boolean findUndirectedPath(String[][] edges, String nodeA, String nodeB){
        // build the edges connecting the nodes
        graph = buildGraph(edges);
        // Set representing visited Nodes
        Set<String> visitedNodes = new HashSet<>();

        return hasPath(graph, nodeA, nodeB, visitedNodes);

    }

    private boolean hasPath(Map<String, List<String>> graph, String source, String destination, Set<String> visited) {
        // the path from source to destination is already determined. No further processing needed.
        if (source.equals(destination)) return true;
        // This (source) node has already been visited. Do not repeat it, to prevent an infinite loop.
        if (visited.contains(source)) return false;

        // We are about to visit this (source) node, add it to the 'visited' set.
        visited.add(source);
        System.out.println("visited Nodes= " + visited);

        for (String neighbour : graph.get(source)) {
            // given that the neighbour is connected to the source
           if(hasPath(graph, neighbour, destination, visited)) {
               // it follows that the source is also connected to the destination if the neighbour is connected to the destination
               return true;
           }
        }

        return false;
    }

    /**
     * build a graph representation of nodes and edges in key-value pairs
     * @param edges the array of the nodes
     * @return the built graph
     */
    private Map<String, List<String>> buildGraph(String[][] edges) {

        for (String[] edge : edges) {
            // if the graph does not have these keys (node) from the array, create them
            if (!(graph.containsKey(edge[0]))) graph.put(edge[0], null);
            if (!(graph.containsKey(edge[1]))) graph.put(edge[1], null);

            // add an edge for each of the members of the array because they are connected (since they are in the same array)
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);

        }
        System.out.println("Nodes as keys and edges as values: " + graph.entrySet());

        return graph;

    }
}
