package arrays;

import java.text.MessageFormat;
import java.util.Arrays;

/*
 * Given an integer array arr, find the contiguous subarray (containing at least one number) which 
 * has the largest sum and returns its sum and prints the subarray.
 */
public class KadaneAlgo {
    public static void main(String[] args) {
       KadaneAlgo obj = new KadaneAlgo();
       int[] arr = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
       //int[] arr = new int[] { -1, -4, -5 };
       obj.approach1(arr);
       obj.approach2(arr);
       obj.approach3(arr);
    }
    /*
     * Time - O(N * N * N)
     * Space - O(1)
     */
    private void approach1(int[] arr) {
        int n = arr.length;
        int maxSum = 0;
        int firstIndx = -1, secondIndx = -1;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int currSum = 0;
                for(int k = i; k <= j; k++) {
                    currSum += arr[k];
                    if (currSum > maxSum) {
                        firstIndx = i;
                        secondIndx = j;
                    }
                    maxSum = Math.max(currSum, maxSum);
                }
            }
        }
        System.out.println("Array = " + Arrays.toString(arr));
        if(maxSum == 0) {
            System.out.println("Max sum sub-array = 0.");
        } else {
            System.out.println(MessageFormat.format("Max sum sub-array = {0} Sum = {1}", Arrays.toString(Arrays.copyOfRange(arr, firstIndx, secondIndx + 1)), maxSum));
        }
    }
    /*
     * Time - O(N * N)
     * Space - O(1)
     */
    private void approach2(int[] arr) {
        int n = arr.length;
        int maxSum = 0;
        int firstIndx = -1, secondIndx = -1;
        for(int i = 0; i < n; i++) {
            int currSum = 0;
            for(int j = i; j < n; j++) {
                currSum += arr[j];
                if (currSum > maxSum) {
                    firstIndx = i;
                    secondIndx = j;
                }
                maxSum = Math.max(currSum, maxSum);
            }
        }
        System.out.println("Array = " + Arrays.toString(arr));
        if(maxSum == 0) {
            System.out.println("Max sum sub-array = 0.");
        } else {
            System.out.println(MessageFormat.format("Max sum sub-array = {0} Sum = {1}", Arrays.toString(Arrays.copyOfRange(arr, firstIndx, secondIndx + 1)), maxSum));
        }
    }
    /*
     * Time - O(N)
     * Space - O(1)
     * 
     * Kadane's algo:
     *      The intuition of the algorithm is not to consider the subarray as a part of the answer if its sum is less than 0. 
     *      A subarray with a sum less than 0 will always reduce our answer and so this type of subarray cannot be a part of the subarray with maximum sum.
     */
    private void approach3(int[] arr) {
        int n = arr.length;
        int sum = 0, itr = 0;
        int firstIndx = -1, secondIndx = -1;
        int maxSum = 0;
        while(itr < n) {
            sum += arr[itr];
            if(sum > maxSum) {
                maxSum = sum;
                secondIndx = itr;
            }
            if(sum < 0) {
                sum = 0;
                firstIndx = itr + 1;
            }
            itr++;
        }
        System.out.println("Array = " + Arrays.toString(arr));
        if(maxSum == 0) {
            System.out.println("Max sum sub-array = 0.");
        } else {
            System.out.println(MessageFormat.format("Max sum sub-array = {0} Sum = {1}", Arrays.toString(Arrays.copyOfRange(arr, firstIndx, secondIndx + 1)), maxSum));
        }
    }
}
