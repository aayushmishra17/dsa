package sorting;

import java.util.Arrays;

public class InsertionSortDemo {
    public static void main(String[] args) {
        InsertionSortDemo obj = new InsertionSortDemo();
        int[] arr = new int[] { 7, 3, 1, 2, 5, 6, 4 };
        obj.insertionSort(arr);
    }
    /*
        Time - O(N * N) or O(N)[Best case- sorted array]
        Space - O(1)
        Stable
        In-place

        We can think of this sorting process to be similar to how
        we sort a hand of playing cards. We place the key in the right
        position.
     */
    private void insertionSort(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = newArr.length;
        for(int i = 1; i < n; i++) {
            int j = i - 1;
            int key = newArr[i];
            while(j >= 0 && newArr[j] > key) { // This will not iterate if array is already sorted.
                newArr[j + 1] = newArr[j];
                j--;
            }
            newArr[j + 1] = key;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
}
