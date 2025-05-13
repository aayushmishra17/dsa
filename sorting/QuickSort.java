package sorting;

import java.util.Arrays;

/*
 * The idea is to pick any element as pivot element and try to place it in 
 * its correct position in the sorted array.
 * If we repeat this for every element then the array would get sorted.
 * 
 * Step 1: We pick a pivot element.
 * Step 2: We place all elements less than pivot to the left of pivot and all elements 
 *         greater than pivot to the right of pivot.
 * Step 3: Once step 2 is completed we use the pivot position as the partition point and repeat the 
 *         process for the sub arrays.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 4, 6, 2, 5, 7, 9, 1, 3 };
        System.out.println("Arr = " + Arrays.toString(arr));
        QuickSort obj = new QuickSort();
        obj.quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted arr = " + Arrays.toString(arr));
    }
    /*
     * Time - O(N * log N)
     * Space - O(1)
     */
    private void quickSort(int[] arr, int start, int end) {
        if(start < end) {
            int partitionIndex = partition(arr, start, end);
            quickSort(arr, start, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private int partition(int[] arr, int start, int end) {
        int pivot = start;
        int left = start + 1;
        int right = end;
        while (left <= right) {
            while(left <= right && arr[left] <= arr[pivot]) {
                left++;
            }
            while(left <= right && arr[right] >= arr[pivot]) {
                right--;
            }
            if(left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        swap(arr, pivot, right);
        return pivot;
    }
    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
