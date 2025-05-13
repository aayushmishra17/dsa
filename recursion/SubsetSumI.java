package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * Given a list of integers, print the sums of all subsets.
 * The ouput should be in increasing order of sums.
 */
public class SubsetSumI {
    public static void main(String[] args) {
        int[] arr = new int[] { 3, 1, 2 };
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Subset sums:");
        SubsetSumI obj = new SubsetSumI();
        obj.approach1(arr);
    }
    /*
     * Time - O(2^N) + O(2^N log 2^N)[sort]
     *        2^N = No. of subsets possible.
     * Space - O(2^N)
     */
    private void approach1(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        subsetSum(0, arr, 0, ans);
        ans.sort(Comparator.naturalOrder());
        System.out.println(ans);
    }
    private void subsetSum(int indx, int[] arr, int sum, List<Integer> ans) {
        if(indx == arr.length) {
            ans.add(sum);
            return;
        }
        subsetSum(indx + 1, arr, sum, ans); // don't take
        subsetSum(indx + 1, arr, sum + arr[indx], ans); // take
    }
}
