package com.odogwudozilla.algoexpert.graphalgorithms;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstAndBreathFirstGraph {
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("a", List.of(new String[]{"c", "b"}));
        graph.put("b", List.of(new String[]{"d"}));
        graph.put("c", List.of(new String[]{"e"}));
        graph.put("d", List.of(new String[]{"f"}));
        graph.put("e", List.of(new String[]{}));
        graph.put("f", List.of(new String[]{}));

        DepthFirstAndBreathFirstGraph depthFirstAndBreathFirstGraph = new DepthFirstAndBreathFirstGraph();
        depthFirstAndBreathFirstGraph.depthFirstPrint(graph, "a");
        System.out.println();
        depthFirstAndBreathFirstGraph.depthFirstByRecursionPrint(graph, "a");

        System.out.println();
        depthFirstAndBreathFirstGraph.breathFirstGraphPrint(graph, "a");

        System.out.println();
        System.out.println(depthFirstAndBreathFirstGraph.hasPath(graph, "b", "d"));
    }

    private void depthFirstPrint(Map<String, List<String>> graph, String source) {
        Stack<String> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("current = " + current);

            for (String elem : graph.get(current)) {
                stack.push(elem);
            }
        }
    }

    private void depthFirstByRecursionPrint(Map<String, List<String>> graph, String source) {
        System.out.println("source = " + source);
        for (String neighbor : graph.get(source)) {
            depthFirstByRecursionPrint(graph, neighbor);
        }
    }

    private void breathFirstGraphPrint (Map<String, List<String>> graph, String source) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            String currentQueueItem = queue.remove();
            System.out.println("currentQueueItem = " + currentQueueItem);

            for (String elem : graph.get(currentQueueItem)) {
                queue.add(elem);
            }
        }
    }

    private boolean hasPath (Map<String, List<String>> graph, String source, String destination) {
        Queue<String> queue = new ArrayDeque<>();
        // Add the source item to the created queue.
        queue.add(source);

        while (!queue.isEmpty()) {
            String currentQueueItem = queue.remove();
            System.out.println("currentQueueItem = " + currentQueueItem);
            if (currentQueueItem.equals(destination)) return true;

            for (String neighbour : graph.get(currentQueueItem)) {
                // Loop through each neighbour of the currentItem and add it to the queue.
                queue.add(neighbour);
            }
        }
        return false;
    }
}
