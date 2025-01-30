package arrays;

import java.util.Arrays;

public class SortArrayof0s1s2s {
    public static void main(String[] args) {
        SortArrayof0s1s2s obj = new SortArrayof0s1s2s();
        int[] arr = new int[] { 2, 0, 2, 1, 1, 0 };
        obj.approach1(arr);
        obj.approach2(arr);
        obj.approach3(arr);
    }
    /*
     * Time - O(n log N)
     * Space - O(N)(for duplicate array)
     */
    private void approach1(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        System.out.println("Original array = " + Arrays.toString(arr));
        System.out.println("Sorted array = " + Arrays.toString(newArr));
    }
    /*
     * Time - O(N) + O(N)
     * Space - O(1)
     * This approach counts the number of 0s, 1s and 2s and then refills the array as per their count.
     */
    private void approach2(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for(int itr = 0; itr < n; itr++) {
            if(arr[itr] == 0) {
                cnt0++;
            } else if(arr[itr] == 1) {
                cnt1++;
            } else {
                cnt2++;
            }
        }
        for(int itr = 0; itr < cnt0; itr++) {
            newArr[itr] = 0;
        }
        for(int itr = cnt0; itr < cnt0 + cnt1; itr++) {
            newArr[itr] = 1;
        }
        for(int itr = cnt0 + cnt1; itr < n; itr++) {
            newArr[itr] = 2;
        }
        System.out.println("Original arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
    /*
     * Time - O(N)
     * Space - O(1)
     * 
     * This aproach is called the Dutch National Flag algorithm.
     * It utilizes three pointers: low, mid and high.
     * [0, low - 1] would contain 0s.
     * [low, mid - 1] would contain 1s.
     * [mid, high] un-sorted
     * [high + 1, n - 1] would contain 2s.
     * 
     * 000000000001111111111111**unsorted**222222222222
     * [0 - low-1][low - mid-1][mid - high][high - n-1]
     * Initially for a given array, mid = 0 and high = n-1 since entire array is unsorted.
     */
    private void approach3(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        int low = 0, mid = 0, high = n - 1;
        while(mid <= high) {
            if(newArr[mid] == 0) {
                int temp = newArr[mid];
                newArr[mid] = newArr[low];
                newArr[low] = temp;
                low++;
                mid++;
            } else if(newArr[mid] == 1) {
                mid++;
            } else {
                int temp = newArr[mid];
                newArr[mid] = newArr[high];
                newArr[high] = temp;
                high--;
            }
        }

        System.out.println("Original arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
}
