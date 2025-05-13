package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    We need to place N queens on a NxN chess board such that no two
    queens attack each other. Given integer N, return all distinct solutions.
    Every row should have 1 queen.
    Every column should have 1 queen.
 */
public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("N = " + n);
        System.out.println("Possible solutions:");
        NQueens obj = new NQueens();
        obj.approach1(n);
    }
    /*
        Time - O()
        Space - O()

        We consider a column and iterate over its row while trying to
        place a queen.
     */
    private void approach1(int n) {
        List<String> board = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            board.add(".".repeat(n));
        }
        List<List<String>> ans = new ArrayList<>();
        solveNQueens(0, n, board, ans);
        System.out.println(ans);
    }
    /*
        Time - O(N!)[no. of possible queen placements] * O(3N)[Queen placement] * O(N)[copying the board]
        Space - O(N*N)[Board storage] + O(no. of solutions * N*N)[ans storage] + O(N)[aux. stack space]
     */
    private void solveNQueens(int col, int n, List<String> board, List<List<String>> ans) {
        if(col == n) {
            ans.add(new ArrayList<>(board));
            return;
        }
        for(int row = 0; row < n; row++) {
            if(canPlaceQueen(row, col, board, n)) {
                String str = board.get(row).substring(0, col) + "Q" + board.get(row).substring(col + 1);
                board.set(row, str);

                solveNQueens(col + 1, n, board, ans);

                board.set(row, ".".repeat(n));
            }
        }
    }
    /*
        Time - O(N) + O(N) + O(N)
        Space - O(1)

        Since we start placing the queen from the top row and, we are moving
        from 1st to last column, we only need to check for other queens in 3 directions
        upper left diagonal, left and lower left diagonal.
     */
    private boolean canPlaceQueen(int row, int col, List<String> board, int n) {
        int rowDup = row;
        int colDup = col;

        // Upper diagonal
        while(row >= 0 && col >= 0) {
            if(board.get(row).charAt(col) == 'Q') {
                return false;
            }
            row--;
            col--;
        }
        row = rowDup;
        col = colDup;

        // Check left
        while(col >= 0) {
            if(board.get(row).charAt(col) == 'Q') {
                return false;
            }
            col--;
        }
        row = rowDup;
        col = colDup;

        // Check lower diagonal
        while(row < n && col >= 0) {
            if(board.get(row).charAt(col) == 'Q') {
                return false;
            }
            row++;
            col--;
        }

        return true;
    }
    /*
        Time - O(N!) * O(N)
        Space - O(N) + O(2N-1) + O(2N-1)

        We can optimize the queen placement check by using array hashing.
        Left row -
                   We maintain an array of size N and mark particular index if we place
                   any queen on left row.
        Upper diagonal -
                   There is a formula [(N-1) + (col - row)] should be marked when we place
                   a queen on the upper diagonal.Size of array would be 2N-1.
        Lower diagonal -
                   Formula [row + col] should be marked when we place a queen on lower diagonal.
                   Size of array would be 2N-1.
     */
    private void solveNQueens2(int col, int n, List<String> board, List<List<String>> ans,
                               List<Integer> leftRow, List<Integer> lowerDiagonal,
                               List<Integer> upperDiagonal) {
        if(col == n) {
            ans.add(new ArrayList<>(board));
            return;
        }

        for(int row = 0; row < n; row++) {
            board.set(row, board.get(row).substring(0, col) + "Q" + board.get(row).substring(col + 1));
            leftRow.set(row, 1);
            lowerDiagonal.set(row + col, 1);
            upperDiagonal.set(n - 1 + col - row, 1);

            solveNQueens2(col + 1, n, board, ans, leftRow, lowerDiagonal, upperDiagonal);

            board.set(row, ".".repeat(n));
            leftRow.set(row, 0);
            lowerDiagonal.set(row + col, 0);
            upperDiagonal.set(n - 1 + col - row, 0);
        }
    }
}
