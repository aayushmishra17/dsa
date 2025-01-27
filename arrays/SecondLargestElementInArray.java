package arrays;

import java.util.Arrays;

/*
    Need to find second-largest element in array without sorting.
    Array may contain duplicate elements.
 */
public class SecondLargestElementInArray {
    public static void main(String[] args) {
        int[] arr = new int[] { 8, 2, 10, 3, 5, 1, 4, 7, 6, 9, 1, 9, 3, 3 };
        SecondLargestElementInArray obj = new SecondLargestElementInArray();
        System.out.println(obj.getSecondLargestUsingSorting(arr));
        System.out.println(obj.getSecondLargestTwoPass(arr));
        System.out.println(obj.getSecondLargestOnePass(arr));
    }
    /*
        Time - O(n logN + n)
        Space - O(1)
     */
    private int getSecondLargestUsingSorting(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);

        int largest = newArr[newArr.length - 1];
        int secondLargest = Integer.MIN_VALUE;
        for(int itr = newArr.length - 2; itr >= 0; itr--) {
            if(newArr[itr] != largest && newArr[itr] < largest) {
                secondLargest = newArr[itr];
                break;
            }
        }
        return secondLargest;
    }
    /*
        Time - O(2N)
        Space - O(1)
     */
    private int getSecondLargestTwoPass(int[] arr) {
        int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;
        // 1st pass
        for(int itr = 0; itr < arr.length; itr++) {
            if(arr[itr] > largest) {
                largest = arr[itr];
            }
        }
        // 2nd pass
        for(int itr = 0; itr < arr.length; itr++) {
            if(arr[itr] != largest && arr[itr] > secondLargest) {
                secondLargest = arr[itr];
            }
        }
        return secondLargest;
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private int getSecondLargestOnePass(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for(int itr = 0; itr < arr.length; itr++) {
            if(arr[itr] > largest && arr[itr] != largest) {
                secondLargest = largest;
                largest = arr[itr];
            }
            if(arr[itr] != largest && arr[itr] > secondLargest) {
                secondLargest = arr[itr];
            }
        }
        return secondLargest;
    }
}
