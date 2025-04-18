package sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort obj = new BubbleSort();
        int[] arr = new int[] { 7, 8, 9, 3, 4, 1, 2, 5, 6 };
        obj.bubbleSort(arr);
    }
    /*
        Time - O(N * N)
        Space - O(1)
        Stable
        In-place

        The name "bubble sort" comes from the way larger elements "bubble"
        to the top of the list during the sorting process.

        If two adjacent elements are equal, the algorithm does not swap them, ensuring
        their original order is maintained. Hence, bubble sort is stable.

        On every scan through the array we are swapping elements if current element is greater than the next element, 
        so element at current index is always the largest element encounterd till now. This largest element is moved to the 
        end of the array in each scan. This is why on next scan we don't need to go to the very end of the array since it 
        would have the largest element from previous scans, so we decrease the length to which we go by 1 every time in 
        the inner loop: j < n - 1 - i.
     */
    private void bubbleSort(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = newArr.length;
        for(int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for(int j = 0; j < n - 1 - i; j++) {
                if(newArr[j] > newArr[j + 1]) {
                    int temp = newArr[j];
                    newArr[j] = newArr[j + 1];
                    newArr[j + 1] = temp;
                    swapped = true;
                }
            }
            if(swapped == false) {
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Sorted arr = " + Arrays.toString(newArr));
    }
}
