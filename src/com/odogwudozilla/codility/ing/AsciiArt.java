package com.odogwudozilla.codility.ing;

public class AsciiArt {
    public static void main(String[] args) {
        solution(2);
    }

    public static void solution(int N) {

        for (int i = 0; i < N-1; i++) {
            // Print the letter 'L' N-1 times with line breaks
            System.out.println("L");

        }
        for (int i = 0; i < N; i++) {
            // Print the letter 'L' N times with no line breaks
            System.out.print("L");
        }
    }
}
