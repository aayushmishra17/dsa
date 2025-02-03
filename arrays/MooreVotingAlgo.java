package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    Given an array of N integers, write a program to return an element
    that occurs more than N/2 times in the given array.
 */
public class MooreVotingAlgo {
    public static void main(String[] args) {
        MooreVotingAlgo obj = new MooreVotingAlgo();
        int[] arr = new int[] { 2, 2, 1, 1, 1, 2, 2 };
        obj.approach1(arr);
        obj.approach2(arr);
        obj.approach3(arr);
    }
    /*
        Time - O(N * N)
        Space - O(1)
     */
    private void approach1(int[] arr) {
        int n = arr.length;
        int majorityElement = -1;
        for(int i = 0; i < n; i++) {
            int currCnt = 0;
            for(int j = 0; j < n; j++) {
                if(arr[i] == arr[j]) {
                    currCnt++;
                }
            }
            if(currCnt > n / 2) {
                majorityElement = arr[i];
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Majority element = " + majorityElement);
    }
    /*
        Time - O(N)[using a HashMap, can be N*N in case of hash collision] + O(N)
        Space - O(N)
     */
    private void approach2(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        int majorityElement = -1;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > n / 2) {
                majorityElement = entry.getKey();
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Majority element = " + majorityElement);
    }
    /*
        Time - O(N) + O(N)[Verification step in case majority element is not guaranteed]
        Space - O()
        Moore's voting algo: We consider every unique element as a candidate and try to figure out
                             who gets the majority(> n/2) votes.
                             This is done by maintaining two parameters currElem and count.
                             If we encounter the same element we increment the count otherwise we
                             decrement the count. If at the end count is > 0 then we have a
                             majority element.
                             In case the arr is not guaranteed to have a majority element then
                             we need to iterate the array again and confirm if the elem we found is the
                             majority element by checking count > n/2.
     */
    private void approach3(int[] arr) {
        int n = arr.length;

        int currCandidate = -1;
        int voteCount = 0;
        for(int elem : arr) {
            if(voteCount == 0) {
                currCandidate = elem;
                voteCount++;
            } else if(elem == currCandidate) {
                voteCount++;
            } else {
                voteCount--;
            }
        }
        if(voteCount > 0) {
            int count = 0;
            for(int elem : arr) {
                if(elem == currCandidate) {
                    count++;
                }
            }
            if(count > n / 2) {
                System.out.println("Arr = " + Arrays.toString(arr));
                System.out.println("Majority element = " + currCandidate);
                return;
            }
        }
        System.out.println("No majority element");
    }
}
