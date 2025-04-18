package sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort obj = new SelectionSort();
        int[] arr = new int[] { 7, 4, 5, 3, 2, 1, 6 };
        obj.selectionSort(arr);
    }
    /*
        Time - O(N * N)
        Space - O(1)
        Unstable - consider [3, 3, 1]. here the relative order of 3,3 will alter when we swap 3 and 1.
        In-place

        The term "selection sort" derives from the algorithm's fundamental operation of selecting
        the smallest (or largest) element from an unsorted portion of the array and moving it to
        its correct position in the sorted portion.

        We start from the first element and in the remaining part of the array we try to locate the 
        smallest element then we swap. Next, we start again from the next element and this is repeated.
     */
    private void selectionSort(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = newArr.length;
        for(int i = 0; i < n - 1; i++) {
            int smallest = i;
            for(int j = i + 1; j < n; j++) {
                if(newArr[j] < newArr[smallest]) {
                    smallest = j;
                }
            }
            // Swap
            int temp = newArr[smallest];
            newArr[smallest] = newArr[i];
            newArr[i] = temp;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
}
