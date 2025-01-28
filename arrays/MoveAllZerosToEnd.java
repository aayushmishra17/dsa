package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveAllZerosToEnd {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 0, 0, 3, 0, 4, 4, 0, 0, 0, 5 };
        MoveAllZerosToEnd obj = new MoveAllZerosToEnd();
        obj.approachOne(arr);
        obj.approachTwo(arr);
    }
    /*
        Time - O(N) + O(x) + O(N - x) = O(2N)
        Space - O(N)
     */
    private void approachOne(int[] arr) {
        System.out.println("Original arr = " + Arrays.toString(arr));
        int[] newArr = Arrays.copyOf(arr, arr.length);
        List<Integer> nonZeros = new ArrayList<>();
        for(int elem : newArr) {
            if(elem != 0) {
                nonZeros.add(elem);
            }
        }
        int itr = 0;
        for(itr = 0; itr < nonZeros.size(); itr++) {
            newArr[itr] = nonZeros.get(itr);
        }
        for(; itr < newArr.length; itr++) {
            newArr[itr] = 0;
        }
        System.out.println("Final arr = " + Arrays.toString(newArr));
    }
    /*
        Time - O(x) + O(N - x) = O(N)
        Space - O(1)
     */
    private void approachTwo(int[] arr) {
        System.out.println("Original arr = " + Arrays.toString(arr));
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int j = -1;
        for(int i = 0; i < newArr.length; i++) {
            if(newArr[i] == 0) {
                j = i;
                break;
            }
        }
        for(int i = j + 1; i < newArr.length; i++) {
            if(newArr[i] != 0) {
                newArr[j] = newArr[i];
                newArr[i] = 0;
                j++;
            }
        }
        System.out.println("Final arr = " + Arrays.toString(newArr));
    }
}
