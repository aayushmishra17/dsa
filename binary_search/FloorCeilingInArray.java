package binary_search;

import java.util.Arrays;

/*
    You're given a sorted array arr of n integers and an integer x.
    Find the floor and ceiling of x in arr[0...n-1].
    The floor of x is the largest element in the array which is smaller than or equal to x.
    The ceiling of x is the smallest element in the array greater than or equal to x.
 */
public class FloorCeilingInArray {
    public static void main(String[] args) {
        FloorCeilingInArray obj = new FloorCeilingInArray();
        int[] arr1 = new int[] { 3, 4, 4, 7, 8, 10 };
        int x1 = 5;
        int[] arr2 = new int[] { 3, 4, 4, 7, 8, 10 };
        int x2 = 8;
        obj.findFloor(arr1, x1);
        obj.findCeiling(arr1, x1);
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private void findFloor(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] <= x) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(ans == -1) {
            System.out.println("Floor of " + x + " doesn't exist!");
        } else {
            System.out.println("Floor of " + x + " = " + arr[ans]);
        }
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private void findCeiling(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int ans = n;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] >= x) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(ans == n) {
            System.out.println("Ceiling of " + x + " doesn't exist!");
        } else {
            System.out.println("Ceiling of " + x + " = " + arr[ans]);
        }
    }
}
