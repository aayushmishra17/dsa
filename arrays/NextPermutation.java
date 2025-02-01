package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/*
    Given an array Arr[] of integers, rearrange the numbers of the given array into
    the lexicographically next greater permutation of numbers. If such an arrangement
    is not possible, it must rearrange to the lowest possible order (i.e., sorted in ascending order).
 */
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation obj = new NextPermutation();
        int[] arr = new int[] { 1, 3, 2 };
        obj.approach1(arr);
        obj.approach2(arr);
    }
    /*
        Time - O(N * N!) [N! possible permutations. Each of length N, O(N) to search each permutation.]
        Space - O(N)[Stack space]

        Generate all possible permutations and then search for given permutation
        then return the next permutation.
        Refer recursion.PrintAllPermutationsOfArray
     */
    private void approach1(int[] arr) {
        /*
            Find all permutations in sorted order. O(N!)
            Then search in the generated permutations for the given permutation. O(N)
            Then take the next permutation in order.
         */
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private void approach2(int[] arr) {
        int indx = -1;
        int n = arr.length;
        int[] newArr = Arrays.copyOf(arr, arr.length);
        /*
            We start from n-2 from the end since at least 2 elements
            are required to identify an inflection.
         */
        for(int i = n - 2; i >= 0; i--) {
            if(newArr[i] < newArr[i + 1]) {
                indx = i;
                break;
            }
        }
        // Given permutation is the last in sequence so just reverse the given permutation.
        if(indx == -1) {
            reverse(newArr, 0, n - 1);
        } else {
            /*
                Iterate from the end and first the first element such that
                A[i] > A[indx], then swap(A[i], A[indx])
             */
            for(int i = n-1; i > indx; i--) {
                if(newArr[i] > newArr[indx]) {
                    int temp = newArr[indx];
                    newArr[indx] = newArr[i];
                    newArr[i] = temp;
                    break;
                }
            }
            reverse(newArr, indx + 1, n - 1);
        }
        System.out.println("Given permutation = " + Arrays.toString(arr));
        System.out.println("Next permutation = " + Arrays.toString(newArr));
    }
    private void reverse(int[] arr, int fromIndx, int toIndx) {
        while(fromIndx < toIndx) {
            int temp = arr[fromIndx];
            arr[fromIndx] = arr[toIndx];
            arr[toIndx] = temp;
            fromIndx++;
            toIndx--;
        }
    }
}
