package recursion;

import java.util.ArrayList;
import java.util.List;

/*
    Given an array of integers we need to print all possible
    subsequence of the array elements.
    E.g.: arr = [3, 1, 2]
    Possible subsequences: {}, {3}, {1}, {2}, {3, 1}, {3, 2}, {1, 2}, {3, 1, 2}
 */
public class PrintAllSubsequences {
    public static void main(String[] args) {
        int[] arr = new int[] { 3, 1, 2 };
        PrintAllSubsequences obj = new PrintAllSubsequences();
        System.out.println("Arr = " + arr);
        System.out.println("Possible subsequences : ");
        obj.approach1(arr);
    }
    /*
        Time - O(2 ^ N)
        Space - O(N)[aux. stack space]
     */
    private void approach1(int[] arr) {
        takeOrNotTake(0, arr, new ArrayList<>());
    }
    private void takeOrNotTake(int indx, int[] arr, List<Integer> bucket) {
        if(indx > arr.length - 1) {
            System.out.print("{ " + bucket.toString() + " } ");
            return;
        }
        bucket.add(arr[indx]);
        takeOrNotTake(indx + 1, arr, bucket); // Take the current element.
        bucket.remove(bucket.size() - 1);
        takeOrNotTake(indx + 1, arr, bucket); // Do not take the current element.
    }
}
