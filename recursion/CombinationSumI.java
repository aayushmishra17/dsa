package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an arrya of distinct integers and a target value, return a list of al unique combinations 
 * where the numbers sum to the given target value. The same number can be chosen any number of times.
 */
public class CombinationSumI {
    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 6, 7 };
        int target = 7;
        CombinationSumI obj = new CombinationSumI();
        System.out.println("Arr = " + Arrays.toString(arr));
        List<List<Integer>> ans = obj.approach1(arr, target);
        System.out.println("Ans = " + ans);
    }
    /*
     * Time - O(2 ^ target) * O(k)
     *        k = no. of possible unique combination.
     *        2^target instead of 2^n because to sum to the target value we can take 
     *        same element multiple times and this recusion depth can be larger than the 
     *        total no. of array elements.
     * Space - O(k * x) k = no. of possible unique combination; x = avg. length of each combo.
     */
    private List<List<Integer>> approach1(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(arr, 0, target, ans, new ArrayList<>());
        return ans;
    }
    private void findCombinations(int[] arr, int indx, int target, List<List<Integer>> ans, List<Integer> bucket) {
        if(indx == arr.length) {
            if(target == 0) {
                ans.add(new ArrayList<>(bucket));
            }
            return;
        }
        if(arr[indx] <= target) {
            bucket.add(arr[indx]);
            findCombinations(arr, indx, target - arr[indx], ans, bucket);
            bucket.remove(bucket.size() - 1);
        }
        findCombinations(arr, indx + 1, target, ans, bucket);
    }
}
