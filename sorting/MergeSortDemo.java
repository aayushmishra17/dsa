package sorting;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        int[] arr = {13,14,9,3,1,10};
        System.out.println("Array before merge sort: " + Arrays.toString(arr));

        MergeSortDemo obj = new MergeSortDemo();
        obj.mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted arr = " + Arrays.toString(arr));
    }
    /*
    Time = O(N log N)
     */
    private void mergeSort(int[] arr, int start, int end) {
        if(start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }
    /*
    Time = O(m + n) ~ O(N)
    Space = O(m + n) ~ O(N)
     */
    private void merge(int[] arr, int start, int mid, int end) {

        int n1 = mid - start + 1;
        int n2 = end - mid;

        // Create two arrays and copy elements.
        int[] left = new int[n1];
        int[] right = new int[n2];
        int i = 0, j = 0, k = start;
        while(i < n1) {
            left[i] = arr[start + i];
            i++;
        }
        while(j < n2) {
            right[j] = arr[mid + 1 + j];
            j++;
        }

        // Perform merge
        i = 0;
        j = 0;
        while (i < n1 && j < n2) {
            if(left[i] >= right[j]) {
                arr[k++] = right[j++];
            }
            else {
                arr[k++] = left[i++];
            }
        }
        // Copy remaining elements
        while(i < n1) {
            arr[k++] = left[i++];
        }
        while (j < n2) {
            arr[k++] = right[j++];
        }

    }
}
