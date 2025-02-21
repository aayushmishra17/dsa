package binary_search;

import java.util.Arrays;

/*
    Given a sorted array of N integers and an integer x,
    write a program to find the lower bound of x.
    Lower bound(x) is the smallest index where arr[index] >= x.
 */
public class LowerBound {
    public static void main(String[] args) {
        LowerBound obj = new LowerBound();
        int[] arr = new int[] { 3, 5, 8, 15, 19 };
        //int[] arr = new int[] { 1, 2, 2, 3 };
        int x = 9;
        obj.lowerBound(arr, x);
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private void lowerBound(int[] arr, int x) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] >= x) {
                /*
                    There may be duplicate elements,
                    so we need search further on the left
                    since lower bound = smallest index where
                    arr[index] >= x.
                 */
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if(start > n - 1) {
            System.out.println("Lower bound doesn't exists!");
            return;
        }
        System.out.println("Arr = " + Arrays.toString(arr) + " x = " + x);
        System.out.println("Lower bound = " + arr[start] + " at index = " + start);
    }
}
