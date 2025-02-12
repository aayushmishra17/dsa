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
