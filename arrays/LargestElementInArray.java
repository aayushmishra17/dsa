package arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LargestElementInArray
 */
public class LargestElementInArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input array size -");
        int n = Integer.parseInt(reader.readLine());
        System.out.println("Input array elements -");
        List<Integer> arrList = new ArrayList<>();
        int[] arr = new int[n];
        for (String elem : reader.readLine().split(" ")) {
            arrList.add(Integer.parseInt(elem));
        }
        arr = arrList.stream().mapToInt(i -> i).toArray();
        System.out.println("Max element = " + findMax(arr));
        System.out.println("Max element = " + findMaxRecur(1, arr, Integer.MIN_VALUE));
    }

    /*
    Time - O(n log n)
    Space - O(1)
     */
    private static int findMaxUsingSort(int[] arr) {
        Arrays.sort(arr);
        return  arr[0];
    }

    /*
     * Time - O(N)
     * Space - O(1)
     */
    private static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int elem : arr) {
            if (elem > max) {
                max = elem;
            }
        }
        return max;
    }
    /*
     * Time - O(N)
     * Space - O(N) - auxiliary stack space
     */

    private static int findMaxRecur(int indx, int[] arr, int max) {
        if (indx > arr.length - 1) {
            return max;
        }
        if (arr[indx] > max) {
            max = arr[indx];
        }
        max = findMaxRecur(indx + 1, arr, max);

        return max;
    }
}