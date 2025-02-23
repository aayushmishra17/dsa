package binary_search;

import java.util.Arrays;

/*
    Given an integer array arr of size N, sorted in ascending order (with distinct values)
    and a target value k. Now the array is rotated at some pivot point unknown to you.
    Find the index at which k is present and if k is not present return -1.
 */
public class RotatedSortedArray1 {
    public static void main(String[] args) {
        RotatedSortedArray1 obj = new RotatedSortedArray1();
        int[] arr1 = new int[] { 4, 5, 6, 7, 0, 1, 2, 3 };
        int x1 = 0;
        //int[] arr2 = new int[] { 4, 5, 6, 7, 0, 1, 2 };
        //int x2 = 3;
        obj.findX(arr1, x1);
    }
    /*
        Time - O(log N)
        Space - O(1)

        Here we need to identify the sorted part of the array before
        we can apply binary search in that sorted part.
        Since there is only one pivot point, so the array would
        always be divided into two sorted parts.
     */
    private void findX(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        boolean found = false;
        int index = -1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == x) {
                found = true;
                index = mid;
                break;
            } else if(arr[start] <= arr[mid]) {
                /*
                    left part is sorted
                 */
                if(arr[start] <= x && x <= arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                /*
                    right part is sorted
                 */
                if(arr[mid] <= x && x <= arr[end]) {
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
            System.out.println("x = " + x + " doesn't exists in the array.");
        }
    }
}
