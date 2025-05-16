package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * We need to solve a 9x9 Sudoku puzzle by filling the empty cells.
 */
public class SudokuSolver {
    public static void main(String[] args) {
        List<List<String>> board = Arrays.asList(
            Arrays.asList("5", "3", ".",    ".", "7", ".",      ".", ".", "."),
            Arrays.asList("6", ".", ".",    "1", "9", "5",      ".", ".", "."),
            Arrays.asList(".", "9", "8",    ".", ".", ".",      ".", "6", "."),
            
            Arrays.asList("8", ".", ".",    ".", "6", ".",      ".", ".", "3"),
            Arrays.asList("4", ".", ".",    "8", ".", "3",      ".", ".", "1"),
            Arrays.asList("7", ".", ".",    ".", "2", ".",      ".", ".", "6"),
            
            Arrays.asList(".", "6", ".",    ".", ".", ".",      "2", "8", "."),
            Arrays.asList(".", ".", ".",    "4", "1", "9",      ".", ".", "5"),
            Arrays.asList(".", ".", ".",    ".", "8", ".",      ".", "7", "9")
        );
        System.out.println("Sudoku board:");
        System.out.println(board);
        SudokuSolver obj = new SudokuSolver();
        obj.approach1(board);
        System.out.println("Solved board:");
        System.out.println(board);
    }
    /*
     * Time - O( 9 ^ N ) * O(9)
     * Space - O(1)
     * 
     * How to solve a Sudoku?
     *      Find any empty cell and place a valid number in it then move to the next empty cell.
     *      In the new empty cell if we are not able to place any number then we need to backtrack to our
     *      previous step and place a different number there.
     */
    private boolean approach1(List<List<String>> board) {
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(board.get(row).get(col) == ".") {
                    for(int newNumber = 1; newNumber <= 9; newNumber++) {
                        if(canPlace(String.valueOf(newNumber), row, col, board)) {
                            board.get(row).set(col, String.valueOf(newNumber));
                            if(approach1(board)) {
                                return true;
                            }
                            board.get(row).set(col, ".");
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    /*
     * Time - O(9)
     * Space - O(1)
     */
    private boolean canPlace(String newNumber, int row, int col, List<List<String>> board) {
        for(int i = 0; i < 9; i++) {
            if(board.get(i).get(col).equals(newNumber)) {
                return false;
            }
            if(board.get(row).get(i).equals(newNumber)) {
                return false;
            }
            if(board.get( 3 * (row / 3) + (i / 3) ).get(3 * (col / 3) + (i % 3)) == newNumber) {
                return false;
            }
        }
        return true;
    }
}
