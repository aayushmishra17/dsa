package binary_search;

import java.util.Arrays;

/*
    You are given a sorted array containing N integers and a number X,
    you have to find the occurrences of X in the given array.
 */
public class CountOccurrences {
    public static void main(String[] args) {
        CountOccurrences obj = new CountOccurrences();
        int[] arr1 = new int[] { 2, 2, 3, 3, 3, 3, 4 };
        int x1 = 3;
        obj.countOccurrences(arr1, x1);
    }
    /*
        Time - O(log N)[last occurrence] + O(log N)[first occurrence]
        Space - O(1)
     */
    private void countOccurrences(int[] arr, int x) {
        int lastIndx = findLastOccurrence(arr, x);
        int firstIndx = findFirstOccurrence(arr, x);
        System.out.println("Arr = " + Arrays.toString(arr));
        if(lastIndx == arr.length || firstIndx == -1) {
            System.out.println("x = " + x + " doesn't exist in the array.");
        } else {
            System.out.println("Count of x = " + x + " is = " + (lastIndx - firstIndx + 1));
        }
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private int findLastOccurrence(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int lastIndx = n;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == x) {
                lastIndx = mid;
                start = mid + 1;
            } else if(arr[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return lastIndx;
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private int findFirstOccurrence(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int firstIndx = -1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == x) {
                firstIndx = mid;
                end = mid - 1;
            } else if(arr[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return firstIndx;
    }
}
