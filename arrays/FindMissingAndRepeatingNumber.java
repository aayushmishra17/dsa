package arrays;

import java.util.Arrays;

/*
    You are given a read-only array of N integers with values also in
    the range [1, N] both inclusive. Each integer appears exactly once
    except A which appears twice and B which is missing. The task is to
    find the repeating and missing numbers A and B where A repeats twice and B is missing.
 */
public class FindMissingAndRepeatingNumber {
    public static void main(String[] args) {
        FindMissingAndRepeatingNumber obj = new FindMissingAndRepeatingNumber();
        int[] arr = new int[] { 3, 1, 2, 5, 3 };
        int n = 5;
        obj.approach1(arr, n);
        obj.approach2(arr, n);
        obj.approach3(arr, n);
        obj.approach4(arr, n);
    }
    /*
        Time - O(N * N)
        Space - O(1)
     */
    private void approach1(int[] arr, int n) {
        int missingNo = -1;
        int repeatingNo = -1;
        for(int i = 1; i <= n; i++) {
            int count = 0;
            for(int j = 0; j < arr.length; j++) {
                if(arr[j] == i) {
                    count++;
                }
            }
            if(count > 1) {
                repeatingNo = i;
            }
            if(count == 0) {
                missingNo = i;
            }
            if(missingNo != -1 && repeatingNo != -1) {
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing no = " + missingNo);
        System.out.println("Repeating no = " + repeatingNo);
    }
    /*
        Time - O(N) + O(N)
        Space - O(N)
     */
    private void approach2(int[] arr, int n) {
        int[] hash = new int[n + 1];
        for(int i = 0; i < arr.length; i++) {
            hash[arr[i]]++;
        }
        int missingNo = -1;
        int repeatingNo = -1;
        for(int i = 1; i < n + 1; i++) {
            if(hash[i] == 0) {
                missingNo = i;
            }
            if(hash[i] > 1) {
                repeatingNo = i;
            }
            if(missingNo != -1 && repeatingNo != -1) {
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing no = " + missingNo);
        System.out.println("Repeating no = " + repeatingNo);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private void approach3(int[] arr, int n) {
        int arrSize = arr.length;
        long sumOfNNos = (arrSize * (arrSize + 1)) / 2;
        long sumOfSquaresOfNNos = (arrSize * (arrSize + 1) * (2 * arrSize + 1)) / 6;
        long sum = 0;
        long sumOfSquares = 0;
        for(int i = 0; i < arrSize; i++) {
            sum += arr[i];
            sumOfSquares += arr[i] * arr[i];
        }
        long val1 = sum - sumOfNNos; // x - y
        // x + y
        long val2 = sumOfSquares - sumOfSquaresOfNNos;
        val2 = val2 / val1;
        long x = (val1 + val2) / 2;
        long y = x - val1;
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing no = " + y);
        System.out.println("Repeating no = " + x);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private void approach4(int[] arr, int n) {
        /*
            Find the (xor of given arr number) ^ (number from [1,n]).
         */
        int xr = 0;
        for(int i = 0; i < arr.length; i++) {
            xr = xr ^ arr[i];
            xr = xr ^ (i + 1);
        }
        /*
            Find 1st set bit from the right.
         */
        int bitNo = 0;
        while((xr & (1 << bitNo)) == 0) {
            bitNo++;
        }
        /*
            Group array elements into two buckets, one bucket represents
            elements that has 0 at bitNo and other bucket elements have 1 at bitNo.
         */
        int zeroBucket = 0;
        int oneBucket = 0;
        for(int i = 0; i < arr.length; i++) {
            // part of one bucket
            if((arr[i] & (1 << bitNo)) != 0) {
                oneBucket ^= arr[i];
            } else { // part of zero bucket
                zeroBucket ^= arr[i];
            }
            // part of one bucket
            if(((i + 1) & (1 << bitNo)) != 0) {
                oneBucket ^= i + 1;
            } else { // part of zero bucket
                zeroBucket ^= i + 1;
            }
        }
        /*
            Iterate through the array elements and find out which one
            is the missing no and which one is the repeating no.
         */
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == oneBucket) {
                count++;
            }
        }
        int missingNo = -1;
        int repeatingNo = -1;
        if(count > 1) {
            repeatingNo = oneBucket;
            missingNo = zeroBucket;
        } else {
            missingNo = oneBucket;
            repeatingNo = zeroBucket;
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing no = " + missingNo);
        System.out.println("Repeating no = " + repeatingNo);
    }
}
