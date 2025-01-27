package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class RotateArrayByKPlaces {
    public static void main(String[] args) {
        RotateArrayByKPlaces obj = new RotateArrayByKPlaces();
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int k = 3;
        obj.rotateArrayApproach1(arr, k);
        obj.rotateArrayApproach2(arr, k);
    }
    /*
        Time - O(k) + O(n - k) + O(k) = O(n + k)
        Space - O(k)
     */
    private void rotateArrayApproach1(int[] arr, int k) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        k = k % newArr.length;

        int[] temp = new int[k];
        for(int itr = 0; itr < k; itr++) {
            temp[itr] = newArr[itr];
        }

        for(int i = k; i < newArr.length; i++) {
            newArr[i - k] = newArr[i];
        }
        for(int i = k + 1; i < newArr.length; i++) {
            newArr[i] = temp[i - k - 1];
        }

        System.out.println("Original arr = " + Arrays.toString(arr) + " K = " + k);
        System.out.println("Left Rotated arr = " + Arrays.toString(newArr));
    }
    /*
        Time - O(k) + O(n - k) + O(n) = O(2n)
        Space - O(1)
     */
    private void rotateArrayApproach2(int[] arr, int k) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        System.out.println("Original arr = " + Arrays.toString(arr) + " K = " + k);
        BiConsumer<Integer, Integer> reverseArr = (Integer startIndx, Integer endIndx) -> {
            while(startIndx <= endIndx) {
                int temp = newArr[startIndx];
                newArr[startIndx] = newArr[endIndx];
                newArr[endIndx] = temp;
                startIndx++;
                endIndx--;
            }
        };
        // Reverse [0, k - 1]
        reverseArr.accept(0, k - 1);
        // Reverse [k, n - 1]
        reverseArr.accept(k, newArr.length - 1);
        // Reverse [0, n - 1]
        reverseArr.accept(0, newArr.length - 1);
        System.out.println("Left Rotated arr = " + Arrays.toString(newArr));

        // Demo right rotation by K
        int[] newArr2 = Arrays.copyOf(arr, arr.length);
        System.out.println("Original arr = " + Arrays.toString(arr) + " K = " + k);
        BiConsumer<Integer, Integer> reverseArr2 = (Integer startIndx, Integer endIndx) -> {
            while(startIndx <= endIndx) {
                int temp = newArr2[startIndx];
                newArr2[startIndx] = newArr2[endIndx];
                newArr2[endIndx] = temp;
                startIndx++;
                endIndx--;
            }
        };
        // Reverse [0, n - k - 1]
        reverseArr2.accept(0, newArr2.length - k - 1);
        // Reverse [n - k, n - 1]
        reverseArr2.accept(newArr2.length - k, newArr2.length - 1);
        // Reverse [0, n - 1]
        reverseArr2.accept(0, newArr2.length - 1);
        System.out.println("Right Rotated arr = " + Arrays.toString(newArr2));
    }
}
