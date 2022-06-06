package com.odogwudozilla.algodaily;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner
 * (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(composePath(3, 7));
    }

    private static int composePath(int m, int n) {
        int[][] matrix = new int[m][n];

        // The last column values are always 1 since there is only one way to proceed from those points. Fill them.
        for (int i=0; i<m; i++) {
            matrix[i][n-1] = 1;
        }
        // The bottom row values are always 1 since there is only one way to proceed from those points. Fill them.
        for (int j=0; j<n; j++) {
            matrix[m-1][j] = 1;
        }

        // Fill the remaining boxes on the grid
        for (int row = m-2; row >=0; row--) {
            for(int column = n-2; column >=0; column--) {
                // Apply the sum of the 'right' and 'down' values to each current row/column
                matrix[row][column] = matrix[row][column + 1] + matrix[row + 1][column];
            }
        }

        // Return the value of the first box
        return matrix[0][0];
    }
}
