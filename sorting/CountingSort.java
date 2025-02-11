package sorting;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        CountingSort obj = new CountingSort();
        int[] arr = new int[] { 8, 8, 3, 4, 5, 7, 7, 7, 1, 2, 2, 6, 9 };
        obj.countingSort(arr);
        obj.countingSortDesc(arr);
        obj.countingSortWithNegative(arr);
    }
    /*
        Time - O(N) + O(max elem)
        Space - O(N) + O(max elem)
        Stable
        Not in-place
        Non comparison based sorting algorithm.
     */
    private void countingSort(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int maxValue = newArr[0];
        for(int elem : newArr) {
            maxValue = Math.max(maxValue, elem);
        }
        int[] count = new int[maxValue + 1];
        for(int elem : newArr) {
            count[elem]++;
        }
        /*
            Create prefix sum array such that
            count[i] = count[i] + count[i - 1]
            Now the number at a given index would represent the count
            of elements that appear before this element.
         */
        for(int indx = 1; indx < count.length; indx++) {
            count[indx] = count[indx] + count[indx - 1];
        }
        /*
            Now we need to iterate the original array from the back.
            This is required if we need stable sort.
            Since the count[index] represent the number of elements that
            appear before this element.
         */
        for(int indx = newArr.length - 1; indx >= 0; indx--) {
            newArr[count[arr[indx]] - 1] = arr[indx];
            count[arr[indx]]--;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
    private void countingSortDesc(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int maxValue = newArr[0];
        for(int elem : newArr) {
            maxValue = Math.max(maxValue, elem);
        }
        int[] count = new int[maxValue + 1];
        for(int elem : newArr) {
            count[elem]++;
        }
        /*
            Create prefix sum array such that
            count[i] = count[i] + count[i - 1]
            Now the number at a given index would represent the count
            of elements that appear after this element.
         */
        for(int indx = count.length - 2; indx >= 0 ; indx--) {
            count[indx] = count[indx] + count[indx + 1];
        }
        /*
            Now we need to iterate the original array from the start.
            This is required if we need stable sort.
            Since the count[index] represent the number of elements that
            appear after this element.
         */
        for(int indx = 0; indx < newArr.length; indx++) {
            newArr[count[arr[indx]] - 1] = arr[indx];
            count[arr[indx]]--;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
    /*
        The problem with the previous counting sort was that we could not sort
        the elements if we have negative numbers in it. Because there are no negative
        array indices. So what we do is, we find the minimum element, and we will store
        count of that minimum element at zero index.
     */
    private void countingSortWithNegative(int[] arr) {
        arr = new int[] { 5, 4, 1, 2, 3, 0, -1, -5, -3, -2, -4, -4 };
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for(int elem : newArr) {
            maxValue = Math.max(maxValue, elem);
            minValue = Math.min(minValue, elem);
        }
        int range = maxValue - minValue + 1;
        int[] count = new int[range];
        for(int elem : newArr) {
            count[elem - minValue]++;
        }
        /*
            Create prefix sum array such that
            count[i] = count[i] + count[i - 1]
            Now the number at a given index would represent the count
            of elements that appear before this element.
         */
        for(int indx = 1; indx < count.length; indx++) {
            count[indx] = count[indx] + count[indx - 1];
        }
        /*
            Now we need to iterate the original array from the back.
            This is required if we need stable sort.
            Since the count[index] represent the number of elements that
            appear before this element.
         */
        for(int indx = newArr.length - 1; indx >= 0; indx--) {
            newArr[count[arr[indx] - minValue] - 1] = arr[indx];
            count[arr[indx] - minValue]--;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
}
