package binary_search;

import java.util.Arrays;

/*
    You are given a strictly increasing array ‘vec’ and a positive integer 'k'.
    Find the 'kth' positive integer missing from 'vec'.
 */
public class KthMissingNumber {
    public static void main(String[] args) {
        KthMissingNumber obj = new KthMissingNumber();
        int[] vector1 = new int[] { 4, 7, 9, 10 };
        int k1 = 1;
        int[] vector2 = new int[] { 4, 7, 9, 10 };
        int k2 = 4;
        int[] vector3 = new int[] { 2, 3, 4, 7, 11 };
        int k3 = 5;
        //obj.approach1(vector3, k3);
        obj.approach2(vector3, k3);
    }
    /*
        Time - O(N)
        Space - O(1)

        Normal sequence :   1 2 3 4 5 6 7 8 9 10
        Given arr :         . 2 3 4 . . 7 . . . 11
        K = 5
     */
    private void approach1(int[] arr, int k) {
        for (int elem : arr) {
            if (elem <= k) {
                k++;
            } else {
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("K = " + k);
        System.out.println("Kth missing positive integer = " + k);
    }
    /*
        Time - O(log N)
        Space - O(1)

        In order to apply binary search, first we try to find out
        two indices between which the missing number would lie.

        How do we figure out the two indices? to figure this out we can
        use the given arr to see for any given number how many are missing.
        Normal sequence :   1 2 3 4 5 6 7 8 9 10
        Given arr :         . 2 3 4 . . 7 . . . 11
        Indices :           0 1 2 3 4 5 6 7 8 9 10
        Given arr :         2 3 4 7 11
        K = 5
        For index 3, count of missing nos = 7 - (3+1) = 3
        For index 4, count of missing nos = 11 - (4+1) = 6
        So, we can see the k = 5th missing number would lie b/t values 7 and 11.
        Using this observation we can create an array of count of missing nos
        for a particular given number in arr.
        Then we can apply binary search in this missing numbers array to get
        the two indices between which the missing number would lie.
        When the binary search ends we'll have low > high, so high would be
        pointing to the first index b/t the two indices so the missing number
        would lie after arr[high].
        The kth missing number = arr[high] + (k - count of missing nos at index high)
                               = arr[high] + K - (arr[high] - (high + 1))
                               = arr[high] + K - (arr[high] - high - 1)
                               = arr[high] + K - arr[high] + high + 1
                               = high + 1 + K
                               = low + K (since low = high + 1)
     */
    private void approach2(int[] arr, int k) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            int countOfMissingNos = arr[mid] - (mid + 1);
            if(countOfMissingNos < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("K = " + k);
        System.out.println("Kth missing positive integer = " + (low + k));
    }
}
