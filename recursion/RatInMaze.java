package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * A rat is placed at (0, 0) in a square matrix of order (N*N). It has to reach the destination (N-1, N-1).
 * Find all possible paths the rat can take to reach the destination. The rat can move Up, Down, Left, Right directions.
 * Value 0 represent the cell is blocked and value 1 represents a path.
 * In a path, no cell can be visited more than once.
 * Print possible paths in lexicographical order.
 */
public class RatInMaze {
    public static void main(String[] args) {
        int n = 4;
        int[][] maze = new int[][] {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1},
        };
        System.out.println("Maze:");
        for(int[] row : maze) {
            System.out.println(Arrays.toString(row));
        }
        RatInMaze obj = new RatInMaze();
        obj.approach1(maze, n);
    }
    /*
     * Time - O(4^(N*N))
     * Space - O(N * N)[aux. stack space in worst case where we traverse every cell before backtracking]
     */
    private void approach1(int[][] maze, int n) {
        if(maze[0][0] == 0) {
            System.out.println("Entry cell is blocked!");
            return;
        }
        int[][] visited = new int[n][n];
        List<String> ans = new ArrayList<>();
        exploreMaze1(0, 0, "", maze, visited, ans, n);
        System.out.println("Possible paths:");
        System.out.println(ans);
    }
    private void exploreMaze1(int row, int col, String path, int[][] maze, int[][] visited, List<String> ans, int n) {
        if(row == n - 1 && col == n - 1) {
            ans.add(path);
            return;
        }
        // Down
        if(row + 1 < n && visited[row + 1][col] == 0 && maze[row + 1][col] == 1) {
            visited[row][col] = 1;
            exploreMaze1(row + 1, col, path + "D", maze, visited, ans, n);
            visited[row][col] = 0;
        }
        // Left
        if(col - 1 >= 0 && visited[row][col - 1] == 0 && maze[row][col - 1] == 1) {
            visited[row][col] = 1;
            exploreMaze1(row, col - 1, path + "L", maze, visited, ans, n);
            visited[row][col] = 0;
        }
        // Right
        if(col + 1 < n && visited[row][col + 1] == 0 && maze[row][col + 1] == 1) {
            visited[row][col] = 1;
            exploreMaze1(row, col + 1, path + "R", maze, visited, ans, n);
            visited[row][col] = 0;
        }
        // Up
        if(row - 1 >= 0 && visited[row - 1][col] == 0 && maze[row - 1][col] == 1) {
            visited[row][col] = 1;
            exploreMaze1(row - 1, col, path + "U", maze, visited, ans, n);
            visited[row][col] = 0;
        }
    }
    /*
     * Optimizes exploreMaze1 method by using below observation we can avoid writing four separate recursion steps.
     * All the DLRU movements lead to follwoing change in row and col values:
     * Down ->  (+1, 0)
     * Left ->  (0, -1)
     * Right -> (0, +1)
     * Up ->    (-1, 0)
     * So we can just maintain two arrays di and dj and iterate over the array.
     */
    private void exploreMaze2(int row, int col, String path, int[][] maze, int[][] visited, List<String> ans, int n) {
        if(row == n - 1 && col == n - 1) {
            ans.add(path);
            return;
        }
        String direction = "DLRU";
        for(int indx = 0; indx < 4; indx++) {
            int nextRow = row + direction.charAt(indx);
            int nextCol = col + direction.charAt(indx);
            if(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < n && visited[nextRow][nextCol] == 0 
                && maze[nextRow][nextCol] == 1) {
                    visited[row][col] = 1;
                    exploreMaze2(nextRow, nextCol, path + direction.charAt(indx), maze, visited, ans, n);
                    visited[row][col] = 0;
            }
        }
    }
}
