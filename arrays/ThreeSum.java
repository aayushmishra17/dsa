package arrays;

import java.util.*;

/*
    Given an array of N integers, your task is to find unique triplets that add up
    to give a sum of zero. In short, you need to return an array of all the unique
    triplets [arr[a], arr[b], arr[c]] such that i!=j, j!=k, k!=i, and their sum is equal to zero.
 */
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        //int[] nums = new int[] { -1, 0, 1, 2, -1, -4 };
        //int[] nums = new int[] { -1, 0, 1, 0 };
        //int[] nums = new int[] { -1, 0, 1, 2, 2, -1, -4 };
        int[] nums = new int[] { -2, -2, -2, -1, -1, -1, 0, 0, 0, 2, 2, 2, 2 };
        obj.approach1(nums);
        obj.approach2(nums);
        obj.approach3(nums);
    }
    /*
        Time - O(N * N * N)
        Space - 2 * O(no. of unique triplets)
     */
    private void approach1(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        list.sort(Comparator.naturalOrder());
                        set.add(list);
                    }
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("Unique triplets = " + set);
    }
    /*
        Time - O(N * N * log M)
        Space - O(N)[map] + O(no. of unique triplets) * 2[List of List]
     */
    private void approach2(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // (elem, indx)
        for(int indx = 0; indx < n; indx++) {
            map.put(nums[indx], indx);
        }
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (map.containsKey(-(nums[i] + nums[j])) &&
                        map.get(-(nums[i] + nums[j])) != i && // need to have this check since we can't take the same element twice.
                        map.get(-(nums[i] + nums[j])) != j) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], nums[map.get(-(nums[i] + nums[j]))]);
                    list.sort(Comparator.naturalOrder());
                    set.add(list);
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("Unique triplets = " + set);
    }
    /*
        Time - O(N logN)[sort] + O(N * N)
        Space - O(1) or O(no. of unique triplets)[if we consider the space taken to store the answers]

        To reduce the space complexity, we will try to remove the usage of the Set
        utilized previously to enforce unique triplets. To do this we will first
        sort the given array.
     */
    private void approach3(int[] nums) {
        int n = nums.length;
        int[] newNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newNums);
        int i = 0; // will indicate first element in triplet.
        int j; // will indicate middle element in triplet.
        int k; // will indicate last element in triplet.
        List<List<Integer>> ans = new ArrayList<>();
        while(i < n - 2) {
            j = i + 1;
            k = n - 1;
            while(j < k) {
                if(newNums[i] + newNums[j] + newNums[k] == 0) {
                    List<Integer> list = Arrays.asList(newNums[i], newNums[j], newNums[k]);
                    ans.add(list);
                    do {
                        j++;
                    } while (j < k && newNums[j] == newNums[j - 1]);
                    do {
                        k--;
                    } while (k > j && newNums[k] == newNums[k + 1]);
                } else if(newNums[i] + newNums[j] + newNums[k] < 0) {
                    j++;
                } else {
                    k--;
                }
            }
            do {
                i++;
            } while (i < n && newNums[i] == newNums[i - 1]);
        }
        System.out.println("Arr = " + Arrays.toString(nums));
        System.out.println("Unique triplets = " + ans);
    }
}
