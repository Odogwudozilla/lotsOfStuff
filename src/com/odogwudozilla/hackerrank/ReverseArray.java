package com.odogwudozilla.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReverseArray {
    public static void main(String[] args) {

    }

    public static List<Integer> reversingArray(List<Integer> a) {
        // Return same array for input size less than two
        if (a.size() < 2) return a;

        List<Integer> reversedArray = new ArrayList<>();
        Stack<Integer> newStack = new Stack<>();

        for (Integer elem : a){
            // Push the elements in the stack.
            newStack.push(elem);
        }

        while (!newStack.isEmpty()) {
            reversedArray.add(newStack.pop());
        }

        return reversedArray;
    }

}
