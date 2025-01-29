package arrays;

import java.util.Arrays;

/*
    Find the missing number in an array that contains number
    from 1 to N except the missing number.
 */
public class FindMissingNumber {
    public static void main(String[] args) {
        FindMissingNumber obj = new FindMissingNumber();
        int[] arr = new int[] { 1, 2, 3, 4, 6, 7, 8, 9 };
        int  n = 9;
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
        int missingNum = -1;
        for(int num = 1; num <= n; num++) {
            boolean found = false;
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] == num) {
                    found = true;
                    break;
                }
            }
            if(found == false) {
                missingNum = num;
                break;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing number = " + missingNum);
    }
    /*
        Time - O(N + N)
        Space - O(N)
     */
    private void approach2(int[] arr, int n) {
        int[] hash = new int[n + 1];
        for(int indx = 0; indx < arr.length; indx++) {
            hash[arr[indx]]++;
        }
        int missingNum = -1;
        for(int itr = 1; itr <= n; itr++) {
            if(hash[itr] == 0) {
                missingNum = itr;
            }
        }
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing number = " + missingNum);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private void approach3(int[] arr, int n)
    {
        int sumOfN = ( n * (n + 1) ) / 2;
        int sum = Arrays.stream(arr).sum();
        int missingNum = sumOfN - sum;
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing number = " + missingNum);
    }
    /*
        Time - O(N)
        Space - O(1)
     */
    private void approach4(int[] arr, int n)
    {
        int xor1 = 0, xor2 = 0;
        for(int itr = 0; itr < arr.length; itr++) {
            xor1 ^= arr[itr];
            xor2 ^= itr + 1;
        }
        xor2 ^= n;

        int missingNum = xor1 ^ xor2;
        System.out.println("Arr = " + Arrays.toString(arr));
        System.out.println("Missing number = " + missingNum);
    }
}
