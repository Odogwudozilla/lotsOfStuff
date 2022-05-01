package com.odogwudozilla.hackerrank;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 *
 * For example, the square matrix  is shown below:
 *
 * 1 2 3
 * 4 5 6
 * 9 8 9
 * The left-to-right diagonal = 1+5+9=15. The right to left diagonal = 3+5+9=17. Their absolute difference is |15-17| = 2.
 *
 * Function description
 *
 * Complete the  function in the editor below.
 *
 * diagonalDifference takes the following parameter:
 *
 * int arr[n][m]: an array of integers
 * Return
 * int: the absolute diagonal difference
 *
 * Input Format
 *
 * The first line contains a single integer,n, the number of rows and columns in the square matrix arr.
 * Each of the next n lines describes a row, arr[i], and consists of n space-separated integers arr[i][j].
 *
 * Constraints
 *-100<=arr[i][j]<=100
 * Output Format
 *
 * Return the absolute difference between the sums of the matrix's two diagonals as a single integer.
 */
public class DiagonalDifference {

	public static void main(String[] args) {
		List<List<Integer>> list = List.of(
				List.of(3),
				List.of(11, 2, 4),
				List.of(4, 5, 6),
				List.of(10, 8, -12)
		);
		diagonalDifference(list);

	}

	/*
	 * Complete the 'diagonalDifference' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts 2D_INTEGER_ARRAY arr as parameter.
	 */

	public static int diagonalDifference(List<List<Integer>> arr) {

		List<Integer> listFirstDiagonal = new ArrayList<>();
		List<Integer> listSecondDiagonal = new ArrayList<>();
		Integer sumFirstDiagonal;
		Integer sumSecondDiagonal;
		int keepTrackOne = 0;
		int keepTrackTwo = 3;


		for (int i=0; i<arr.size(); i++){
			if (arr.get(i).size() != 3) {
				continue;
			}
			for (int j=0; j<arr.get(i).size(); j++) {
				if (keepTrackOne==j) {
					listFirstDiagonal.add(arr.get(i).get(keepTrackOne));
					listSecondDiagonal.add(arr.get(i).get(keepTrackTwo-1));
					keepTrackTwo--;
				}
			}
			keepTrackOne++;
		}
		sumFirstDiagonal = Math.toIntExact(listFirstDiagonal.stream()
				.mapToLong(Integer::longValue)
				.sum());
		sumSecondDiagonal = Math.toIntExact(listSecondDiagonal.stream()
				.mapToLong(Integer::longValue)
				.sum());

		System.out.println(listFirstDiagonal);
		System.out.println(listSecondDiagonal);
		System.out.println(sumFirstDiagonal);
		System.out.println(sumSecondDiagonal);
		System.out.println(sumFirstDiagonal - sumSecondDiagonal);

		return Math.abs(sumFirstDiagonal - sumSecondDiagonal);
	}
}
