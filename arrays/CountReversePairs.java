package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an array of numbers, you need to return the count of reverse pairs. 
 * Reverse Pairs are those pairs where i<j and arr[i]>2*arr[j].
 */
public class CountReversePairs {
    public static void main(String[] args) {
        CountReversePairs obj = new CountReversePairs();
        int[] arr = new int[] { 1, 3, 2, 3, 1 };
        // int[] arr = new int[] { 3, 2, 1, 4 };
        obj.approach1(arr);
        obj.approach2(arr);
    }
    /*
     * Time - O(N * N)
     * Space - O(1)
     */
    private void approach1(int[] arr) {
        int n = arr.length;
        int count = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(arr[i] > 2 * arr[j]) {
                    count++;
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Count of reverse pairs = " + count);
    }
    /*
     * Time - O(N log (N+N))
     * Space - O(N)
     * 
     * This approach will utilize the merge sort algorithm to count the number of reverse pairs.
     * The count reverse pairs will be a separate step that would be performed before the merge step.
     */
    private void approach2(int[] arr) {
        int count = mergeSort(arr, 0, arr.length - 1);
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Count of reverse pairs = " + count);
    }
    private int mergeSort(int[] arr, int start, int end) {
        int count = 0;
        if(start >= end) {
            return count;
        }
        int mid = start + (end - start) / 2;
        count += mergeSort(arr, start, mid);
        count += mergeSort(arr, mid + 1, end);
        count += countPairs(arr, start, mid, end);
        merge(arr, start, mid, end);
        return count;
    }
    /*
     * Time - O(N)
     * Space - O(N)
     */
    private void merge(int[] arr, int low, int mid, int high) {
        List<Integer> temp = new ArrayList<>();
        int left = low;
        int right = mid + 1;
        
        while(left <= mid && right <= high) {
            if(arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }
        while(left <= mid) {
            temp.add(arr[left++]);
        }
        while(right <= high) {
            temp.add(arr[right++]);
        }
        for(int i = low; i <= high; i++) {
            /*
             * Why (i - low):
             *      Since arr is being tracked starting at indx = low, where low can be non-zero index, however 
             *      the temp arraylist will start from index 0, so we need to map array starting from index = low into 
             *      an array starting from index = 0. This can be done by substracting low from i.
             */
            arr[i] = temp.get(i - low);
        }
    }
    /*
     * Time - O(N)[we iterate over all elements in left array only once] + O(N)[we iterate over all elements in right array only once]
     * Space - O(1)
     */
    private int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1;
        int count = 0;
        for(int i = low; i <= mid; i++) {
            while(right <= high && arr[i] > 2 * arr[right]) {
                right++;
            }
            count += right - (mid + 1);
        }
        return count;
    }
}
