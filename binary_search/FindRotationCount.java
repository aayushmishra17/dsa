package binary_search;

import java.util.Arrays;

/*
    Given an integer array arr of size N, sorted in ascending order
    (with distinct values). Now the array is rotated between 1 to N
    times which is unknown. Find how many times the array has been rotated.
 */
public class FindRotationCount {
    public static void main(String[] args) {
        FindRotationCount obj = new FindRotationCount();
        int[] arr1 = new int[] { 4, 5, 6, 7, 0, 1, 2, 3 };
        int[] arr2 = new int[] { 1, 2, 3, 4, 5, 6, 7, 0 };
        int[] arr3 = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        obj.findRotationCount(arr1);
    }
    /*
        Time - O(log N)
        Space - O(1)

        Index of the minimum element would give the rotation count.
     */
    private void findRotationCount(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int indexOfMin = -1;
        int minimum = Integer.MAX_VALUE;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[start] <= arr[end]) {
                // Entire search space is sorted.
                if(arr[start] <= minimum) {
                    minimum = arr[start];
                    indexOfMin = start;
                }
                break;
            }
            if(arr[start] <= arr[mid]) {
                // Left half is sorted.
                if(arr[start] <= minimum) {
                    minimum = arr[start];
                    indexOfMin = start;
                }
                start = mid  +1;
            } else {
                // Right half is sorted.
                if(arr[mid] <= minimum) {
                    minimum = arr[mid];
                    indexOfMin = mid;
                }
                end = mid - 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Rotation count = " + indexOfMin);
    }
}
