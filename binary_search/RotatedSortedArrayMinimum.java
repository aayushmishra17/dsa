package binary_search;

import java.util.Arrays;

public class RotatedSortedArrayMinimum {
    public static void main(String[] args) {
        RotatedSortedArrayMinimum obj = new RotatedSortedArrayMinimum();
        int[] arr = new int[] { 4, 5, 6, 7, 0, 1, 2, 3 };
        obj.findMin(arr);
    }
    /*
        Time - O(log N)
        Space - O(1)

        We locate the sorted half and in a sorted half the minimum value will
        be the first value, so after considering the first value we can
        eliminate that half of the array.
     */
    private void findMin(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int minimum = Integer.MAX_VALUE;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            /*
                The below if case is an optimization where we
                use the observation that if my ENTIRE search space is sorted
                then I don't need to perform binary search any further, and
                we can just consider the first element as the minimum element.
             */
            if(arr[start] <= arr[end]) {
                minimum = Math.min(minimum, arr[start]);
                break;
            }
            if(arr[start] <= arr[mid]) {
                // Left half is sorted.
                minimum = Math.min(minimum, arr[start]);
                start = mid + 1;
            } else {
                // Right half is sorted.
                minimum = Math.min(minimum, arr[mid]);
                end = mid - 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Minimum = " + minimum);
    }
}
