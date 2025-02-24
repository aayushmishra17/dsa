package binary_search;

import java.util.Arrays;

/*
 * You are given 'N’ roses and you are also given an array 'arr' where 'arr[i]' denotes 
 * that the 'ith' rose will bloom on the 'arr[i]th' day. You can only pick already bloomed roses 
 * that are adjacent to make a bouquet. You are also told that you require exactly 'k' adjacent bloomed 
 * roses to make a single bouquet. Find the minimum number of days required to make at least ‘m' 
 * bouquets each containing 'k' roses. Return -1 if it is not possible.
 */
public class MinDaysForMBouquets {
    public static void main(String[] args) {
        MinDaysForMBouquets obj = new MinDaysForMBouquets();
        int[] arr1 = new int[] { 7, 7, 7, 7, 13, 11, 12, 7 };
        // int[] arr2 = new int[] { 1, 10, 3, 10, 2 };
        int numOfRoses = arr1.length;
        int k = 3;
        int m = 2;
        obj.approach1(arr1, k, m);
        obj.approach2(arr1, k, m);
    }
    /*
     * Time - O(max(arr) - min(arr) + 1) * O(N)
     * Space - O(1)
     * 
     * Iterate from the minimum no. of days it takes to bloom to the 
     * maximum no. of days ot takes to bloom.
     * k = no. of adjacent roses to make a bouquet.
     * m = no. of required bouquet.
     */
    private void approach1(int[] arr, int k, int m) {
        int n = arr.length;
        int minimumDaysRequired = findMax(arr);
        /*
         * If no of available roses is less than the required roses to make m bouquets.
         */
        if(n < m * k) {
            System.out.println("It is not possible to make " + m + " bouquets.");
            return;
        }
        for(int noOfDays = findMin(arr); noOfDays <= findMax(arr); noOfDays++) {
            if(isFeasible(arr, noOfDays, k, m)) {
                minimumDaysRequired = noOfDays;
                break;
            }
        }
        System.out.println("Arr= " + Arrays.toString(arr));
        System.out.println("No of adjacent roses required, k = " + k);
        System.out.println("No of required bouquets, m = " + m);
        System.out.println("Minimum days required to have " + m + " bouquets = " + minimumDaysRequired);
    }
    private int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for(int indx = 0; indx < arr.length; indx++) {
            min = Math.min(min, arr[indx]);
        }
        return min;
    }
    private int findMax(int[] arr) {
        int max = Integer.MAX_VALUE;
        for(int indx = 0; indx < arr.length; indx++) {
            max = Math.max(max, arr[indx]);
        }
        return max;
    }
    /*
     * Time - O(N)
     * Space - O(1)
     * 
     * Method returns true if it is possible to have m bouquets 
     * in the given noOfDays, else returns false.
     */
    private boolean isFeasible(int[] arr, int noOfDays, int k, int m) {
        int n = arr.length;
        int count = 0; // Tracks number of adjacent bloomed roses for current noOfDays.
        int noOfBouquet = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] <= noOfDays) {
                count++;
            } else {
                noOfBouquet += count / k;
                count = 0; // reset the counter since current rose requires more days to bloom.
            }
        }
        noOfBouquet += count / k; // Consider the case where last k roses are taken in the bouquet.
        if(noOfBouquet >= m) {
            return true;
        } else {
            return false;
        }
    }
    /*
     * Time - O(log ( max(arr) - min(arr) + 1 )) * O(N)
     */
    private void approach2(int[] arr, int k, int m) {
        int n = arr.length;
        /*
         * If no of available roses is less than the required roses to make m bouquets.
         */
        if(n < m * k) {
            System.out.println("It is not possible to make " + m + " bouquets.");
            return;
        }
        int low = findMin(arr);
        int high = findMax(arr);
        int minimumNoOfDays = findMax(arr);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if(isFeasible(arr, mid, k, m)) {
                minimumNoOfDays = Math.min(minimumNoOfDays, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("Arr= " + Arrays.toString(arr));
        System.out.println("No of adjacent roses required, k = " + k);
        System.out.println("No of required bouquets, m = " + m);
        System.out.println("Minimum days required to have " + m + " bouquets = " + minimumNoOfDays);
    }
}
