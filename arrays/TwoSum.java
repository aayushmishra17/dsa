package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given an integer array and an integer target, find if a pair exists 
 * such that their sum is equal to the target.
 * Cannot use the same element twice.
 */
public class TwoSum {
    public static void main(String[] args) {
        TwoSum obj = new TwoSum();
        int[] arr = new int[] { 2, 6, 5, 8, 11 };
        int targetSum = 14;
        obj.approach1(arr, targetSum);
        obj.approach2(arr, targetSum);
        obj.approach3(arr, targetSum);
    }
    /*
     * Time - O(N * N)
     * Space - O(1)
     */
    private void approach1(int[] arr, int targetSum) {
        int n = arr.length;
        int firstIndx = -1, secondIndx = -1;
        outer: for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == targetSum) {
                    firstIndx = i;
                    secondIndx = j;
                    break outer;
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if (firstIndx != -1 && secondIndx != -1) {
            System.out.println("Pair: (" + arr[firstIndx] + "," + arr[secondIndx] + ")");
        } else {
            System.out.println("No pair exists whose sum is " + targetSum + ".");
        }
    }
    /*
     * Time - O(N) Since we have used HashMap, in case of hash collision it may become O(N * N)
     * Space - O(N)
     * This approach stores the previously encounterd elements in the map in the form (element, index).
     * For every element it checks if the map contains (targetSum - currElem) in the map, if it does then we found a pair.
     */
    private void approach2(int[] arr, int targetSum) {
        int n = arr.length;
        int firstIndx = -1, secondIndx = -1;
        Map<Integer, Integer> hash = new HashMap<>(); // (element, index)
        for(int i = 0; i < n; i++) {
            if (hash.containsKey(targetSum - arr[i])) {
                firstIndx = hash.get(targetSum - arr[i]);
                secondIndx = i;
                break;
            }
            hash.put(arr[i], i);
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if (firstIndx != -1 && secondIndx != -1) {
            System.out.println("Pair: (" + arr[firstIndx] + "," + arr[secondIndx] + ")");
        } else {
            System.out.println("No pair exists whose sum is " + targetSum + ".");
        }
    }
    /*
     * Time - O(N) + O(n log N)
     * Space - O(1)
     * In this approach we will first have to sort the array, then we can use two pointers, 
     * one starting from the begining(i) and the other starting from the end(j) of the array.
     * Since arr is sorted now, we know i++ means increasing the sum and j-- means decreasing the sum of the elements.
     * We iterate thorugh the array and compare the sum until we get the targetSum.
     * 
     * This approach has the same time complexity but better space complexity, so we can use this approach when we are not allowed 
     * to use a map like in the previous approach.
     * If we need to return the indices of the original array then we should prefer previous approach.
     */
    private void approach3(int[] arr, int targetSum) {
        int n = arr.length;
        int firstIndx = -1, secondIndx = -1;
        
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int i = 0, j = n - 1;
        Arrays.sort(newArr);
        while(i < j) {
            if(newArr[i] + newArr[j] == targetSum) {
                firstIndx = i;
                secondIndx = j;
                break;
            } else if(newArr[i] + newArr[j] > targetSum) {
                j--; // decrease total sum
            } else {
                i++; // increase total sum
            }
        }

        System.out.println("Arr = " + Arrays.toString(arr));
        if (firstIndx != -1 && secondIndx != -1) {
            System.out.println("Pair: (" + newArr[firstIndx] + "," + newArr[secondIndx] + ")");
        } else {
            System.out.println("No pair exists whose sum is " + targetSum + ".");
        }
    }
}
