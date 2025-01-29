package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Intersection of two sorted arrays is defined as an array that contains only those elements
    that are present in both the arrays, the intersection array may contain duplicate elements as
    per the contents of the original arrays.
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] arr1 = new int[] { 1, 2, 2, 3, 3, 4, 5, 6 };
        int[] arr2 = new int[] { 2, 3, 3, 5, 6, 6, 7 };
        IntersectionOfTwoArrays obj = new IntersectionOfTwoArrays();
        obj.approach1(arr1, arr2);
        obj.approach2(arr1, arr2);
    }
    /*
        Time - O(n1 * n2)
        Space - O(n2)
     */
    private void approach1(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int[] visited = new int[arr2.length];
        for(int i = 0; i < arr1.length; i++) {
            for(int j = 0; j < arr2.length; j++) {
                if(arr1[i] == arr2[j] && visited[j] == 0) {
                    result.add(arr1[i]);
                    visited[j] = 1;
                    break;
                }
                if(arr2[j] > arr1[i]) {
                    break;
                }
            }
        }
        System.out.println("Arr1 = " + Arrays.toString(arr1));
        System.out.println("Arr2 = " + Arrays.toString(arr2));
        System.out.println("Intersection = " + result);
    }
    /*
        Time - O(n1 + n2)
        Space - O(1)
     */
    private void approach2(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] == arr2[j]) {
                result.add(arr1[i]);
                i++;
                j++;
            } else if(arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println("Arr1 = " + Arrays.toString(arr1));
        System.out.println("Arr2 = " + Arrays.toString(arr2));
        System.out.println("Intersection = " + result);
    }
}
