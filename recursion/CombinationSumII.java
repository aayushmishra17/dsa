package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given an array of distinct integers and a target value, return a list of al unique combinations 
 * where the numbers sum to the given target value. The same number cannot be used more than once.
 */
public class CombinationSumII {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 1, 1, 2, 2 };
        int target = 4;
        CombinationSumII obj = new CombinationSumII();
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Target = " + target);
        List<List<Integer>> ans = obj.approach2(arr, target);
        System.out.println("Ans = " + ans);
    }
    /*
     * Time - O(2^N) * O(k * logN)
     * Space - O(k * x) k = number of combos; x = avg. length of each combo.
     */
    private List<List<Integer>> approach1(int[] arr, int target) {
        Set<List<Integer>> hashSet = new HashSet<>();
        findCombinations(arr, 0, target, hashSet, new ArrayList<>());
        return new ArrayList<>(hashSet);
    }
    /*
     * Time - O(2^n) * O(k)
     * Space - O(k * x) k = avg. length of combo; x = no. of combos.
     */
    private void findCombinations(int[] arr, int indx, int target, Set<List<Integer>> ans, List<Integer> bucket) {
        if(indx == arr.length) {
            if(target == 0) {
                ans.add(new ArrayList<>(bucket));
            }
            return;
        }
        if(arr[indx] <= target) {
            bucket.add(arr[indx]);
            findCombinations(arr, indx + 1, target - arr[indx], ans, bucket);
            bucket.remove(bucket.size() - 1);
        }
        findCombinations(arr, indx + 1, target, ans, bucket);
    }
    /*
     * Time - O(2^N) * O(k) + O(N logN)[sorting given array]
     * Space - O(k * x) k = avg. length of combo; x = no. of combos.
     * 
     * We'll first need to sort the given array since we need sorted combinations.
     */
    private List<List<Integer>> approach2(int[] arr, int target) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations2(newArr, 0, target, ans, new ArrayList<>());
        return ans;
    }
    private void findCombinations2(int[] arr, int indx, int target, List<List<Integer>> ans, List<Integer> bucket) {
        if(target == 0) {
            ans.add(new ArrayList<>(bucket));
            return;
        }
        for(int i = indx; i < arr.length; i++) {
            if(i > indx && arr[i] == arr[i - 1]) {
                /*
                 * If we have already taken a specific number then we cannot take the same number
                 * in next recursion as it will lead to duplicate combination.
                 */
                continue;
            }
            if(target < arr[i]) {
                break;
            }
            bucket.add(arr[i]);
            findCombinations2(arr, i + 1, target - arr[i], ans, bucket);
            bucket.remove(bucket.size() - 1);
        }
    }
}
