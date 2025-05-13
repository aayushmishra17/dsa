package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given an array of integers that contains duplicates, return all possible subsets.
 */
public class SubsetSumII {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 2 };
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Possible subsets:");
        SubsetSumII obj = new SubsetSumII();
        obj.approach2(arr);
    }
    /*
     * Time - O(2^N) + O(2^N log 2^N)[Inserting in hash set]
     * Space - O(2^N)
     * 
     * Generate all possible subsets including duplicates and then put the 
     * generated subsets into a hasset to remove the duplicates.
     */
    private void approach1(int[] arr) {
        List<List<Integer>> allPossibleSubsets = new ArrayList<>();
        generateAllSubsets(0, arr, allPossibleSubsets, new ArrayList<>());
        Set<List<Integer>> hashSet = new HashSet<>(allPossibleSubsets);
        allPossibleSubsets = new ArrayList<>(hashSet);
        System.out.println(allPossibleSubsets);
    }
    /*
     * Time - O(2^N) * O(k); k = avg. size of subset.
     * Space - O(2^N)
     */
    private void generateAllSubsets(int indx, int[] arr, List<List<Integer>> ans, List<Integer> bucket) {
        if(indx == arr.length) {
            ans.add(new ArrayList<>(bucket));
            return;
        }
        // take
        bucket.add(arr[indx]);
        generateAllSubsets(indx + 1, arr, ans, bucket);
        bucket.remove(bucket.size() - 1);

        generateAllSubsets(indx + 1, arr, ans, bucket); // don't take
    }
    /*
     * Time - O(n logN)[sorting the given array] + O(2^N) * O(k); k = avg. length of subset.
     * Space - O(2^N)
     * 
     * First we'll sort the given array because only then we can modify the recursion and 
     * avoid the duplicates.
     */
    private void approach2(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        List<List<Integer>> ans = new ArrayList<>();
        generateUniqueSubsets(0, newArr, ans, new ArrayList<>());
        System.out.println(ans);
    }
    private void generateUniqueSubsets(int indx, int[] arr, List<List<Integer>> ans, List<Integer> bucket) {
        ans.add(new ArrayList<>(bucket));
        for(int i = indx; i < arr.length; i++) {
            if(i != indx && arr[i] == arr[i - 1]) {
                /*
                 * If we have already taken a specific number then we cannot take the same number
                 * in next recursion as it will lead to duplicate subset.
                 */
                continue;
            }
            bucket.add(arr[i]);
            generateUniqueSubsets(i + 1, arr, ans, bucket);
            bucket.remove(bucket.size() - 1);
        }
    }
}
