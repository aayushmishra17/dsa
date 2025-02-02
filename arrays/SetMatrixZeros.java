package arrays;

import java.util.Arrays;

public class SetMatrixZeros {
    public static void main(String[] args) {
        SetMatrixZeros obj = new SetMatrixZeros();
        int[][] matrix = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 0, 1, 1 },
                { 1, 1, 0, 1 },
                { 0, 1, 1, 1 }
        };
        obj.approach1(matrix);
        obj.approach2(matrix);
        obj.approach3(matrix);
    }
    /*
        Time - O((N * M) * (N + M))[For every 0 element we set the entire row and column to -1] + O(N * M)
        Space - O(1)
     */
    private void approach1(int[][] matrix) {
        int[][] newMatrix = clone2DArray(matrix);
        int rowCount = newMatrix.length;
        int colCount = newMatrix[0].length;
        for(int row = 0; row < rowCount; row++) {
            for(int col = 0; col < colCount; col++) {
                if(newMatrix[row][col] == 0) {
                    // Set 'row' row elements as -1
                    for(int c = 0; c < colCount; c++) {
                        if (newMatrix[row][c] != 0) {
                            newMatrix[row][c] = -1;
                        }
                    }
                    // Set 'col' column elements as -1
                    for(int r = 0; r < rowCount; r++) {
                        if (newMatrix[r][col] != 0) {
                            newMatrix[r][col] = -1;
                        }
                    }
                }
            }
        }
        for(int row = 0; row < rowCount; row++) {
            for(int col = 0; col < colCount; col++) {
                if(newMatrix[row][col] == -1) {
                    newMatrix[row][col] = 0;
                }
            }
        }
        printMatrix(matrix, newMatrix);
    }
    /*
        Time - O(N * M) + O(N * M)
        Space - O(N) + O(M)
        We utilize separate rowHash and colHash arrays to mark the rows and column to be set as 0.
     */
    private void approach2(int[][] matrix) {
        int[][] newMatrix = clone2DArray(matrix);
        int rowCount = newMatrix.length;
        int colCount = newMatrix[0].length;
        int[] rowHash = new int[rowCount];
        int[] colHash = new int[colCount];
        for(int row = 0; row < rowCount; row++) {
            for(int col = 0; col < colCount; col++) {
                if(newMatrix[row][col] == 0) {
                    rowHash[row] = 1;
                    colHash[col] = 1;
                }
            }
        }
        for(int row = 0; row < rowCount; row++) {
            for(int col = 0; col < colCount; col++) {
                if(rowHash[row] == 1 || colHash[col] == 1) {
                    newMatrix[row][col] = 0;
                }
            }
        }
        printMatrix(matrix, newMatrix);
    }
    /*
        Time - O(N * M) + O(N * M)
        Space - O(1)
        We'll use the first row from row[0...colCount-1] as a marker for columns.
        We'll use the first column from col[1...rowCount-1] + col0 as a marker for rows.
     */
    private void approach3(int[][] matrix) {
        int[][] newMatrix = clone2DArray(matrix);
        int rowCount = newMatrix.length;
        int colCount = newMatrix[0].length;
        int col0 = 1;
        for(int row = 0; row < rowCount; row++) {
            for(int col = 0; col < colCount; col++) {
                if(newMatrix[row][col] == 0) {
                    // Mark row matrix[...][0] as 0
                    newMatrix[row][0] = 0;
                    // Mark col matrix[0][...] as 0
                    if(col != 0) {
                        newMatrix[0][col] = 0;
                    } else {
                        col0 = 0;
                    }
                }
            }
        }
        for(int row = rowCount - 1; row >= 1; row--) {
            for(int col = colCount - 1; col >= 1; col--) {
                if(newMatrix[row][col] != 0) {
                    if(newMatrix[0][col] == 0 || newMatrix[row][0] == 0) {
                        newMatrix[row][col] = 0;
                    }
                }
            }
        }
        if(newMatrix[0][0] == 0) {
            for(int col = 0; col < colCount; col++) {
                newMatrix[0][col] = 0;
            }
        }
        if(col0 == 0) {
            for(int row = 0; row < rowCount; row++) {
                newMatrix[row][0] = 0;
            }
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
        System.out.println("Modified matrix:");
        for(int row = 0; row < modified.length; row++) {
            System.out.println(Arrays.toString(modified[row]));
        }
    }
}
