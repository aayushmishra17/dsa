package arrays;

import java.util.*;

/*
    Union of two arrays is an array containing all unique elements from both arrays.
    In this problem we need to find the union of two sorted arrays.
 */
public class UnionOfTwoArrays {
    public static void main(String[] args) {
        int[] arr1 = new int[] { 1, 2, 3, 4, 6, 7, 8, 14, 14, 15 };
        int[] arr2 = new int[] { 2, 3, 5, 9, 10, 11, 12, 13, 13 };
        UnionOfTwoArrays obj = new UnionOfTwoArrays();
        obj.approach1(arr1, arr2);
        obj.approach2(arr1, arr2);
    }
    /*
        Time - O(n1 log n) + O(n2 log n) + O(n1 + n2)
        Space- O(n1 + n2)
     */
    private void approach1(int[] arr1, int[] arr2) {

        Set<Integer> set = new TreeSet<>();
        for(int elem : arr1) {
            set.add(elem);
        }
        for(int elem : arr2) {
            set.add(elem);
        }

        System.out.println("Set = " + set);
        List<Integer> result = new ArrayList<>(set);
        System.out.println("Arr1 = " + Arrays.toString(arr1));
        System.out.println("Arr2 = " + Arrays.toString(arr2));
        System.out.println("Union = " + result);

    }
    /*
        Time - O(n1 + n2)
        Space - O(1) [result arr is only created to return the result.]
     */
    private void approach2(int[] arr1, int[] arr2) {

        int i = 0, j = 0, prevElem = -1;
        List<Integer> result = new ArrayList<>();
        while (i < arr1.length && j < arr2.length) {
            if(arr1[i] <= arr2[j]) {
                if (prevElem != arr1[i]) {
                    result.add(arr1[i]);
                    prevElem = arr1[i];
                }
                i++;
            } else {
                if(prevElem != arr2[j]) {
                    result.add(arr2[j]);
                    prevElem = arr2[j];
                }
                j++;
            }
        }
        while (i < arr1.length && arr1[i] != prevElem) {
            result.add(arr1[i]);
        }
        while (j < arr2.length && arr2[j] != prevElem) {
            result.add(arr2[j]);
        }
        System.out.println("Arr1 = " + Arrays.toString(arr1));
        System.out.println("Arr2 = " + Arrays.toString(arr2));
        System.out.println("Union = " + result);

    }
}
