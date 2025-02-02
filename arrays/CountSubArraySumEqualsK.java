package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    Given an array of integers and an integer k, return the total number of sub-arrays whose sum equals k.
    A sub-array is a contiguous non-empty sequence of elements within an array.
 */
public class CountSubArraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = new int[] { 3, 1, 2, 4 };
        //int[] nums = new int[] { 3, -3, 1, 1, 1 };
        int k = 6;
        CountSubArraySumEqualsK obj = new CountSubArraySumEqualsK();
        obj.approach1(nums, k);
        obj.approach2(nums, k);
        obj.approach3(nums, k);
    }
    /*
        Time - O(N * N * N)
        Space - O(1)
     */
    private void approach1(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int sum = 0;
                for(int z = i; z <= j; z++) {
                    sum += nums[z];
                }
                if(sum == k) {
                    count++;
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("K = " + k);
        System.out.println("Count = " + count);
    }
    /*
        Time - O(N * N)
        Space -O(1)
     */
    private void approach2(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i ; j < n; j++) {
                sum += nums[j];
                if(sum == k) {
                    count++;
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("K = " + k);
        System.out.println("Count = " + count);
    }
    /*
        Time - O(N * 1)[Could be N*N is case of hash collision]
        Space - O(N)

        Will need to find how many sub-arrays are there with sum (x-k),
        this will be equal to the number of sub-arrays with sum k.
        |indx-0----------x-k-----------|----k-----indx-N| Sum till indx-N = x.
     */
    private void approach3(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // (prefixSum, count of how many times this prefixSum encountered)
        map.put(0, 1); // Initially, we have prefixSum = 0
        int prefixSum = 0;
        int count = 0;
        for(int num : nums) {
            prefixSum += num;
            count += map.getOrDefault(prefixSum - k, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("K = " + k);
        System.out.println("Count = " + count);
    }
}
