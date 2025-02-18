package binary_search;

import java.util.Arrays;

public class BinarySearchDemo {
    public static void main(String[] args) {
        BinarySearchDemo obj = new BinarySearchDemo();
        int[] arr = new int[] { 0, 1, 3, 4, 5, 6, 2, 7, 8, 9 };
        int x = 9;
        obj.binarySearch(arr, x);

        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        obj.binarySearch_recursive(newArr, 2, 0, newArr.length - 1);
    }
    /*
        Time - O(n log N)[sort] + O(log N)
        Space - O(1)
     */
    private void binarySearch(int[] arr, int x) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = newArr.length;
        Arrays.sort(newArr);
        int start = 0;
        int end = n - 1;
        boolean found = false;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(newArr[mid] == x) {
                found = true;
                break;
            } else if(newArr[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if (found) {
            System.out.println(x + " exists in arr.");
        } else {
            System.out.println(x + " doesn't exist in arr.");
        }
    }
    /*
        Time - O(log N)
        Space - O(log N)[auxiliary stack space]

        Assumes sorted array.
     */
    private void binarySearch_recursive(int[] arr, int x, int start, int end) {
        if(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == x) {
                System.out.println("Element found!");
                return;
            } else if(arr[mid] < x) {
                start = mid + 1;
                binarySearch_recursive(arr, x, start, end);
            } else {
                end = mid - 1;
                binarySearch_recursive(arr, x, start, end);
            }
        } else {
            System.out.println("Element not found!");
        }
    }
}
