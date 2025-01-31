package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    There’s an array ‘A’ of size ‘N’ with an equal number of positive and negative elements.
    Without altering the relative order of positive and negative elements, you must return
    an array of alternately positive and negative values.
 */
public class RearrangeBySignAlternating {
    public static void main(String[] args) {
        RearrangeBySignAlternating obj = new RearrangeBySignAlternating();
        int[] arr = new int[] { 1, 2, -3, -1, -2, 3 };
        obj.approach1(arr);
        obj.approach2(arr);
        obj.approach3(arr);
    }
    /*
        Time - O(N) + O(N/2)(since it is given that +ve and -ve element count is equal)
        Space - O(N)
     */
    private void approach1(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        List<Integer> positiveElements = new ArrayList<>();
        List<Integer> negativeElements = new ArrayList<>();
        for (int elem : newArr) {
            if(elem >= 0) {
                positiveElements.add(elem);
            } else {
                negativeElements.add(elem);
            }
        }
        int n1 = positiveElements.size();
        int n2 = negativeElements.size();
        int i = 0, j = 0, z = 0;
        while (i < n1 && j < n2) {
            newArr[z++] = positiveElements.get(i);
            newArr[z++] = negativeElements.get(j);
            i++;
            j++;
        }
        System.out.println("Original arr = " + Arrays.toString(arr));
        System.out.println("Alternating sign arr = " + Arrays.toString(newArr));
    }
    /*
        Time - O(N)
        Space - O(1)

        This will work only if count of +ve and -ve elements is equal.
     */
    private void approach2(int[] arr) {
        int n = arr.length;
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int positiveIndx = 0, negativeIndx = 1;
        int i = 0;
        while (i < n) {
            if(arr[i] >= 0) {
                newArr[positiveIndx] = arr[i];
                positiveIndx += 2;
            } else {
                newArr[negativeIndx] = arr[i];
                negativeIndx += 2;
            }
            i++;
        }
        System.out.println("Original arr = " + Arrays.toString(arr));
        System.out.println("Alternating sign arr = " + Arrays.toString(newArr));
    }
    /*
        Time - O(N) + O(N)
        Space - O(N)

        This approach assumes the +ve and -ve element count may not be equal.
        Place the left-over elements at the end.
     */
    private void approach3(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        List<Integer> positiveElements = new ArrayList<>();
        List<Integer> negativeElements = new ArrayList<>();
        for(int elem : arr) {
            if(elem >= 0) {
                positiveElements.add(elem);
            } else {
                negativeElements.add(elem);
            }
        }
        if(negativeElements.size() > positiveElements.size()) {
            for(int i = 0; i < positiveElements.size(); i++) {
                newArr[2*i] = positiveElements.get(i);
                newArr[2*i + 1] = negativeElements.get(i);
            }
            int indx = positiveElements.size();
            for(int i = positiveElements.size(); i < negativeElements.size(); i++) {
                newArr[indx++] = negativeElements.get(i);
            }
        } else {
            for(int i = 0; i < negativeElements.size(); i++) {
                newArr[2*i] = positiveElements.get(i);
                newArr[2*i + 1] = negativeElements.get(i);
            }
            int indx = negativeElements.size();
            for(int i = negativeElements.size(); i < positiveElements.size(); i++) {
                newArr[indx++] = positiveElements.get(i);
            }
        }
        System.out.println("Original arr = " + Arrays.toString(arr));
        System.out.println("Alternating sign arr = " + Arrays.toString(newArr));
    }
}
