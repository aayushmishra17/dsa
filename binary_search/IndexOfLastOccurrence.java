package binary_search;

import java.util.Arrays;

/*
    Given a sorted array of N integers, write a program to find the
    index of the last occurrence of the target key.
    If the target is not found then return -1.
 */
public class IndexOfLastOccurrence {
    public static void main(String[] args) {
        IndexOfLastOccurrence obj = new IndexOfLastOccurrence();
        int[] arr1 = new int[] { 3, 4, 13, 13, 13, 20, 40 };
        int x1 = 13;
        int[] arr2 = new int[] { 3, 4, 13, 13, 13, 13, 13 };
        int[] arr3 = new int[] { 3, 4, 13, 13, 13, 20, 13 };
        int x2 = 1;
        obj.findLastOccurrence(arr1, x1);
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private void findLastOccurrence(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int ans = n;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == x) {
                ans = mid;
                start = mid + 1;
            } else if(arr[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(ans == n) {
            System.out.println("x = " + x + " doesn't exist in the array.");
        } else {
            System.out.println("Last occurrence of x is at index " + ans);
        }
    }
}
