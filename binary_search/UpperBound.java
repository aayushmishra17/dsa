package binary_search;

import java.util.Arrays;

/*
    Given a sorted array of N integers and an integer x,
    write a program to find the upper bound of x.
    Upper bound = smallest index such that arr[index] > x.
 */
public class UpperBound {
    public static void main(String[] args) {
        UpperBound obj = new UpperBound();
        int[] arr = new int[] { 1, 2, 2, 3 };
        //int[] arr = new int[] { 3, 5, 8, 9, 15, 19 };
        int x = 2;
        obj.upperBound(arr, x);
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private void upperBound(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int ans = n;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] <= x) {
                start = mid + 1;
            } else {
                ans = mid;
                /*
                    Need to look to smaller index of left as there may be
                    duplicate elements.
                 */
                end = mid - 1;
            }
        }
        if(ans == n) {
            System.out.println("Upper bound not found!");
        } else {
            System.out.println("Arr = " + Arrays.toString(arr) + " x = " + x);
            System.out.println("Upper bound = " + arr[ans] + " at index = " + ans);
        }
    }
}
