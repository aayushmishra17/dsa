package binary_search;

import java.util.Arrays;

/*
    You are given a sorted array arr of distinct values and a target value x.
    You need to search for the index of the target value in the array.
    If the value is present in the array, then return its index. Otherwise,
    determine the index where it would be inserted in the array while maintaining the sorted order.
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        SearchInsertPosition obj = new SearchInsertPosition();
        int[] arr = new int[] { 1, 2, 4, 7 };
        int x = 6;
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
        int ans = n;
        boolean elementFound = false;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] == x) {
                ans = mid;
                elementFound = true;
                break;
            } else if(arr[mid] > x) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(ans == n || elementFound == false) {
            System.out.print("x = " + x + " is not present in the array. It's correct position would be ");
            System.out.println("at index = " + ans);
        } else {
            System.out.println("x = " + x + " is present at index = " + ans);
        }
    }
}
