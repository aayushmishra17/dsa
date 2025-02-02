package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/*
    Given an N * N matrix, your task is to rotate the matrix 90 degrees clockwise.
 */
public class RotateMatrix90Degree {
    public static void main(String[] args) {
        RotateMatrix90Degree obj = new RotateMatrix90Degree();
        int[][] matrix = new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        obj.approach1(matrix);
        obj.approach2(matrix);
    }
    /*
        Time - O(N * N)
        Space - O(N * N)

        By observation we notice that for every element at indices (i, j), the indices
        get modified to (j, (N - 1) - i)
     */
    private void approach1(int[][] matrix) {
        int[][] newMatrix = clone2DArray(matrix);
        int rowCount = newMatrix.length;
        int colCount = newMatrix[0].length;
        for(int row = 0; row < rowCount; row++) {
            for(int col = 0; col < colCount; col++) {
                newMatrix[col][rowCount - 1 - row] = matrix[row][col];
            }
        }
        printMatrix(matrix, newMatrix);
    }
    /*
        Time - O(N/2 * N/2)[transpose matrix] + O(N * N/2)[reverse each row of transposed matrix]
        Space - O(1)
     */
    private void approach2(int[][] matrix) {
        int[][] newMatrix = clone2DArray(matrix);
        int rowCount = newMatrix.length;
        int colCount = newMatrix[0].length;
        transposeMatrix(newMatrix);
        for(int[] row : matrix) {
            reverseAllRows(newMatrix);
        }
        printMatrix(matrix, newMatrix);
    }
    private int[][] clone2DArray(int[][] original2DArr) {
        int[][] clone = new int[original2DArr.length][];
        for(int row = 0; row < original2DArr.length; row++) {
            clone[row] = Arrays.copyOf(original2DArr[row], original2DArr[row].length);
        }
        return clone;
    }
    private void printMatrix(int[][] original, int[][] modified) {
        System.out.println("Original matrix: ");
        for(int row = 0; row < original.length; row++) {
            System.out.println(Arrays.toString(original[row]));
        }
        System.out.println("Rotated matrix by 90 degree:");
        for(int row = 0; row < modified.length; row++) {
            System.out.println(Arrays.toString(modified[row]));
        }
    }
    private void transposeMatrix(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        for(int row = 0; row < rowCount; row++) {
            for(int col = row; col < colCount; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    }
    private void reverseAllRows(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        for(int row = 0; row < rowCount; row++) {
            for(int col = 0; col < colCount / 2; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][colCount - 1 - col];
                matrix[row][colCount - 1 - col] = temp;
            }
        }
    }
}
