package binary_search;

import java.util.Arrays;

/*
 * You are given an array of integers 'arr' and an integer i.e. a threshold value 'limit'. 
 * Your task is to find the smallest positive integer divisor, such that upon dividing all 
 * the elements of the given array by it, the sum of the division's result is less than or 
 * equal to the given threshold value.
 */
public class FindSmallestDivisor {
    public static void main(String[] args) {
        FindSmallestDivisor obj = new FindSmallestDivisor();
        int[] arr = new int[] { 1, 2, 3, 4, 5 };
        int threshold = 8;
        obj.approach1(arr, threshold);
    }
    /*
     * Time - O(max(arr)) * O(N)
     * Space - O(1)
     * 
     * The smallest possible divisor is always 1 and the highest possible divisor 
     * would be equal to max(arr) since any greater value would also give the same result.
     * So our search space would be [1, max(arr)].
     */
    private void approach1(int[] arr, int threshold) {
        int n = arr.length;
        int ans = -1;
        for(int divisor = 1; divisor < findMax(arr); divisor++) {
            if(isFeasible(arr, divisor, threshold)) {
                ans = divisor;
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(ans == -1) {
            System.out.println("Such such divisor exists!");
        } else {
            System.out.println("Smallest divisor = " + ans);
        }
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private int findMax(int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++) {
            maxVal = Math.max(maxVal, arr[i]);
        }
        return maxVal;
    }
    /*
     * Time - O(N)
     * Space - O(1)
     */
    private boolean isFeasible(int[] arr, int divisor, int threshold) {
        int n = arr.length;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += Math.ceil((double)arr[i] / (double)divisor);
        }
        if(sum <= threshold) {
            return true;
        }
        return false;
    }
    /*
     * Time - O(log max(arr)) * O(N)
     * Space - O(1)
     */
    private void approach2(int[] arr, int threshold) {
        int ans = -1;
        int low = 1;
        int high = findMax(arr);
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(isFeasible(arr, mid, threshold)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(ans == -1) {
            System.out.println("Such such divisor exists!");
        } else {
            System.out.println("Smallest divisor = " + ans);
        }
    }
}
