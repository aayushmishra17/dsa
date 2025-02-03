package arrays;

import java.text.MessageFormat;
import java.util.*;

/*
    Given an array of N integers. Find the elements that appear more than N/3 times
    in the array. If no such element exists, return an empty vector.
 */
public class MajorityElementMoreThanNBy3 {
    public static void main(String[] args) {
        MajorityElementMoreThanNBy3 obj = new MajorityElementMoreThanNBy3();
        int[] arr = new int[] { 11, 33, 33, 11, 33, 11 };
        //int[] arr = new int[] { 1, 2, 2, 3, 2 };
        obj.approach1(arr);
        obj.approach2(arr);
        obj.approach3(arr);
    }
    /*
        Time - O(N * N)
        Space - O(1)

        Note: Since majority element is defined as element > n/3, so there can
        only be at most 2 such elements in any array.
     */
    private void approach1(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int count = 0;
            if(ans.contains(nums[i]) == false) {
                for(int j = 0; j < n; j++) {
                    if(nums[j] == nums[i]) {
                        count++;
                    }
                    if(count > n / 3) {
                        ans.add(nums[i]);
                        break;
                    }
                }
            }
            if(ans.size() == 2) {
                break;
            }
        }
        System.out.println("Nums = " + Arrays.toString(nums));
        System.out.println("Majority elements = " + ans);
    }
    /*
        Time - O(N)
        Space - O(N)
     */
    private void approach2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for(int elem : nums) {
            int count = map.getOrDefault(elem, 0);
            map.put(elem, count + 1);
            if(count + 1 > n / 3 && ans.contains(elem) == false) {
                ans.add(elem);
            }
            if(ans.size() == 2) {
                break;
            }
        }
        System.out.println("Nums = " + Arrays.toString(nums));
        System.out.println("Majority elements = " + ans);
    }
    /*
        Time - O(N) + O(N)
        Space - O(1)

        We'll use the Moore's voting algo and modify it to work for finding two
        majority elements instead of just 1. Since, only one > N/2 element can exist
        while two > N/3 elements can exist.
     */
    private void approach3(int[] nums) {
        int n =  nums.length;
        int voteCount1 = 0, voteCount2 = 0;
        int candidate1 = -1, candidate2 = -1;
        for(int elem : nums) {
            if(voteCount1 == 0 && candidate2 != elem) {
                candidate1 = elem;
                voteCount1++;
            } else if(voteCount2 == 0 && candidate1 != elem) {
                candidate2 = elem;
                voteCount2++;
            } else if(elem == candidate1) {
                voteCount1++;
            } else if(elem == candidate2) {
                voteCount2++;
            } else {
                voteCount1--;
            }
        }
        int count1 = 0, count2 = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] == candidate1) {
                count1++;
            } else if(nums[i] == candidate2) {
                count2++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if(count1 > n / 3) {
            ans.add(candidate1);
        }
        if(count2 > n / 3) {
            ans.add(candidate2);
        }
        System.out.println("Nums = " + Arrays.toString(nums));
        System.out.println("Majority elements = " + ans);
    }
}
