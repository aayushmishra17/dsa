package arrays;

import java.util.*;

/*
    Remove duplicate elements from given sorted array, in-place(without using extra space).
    Return the length of the unique elements array.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 1, 1, 2, 2, 3, 3, 3 };
        RemoveDuplicates obj = new RemoveDuplicates();
        obj.removeDuplicatesUsingSet(arr);
        obj.removeDuplicates(arr);
    }
    /*
        Time - O(n log n + n)
        Space - O(n)
     */
    private int removeDuplicatesUsingSet(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Set<Integer> set = new HashSet<>(Arrays.asList(Arrays.stream(newArr).boxed().toArray(Integer[]::new)));
        Iterator<Integer> itr = set.iterator();
        int indx = 0;
        while(itr.hasNext()) {
            Integer current = itr.next();
            newArr[indx++] = current;
        }

        System.out.println("Unique elements array: " + Arrays.toString(newArr));
        System.out.println("Length = " + indx);

        return indx;
    }
    /*
        Time - O(n)
        Space - O(1)
     */
    private int removeDuplicates(int[] arr) {
        int i = 0;

        for(int j = i + 1; j < arr.length; j++) {
            if(arr[j] != arr[i]) {
              i = i + 1;
              arr[i] = arr[j];
            }
        }

        System.out.println("Unique elements array: " + Arrays.toString(arr));
        System.out.println("Length = " + (i + 1));

        return i + 1;
    }
}
