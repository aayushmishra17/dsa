package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given an array, print all the elements which are leaders.
    A Leader is an element that is greater than all of the elements on its right side in the array.
 */
public class LeadersInArray {
    public static void main(String[] args) {
        LeadersInArray obj = new LeadersInArray();
        int[] arr = new int[] { 10, 22, 12, 3, 0, 6 };
        obj.approach1(arr);
        obj.approach2(arr);
    }
    /*
        Time - O(N * N)
        Space - O(1)
     */
    private void approach1(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            boolean isLeader = true;
            for(int j = i + 1; j < n; j++) {
                if(arr[j] > arr[i]) {
                    isLeader = false;
                }
            }
            if (isLeader) {
                leaders.add(arr[i]);
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Leaders = " + leaders);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private void approach2(int[] arr) {
        int n = arr.length;
        int max = arr[n - 1];
        List<Integer> leaders = new ArrayList<>();
        leaders.add(arr[n - 1]);
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] > max) {
                leaders.add(arr[i]);
                max = arr[i];
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Leaders = " + leaders);
    }
}
