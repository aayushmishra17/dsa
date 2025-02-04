package arrays;

import java.util.Arrays;

/*
    Given an array of N integers, count the inversion of the array.
    What is an inversion of an array? Definition: for all i & j < size
    of array, if i < j then you have to find pair (A[i],A[j]) such that A[j] < A[i].
 */
public class CountInversions {
    public static void main(String[] args) {
        CountInversions obj = new CountInversions();
        int[] arr = new int[] { 5, 3, 2, 1, 4 };
//        int[] arr = new int[] { 5, 4, 3, 2, 1 };
//        int[] arr = new int[] { 1, 2, 3, 4, 5 };
        obj.approach1(arr);
        obj.approach2(arr);
    }
    /*
        Time - O(N * N)
        Space - O(1)
     */
    private void approach1(int[] arr) {
        int n = arr.length;
        int inversionCount = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(arr[j] < arr[i]) {
                    inversionCount++;
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Inversion count = " + inversionCount);
    }
    /*
        Time - O(N log N)
        Space - O(1)

        Utilizing merge sort algo's merge step to count the inversions.
     */
    private void approach2(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int inversionCount = mergeSort(newArr, 0, newArr.length - 1);
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Inversion count = " + inversionCount);
    }
    private int mergeSort(int[] arr, int start, int end) {
        int inversionCount = 0;
        if(start >= end) {
            return inversionCount;
        }
        int mid = start + (end - start) / 2;
        inversionCount += mergeSort(arr, start, mid);
        inversionCount += mergeSort(arr, mid + 1, end);
        inversionCount += merge(arr, start, mid, end);
        return inversionCount;
    }
    private int merge(int[] arr, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];
        int i = 0, j = 0, k = start;

        while(i < n1) {
            left[i] = arr[start + i];
            i++;
        }
        while(j < n2) {
            right[j] = arr[mid + 1 + j];
            j++;
        }

        i = 0;
        j = 0;
        int inversionCount = 0;
        while(i < n1 && j < n2) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                // inversion
                inversionCount += mid - i + 1;
                arr[k++] = right[j++];
            }
        }
        while(i < n1) {
            arr[k++] = left[i++];
        }
        while(j < n2) {
            arr[k++] = right[j++];
        }

        return inversionCount;
    }
}
