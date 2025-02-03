package arrays;

import javax.swing.*;
import java.util.*;

/*
    Given an array of N integers, your task is to find unique quads that add
    up to give a target value. In short, you need to return an array of all
    the unique quadruplets [arr[a], arr[b], arr[c], arr[d]] such that their
    sum is equal to a given target.
 */
public class FourSum {
    public static void main(String[] args) {
        FourSum obj = new FourSum();
        int[] nums = new int[] { 4, 3, 3, 4, 4, 2, 1, 2, 1, 1 };
        int target = 9;
        obj.approach1(nums, target);
        obj.approach2(nums, target);
        obj.approach3(nums, target);
    }
    /*
        Time - O(N * N * N * N)
        Space - O(No. of unique quadruplets) * 2[since we create no. of unique quadruplets no. of lists and
        the same number of entries in the set]
     */
    private void approach1(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    for(int l = k + 1; l < n; l++) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if(sum == target) {
                            List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            list.sort(Comparator.naturalOrder());
                            set.add(list);
                        }
                    }
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("Unique quadruplets = " + set);
    }
    /*
        Time - O(N) + O(N * N * N)
        Space - O(N) + O(No. of unique quadruplets) * 2
     */
    private void approach2(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // (elem, indx)
        for(int indx = 0; indx < n; indx++) {
            map.put(nums[indx], indx);
        }
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    int requiredElem = target - (nums[i] + nums[j] + nums[k]);
                    if(map.containsKey(requiredElem) &&
                            map.get(requiredElem) != i && // need to have this check since we can't take the same element twice.
                            map.get(requiredElem) != j &&
                            map.get(requiredElem) != k) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k], nums[map.get(requiredElem)]);
                        list.sort(Comparator.naturalOrder());
                        set.add(list);
                    }
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("Unique quadruplets = " + set);
    }
    /*
        Time - O(N logN)[sort] + O(N * N * N)
        Space - O(1) or O(No. of unique quadruplets)[if we consider the space taken to store the answers]

        To reduce the space complexity, we will try to remove the usage of the Set
        utilized previously to enforce unique quadruplets. To do this we will first
        sort the given array.
     */
    private void approach3(int[] nums, int target) {
        int n = nums.length;
        int[] newNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newNums);
        int i = 0; // will indicate first element in quadruplet.
        int j; // will indicate second element in quadruplet.
        int k; // will indicate third element in quadruplet.
        int l; // will indicate last element in quadruplet.
        List<List<Integer>> ans = new ArrayList<>();
        while(i < n - 3) {
            j = i + 1;
            while(j < n - 2) {
                k = j + 1;
                l = n - 1;
                while(k < l) {
                    int sum = newNums[i] + newNums[j] + newNums[k] + newNums[l];
                    if(sum == target) {
                        ans.add(Arrays.asList(newNums[i], newNums[j], newNums[k], newNums[l]));
                        do {
                            k++;
                        } while (k < l && newNums[k] == newNums[k - 1]);
                        do {
                            l--;
                        } while (l > k && newNums[l] == newNums[l + 1]);
                    } else if(sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
                do {
                    j++;
                } while (j < n - 2 && newNums[j] == newNums[j - 1]);
            }
            do {
                i++;
            } while (i < n - 3 && newNums[i] == newNums[i - 1]);
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("Unique quadruplets = " + ans);
    }
}
