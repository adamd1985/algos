package org.adamd.challanges;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RobotMatrix {
    public static void main(String[] args) {
        // we store the path in a LinkedHashSet
        Set<Point> path = new LinkedHashSet<>();

        // we define the maze
        boolean[][] maze  = new boolean[6][6];
        maze[2][0]=true;
        maze[3][0]=true;
        maze[3][1]=true;
        maze[3][2]=true;
        maze[3][3]=true;

        // we define a Set for storing the visited cells
        Set<Point> visited = new HashSet<>();
        // we compute and display the path
        RobotMatrix.computePath(5, 5, maze, path, visited);
        System.out.println("\nComputed path (Memoization):");
        path.forEach(System.out::println);

        System.out.println(String.format("\nCount Paths: %d", countPathsBottomUp(5, 5)));
    }

    public static boolean computePath(int m, int n, boolean[][] maze, Set<Point> path, Set<Point> visitFailed) {
        if (path == null || maze ==null || visitFailed == null) {
            throw new IllegalArgumentException("Path, maze and visitFailed cannot be null");
        }
        // we fell off the grid so we return
        if (m < 0 || n < 0) {
            return false;
        }
        // we cannot step at this cell
        if (maze[m][n]) {
            return false;
        }
        Point cell = new Point(m, n);
        // Check if we've already visited this cell
        if (visitFailed.contains(cell)) {
            return false;
        }
        if (((m == 0) && (n == 0)) // we reached the target (this is the bottom-right corner)
                || computePath(m, n - 1, maze, path, visitFailed) // try to go to the right
                || computePath(m - 1, n, maze, path, visitFailed)) { // try to go to down

            // we add the cell to the path
            path.add(cell);
            return true;
        }
        visitFailed.add(cell);
        return false;
    }
    // Memoization
    public static int countPathsBottomUp(int m, int n) {
        if (m <= 1 || n <= 1) {
            return -1;
        }
        // cache the results of subproblems
        int[][] count = new int[m][n];
        // go in any cell of the first row is 1
        for (int j = 0; j < n; j++) {
            count[0][j] = 1;
        }
        // go in any cell of the first column is 1
        for (int i = 0; i < m; i++) {
            count[i][0] = 1;
        }
        // determine the paths for other cells in bottom-up manner
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                count[i][j] = count[i - 1][j] + count[i][j - 1];
                // if diagonal movements are allowed then add: + count[i-1][j-1];
            }
        }
        return count[m - 1][n - 1];
    }
}
