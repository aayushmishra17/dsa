package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given a Matrix, print the given matrix in spiral order.
 */
public class SpiralTraversalOfMatrix {
    public static void main(String[] args) {
        SpiralTraversalOfMatrix obj = new SpiralTraversalOfMatrix();
        int[][] matrix = new int[][] {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        };
        obj.approach1(matrix);
    }
    /*
        Time - O(N * M)[We are iterating over every element only once]
        Space - O(1)
     */
    private void approach1(int[][] matrix) {
        List<Integer> spiralOrder = new ArrayList<>();
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int left = 0;
        int right = rowCount - 1;
        int top = 0;
        int bottom = colCount - 1;

        while(left <= right && top <= bottom) {
            // Traverse right
            for(int i = left; i <= right; i++) {
                spiralOrder.add(matrix[top][i]);
            }
            top++;
            // Traverse down
            for(int i = top; i <= bottom; i++) {
                spiralOrder.add(matrix[i][right]);
            }
            right--;
            // Traverse left
            if (top <= bottom) {
                for(int i = right; i >= left; i--) {
                    spiralOrder.add(matrix[bottom][i]);
                }
                bottom--;
            }
            // Traverse up
            if (left <= right) {
                for(int i = bottom; i >= top; i--) {
                    spiralOrder.add(matrix[i][left]);
                }
                left++;
            }
        }

        System.out.println("Original matrix: ");
        for(int row = 0; row < matrix.length; row++) {
            System.out.println(Arrays.toString(matrix[row]));
        }
        System.out.println("Spiral order: " + spiralOrder);
    }
}
