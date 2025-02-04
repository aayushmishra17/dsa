package arrays;

import java.util.Arrays;

/*
 * Given two sorted arrays arr1[] and arr2[] of sizes n and m in non-decreasing order. 
 * Merge them in sorted order. Modify arr1 so that it contains the first N elements and 
 * modify arr2 so that it contains the last M elements.
 */
public class MergeTwoSortedArrays {
    public static void main(String[] args) {
        MergeTwoSortedArrays obj = new MergeTwoSortedArrays();
        int[] arr1 = new int[] { 1, 4, 8, 10 };
        int[] arr2 = new int[] { 2, 3, 9 };
        obj.approach1(arr1, arr2);
        obj.approach2(arr1, arr2);
        obj.approach3(arr1, arr2);
    }
    /*
     * Time - O(n + m)[to build arr3] + O(n + m)[to put the elements back into arr1 and arr2]
     * Space - O(n + m)
     */
    private void approach1(int[] arr1, int[] arr2) {
        int[] newArr1 = Arrays.copyOf(arr1, arr1.length);
        int[] newArr2 = Arrays.copyOf(arr2, arr2.length);
        int n = newArr1.length;
        int m = newArr2.length;
        int[] arr3 = new int[n + m];
        int left = 0, right = 0;
        int index = 0;
        while(left < n && right < m) {
            if(newArr1[left] <= newArr2[right]) {
                arr3[index] = newArr1[left];
                left++;
            } else {
                arr3[index] = newArr2[right];
                right++;
            }
            index++;
        }
        while(left < n) {
            arr3[index++] = newArr1[left++];
        }
        while(right < m) {
            arr3[index++] = newArr2[right++];
        }
        for(int i = 0; i < n + m; i++) {
            if(i < n) {
                newArr1[i] = arr3[i];
            } else {
                newArr2[i - n] = arr3[i];
            }
        }
        System.out.println("Arr1 = " + Arrays.toString(arr1));
        System.out.println("Arr2 = " + Arrays.toString(arr2));
        System.out.println("Merged Arr1 = " + Arrays.toString(newArr1));
        System.out.println("Merged Arr2 = " + Arrays.toString(newArr2));
    }
    /*
     * Time - O(min(n, m)) + O(n log n) + O(m log m)
     * Space - O(1)
     * 
     * Since both the given arrays are in sorted order, if we stand at the end of arr1 and at the 
     * begining of arr2 then we can traverse arr1 from end to start and arr2 from start to end.
     * If any element in arr2 is less than the element in arr1 then that arr2 element should be brought into arr1 
     * and the arr1 element should be brought into arr2.
     */
    private void approach2(int[] arr1, int[] arr2) {
        int[] newArr1 = Arrays.copyOf(arr1, arr1.length);
        int[] newArr2 = Arrays.copyOf(arr2, arr2.length);
        int n = newArr1.length;
        int m = newArr2.length;
        int left = n - 1;
        int right = 0;
        while(left >= 0 && right < m) {
            if(newArr1[left] > newArr2[right]) {
                // Swap newArr1[left] and newArr2[right]
                int temp = newArr1[left];
                newArr1[left] = newArr2[right];
                newArr2[right] = temp;

                left--;
                right++;
            } else {
                break;
            }
        }
        Arrays.sort(newArr1);
        Arrays.sort(newArr2);
        System.out.println("Arr1 = " + Arrays.toString(arr1));
        System.out.println("Arr2 = " + Arrays.toString(arr2));
        System.out.println("Merged Arr1 = " + Arrays.toString(newArr1));
        System.out.println("Merged Arr2 = " + Arrays.toString(newArr2));
    }
    /*
     * Time - O(log n+m) * O(n+m)
     * Space - O(1)
     * 
     * Gap method based on the Shell sort algo.
     * Gap = ceiling[(n + m) / 2], once gap becomes 1 we stop.
     */
    private void approach3(int[] arr1, int[] arr2) {
        int[] newArr1 = Arrays.copyOf(arr1, arr1.length);
        int[] newArr2 = Arrays.copyOf(arr2, arr2.length);
        int n = newArr1.length;
        int m = newArr2.length;
        int length = n + m;
        int gap = length / 2 + length % 2;
        while(gap > 0) {
            int left = 0;
            int right = left + gap;
            while(right < length) {
                if(left < n && right >= n) {
                    // When left is in arr1 and right is in arr2
                    swap(newArr1, newArr2, left, right - n);
                } else if(left >= n) {
                    // When left is in arr2 and right is in arr2
                    swap(newArr2, newArr2, left - n, right - n);
                } else {
                    // When left is in arr1 and right is in arr1
                    swap(newArr1, newArr1, left, right);
                }
                left++;
                right++;
            }
            if(gap == 1) {
                break;
            }
            gap = gap / 2 + gap % 2;
        }
        System.out.println("Arr1 = " + Arrays.toString(arr1));
        System.out.println("Arr2 = " + Arrays.toString(arr2));
        System.out.println("Merged Arr1 = " + Arrays.toString(newArr1));
        System.out.println("Merged Arr2 = " + Arrays.toString(newArr2));
    }
    private void swap(int[] arr1, int[] arr2, int indx1, int indx2) {
        if(arr1[indx1] > arr2[indx2]) {
            int temp = arr1[indx1];
            arr1[indx1] = arr2[indx2];
            arr2[indx2] = temp;
        }
    }
}
