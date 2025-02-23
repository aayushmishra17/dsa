package binary_search;

import java.util.Arrays;

/*
    Given an array of length N. Peak element is defined as the element greater
    than both of its neighbors. Formally, if 'arr[i]' is the peak element,
    'arr[i - 1]' < 'arr[i]' and 'arr[i + 1]' < 'arr[i]'. Find the index(0-based) of
    a peak element in the array. If there are multiple peak numbers, return the index of any peak number.
    Note: For the first element, the previous element should be considered as negative
    infinity as well as for the last element, the next element should be considered as negative infinity.
 */
public class PeakElement {
    public static void main(String[] args) {
        PeakElement obj = new PeakElement();
        int[] arr1 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 5, 1 };
        int[] arr2 = new int[] { 1, 5, 1, 2, 1 };
        obj.findPeak(arr1);
    }
    /*
        Time - O(log N)
        Space - O(1)
     */
    private void findPeak(int[] arr) {
        int n = arr.length;
        int peakIndex = -1;
        if(n == 1) {
            // Array has only one element.
            peakIndex = 0;
        }
        if(arr[0] > arr[1]) {
            // Could be a strictly decreasing array.
            peakIndex = 0;
        } else if(arr[n - 1] > arr[n - 2]) {
            // Could be a strictly increasing array.
            peakIndex = n - 1;
        }
        int start = 1;
        int end = n - 2;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                peakIndex = mid;
                break;
            } else if(arr[mid] >= arr[mid - 1]) {
                /*
                    Standing on incline, so peak is on the right half.
                 */
                start = mid + 1;
            } else {
                /*
                    Standing on decline, so peak is on the left half.
                    Or, we may be sitting on a local minima(in case of multiple peaks),
                    in which case we can choose to go either on the right or left.
                    E.g.: Arr= [1 5 1 2 1] and mid is at index 2 which is local minima.
                 */
                end = mid - 1;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        if(peakIndex == -1) {
            System.out.println("No peak element exists.");
        } else {
            System.out.println("Peak element = " + arr[peakIndex] + " at index = " + peakIndex);
        }
    }
}
