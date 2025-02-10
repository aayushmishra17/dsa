package sorting;

import java.util.Arrays;

/*
 * Shell sort is considered as a generalization/optimization of insertion sort algo.
 * Shell sort operates on the basis of the gap method which brings elements far 
 * apart from each other closer, hence reducing the number of swaps required overall.
 * This gap is reduced /2 every time untill it becomes 1, in which case we it becomes 
 * insertion sort, since insertion sort uses a gap = 1.
 * Shell sort optimizes insertion sort by reducing the total number of swaps required 
 * to be performed in order to sort the array.
 */
public class ShellSort {
    public static void main(String[] args) {
        ShellSort obj = new ShellSort();
        int[] arr = new int[] { 3, 22, 14, -1, 31, 10, 7, 25 };
        obj.shellSort(arr);
    }
    /*
     * Time - O(N * N)
     * Space - O(1)
     * Un-stable
     * In-place
     * 
     * The time complexity of Shellsort depends on the gap sequence used. 
     * There isn't a single formula to determine the complexity for every sequence, 
     * each must be analyzed individually.
     * With Shell's original sequence, the algorithm performs poorly, with a time 
     * complexity of O(N * N). 
     * Original sequence = N/2, N/4, ..., 1
     */
    private void shellSort(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = newArr.length;
        int gap = n / 2;
        while(gap > 0) { // Reduces the gap interval.
            for(int i = gap; i < n; i++) { // Increments the index of rightmost comparison element by 1.
                for(int j = i; j >= gap && newArr[j] < newArr[j - gap]; j -= gap) {
                    /*
                     * The loop compares all elements at gap length.
                     */
                    int temp = newArr[j];
                    newArr[j] = newArr[j - gap];
                    newArr[j - gap] = temp;
                }
            }
            gap /= 2;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
}
