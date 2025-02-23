package binary_search;

import java.util.Arrays;

/*
    Given an integer array arr of size N, sorted in ascending order
    (may contain duplicate values) and a target value k. Now the
    array is rotated at some pivot point unknown to you.
    Return True if k is present and otherwise, return False.
 */
public class RotatedSortedArray2 {
    public static void main(String[] args) {
        RotatedSortedArray2 obj = new RotatedSortedArray2();
        int[] arr1 = new int[] { 7, 8, 1, 2, 3, 3, 3, 4, 5, 6 };
        int x1 = 3;
        int[] arr2 = new int[] { 7, 8, 1, 2, 3, 3, 3, 4, 5, 6 };
        int x2 = 10;
        int[] arr3 = new int[] { 3, 1, 3, 3, 3 };
        int x3 = 3;
        obj.findX(arr3, x3);
    }
    /*
        Time - O(log N)[Ang. case]
               O(N/2)[ approx in worst case, where we keep shrinking the search space when
                       arr[start] = arr[mid] == arr[end] ]
        Space - O(1)

        Here since the array can contain duplicate elements therefore
        the previous technique of identifying the sorted part of the
        array won't work.
        Consider arr = [ 3, 1, 3, 3, 3 ], here arr[start] <= arr[mid] but
        the left part is not in sorted order.
        So, whenever we have arr[start] == arr[mid] == arr[end] we can shrink the
        search space.
     */
    private void findX(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        boolean found = false;
        int index = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == x) {
                found = true;
                index = mid;
                break;
            }
            if(arr[start] == arr[mid] && arr[mid] == arr[end]) {
                start++;
                end--;
                continue;
            }
            if(arr[start] <= arr[mid]) {
                /*
                    Left part is sorted.
                 */
                if(arr[start] <= x && x <= arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                /*
                    Right part is sorted.
                 */
                if(arr[mid] <= x && arr[mid] <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(found) {
            System.out.println("x = " + x + " exists in the array at index = " + index);
        } else {
            System.out.println("x = " + x + " doesn't exist in the array.");
        }
    }
}
