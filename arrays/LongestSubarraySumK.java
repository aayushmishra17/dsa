package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    Given an array of positive numbers(including 0) and a value K,
    find the length of longest sub-array that sums to K.
 */
public class LongestSubarraySumK {
    public static void main(String[] args) {
        LongestSubarraySumK obj = new LongestSubarraySumK();
        int[] arr = new int[] { 2, 3, 5, 1, 9 };
        long k = 10;
        obj.approach1(arr, k);
        obj.approach2(arr, k);
        obj.approach3(arr, k);
        obj.approach4(arr, k);
    }
    /*
        Time - O(N*N*N)
        Space - O(1)
     */
    private void approach1(int[] arr, long k) {
        int n = arr.length;
        long maxLen = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                long sum = 0;
                for(int z = i; z <= j; z++) {
                    sum += arr[z];
                }
                if(sum == k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        System.out.println("Array = " + Arrays.toString(arr));
        System.out.println("Max len = " + maxLen);
    }
    /*
        Time - O(N*N)
        Space - O(1)
     */
    private void approach2(int[] arr, long k) {
        int n = arr.length;
        long maxLen = 0;
        for(int i = 0; i < n; i++) {
            long sum = 0;
            for(int j = 0; j < n; j++) {
                sum += arr[j];
                if(sum == k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        System.out.println("Array = " + Arrays.toString(arr));
        System.out.println("Max len = " + maxLen);
    }
    /*
        Time - O(N) however for worst case in case of hash collision - O(N*N) since we are using HashMap.
        Space - O(N) in case every element has unique prefixSum as key.

        This is the optimal approach when array contains both positive, 0s and negative numbers.
     */
    private void approach3(int[] arr, long k) {
        Map<Long, Integer> prefixSumMap = new HashMap<>(); // (currSum, index)
        long maxLen = 0;
        long prefixSum = 0;
        for(int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if(prefixSum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }
            if (prefixSumMap.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - prefixSumMap.get(prefixSum - k));
            }
            if (prefixSumMap.containsKey(prefixSum) == false) {
                /*
                    This if is to handle 0s in the array
                    E.g.: [2, 0, 0, 3] k = 3, should give maxLen = 3 and not 1.
                 */
                prefixSumMap.put(prefixSum, i);
            }
        }
        System.out.println("Array = " + Arrays.toString(arr));
        System.out.println("Max len = " + maxLen);
    }
    /*
        Time - O(N)
        Space - O(1)
        This is the optimal solution when array contains only positive and 0s. No -ve numbers.
     */
    private void approach4(int[] arr, long k) {
        int n = arr.length;
        int i = 0, j = 0;
        long sum = arr[0];
        long maxLen = 0;
        while(j < n) {
            while (i <= j && sum > k) {
                sum -= arr[i];
                i++;
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, j - i + 1);
            }
            j++;
            if (j < n) {
                sum += arr[j];
            }
        }
        System.out.println("Array = " + Arrays.toString(arr));
        System.out.println("Max len = " + maxLen);
    }
}
