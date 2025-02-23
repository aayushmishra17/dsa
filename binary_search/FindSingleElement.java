package binary_search;

import java.util.Arrays;

/*
    Given a sorted array of N integers. Every number in the array except one appears twice.
    Find the single number in the array.
 */
public class FindSingleElement {
    public static void main(String[] args) {
        FindSingleElement obj = new FindSingleElement();
        int[] arr = new int[] { 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6 };
        obj.findSingleElement(arr);
    }
    /*
        Time - O(log N)
        Space - O(1)

        By observation we notice that for all repeating pairs on
        the left side of the single element, for any even index the
        value[evenIndex] = value[oddIndex] and for any repeating pair
        on the right side of the single element, for any odd index the
        value[oddIndex] = value[evenIndex].
        This observation can be used to eliminate the halves in binary search.
        Indices:  0 1 2 3 4 5 6 7 8 9 10
        Elements: 1 1 2 2 3 3 4 5 5 6 6
                  e o e o e o e o e o e
     */
    private void findSingleElement(int[] arr) {
        int n = arr.length;
        int singleElement = -1;
        if(n == 1) {
            // If array contains only onr element.
            singleElement = arr[0];
        }
        if(arr[0] != arr[1]) {
            // In case the first element in the single element.
            singleElement = arr[0];
        }
        if(arr[n - 1] != arr[n - 2]) {
            // In case the last element in a single element.
            singleElement = arr[n - 1];
        }
        /*
            We'll take start element from index = 1 and
            the last element at index = n - 2, so this way we can
            eliminate handling index based edge case in the binary search code.
         */
        int start = 1;
        int end = n - 2;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if(arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
                singleElement = arr[mid];
                break;
            }
            boolean isEvenIndex = mid % 2 == 0;
            if(isEvenIndex) {
                /*
                    Standing at even index.
                    If element at next index is same then single
                    element in on right half, so eliminate left half.
                    Else, if element at previous index is same then
                    single element in on the left half, so eliminate the
                    right half.
                 */
                if(arr[mid] == arr[mid + 1]) {
                    // Eliminate left half.
                    start = mid + 1;
                } else if(arr[mid] == arr[mid - 1]) {
                    // Eliminate right half.
                    end = mid - 1;
                }
            } else {
                /*
                    Standing at odd index.
                    If element at next index is same then single element
                    in on the left half, so eliminate right half.
                 */
                if(arr[mid] == arr[mid + 1]) {
                    // Eliminate right half.
                    end = mid - 1;
                } else if(arr[mid] == arr[mid - 1]) {
                    // eliminate left half.
                    start = mid + 1;
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Single element = " + singleElement);
    }
}
