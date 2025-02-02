package arrays;

import java.util.*;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        LongestConsecutiveSequence obj = new LongestConsecutiveSequence();
        int[] arr = new int[] { 100, 200, 1, 3, 2, 4, 7, 8, 9, 10, 11 };
        obj.approach1(arr);
        obj.approach2(arr);
        obj.approach3(arr);
    }
    /*
        Time - O(N * N)
        Space - O(1)

        Iterate over every element and for each element check if (elem + 1) exists,
        if it does then continue the process and maintain current length, else if the (elem + 1)
        doesn't exist then continue to the next array element.
        O(N) - to iterate over all array elements.
        O(N) - for linear search in the array for (elem + 1).
     */
    private void approach1(int[] arr) {
        int n = arr.length;
        int i = 0;
        int longestLength = 1;
        List<Integer> longestConsecutiveSequence = new ArrayList<>();
        while(i < n) {
            int currLength = 1;
            int currElem = arr[i];
            List<Integer> currSequence = new ArrayList<>();
            currSequence.add(currElem);
            int nextElem = currElem + 1;

            for(int indx = 0; indx < n; indx++) {
                if(arr[indx] == nextElem) {
                    currLength++;
                    currSequence.add(arr[indx]);
                    nextElem++;
                    indx = 0;
                }
            }

            if(currLength > longestLength) {
                longestConsecutiveSequence.clear();
                longestConsecutiveSequence.addAll(currSequence);
                longestLength = currLength;
            }
            i++;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence = " + longestConsecutiveSequence);
        System.out.println("Length = " + longestLength);
    }
    /*
        Time - O(N logN)[for sorting the array] + O(N)
        Space - O(1)

        First we sort the array and then find the longest consecutive sequence.
     */
    private void approach2(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        int n = newArr.length;
        Arrays.sort(newArr);
        int currLen = 1;
        int longestLen = 1;
        List<Integer> longestSequence = new ArrayList<>();
        List<Integer> currSequence = new ArrayList<>();
        currSequence.add(newArr[0]);
        for(int indx = 1; indx < n; indx++) {
            if(newArr[indx] == newArr[indx - 1] + 1) {
                currLen++;
                currSequence.add(newArr[indx]);
            } else {
                currLen = 1;
                currSequence.clear();
                currSequence.add(newArr[indx]);
            }
            if(currLen > longestLen) {
                longestLen = currLen;
                longestSequence.clear();
                longestSequence.addAll(currSequence);
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence = " + longestSequence);
        System.out.println("Length = " + longestLen);
    }
    /*
        Time -  O(N)[add all elements in set] +
                O(N)[Iterate over all elements in set] +
                O(N)[For an elem whose () doesn't exist then this is a starting element of a sequence, and we
                need to find the length of this sequence]
        Space - O(N)
     */
    private void approach3(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        for(int elem : arr) {
            set.add(elem);
        }
        int longestLen = 1;
        List<Integer> longestSequence = new ArrayList<>();
        for(int currElem : set) {
            if(set.contains(currElem - 1) == false) {
                int nextElem = currElem + 1;
                int currLen = 1;
                List<Integer> currSequence = new ArrayList<>();
                currSequence.add(currElem);
                while(set.contains(nextElem)) {
                    currLen++;
                    currSequence.add(nextElem);
                    nextElem++;
                }
                if(currLen > longestLen) {
                    longestLen = currLen;
                    longestSequence.clear();
                    longestSequence.addAll(currSequence);
                }
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Longest consecutive sequence = " + longestSequence);
        System.out.println("Length = " + longestLen);
    }
}
