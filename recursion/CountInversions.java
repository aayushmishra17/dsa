package recursion;

import java.util.Arrays;

/*
 * Given an array of integers, find the number of pairs such that for any i < j,
 * a[i] > a[j].
 * E.g. arr = [5, 3, 2, 4, 1]
 * Inversion = (5,3), (5,2), (5, 4), (5,1), (3,2), (3,1), (2,1), (4,1)
 */
public class CountInversions {
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 3, 2, 4, 1 };
        System.out.println("Arr = " + Arrays.toString(arr));
        CountInversions obj = new CountInversions();
        obj.approach1(arr);
        obj.approach2(arr);
    }
    /*
     * Time - O(N * N)
     * Space - O(1)
     */
    private void approach1(int[] arr) {
        int count = 0;
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        System.out.println("Inversion count = " + count);
    }
    /*
     * Time - O(N * log N)
     * Space - O(1)
     * We'll use the merge sort algo and count the inversion while merging and return the count.
     */
    private void approach2(int[] arr) {
        System.out.println("Inversion count = " + mergeSort(arr, 0, arr.length - 1));;
    }
    private int mergeSort(int[] arr, int start, int end) {
        int count = 0;
        if(start < end) {
            int mid = start + (end - start) / 2;
            count += mergeSort(arr, start, mid);
            count += mergeSort(arr, mid + 1, end);
            count += merge(arr, start, mid, end);
        }
        return count;
    }
    private int merge(int[] arr, int start, int mid, int end) {
        int count = 0;

        int n1 = mid - start + 1;
        int n2 = end - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];
        int k = start, i = 0, j = 0;

        while(i < n1) {
            left[i] = arr[i + start];
            i++;
        }
        while (j < n2) {
            right[j] = arr[mid + 1 + j];
            j++;
        }
        i = 0;
        j = 0;

        while (i < n1 && j < n2) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                count += n1 - i;
                arr[k++] = right[j++];
            }
        }
        while(i < n1) {
            arr[k++] = left[i++];
        }
        while (j < n2) {
            arr[k++] = right[j++];
        }

        return count;
    }
}
